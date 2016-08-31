package org.pensions.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GratuityDTO {

	private long id;
	private String accountNumber;
	private Double gratuityAmount;
	private Double govDeduction;
	private Double netGratuityAmount;
	private Hetoes pensioner;	
	private String type;
	private Hetoes branch;
	private Hetoes state;
	
	public GratuityDTO() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public Double getGratuityAmount() {
		return gratuityAmount;
	}

	public Double getGovDeduction() {
		return govDeduction;
	}

	public Hetoes getBranch() {
		return branch;
	}

	public void setBranch(Hetoes branch) {
		this.branch = branch;
	}

	public Double getNetGratuityAmount() {
		return netGratuityAmount;
	}

	public Hetoes getPensioner() {
		return pensioner;
	}

	public String getType() {
		return type;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setGratuityAmount(Double gratuityAmount) {
		this.gratuityAmount = gratuityAmount;
	}

	public void setGovDeduction(Double govDeduction) {
		this.govDeduction = govDeduction;
	}

	public void setNetGratuityAmount(Double netGratuityAmount) {
		this.netGratuityAmount = netGratuityAmount;
	}

	public void setPensioner(Hetoes pensioner) {
		this.pensioner = pensioner;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Hetoes getState() {
		return state;
	}

	public void setState(Hetoes state) {
		this.state = state;
	}

	
}
