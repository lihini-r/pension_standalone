package org.pensions.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GratuityListDTO {
	
	private int id;
	private Hetoes pensioner;	

	public Hetoes getPensioner() {
		return pensioner;
	}

	public void setPensioner(Hetoes pensioner) {
		this.pensioner = pensioner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
