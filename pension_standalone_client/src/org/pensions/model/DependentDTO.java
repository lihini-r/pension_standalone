package org.pensions.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("dependentDTO")
public class DependentDTO extends SuperDependent{
	
	private long id;
	private String name;
	private String address;
	private Boolean maritalStatus;
	private String relation;
	private String accountNumber;
	private String nic;
	private String dob;
	private Hetoes branch;
	private Hetoes pensioner;
	
	public DependentDTO() {
		super();
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public Boolean getMaritalStatus() {
		return maritalStatus;
	}

	public String getRelation() {
		return relation;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getNic() {
		return nic;
	}

	public String getDob() {
		return dob;
	}

	public Hetoes getBranch() {
		return branch;
	}

	public Hetoes getPensioner() {
		return pensioner;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setMaritalStatus(Boolean maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setBranch(Hetoes branch) {
		this.branch = branch;
	}

	public void setPensioner(Hetoes pensioner) {
		this.pensioner = pensioner;
	}

	public String getMerital() {
		return this.getMaritalStatus() ? "Married" : "Unmarried";
	}

}
