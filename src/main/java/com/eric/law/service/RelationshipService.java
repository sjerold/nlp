package com.eric.law.service;

import java.util.List;
import java.util.stream.Collectors;

import com.eric.law.domain.relation.EntityRelation;
import com.eric.law.domain.relation.EntityType;
import com.eric.law.domain.relation.LawEntity;
import com.eric.law.reference.Keywords;
import com.eric.law.uitl.Lists;

public class RelationshipService extends AbstractSectionService<EntityRelation> {

	@Override
	public List<EntityRelation> processData(List<String> data) {
		// TODO Auto-generated method stub
		Integer requestLine = this.getRequestLine(data);
		if (null == requestLine) {
			System.out.println("Unable to reqd the request and ayalisys the relationship");
			return Lists.newArrayList();
		}

		List<LawEntity> dataForRelation = data.stream().map(e -> getEntity(e)).filter(m -> m != null)
				.collect(Collectors.toList());
		List<EntityRelation> result = analyseRelation(dataForRelation);
		result.forEach(r->System.out.println(r.toString()));
		return result;
	}

	private List<EntityRelation> analyseRelation(List<LawEntity> entities) {
		List<Integer> specialIndex = Lists.newArrayList();
		List<EntityRelation> relations = Lists.newArrayList();
		int plaintiffNumber = 0;
		int defendantNumber = 0;
		for (int i = 0; i < entities.size(); i++) {
			LawEntity e = entities.get(i);
			if (EntityType.PLAINTIFF.equals(e.getEntityType())) {
				plaintiffNumber++;
				e.setOrder(plaintiffNumber);
				specialIndex.add(i);
			} else if (EntityType.DEFENDANT.equals(e.getEntityType())) {
				defendantNumber++;
				e.setOrder(defendantNumber);
				specialIndex.add(i);
			}
		}
		List<LawEntity> mainEntities = Lists.newArrayList();
		for (int i = 0; i < specialIndex.size(); i++) {
			Integer start = specialIndex.get(i);
			Integer end = i + 1 < specialIndex.size() ? specialIndex.get(i + 1) : entities.size();
			List<LawEntity> subEntities = Lists.newArrayList();
			for (int j = start; j < end; j++) {
				subEntities.add(entities.get(j));
			}
			relations.addAll(generateRelationship(subEntities));
			mainEntities.add(getMainEntity(subEntities));
		}
		relations.addAll(generateRelationship(mainEntities));
		return relations;
	}

	private List<EntityRelation> generateRelationship(List<LawEntity> entities) {
		List<EntityRelation> result = Lists.newArrayList();
		for (int i = 0; i < entities.size(); i++) {
			for (int j = i + 1; j < entities.size(); j++) {
				if (entities.get(i).isMainEntity() || entities.get(j).isMainEntity()) {
					result.add(new EntityRelation(entities.get(i), entities.get(j)));
				}
			}
		}
		return result;
	}

	private LawEntity getMainEntity(List<LawEntity> entities) {
		return entities.stream().filter(
				e -> EntityType.PLAINTIFF.equals(e.getEntityType()) || EntityType.DEFENDANT.equals(e.getEntityType()))
				.findFirst().orElse(null);
	}

	final List<EntityType> entities = Lists.newArrayList(EntityType.DEFENDANT, EntityType.PLAINTIFF,
			EntityType.REPRESENTATIVE, EntityType.AGENT);

	public LawEntity getEntity(String line) {

		for (EntityType entityType : entities) {
			List<String> keys = Keywords.get(entityType.getValue());
			for (String key : keys) {
				if (line.contains(key)) {
					LawEntity lawEntity = new LawEntity(line, key, entityType);
					return lawEntity;
				}
			}
		}
		return null;
	}

}
