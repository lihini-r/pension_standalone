package org.pensions.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DSOffice {
	
	private long id;
	private String name;
	private District district;
	private List<VillageOffice> villageOffices = new ArrayList<VillageOffice>();
	
	public DSOffice() {
		// TODO Auto-generated constructor stub
	}

	
	public long getId() {
		return id;
	}

	
	public String getName() {
		return name;
	}
	
	
	public List<VillageOffice> getVillageOffices() {
		return villageOffices;
	}

	
	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public void setVillageOffices(List<VillageOffice> villageOffices) {
		this.villageOffices = villageOffices;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
