package com.eric.law.service;

import java.util.List;
import java.util.stream.IntStream;

import com.eric.law.reference.Keywords;

public abstract class AbstractSectionService<T>{
	
	public abstract List<T> processData(List<String> data) ;
	private Integer getTargetLine(List<String> data, String keyword) {
		int i = IntStream.range(0, data.size()).filter(u -> data.get(u).contains(keyword)).findFirst().orElse(-1);
		return i == -1 ? null : i;
	}
	
	private Integer searchKeyWord(List<String> data, List<String> keywords) {
		for (String keyword : keywords) {
			Integer lineNumber = getTargetLine(data, keyword);
			if (null != lineNumber)
				return lineNumber;
		}
		return null;
	}
	
	protected Integer getRequestLine(List<String> data) {
		List<String> keywords = Keywords.KEYWORD.get(Keywords.REQUEST);
		return searchKeyWord(data, keywords);
	}
	
	protected Integer getJudgementLine(List<String> data) {
		List<String> keywords = Keywords.KEYWORD.get(Keywords.JUDGEMENT);
		return searchKeyWord(data, keywords);
	}
	
	protected Integer getJudgementEndLine(List<String> data) {
		List<String> keywords = Keywords.KEYWORD.get(Keywords.JUDGEMENT_END);
		return searchKeyWord(data, keywords);
	}
}
