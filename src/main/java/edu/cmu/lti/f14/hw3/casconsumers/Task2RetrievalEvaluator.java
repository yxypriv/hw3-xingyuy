package edu.cmu.lti.f14.hw3.casconsumers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

import edu.cmu.lti.f14.hw3.models.ConfigurationMMR;
import edu.cmu.lti.f14.hw3.models.ScoredDocument;
import edu.cmu.lti.f14.hw3.typesystems.Token;
import edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document;
import edu.cmu.lti.f14.hw3.typesystems.task2.ConfigVector;
import edu.cmu.lti.f14.hw3.utils.Utils;
import edu.cmu.lti.f14.hw3.utils.task2.similarity.SimilarityCompute;

public class Task2RetrievalEvaluator extends CasConsumer_ImplBase {

	public static final String[] methods = { "NaiveCos", "TFIDFCos", "NaiveDice", "NaiveJaccard", "NaiveBM25" };

	public static final String PARAM_OUTPUT_FILE = "OutputFile";
	PrintWriter writer = null;

	List<List<ScoredDocument>> configurationGroupedDocList = new ArrayList<List<ScoredDocument>>();

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

		FSIterator<Annotation> it = jcas.getAnnotationIndex(Task2Document.type).iterator();
		List<List<ScoredDocument>> docGroupedDocList = new ArrayList<List<ScoredDocument>>();
		Comparator<ScoredDocument> configurationOrderComparator = new Comparator<ScoredDocument>() {
			@Override
			public int compare(ScoredDocument o1, ScoredDocument o2) {
				int result = o1.getTokenizeMethod().compareTo(o2.getTokenizeMethod());
				if (0 == result)
					result = o1.getAllLowerCase().compareTo(o2.getAllLowerCase());
				if (0 == result)
					result = o1.getFilteredStopword().compareTo(o2.getFilteredStopword());
				if (0 == result)
					result = o1.getStemMethod().compareTo(o2.getStemMethod());
				return result;
			}
		};
		if (it.hasNext()) {
			Task2Document t2doc = (Task2Document) it.next();
			FSList vectorList = t2doc.getVectorList();
			ArrayList<ConfigVector> configVectorList = Utils.fromFSListToCollection(vectorList, ConfigVector.class);
			List<ScoredDocument> sameDocList = new ArrayList<ScoredDocument>();

			for (ConfigVector doc : configVectorList) {
				FSList fsTokenList = doc.getTokenList();
				ArrayList<Token> tokenList = Utils.fromFSListToCollection(fsTokenList, Token.class);
				Map<String, Integer> vector = new HashMap<String, Integer>();
				for (Token token : tokenList) {
					vector.put(token.getText(), token.getFrequency());
				}
				ScoredDocument cdoc = new ScoredDocument();
				cdoc.setQid(t2doc.getQid());
				cdoc.setRel(t2doc.getRel());
				cdoc.setTfVector(vector);
				cdoc.setSentence(t2doc.getText());
				cdoc.setTokenizeMethod(doc.getTokenizeMethod());
				cdoc.setAllLowerCase(doc.getAllLowerCase());
				cdoc.setFilteredStopword(doc.getFilteredStopword());
				cdoc.setStemMethod(doc.getStemMethod());
				sameDocList.add(cdoc);
			}
			Collections.sort(sameDocList, configurationOrderComparator);

			docGroupedDocList.add(sameDocList);
		}

		int configSize = -1;
		if (docGroupedDocList.size() > 0) {
			configSize = docGroupedDocList.get(0).size();
		}
		if (configurationGroupedDocList.size() == 0) {
			for (int i = 0; i < configSize; i++) {
				configurationGroupedDocList.add(new ArrayList<ScoredDocument>());
			}
		}
		for (List<ScoredDocument> sameDocList : docGroupedDocList) {
			for (int i = 0; i < sameDocList.size(); i++) {
				configurationGroupedDocList.get(i).add(sameDocList.get(i));
			}
		}
	}

	/**
	 * TODO 1. Compute Cosine Similarity and rank the retrieved sentences 2.
	 * Compute the MRR metric
	 */
	@Override
	public void collectionProcessComplete(ProcessTrace arg0) throws ResourceProcessException, IOException {

		super.collectionProcessComplete(arg0);
		List<ConfigurationMMR> mmrList = new ArrayList<ConfigurationMMR>();
		for (List<ScoredDocument> docList : configurationGroupedDocList) {
			Integer totalFileLength = 0;
			Map<String, Integer> dfMap = new HashMap<String, Integer>();
			ConfigurationMMR cmmr = null;
			// System.out.println(docList.size());
			for (ScoredDocument cd : docList) {
				if (null == cmmr) {
					cmmr = new ConfigurationMMR(cd);
					String outputString = String.format("[Configuration] tokenize: %s\tlowercase: %s\tstopwords: %s\tstemming: %s",//
							cmmr.getTokenizeMethod(), cmmr.getAllLowerCase(), cmmr.getFilteredStopword(), cmmr.getStemMethod());
					System.out.println(outputString);
					writer.println(outputString);
				}
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

			for (ScoredDocument cd : docList) {
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

			HashMap<Integer, ScoredDocument> queryMap = new HashMap<Integer, ScoredDocument>();
			HashMap<Integer, List<ScoredDocument>> documentsMap = new HashMap<Integer, List<ScoredDocument>>();

			for (ScoredDocument cp : docList) {
				int qid = cp.getQid();
				int rel = cp.getRel();
				if (rel == 99) {
					queryMap.put(qid, cp);
				} else {
					if (!documentsMap.containsKey(qid))
						documentsMap.put(qid, new ArrayList<ScoredDocument>());
					documentsMap.get(qid).add(cp);
				}
			}

			double[] totalMMr = new double[methods.length];
			for (Integer qid : queryMap.keySet()) {
				List<ScoredDocument> docField = documentsMap.get(qid);
				ScoredDocument queryDoc = queryMap.get(qid);

				for (ScoredDocument doc : docField) {
					double naiveScore = SimilarityCompute.computeCosineSimilarity(queryDoc.getTfVector(), doc.getTfVector());
					double tfidfScore = SimilarityCompute.computeCosineSimilarity(queryDoc.getTfIdfVector(), doc.getTfIdfVector());
					double naiveDice = SimilarityCompute.computeDiceSimilarity(queryDoc.getTfVector(), doc.getTfVector());
					double naiveJaccard = SimilarityCompute.computeJaccardSimilarity(queryDoc.getTfVector(), doc.getTfVector());
					double naiveBm25 = SimilarityCompute.computeBM25Similarity(queryDoc.getTfVector(), doc.getTfVector(), aveageFileLength, idfMap);
					doc.getScores().add(naiveScore);
					doc.getScores().add(tfidfScore);
					doc.getScores().add(naiveDice);
					doc.getScores().add(naiveJaccard);
					doc.getScores().add(naiveBm25);
				}

				for (int i = 0; i < methods.length; i++) {
					ScoredDocument.ScoreComparator indexScoreComparator = new ScoredDocument.ScoreComparator(i);
					Collections.sort(docField, Collections.reverseOrder(indexScoreComparator));
					int rank = -1;
					ScoredDocument relativeDoc = null;
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
					// System.out.println(outputString);
					writer.println(outputString);
				}

			}
			for (int i = 0; i < methods.length; i++) {
				double metric_mrr = totalMMr[i] / documentsMap.size();
				String outputString = String.format("Method:%s\tMMR=%f", methods[i], metric_mrr);
				System.out.println(outputString);
				writer.println(outputString);
				ConfigurationMMR ncmmr = new ConfigurationMMR(cmmr);
				ncmmr.setMmr(metric_mrr);
				ncmmr.setSimilarityMethod(methods[i]);
				mmrList.add(ncmmr);
			}
		}
		
		
		int wordCount = 0;
		for (ConfigurationMMR mmr : mmrList) {
			System.out.print(mmr.getMmr() + "\t");
			if(++wordCount % methods.length == 0) 
				System.out.println();
		}
		
		Collections.sort(mmrList, Collections.reverseOrder(ConfigurationMMR.mmrComparator));
		int topdisplay = 5;
		for (ConfigurationMMR mmr : mmrList) {
			String outputString = String.format("MMr: %f\t [Similarity Method] %s\t [Configuration] tokenize: %s\tlowercase: %s\tstopwords: %s\tstemming: %s",//
					mmr.getMmr(), mmr.getSimilarityMethod(), mmr.getTokenizeMethod(), mmr.getAllLowerCase(), mmr.getFilteredStopword(), mmr.getStemMethod());
			if(--topdisplay >= 0)
				System.out.println(outputString);
			writer.println(outputString);
		}
	}

	@Override
	public void destroy() {
		writer.close();
		super.destroy();
	}

	

	

}
