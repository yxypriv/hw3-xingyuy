package edu.cmu.lti.f14.hw3.models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ScoredDocument {
	Map<String, Integer> tfVector;
	Map<String, Double> tfIdfVector;
	String sentence;
	int qid;
	int rel;
	List<Double> scores;
	String tokenizeMethod;
	Boolean allLowerCase;
	Boolean filteredStopword;
	String stemMethod;

	public ScoredDocument() {
		scores = new ArrayList<Double>();
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public int getRel() {
		return rel;
	}

	public void setRel(int rel) {
		this.rel = rel;
	}

	public Map<String, Integer> getTfVector() {
		return tfVector;
	}

	public void setTfVector(Map<String, Integer> tfVector) {
		this.tfVector = tfVector;
	}

	public Map<String, Double> getTfIdfVector() {
		return tfIdfVector;
	}

	public void setTfIdfVector(Map<String, Double> tfIdfVector) {
		this.tfIdfVector = tfIdfVector;
	}

	public List<Double> getScores() {
		return scores;
	}
	
	public String getTokenizeMethod() {
		return tokenizeMethod;
	}

	public void setTokenizeMethod(String tokenizeMethod) {
		this.tokenizeMethod = tokenizeMethod;
	}

	public Boolean getAllLowerCase() {
		return allLowerCase;
	}

	public void setAllLowerCase(Boolean allLowerCase) {
		this.allLowerCase = allLowerCase;
	}

	public Boolean getFilteredStopword() {
		return filteredStopword;
	}

	public void setFilteredStopword(Boolean filteredStopword) {
		this.filteredStopword = filteredStopword;
	}

	public String getStemMethod() {
		return stemMethod;
	}

	public void setStemMethod(String stemMethod) {
		this.stemMethod = stemMethod;
	}


	public static class ScoreComparator implements Comparator<ScoredDocument> {
		int usingScoreIndex;
		public ScoreComparator(int usingScoreIndex) {
			super();
			this.usingScoreIndex = usingScoreIndex;
		}
		@Override
		public int compare(ScoredDocument o1, ScoredDocument o2) {
			return o1.getScores().get(usingScoreIndex).compareTo(o2.getScores().get(usingScoreIndex));
		}
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("{");
		result.append("qid: ").append(qid).append(", ");
		result.append("rel: ").append(rel).append(", ");
		result.append("score: ").append(scores).append(", ");
		result.append("vectors: ").append("{");
		for (String key : tfVector.keySet()) {
			result.append(key).append(": ").append("[");
			result.append(tfVector.get(key)).append(", ");
			result.append(tfIdfVector.get(key)).append("], ");
		}
		result.delete(result.length() - 2, result.length());
		result.append("}, ");
		result.append("senence: ").append("\"").append(sentence.replaceAll("\"", "\\\"")).append("\"");
		result.append("}");
		return result.toString();
	}
}
