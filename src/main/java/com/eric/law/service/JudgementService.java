package com.eric.law.service;

import java.util.List;

import com.eric.law.uitl.Lists;

public class JudgementService extends AbstractSectionService<String> {

	public List<String> processData(List<String> data) {
		Integer judgementLine = this.getJudgementLine(data);
		if (null == judgementLine) {
			System.out.println("Unable to read Judgement");
			return Lists.newArrayList();
		}
		Integer endLine = this.getJudgementEndLine(data);
		if (null == endLine) {
			endLine = data.size();
		}
		List<String> judgement = Lists.newArrayList();
		for (int i = judgementLine + 1; i < endLine; i++) {
			judgement.add(data.get(i));
		}

		System.out.println("Judgement section:");
		judgement.forEach(e -> System.out.println(e));
		return judgement;
	}

}
