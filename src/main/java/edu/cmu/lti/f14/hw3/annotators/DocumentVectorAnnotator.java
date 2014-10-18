package edu.cmu.lti.f14.hw3.annotators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.tcas.Annotation;

import edu.cmu.lti.f14.hw3.typesystems.Document;
import edu.cmu.lti.f14.hw3.typesystems.Token;
import edu.cmu.lti.f14.hw3.utils.Utils;

public class DocumentVectorAnnotator extends JCasAnnotator_ImplBase {

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

	List<String> tokenize0(String doc) {
		List<String> res = new ArrayList<String>();

		for (String s : doc.split("\\s+"))
			res.add(s);
		return res;
	}

	/**
	 * 
	 * @param jcas
	 * @param doc
	 */

	private void createTermFreqVector(JCas jcas, Document doc) {

		String docText = doc.getText();
		List<String> tokenizeList = tokenize0(docText);

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
