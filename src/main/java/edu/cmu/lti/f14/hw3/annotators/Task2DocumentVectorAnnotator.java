package edu.cmu.lti.f14.hw3.annotators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.tartarus.snowball.ext.EnglishStemmer;

import edu.cmu.lti.f14.hw3.typesystems.Document;
import edu.cmu.lti.f14.hw3.typesystems.Token;
import edu.cmu.lti.f14.hw3.utils.FileUtil;
import edu.cmu.lti.f14.hw3.utils.StanfordLemmatizer;
import edu.cmu.lti.f14.hw3.utils.Utils;

public class Task2DocumentVectorAnnotator extends JCasAnnotator_ImplBase {

	EnglishStemmer stemmer = null;
	Set<String> stopwordsSet = null;

	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		super.initialize(aContext);

		stemmer = new EnglishStemmer();
		stopwordsSet = new HashSet<String>();
		FileUtil.iterateFileByLine("stopwords.txt", new FileUtil.FileLineProcess() {
			@Override
			public void process(String line) {
				stopwordsSet.add(line.trim());
			}
		});

	}

	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {

		FSIterator<Annotation> iter = jcas.getAnnotationIndex().iterator();
		if (iter.isValid()) {
			iter.moveToNext();
			Document doc = (Document) iter.get();
			createTermFreqVector(jcas, doc);
			doc.addToIndexes();
		}

	}

	/**
	 * A basic white-space tokenizer, it deliberately does not split on
	 * punctuation!
	 *
	 * @param doc
	 *            input text
	 * @return a list of tokens.
	 */

	List<String> tokenize(String doc) {
		List<String> res = new ArrayList<String>();

		for (String s : doc.split("\\s+"))
			res.add(s);
		return res;
	}

	List<String> tokenize1(String doc) { // stanford
		List<String> res = new ArrayList<String>();

		for (String s : doc.split("\\s+")) {
			res.add(StanfordLemmatizer.stemWord(s));
		}
		return res;
	}

	List<String> tokenize2(String doc) { // snow ball
		List<String> res = new ArrayList<String>();

		for (String s : doc.split("\\s+")) {
			stemmer.setCurrent(s);
			if (stemmer.stem())
				res.add(stemmer.getCurrent());
			else
				res.add(s);
		}
		return res;
	}

	List<String> tokenize1_stopwordsFilter(String doc) {
		List<String> res = new ArrayList<String>();

		for (String s : doc.split("\\s+")) {
			if (stopwordsSet.contains(s))
				continue;
			res.add(StanfordLemmatizer.stemWord(s));
		}
		return res;
	}

	List<String> tokenize2_stopwordsFilter(String doc) {
		List<String> res = new ArrayList<String>();

		for (String s : doc.split("\\s+")) {
			if (stopwordsSet.contains(s))
				continue;
			stemmer.setCurrent(s);
			if (stemmer.stem())
				res.add(stemmer.getCurrent());
			else
				res.add(s);
		}
		return res;
	}

	/**
	 * 
	 * @param jcas
	 * @param doc
	 */

	private void createTermFreqVector(JCas jcas, Document doc) {

		String docText = doc.getText();
		// List<String> tokenizeList = tokenize0(docText);
		// List<String> tokenizeList = tokenize1(docText);
		List<String> tokenizeList = tokenize2(docText);
		// List<String> tokenizeList = tokenize1_stopwordsFilter(docText);
		// List<String> tokenizeList = tokenize2_stopwordsFilter(docText);

		Map<String, Integer> frequenceMap = new HashMap<String, Integer>();
		for (String token : tokenizeList) {
			if (!frequenceMap.containsKey(token))
				frequenceMap.put(token, 1);
			else
				frequenceMap.put(token, frequenceMap.get(token) + 1);
		}

		List<Token> tokeList = new ArrayList<Token>();
		for (String tokenString : frequenceMap.keySet()) {
			Token t = new Token(jcas);
			t.setText(tokenString);
			t.setFrequency(frequenceMap.get(tokenString));
			tokeList.add(t);
		}
		FSList fsList = Utils.fromCollectionToFSList(jcas, tokeList);
		doc.setTokenList(fsList);
		// fsList.addToIndexes(jcas);
	}

}
