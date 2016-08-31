package org.pensions.model;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class District {
	
	private int id;
	private String name;
	
	public District() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}

	
	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
