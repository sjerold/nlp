package com.eric.law.service;

import java.util.List;

import com.eric.law.domain.relation.LawEntity;
import com.eric.law.uitl.Lists;

public class CaseService extends AbstractSectionService<String> {
	private RelationshipService relationshipService = new RelationshipService();
	
	@Override
	public List<String> processData(List<String> data) {
		Integer relationRowNumber = getRelationRowNumber(data);
		if (null==relationRowNumber) {
			System.out.println("Can not find case code");
			return Lists.newArrayList();
		}
		String code = data.get(relationRowNumber-1);
		System.out.println("Case Code: "+code);
		String title = data.get(relationRowNumber-2);
		System.out.println("Case title: "+title);
		String court = data.get(relationRowNumber-3);
		System.out.println("Court: "+court);
		return Lists.newArrayList(code, title, court);
	}

	private Integer getRelationRowNumber(List<String> data) {
		for (int i=0; i<data.size(); i++) {
			LawEntity e = relationshipService.getEntity(data.get(i));
			if (null!=e)
				return i;
		}
		return null;
	}
}
