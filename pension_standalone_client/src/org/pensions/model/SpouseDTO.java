package org.pensions.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("spouseDTO")
public class SpouseDTO extends DependentDTO {

	private String contactNumber;
	private String merrageDate;
	
	public SpouseDTO() {
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public String getMerrageDate() {
		return merrageDate;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void setMerrageDate(String merrageDate) {
		this.merrageDate = merrageDate;
	}
	
}
