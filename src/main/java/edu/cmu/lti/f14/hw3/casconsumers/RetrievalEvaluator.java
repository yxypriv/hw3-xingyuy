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

import edu.cmu.lti.f14.hw3.typesystems.Document;
import edu.cmu.lti.f14.hw3.typesystems.Token;
import edu.cmu.lti.f14.hw3.utils.Triple;
import edu.cmu.lti.f14.hw3.utils.Utils;

public class RetrievalEvaluator extends CasConsumer_ImplBase {

	/** query id number **/
	public ArrayList<Integer> qIdList;

	/** query and text relevant values **/
	public ArrayList<Integer> relList;

	public ArrayList<Map<String, Integer>> vectorList;

	public ArrayList<String> sentenceList;

	public static final String PARAM_OUTPUT_FILE = "OutputFile";
	PrintWriter writer = null;

	@Override
	public void initialize() throws ResourceInitializationException {

		qIdList = new ArrayList<Integer>();
		relList = new ArrayList<Integer>();
		// testdata = new HashMap<Integer, List<Map<String,Integer>>>();
		vectorList = new ArrayList<Map<String, Integer>>();
		sentenceList = new ArrayList<String>();

		String outputFile = ((String) getConfigParameterValue(PARAM_OUTPUT_FILE)).trim();
		URL resource = RetrievalEvaluator.class.getResource(outputFile);
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

			// Make sure that your previous annotators have populated this in
			// CAS
			FSList fsTokenList = doc.getTokenList();
			ArrayList<Token> tokenList = Utils.fromFSListToCollection(fsTokenList, Token.class);
			Map<String, Integer> vector = new HashMap<String, Integer>();
			for (Token token : tokenList) {
				vector.put(token.getText(), token.getFrequency());
			}
			vectorList.add(vector);
			qIdList.add(doc.getQueryID());
			relList.add(doc.getRelevanceValue());
			sentenceList.add(doc.getText());

		}

	}

	/**
	 * TODO 1. Compute Cosine Similarity and rank the retrieved sentences 2.
	 * Compute the MRR metric
	 */
	@Override
	public void collectionProcessComplete(ProcessTrace arg0) throws ResourceProcessException, IOException {

		super.collectionProcessComplete(arg0);
		HashMap<Integer, Map<String, Integer>> queryMap = new HashMap<Integer, Map<String, Integer>>();
		HashMap<Integer, List<Triple<Map<String, Integer>, String, Integer>>> documentsMap = new HashMap<Integer, List<Triple<Map<String, Integer>, String, Integer>>>();

		for (int i = 0; i < qIdList.size(); i++) {
			int qid = qIdList.get(i);
			int rel = relList.get(i);
			Map<String, Integer> vector = vectorList.get(i);
			if (rel == 99) {
				queryMap.put(qid, vector);
			} else {
				Triple<Map<String, Integer>, String, Integer> relVector = new Triple<Map<String, Integer>, String, Integer>(vector, sentenceList.get(i), rel);
				if (!documentsMap.containsKey(qid))
					documentsMap.put(qid, new ArrayList<Triple<Map<String, Integer>, String, Integer>>());
				documentsMap.get(qid).add(relVector);
			}
		}

		double totalMMr = 0.0;
		for (Integer qid : queryMap.keySet()) {
			List<Triple<Map<String, Integer>, String, Integer>> docField = documentsMap.get(qid);
			List<Triple<String, Integer, Double>> scoredDocField = new ArrayList<Triple<String, Integer, Double>>();
			Map<String, Integer> queryVector = queryMap.get(qid);

			for (Triple<Map<String, Integer>, String, Integer> doc : docField) {
				double score = computeCosineSimilarity(queryVector, doc.getV1());
				Triple<String, Integer, Double> scoredDoc = new Triple<String, Integer, Double>(doc.getV2(), doc.getV3(), score);
				scoredDocField.add(scoredDoc);
			}

			Collections.sort(scoredDocField, Collections.reverseOrder(//
					new Comparator<Triple<String, Integer, Double>>() {
						@Override
						public int compare(Triple<String, Integer, Double> o1, Triple<String, Integer, Double> o2) {
							return o1.getV3().compareTo(o2.getV3());
						}
					}));

			int rank = -1;
			Triple<String, Integer, Double> relativeDoc = null;
			for (int i = 0; i < scoredDocField.size(); i++) {
				if (scoredDocField.get(i).getV2() == 1) {
					rank = i + 1;
					relativeDoc = scoredDocField.get(i);
					break;
				}
				System.out.println(String.format("~~ cosine=%f\trank=%d\tqid=%d\trel=%d\t%s", //
						scoredDocField.get(i).getV3(), i+1, qid, scoredDocField.get(i).getV2(),  //
						scoredDocField.get(i).getV1()));
			}
			if (rank != -1)
				totalMMr += 1.0 / rank;
			String outputString = String.format("cosine=%f\trank=%d\tqid=%d\trel=%d\t%s",//
					relativeDoc.getV3(), rank, qid, 1, relativeDoc.getV1());
			System.out.println(outputString);
			writer.println(outputString);
		}

		double metric_mrr = totalMMr / documentsMap.size();
		System.out.println(" (MRR) Mean Reciprocal Rank ::" + metric_mrr);
		writer.println("MRR=" + metric_mrr);
	}

	@Override
	public void destroy() {
		writer.close();
		super.destroy();
	}
	
	/**
	 * 
	 * @return cosine_similarity
	 */
	private double computeCosineSimilarity(Map<String, Integer> queryVector, Map<String, Integer> docVector) {
		double cosine_similarity = 0.0;

		double vectorSum = 0;
		Set<String> dimentionSet = new HashSet<String>();

		dimentionSet.addAll(queryVector.keySet());
		dimentionSet.addAll(docVector.keySet());
		double queryLength = getVictorEuclidianLength(queryVector);
		double docLength = getVictorEuclidianLength(docVector);
		for (String key : dimentionSet) {
			double qf = 0;
			double df = 0;
			if (queryVector.containsKey(key)) {
				qf = queryVector.get(key);
			}
			if (docVector.containsKey(key)) {
				df = docVector.get(key);
			}
			vectorSum += qf * df;
		}

		cosine_similarity = vectorSum / (queryLength * docLength);
//		cosine_similarity = vectorSum;
		return cosine_similarity;
	}

	
	
	private double getVictorEuclidianLength(Map<String, Integer> vector) {
		double squareSum = 0;
		for (String key : vector.keySet()) {
			squareSum += Math.pow(vector.get(key), 2);
		}
		return Math.sqrt(squareSum);
	}
	
	private double getVectorLinearLength(Map<String, Integer> vector) {
		double linearSum = 0.0;
		for(String key : vector.keySet()) {
			linearSum += vector.get(key);
		}
		return linearSum;
	}
}
