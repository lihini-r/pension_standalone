package org.pensions.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("deceasedPensionerDTO")
public class DeceasedPensionerDTO extends PensionerDTO{

	private String officeName;
	
	public DeceasedPensionerDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	
}
