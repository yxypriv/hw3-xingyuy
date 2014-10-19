package edu.cmu.lti.f14.hw3.models;

import java.util.Comparator;

public class ConfigurationMMR {
	Double mmr;
	String tokenizeMethod;
	Boolean allLowerCase;
	Boolean filteredStopword;
	String stemMethod;
	String similarityMethod;

	public ConfigurationMMR(String tokenizeMethod, Boolean allLowerCase, Boolean filteredStopword, String stemMethod) {
		super();
		this.tokenizeMethod = tokenizeMethod;
		this.allLowerCase = allLowerCase;
		this.filteredStopword = filteredStopword;
		this.stemMethod = stemMethod;
	}

	public ConfigurationMMR(CorpusDocument cd) {
		this.tokenizeMethod = cd.getTokenizeMethod();
		this.allLowerCase = cd.getAllLowerCase();
		this.filteredStopword = cd.getFilteredStopword();
		this.stemMethod = cd.getStemMethod();
	}

	public ConfigurationMMR(ConfigurationMMR mmr) {
		this.tokenizeMethod = mmr.getTokenizeMethod();
		this.allLowerCase = mmr.getAllLowerCase();
		this.filteredStopword = mmr.getFilteredStopword();
		this.stemMethod = mmr.getStemMethod();
	}
	
	public Double getMmr() {
		return mmr;
	}

	public void setMmr(Double mmr) {
		this.mmr = mmr;
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

	public static Comparator<ConfigurationMMR> mmrComparator = new Comparator<ConfigurationMMR>() {
		@Override
		public int compare(ConfigurationMMR o1, ConfigurationMMR o2) {
			return o1.mmr.compareTo(o2.mmr);
		}
	};

	public String getSimilarityMethod() {
		return similarityMethod;
	}

	public void setSimilarityMethod(String similarityMethod) {
		this.similarityMethod = similarityMethod;
	}
	
	
}
