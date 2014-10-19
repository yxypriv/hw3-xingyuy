package edu.cmu.lti.f14.hw3.utils.task2.similarity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimilarityCompute {
	public static <T> double computeCosineSimilarity(Map<String, T> queryVector, Map<String, T> docVector) {
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

	public static <T> double getVictorLength(Map<String, T> docVector) {
		double squareSum = 0;
		for (String key : docVector.keySet()) {
			T number = docVector.get(key);
			Double root = _transferNumber2Double(number);

			squareSum += Math.pow(root, 2);
		}
		return Math.sqrt(squareSum);
	}

	public static <T> double computeDiceSimilarity(Map<String, T> queryVector, Map<String, T> docVector) {
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

	public static <T> double computeJaccardSimilarity(Map<String, T> queryVector, Map<String, T> docVector) {
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

	public static <T> double computeBM25Similarity(Map<String, Integer> queryVector, Map<String, Integer> docVector,//
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

	private static <T> double _transferNumber2Double(T number) {
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
