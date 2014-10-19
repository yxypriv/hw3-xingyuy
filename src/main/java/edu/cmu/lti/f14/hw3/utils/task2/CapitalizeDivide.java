package edu.cmu.lti.f14.hw3.utils.task2;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.lti.f14.hw3.utils.Pair;

public class CapitalizeDivide {
	public static List<Pair<List<String>, String>> getAllDivid(List<Pair<List<String>, String>> data) {
		List<Pair<List<String>, String>> result = new ArrayList<Pair<List<String>, String>>();
		for (Pair<List<String>, String> singleData : data) {
			List<String> originWordList = singleData.getV1();
			List<String> lowerCaseWordList = new ArrayList<String>();
			for (String oWord : originWordList) {
				lowerCaseWordList.add(oWord.toLowerCase());
			}
			result.add(new Pair<List<String>, String>(lowerCaseWordList, singleData.getV2() + "#true"));

			singleData.setV2(singleData.getV2() + "#false");
			result.add(singleData);
		}
		return result;
	}
}
