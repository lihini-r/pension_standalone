package org.pensions.enumarations;

public enum Salutation {

	MR("Mr"), MISS("Miss"), REV("Rev");
	
	private String salutation;
	
	Salutation(String salutation) {
		this.salutation = salutation;
	}
	
	public String getSalutation() {
		return this.salutation;
	}
}