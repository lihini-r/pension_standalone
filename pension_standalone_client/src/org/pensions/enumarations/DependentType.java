package org.pensions.enumarations;

public enum DependentType {

	PARENT("Parent"), SPOUSE("Spouse"), CHILD("Child"),SIBLING("Sibling"),DAYAKA_SABHAWA("Dayaka");
	
	private String type;
	
	DependentType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}
