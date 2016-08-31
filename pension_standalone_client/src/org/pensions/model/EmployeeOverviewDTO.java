package org.pensions.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmployeeOverviewDTO {

	private int id;
	private String department;
	private String name;
	private String contactNo;
	
	public EmployeeOverviewDTO() {
	}
	
	

	public EmployeeOverviewDTO(int id, String department, String name,
			String contactNo) {
		super();
		this.id = id;
		this.department = department;
		this.name = name;
		this.contactNo = contactNo;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
}
