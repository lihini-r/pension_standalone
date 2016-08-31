package org.pensions.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.pensions.enumarations.PensionerType;

@XmlRootElement
public class PensionerOverviewDTO {

	private long id;
	private String name;
	private String nic;
	private String type;
	private double netGratuityAmount;
	
	public PensionerOverviewDTO() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getType() {
		return type;
	}

	public void setType(PensionerType type) {
		this.type = type.getType();
	}

	public double getNetGratuityAmount() {
		return netGratuityAmount;
	}

	public void setNetGratuityAmount(double netGratuityAmount) {
		this.netGratuityAmount = netGratuityAmount;
	}
	
}
