package edu.cmu.lti.f14.hw3.utils.task2.configDivider;

import java.util.ArrayList;
import java.util.List;

import org.tartarus.snowball.ext.EnglishStemmer;

import edu.cmu.lti.f14.hw3.utils.Pair;
import edu.cmu.lti.f14.hw3.utils.StanfordLemmatizer;

public class StemmerDivide {
	static EnglishStemmer stemmer = new EnglishStemmer();

	public static List<Pair<List<String>, String>> getAllDivid(List<Pair<List<String>, String>> data) {
		List<Pair<List<String>, String>> result = new ArrayList<Pair<List<String>, String>>();
		for (Pair<List<String>, String> singleData : data) {
			List<String> originWordList = singleData.getV1();
			result.add(new Pair<List<String>, String>(snowballStemming(originWordList), singleData.getV2() + "#snowball"));
			result.add(new Pair<List<String>, String>(stanfordStemming(originWordList), singleData.getV2() + "#stanford"));

			singleData.setV2(singleData.getV2() + "#false");
			result.add(singleData);
		}
		return result;
	}

	public static List<String> snowballStemming(List<String> wordList) {
		List<String> result = new ArrayList<String>();
		for (String word : wordList) {
			stemmer.setCurrent(word);
			if (stemmer.stem())
				result.add(stemmer.getCurrent());
			else
				result.add(word);
		}
		return result;
	}

	public static List<String> stanfordStemming(List<String> wordList) {
		List<String> result = new ArrayList<String>();
		for (String word : wordList) {
			result.add(StanfordLemmatizer.stemWord(word));
		}
		return result;
	}
}
