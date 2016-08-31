package org.pensions.enumarations;

public enum ServiceDomain {

	CENTRAL("central"), PROVINTIAL("provintial");
	
	private String domain;
	
	ServiceDomain(String domain) {
		this.domain = domain;
	}
	
	public String getDomain() {
		return domain;
	}
	
}
