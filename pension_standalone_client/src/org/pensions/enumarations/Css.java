package org.pensions.enumarations;

public enum Css {

	HIDE("hide");
	
	private String value;
	
	private Css(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
