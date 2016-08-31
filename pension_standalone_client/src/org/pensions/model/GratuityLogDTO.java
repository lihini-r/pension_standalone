package org.pensions.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GratuityLogDTO {
	private String state;
	private String timestamp;
	private String responsibleEmployee;
	
	public GratuityLogDTO() {
		// TODO Auto-generated constructor stub
	}
	public synchronized String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public synchronized String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public synchronized String getResponsibleEmployee() {
		return responsibleEmployee;
	}
	public void setResponsibleEmployee(String responsibleEmployee) {
		this.responsibleEmployee = responsibleEmployee;
	}
	
	
	
	
	

}
