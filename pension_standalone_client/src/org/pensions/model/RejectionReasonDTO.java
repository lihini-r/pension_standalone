package org.pensions.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RejectionReasonDTO {
	private long id;
	private String message;
	private String type;
	
	public RejectionReasonDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public long getId() {
		return id;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getType() {
		return type;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
