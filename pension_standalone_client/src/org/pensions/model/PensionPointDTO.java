package org.pensions.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PensionPointDTO {
	
	private int id;
	private String name;
	private String address;
	private String type;
	private String stutoryBody;
	private String email;
	private String fax;
	private String telephone;
	private Hetoes branch;
	private String accountNumber;
	
	public PensionPointDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getType() {
		return type;
	}

	public String getStutoryBody() {
		return stutoryBody;
	}

	public String getEmail() {
		return email;
	}

	public String getFax() {
		return fax;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setStutoryBody(String stutoryBody) {
		this.stutoryBody = stutoryBody;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Hetoes getBranch() {
		return branch;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setBranch(Hetoes branch) {
		this.branch = branch;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
