package org.pensions.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExtraPaymentDTO {

	private long id;
	private String description;
	private String status;
	private double amount;
	private Hetoes pension;
	private String type;
	private boolean pensionable;
	
	public ExtraPaymentDTO() {
	
	}

	public long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getStatus() {
		return status;
	}

	public double getAmount() {
		return amount;
	}

	public Hetoes getPension() {
		return pension;
	}

	public String getType() {
		return type;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setPension(Hetoes pension) {
		this.pension = pension;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isPensionable() {
		return pensionable;
	}

	public void setPensionable(boolean pensionable) {
		this.pensionable = pensionable;
	}

}
