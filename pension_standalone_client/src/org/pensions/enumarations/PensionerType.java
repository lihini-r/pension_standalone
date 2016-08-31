package org.pensions.enumarations;

public enum PensionerType {

	REGULAR_PENSIONER("regular"), FOREIGN_PENSIONER("foreign"), DECEASED_PENSIONER("deceased");
	
	private String type;
	
	PensionerType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}
