package edu.cmu.lti.f14.hw3.annotators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import edu.cmu.lti.f14.hw3.typesystems.Document;
import edu.cmu.lti.f14.hw3.typesystems.Token;
import edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document;
import edu.cmu.lti.f14.hw3.typesystems.task2.ConfigVector;
import edu.cmu.lti.f14.hw3.utils.Pair;
import edu.cmu.lti.f14.hw3.utils.Utils;
import edu.cmu.lti.f14.hw3.utils.task2.configDivider.CapitalizeDivide;
import edu.cmu.lti.f14.hw3.utils.task2.configDivider.StemmerDivide;
import edu.cmu.lti.f14.hw3.utils.task2.configDivider.StopwordDivide;
import edu.cmu.lti.f14.hw3.utils.task2.configDivider.Tokenizer;

public class Task2DocumentVectorAnnotator extends JCasAnnotator_ImplBase {

	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		super.initialize(aContext);

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
	 * 
	 * @param jcas
	 * @param doc
	 */

	private void createTermFreqVector(JCas jcas, Document doc) {

		String docText = doc.getText();
		List<Pair<List<String>, String>> tokenizedResult = Tokenizer.getAllTokenizedResult(docText);
		List<Pair<List<String>, String>> capitalDivideResult = CapitalizeDivide.getAllDivid(tokenizedResult);
		List<Pair<List<String>, String>> stopwordResult = StopwordDivide.getAllDivid(capitalDivideResult);
		List<Pair<List<String>, String>> finalResult = StemmerDivide.getAllDivid(stopwordResult);

		Task2Document t2doc = new Task2Document(jcas);
		t2doc.setText(doc.getText());
		t2doc.setQid(doc.getQueryID());
		t2doc.setRel(doc.getRelevanceValue());
		
		List<ConfigVector> caseResultList = new ArrayList<ConfigVector>();
		for (Pair<List<String>, String> annotatedList : finalResult) {
			List<String> tokenizeList = annotatedList.getV1();
			String annotation = annotatedList.getV2();
			ConfigVector caseResult = new ConfigVector(jcas);
			String[] split = annotation.split("#");
			caseResult.setTokenizeMethod(split[0]);
			caseResult.setAllLowerCase(Boolean.parseBoolean(split[1]));
			caseResult.setFilteredStopword(Boolean.parseBoolean(split[2]));
			caseResult.setStemMethod(split[3]);

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
			caseResult.setTokenList(fsList);

			caseResultList.add(caseResult);
		}
		FSList caseFSList = Utils.fromCollectionToFSList(jcas, caseResultList);
		t2doc.setVectorList(caseFSList);
		t2doc.addToIndexes(jcas);
	}

}
