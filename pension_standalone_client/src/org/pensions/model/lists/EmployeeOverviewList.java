package org.pensions.model.lists;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.pensions.model.EmployeeOverviewDTO;

@XmlRootElement
public class EmployeeOverviewList {

	private List<EmployeeOverviewDTO> employeeList = new ArrayList<EmployeeOverviewDTO>();
	
	public EmployeeOverviewList() {
	}

	public List<EmployeeOverviewDTO> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<EmployeeOverviewDTO> employeeList) {
		this.employeeList = employeeList;
	}
	
}
