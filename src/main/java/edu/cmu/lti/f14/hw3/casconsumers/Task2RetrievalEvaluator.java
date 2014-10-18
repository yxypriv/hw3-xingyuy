package edu.cmu.lti.f14.hw3.casconsumers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.apache.uima.util.ProcessTrace;

import edu.cmu.lti.f14.hw3.models.CorpusDocument;
import edu.cmu.lti.f14.hw3.typesystems.Document;
import edu.cmu.lti.f14.hw3.typesystems.Token;
import edu.cmu.lti.f14.hw3.utils.Utils;

public class Task2RetrievalEvaluator extends CasConsumer_ImplBase {

	public static final String[] methods = { "NaiveCos", "TFIDFCos", "NaiveDice", "NaiveJaccard", "NaiveBM25" };

	public static final String PARAM_OUTPUT_FILE = "OutputFile";
	PrintWriter writer = null;

	List<CorpusDocument> docList = new ArrayList<CorpusDocument>();

	@Override
	public void initialize() throws ResourceInitializationException {

		String outputFile = ((String) getConfigParameterValue(PARAM_OUTPUT_FILE)).trim();
		URL resource = Task2RetrievalEvaluator.class.getResource(outputFile);
		if (null != resource) {
			File f = new File(resource.getFile());
			try {
				writer = new PrintWriter(f);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				writer = new PrintWriter(outputFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * TODO :: 1. construct the global word dictionary 2. keep the word
	 * frequency for each sentence
	 */
	@Override
	public void processCas(CAS aCas) throws ResourceProcessException {

		JCas jcas;
		try {
			jcas = aCas.getJCas();
		} catch (CASException e) {
			throw new ResourceProcessException(e);
		}

		FSIterator<Annotation> it = jcas.getAnnotationIndex(Document.type).iterator();

		if (it.hasNext()) {
			Document doc = (Document) it.next();

			FSList fsTokenList = doc.getTokenList();
			ArrayList<Token> tokenList = Utils.fromFSListToCollection(fsTokenList, Token.class);
			Map<String, Integer> vector = new HashMap<String, Integer>();
			for (Token token : tokenList) {
				vector.put(token.getText(), token.getFrequency());
			}
			CorpusDocument cdoc = new CorpusDocument();
			cdoc.setQid(doc.getQueryID());
			cdoc.setRel(doc.getRelevanceValue());
			cdoc.setTfVector(vector);
			cdoc.setSentence(doc.getText());
			docList.add(cdoc);
		}

	}

	/**
	 * TODO 1. Compute Cosine Similarity and rank the retrieved sentences 2.
	 * Compute the MRR metric
	 */
	@Override
	public void collectionProcessComplete(ProcessTrace arg0) throws ResourceProcessException, IOException {

		super.collectionProcessComplete(arg0);
		Integer totalFileLength = 0;
		Map<String, Integer> dfMap = new HashMap<String, Integer>();
		for (CorpusDocument cd : docList) {
			Map<String, Integer> vector = cd.getTfVector();
			for (String key : vector.keySet()) {
				if (!dfMap.containsKey(key))
					dfMap.put(key, 1);
				else
					dfMap.put(key, dfMap.get(key) + 1);
				totalFileLength += vector.get(key);
			}
		}
		double aveageFileLength = totalFileLength.doubleValue() / dfMap.size();

		Map<String, Double> idfMap = new HashMap<String, Double>();
		for (String key : dfMap.keySet()) {
			idfMap.put(key, Math.log(docList.size() / dfMap.get(key)));
		}

		for (CorpusDocument cd : docList) {
			Map<String, Integer> vector = cd.getTfVector();
			Map<String, Double> tfIdfVector = new HashMap<String, Double>();
			int wordCount = 0;
			for (String key : vector.keySet()) {
				wordCount += vector.get(key);
			}
			for (String key : vector.keySet()) {
				tfIdfVector.put(key, idfMap.get(key) * (0.5 + 0.5 * (vector.get(key) / wordCount)));
			}
			cd.setTfIdfVector(tfIdfVector);
		}

		HashMap<Integer, CorpusDocument> queryMap = new HashMap<Integer, CorpusDocument>();
		HashMap<Integer, List<CorpusDocument>> documentsMap = new HashMap<Integer, List<CorpusDocument>>();

		for (CorpusDocument cp : docList) {
			int qid = cp.getQid();
			int rel = cp.getRel();
			if (rel == 99) {
				queryMap.put(qid, cp);
			} else {
				if (!documentsMap.containsKey(qid))
					documentsMap.put(qid, new ArrayList<CorpusDocument>());
				documentsMap.get(qid).add(cp);
			}
		}

		double[] totalMMr = new double[methods.length];
		for (Integer qid : queryMap.keySet()) {
			List<CorpusDocument> docField = documentsMap.get(qid);
			CorpusDocument queryDoc = queryMap.get(qid);

			for (CorpusDocument doc : docField) {
				double naiveScore = computeCosineSimilarity(queryDoc.getTfVector(), doc.getTfVector());
				double tfidfScore = computeCosineSimilarity(queryDoc.getTfIdfVector(), doc.getTfIdfVector());
				double naiveDice = computeDiceSimilarity(queryDoc.getTfVector(), doc.getTfVector());
				double naiveJaccard = computeJaccardSimilarity(queryDoc.getTfVector(), doc.getTfVector());
				double naiveBm25 = computeBM25Similarity(queryDoc.getTfVector(), doc.getTfVector(), aveageFileLength, idfMap);
				doc.getScores().add(naiveScore);
				doc.getScores().add(tfidfScore);
				doc.getScores().add(naiveDice);
				doc.getScores().add(naiveJaccard);
				doc.getScores().add(naiveBm25);
			}

			for (int i = 0; i < methods.length; i++) {
				CorpusDocument.ScoreComparator indexScoreComparator = new CorpusDocument.ScoreComparator(i);
				Collections.sort(docField, Collections.reverseOrder(indexScoreComparator));
				int rank = -1;
				CorpusDocument relativeDoc = null;
				for (int j = 0; j < docField.size(); j++) {
					if (docField.get(j).getRel() == 1) {
						rank = j + 1;
						relativeDoc = docField.get(j);
						break;
					}
				}

				if (rank != -1)
					totalMMr[i] += 1.0 / rank;
				String outputString = String.format("method:%s\tcosine=%f\trank=%d\tqid=%d\trel=%d\t%s",//
						methods[i], relativeDoc.getScores().get(i), rank, qid, 1, relativeDoc.getSentence());
				System.out.println(outputString);
				writer.println(outputString);
			}

		}
		for (int i = 0; i < methods.length; i++) {
			double metric_mrr = totalMMr[i] / documentsMap.size();
			String outputString = String.format("Method:%s\tMMR=%f", methods[i], metric_mrr);
			System.out.println(outputString);
			writer.println(outputString);
		}

	}

	@Override
	public void destroy() {
		writer.close();
		super.destroy();
	}

	private <T> double computeCosineSimilarity(Map<String, T> queryVector, Map<String, T> docVector) {
		double cosine_similarity = 0.0;

		double vectorSum = 0.0;
		Set<String> dimentionSet = new HashSet<String>();

		dimentionSet.addAll(queryVector.keySet());
		dimentionSet.addAll(docVector.keySet());
		for (String key : dimentionSet) {
			Double qf = 0.0;
			Double df = 0.0;
			if (queryVector.containsKey(key)) {
				qf = _transferNumber2Double(queryVector.get(key));
			}
			if (docVector.containsKey(key)) {
				df = _transferNumber2Double(docVector.get(key));
			}
			vectorSum += qf * df;
		}

		cosine_similarity = vectorSum / (getVictorLength(docVector) * getVictorLength(queryVector));

		return cosine_similarity;
	}

	private <T> double getVictorLength(Map<String, T> docVector) {
		double squareSum = 0;
		for (String key : docVector.keySet()) {
			T number = docVector.get(key);
			Double root = _transferNumber2Double(number);

			squareSum += Math.pow(root, 2);
		}
		return Math.sqrt(squareSum);
	}

	private <T> double computeDiceSimilarity(Map<String, T> queryVector, Map<String, T> docVector) {
		double diceSum = 0.0;
		double intersectionSum = 0.0;
		Set<String> dimentionSet = new HashSet<String>();

		dimentionSet.addAll(queryVector.keySet());
		dimentionSet.addAll(docVector.keySet());
		for (String key : dimentionSet) {
			Double qf = 0.0;
			Double df = 0.0;
			if (queryVector.containsKey(key)) {
				qf = _transferNumber2Double(queryVector.get(key));
			}
			if (docVector.containsKey(key)) {
				df = _transferNumber2Double(docVector.get(key));
			}
			diceSum += (qf + df);
			intersectionSum += Math.min(qf, df);
		}
		return intersectionSum * 2 / diceSum;
	}

	private <T> double computeJaccardSimilarity(Map<String, T> queryVector, Map<String, T> docVector) {
		double unionSum = 0.0;
		double intersectionSum = 0.0;
		Set<String> dimentionSet = new HashSet<String>();

		dimentionSet.addAll(queryVector.keySet());
		dimentionSet.addAll(docVector.keySet());
		for (String key : dimentionSet) {
			Double qf = 0.0;
			Double df = 0.0;
			if (queryVector.containsKey(key)) {
				qf = _transferNumber2Double(queryVector.get(key));
			}
			if (docVector.containsKey(key)) {
				df = _transferNumber2Double(docVector.get(key));
			}
			unionSum += Math.max(qf, df);
			intersectionSum += Math.min(qf, df);
		}
		return intersectionSum * 2 / unionSum;
	}

	private <T> double computeBM25Similarity(Map<String, Integer> queryVector, Map<String, Integer> docVector,//
			double aveageFileLength, Map<String, Double> idfMap) {
		double result = 0.0;
		double k1 = 1.2;
		double b = 0.75;
		int docsize = 0;
		for (String key : docVector.keySet()) {
			docsize += docVector.get(key);
		}
		for (String key : queryVector.keySet()) {
			int tf = 0;
			if (docVector.containsKey(key))
				tf = docVector.get(key);

			result += idfMap.get(key) * tf * (k1 + 1) / (tf + k1 * (1 - b + b * docsize / aveageFileLength));
		}
		return result;
	}

	private <T> double _transferNumber2Double(T number) {
		double result = 0.0;
		if (Double.class.isInstance(number))
			result = (Double) number;
		else if (Integer.class.isInstance(number))
			result = new Integer((Integer) number).doubleValue();
		else {
			System.err.println(number.getClass() + "\t not processed as number converting.");
		}
		return result;
	}

}
