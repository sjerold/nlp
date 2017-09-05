package com.eric.law.domain.relation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.eric.law.uitl.Lists;

public class LawEntity {
	private String name;
	private EntityType entityType;
	private Collection<String> attributes = new ArrayList<>();
	private int order;
	public LawEntity() {}
	public LawEntity(String row,String keyword,EntityType type) {
		entityType = type;
		String rowTmp = row.replace(keyword, "").replace("。", "");
		String[] splitedWords=  rowTmp.split(",");
		name = splitedWords[0];
		if(splitedWords.length>1) {
			List<String> tmpList = Lists.newArrayList(splitedWords);
			attributes.addAll(tmpList.subList(1, tmpList.size()));
		}
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(entityType.getValueCN()).append(order==0?"":order).append(" : ").append(name).append(" - ");
		for(String a : attributes) {
			sb.append(a).append("/");
		}
		return sb.toString();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public EntityType getEntityType() {
		return entityType;
	}
	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}
	public Collection<String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Collection<String> attributes) {
		this.attributes = attributes;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public boolean isMainEntity() {
		return EntityType.PLAINTIFF.equals(entityType)||EntityType.DEFENDANT.equals(entityType);
	}
}
