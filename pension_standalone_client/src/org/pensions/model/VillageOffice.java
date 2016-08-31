package org.pensions.model;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class VillageOffice {

	private long id;
	private String name;
	private DSOffice dsOffice;
	
	public VillageOffice() {
		// TODO Auto-generated constructor stub
	}

	
	public long getId() {
		return id;
	}

	
	public String getName() {
		return name;
	}
	
	
	public DSOffice getDsOffice() {
		return dsOffice;
	}

	public void setDsOffice(DSOffice dsOffice) {
		this.dsOffice = dsOffice;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
