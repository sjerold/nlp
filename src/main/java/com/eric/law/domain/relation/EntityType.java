package com.eric.law.domain.relation;

public enum EntityType {
	PLAINTIFF("plaintiff","原告"),DEFENDANT("defendant","被告"),AGENT("agent","委托诉讼代理人"),REPRESENTATIVE("representative","代表人");
	
	private String value;
	private String valueCN;
	
	private EntityType(String value, String valueCN) {
		this.value = value;
		this.valueCN = valueCN;
	
	}

	public String getValue() {
		return value;
	}

	public String getValueCN() {
		return valueCN;
	}
	
	
	
}
