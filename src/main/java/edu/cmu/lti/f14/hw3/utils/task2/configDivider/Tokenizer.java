package edu.cmu.lti.f14.hw3.utils.task2.configDivider;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.lti.f14.hw3.utils.Pair;

public class Tokenizer {
	public static final String filterSymbol = ",;\".?':";

	public static List<Pair<List<String>, String>> getAllTokenizedResult(String line) {
		List<Pair<List<String>, String>> result = new ArrayList<Pair<List<String>,String>>();
		result.add(new Pair<List<String>, String>(naiveTokenize(line), "naive"));
		result.add(new Pair<List<String>, String>(nonSymbolTokenize(line), "symbolFilter"));
		result.add(new Pair<List<String>, String>(smallFragmentFilter(line), "symbol&SingleCharFilter"));
		return result;
	}
	
	public static List<String> naiveTokenize(String line) {
		List<String> result = new ArrayList<String>();
		for (String word : line.split("\\s+")) {
			if (!word.trim().equals(""))
				result.add(word);
		}
		return result;
	}

	public static List<String> nonSymbolTokenize(String line) {
		line = line.replaceAll("[" + filterSymbol + "]+", " ");
		List<String> result = new ArrayList<String>();
		for (String word : line.split("\\s+")) {
			if (!word.trim().equals(""))
				result.add(word);
		}
		return result;
	}

	public static List<String> smallFragmentFilter(String line) {
		line = line.replaceAll("[" + filterSymbol + "]+", " ");
		List<String> result = new ArrayList<String>();
		for (String word : line.split("\\s+")) {
			word = word.trim();
			if (word.length() >= 2)
				result.add(word);
		}
		return result;
	}
}
