package edu.cmu.lti.f14.hw3.utils.task2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.cmu.lti.f14.hw3.utils.FileUtil;
import edu.cmu.lti.f14.hw3.utils.Pair;

public class StopwordDivide {
	static Set<String> stopwordsSet = null;
	static {
		stopwordsSet = new HashSet<String>();
		FileUtil.iterateFileByLine("stopwords.txt", new FileUtil.FileLineProcess() {
			@Override
			public void process(String line) {
				line = line.trim();
				stopwordsSet.add(line);
				stopwordsSet.add(line.toLowerCase());
				String[] split = line.split(String.format("[%s]", Tokenizer.filterSymbol));
				for (String s : split) {
					stopwordsSet.add(s);
				}
			}
		});
	}
	
	public static List<Pair<List<String>, String>> getAllDivid(List<Pair<List<String>, String>> data) {
		List<Pair<List<String>, String>> result = new ArrayList<Pair<List<String>, String>>();
		for (Pair<List<String>, String> singleData : data) {
			List<String> originWordList = singleData.getV1();
			List<String> nonStopwordWordList = new ArrayList<String>();
			for (String oWord : originWordList) {
				if(!stopwordsSet.contains(oWord)) {
					nonStopwordWordList.add(oWord);
				}
			}
			result.add(new Pair<List<String>, String>(nonStopwordWordList, singleData.getV2() + "#true"));

			singleData.setV2(singleData.getV2() + "#false");
			result.add(singleData);
		}
		return result;
	}

}
