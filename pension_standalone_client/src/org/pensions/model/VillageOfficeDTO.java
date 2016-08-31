package org.pensions.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class VillageOfficeDTO {
	private long id;
	private String name;
	
	
	public VillageOfficeDTO() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
