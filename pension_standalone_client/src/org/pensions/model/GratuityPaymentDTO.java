package org.pensions.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GratuityPaymentDTO {

	private long id;
	private double percentage;
	private double amount;
	private String accountNumber;
	private int branchId;
	private long dependentId;

	public GratuityPaymentDTO() {
		// TODO Auto-generated constructor stub
	}


	public GratuityPaymentDTO(long id, double percentage, double amount,
			String accountNumber, int branchId, long dependentId) {
		super();
		this.id = id;
		this.percentage = percentage;
		this.amount = amount;
		this.accountNumber = accountNumber;
		this.branchId = branchId;
		this.dependentId = dependentId;
	}



	public long getId() {
		return id;
	}

	public double getPercentage() {
		return percentage;
	}

	public double getAmount() {
		return amount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public int getBranchId() {
		return branchId;
	}

	public long getDependentId() {
		return dependentId;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public void setDependentId(long dependentId) {
		this.dependentId = dependentId;
	}




}
