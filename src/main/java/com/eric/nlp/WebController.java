package com.eric.nlp;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

@RestController
public class WebController {
	@RequestMapping("start")
	public String start() {
		String text = "克林顿说，华盛顿将逐步落实对韩国的经济援助。"
		        + "金大中对克林顿的讲话报以掌声：克林顿总统在会谈中重申，他坚定地支持韩国摆脱经济危机,并支付1034556元。";
		Annotation document = new Annotation(text);
		// Setup Chinese Properties by loading them from classpath resources
		Properties props = new Properties();
		try {
			props.load(IOUtils.readerFromString("nlp.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Or this way of doing it also works
		// Properties props = StringUtils.argsToProperties(new String[]{"-props", "StanfordCoreNLP-chinese.properties"});
		StanfordCoreNLP corenlp = new StanfordCoreNLP(props);
		corenlp.annotate(document);
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		for (CoreMap sentence : sentences) {
			for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
				String word = token.get(TextAnnotation.class);
				String pos = token.get(PartOfSpeechAnnotation.class);
				String ne = token.get(NamedEntityTagAnnotation.class);
				System.out.println(word+"||"+pos+"||"+ne);
			}
		}
		return "started";
	}
}
