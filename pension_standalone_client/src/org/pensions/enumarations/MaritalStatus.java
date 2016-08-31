package org.pensions.enumarations;

public enum MaritalStatus {

	NOT_MERRIED("Not merried"), MERRIED("Merried"), SPOUSE_DECEASED("Spouse dead"), DEVORCED("Devorced");
	
	private String status;
	
	private MaritalStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
}
