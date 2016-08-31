package org.pensions.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.pensions.model.Period;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class PensionDTO {

	private long Id;
	private String scale;
	private String accountNumber;
	private String startedDate;
	private Hetoes pensioner;
	private Double consolidatedSalary;
	private Period totalNoPay;
	private String circular;
	private Double netReducedPercentage;
	private Double netUnreducedPercentage;
	private Hetoes branch;
	private Hetoes state;
	private Hetoes pensionPoint;
	
	public PensionDTO() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return Id;
	}

	public Hetoes getPensionPoint() {
		return pensionPoint;
	}

	public void setPensionPoint(Hetoes pensionPoint) {
		this.pensionPoint = pensionPoint;
	}

	public String getScale() {
		return scale;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getStartedDate() {
		return startedDate;
	}

	public Hetoes getPensioner() {
		return pensioner;
	}

	public Double getConsolidatedSalary() {
		return consolidatedSalary;
	}

	public Period getTotalNoPay() {
		return totalNoPay;
	}

	public String getCircular() {
		return circular;
	}

	public Double getNetReducedPercentage() {
		return netReducedPercentage;
	}

	public Double getNetUnreducedPercentage() {
		return netUnreducedPercentage;
	}

	public Hetoes getBranch() {
		return branch;
	}
	
	public Hetoes getState() {
		return state;
	}

	public void setId(long id) {
		Id = id;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setStartedDate(String startedDate) {
		this.startedDate = startedDate;
	}

	public void setPensioner(Hetoes pensioner) {
		this.pensioner = pensioner;
	}

	public void setConsolidatedSalary(Double consolidatedSalary) {
		this.consolidatedSalary = consolidatedSalary;
	}

	public void setTotalNoPay(Period totalNoPay) {
		this.totalNoPay = totalNoPay;
	}

	public void setCircular(String circular) {
		this.circular = circular;
	}

	public void setNetReducedPercentage(Double netReducedPercentage) {
		this.netReducedPercentage = netReducedPercentage;
	}

	public void setNetUnreducedPercentage(Double netUnreducedPercentage) {
		this.netUnreducedPercentage = netUnreducedPercentage;
	}

	public void setBranch(Hetoes branch) {
		this.branch = branch;
	}
	
	public void setState(Hetoes state) {
		this.state = state;
	}

}
