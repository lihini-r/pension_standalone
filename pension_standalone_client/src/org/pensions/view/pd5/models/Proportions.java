package org.pensions.view.pd5.models;

import org.pensions.model.GratuityPaymentDTO;

public class Proportions extends GratuityPaymentDTO {

	String dependentName;
	String bankName;
	String branchName;
	
	public Proportions() {
		// TODO Auto-generated constructor stub
	}

	public String getDependentName() {
		return dependentName;
	}

	public void setDependentName(String dependentName) {
		this.dependentName = dependentName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	
	
}
