package com.eric.law.domain.relation;

public class EntityRelation {
	
	private LawEntity entity1;
	private String name1;
	
	private LawEntity entity2;
	private String name2;
	
	public LawEntity getEntity1() {
		return entity1;
	}
	public void setEntity1(LawEntity entity1) {
		this.entity1 = entity1;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public LawEntity getEntity2() {
		return entity2;
	}
	public void setEntity2(LawEntity entity2) {
		this.entity2 = entity2;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getCaseCode() {
		return caseCode;
	}
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
	public String caseCode;
	public EntityRelation(LawEntity e1, LawEntity e2) {
		this.entity1 = e1;
		name1 = e1.getName();
		this.entity2 = e2;
		name2 = e2.getName();
	}
	public String getRelationDescription() {
		StringBuilder sb = new StringBuilder();
		sb.append(entity1.getEntityType().getValueCN()).append("<->").append(entity2.getEntityType().getValueCN());
		return sb.toString();
		
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getRelationDescription()+"\n");
		sb.append("[").append(entity1.toString()).append("] \n");
		sb.append("[").append(entity2.toString()).append("]");
		return sb.toString();
		
	}

}
