package org.pensions.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmployeeDTO {

	private int id;
	private String name;
	private String nic;
	private String designation;
	private String contactNo;
	private String username;
	private String password;
	private String role;
	private String status;
	private String email;
	private int pensionPoint;
	
	public EmployeeDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNic() {
		return nic;
	}

	public String getDesignation() {
		return designation;
	}

	public String getContactNo() {
		return contactNo;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public int getPensionPoint() {
		return pensionPoint;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPensionPoint(int pensionPoint) {
		this.pensionPoint = pensionPoint;
	}
	
	
	
}
