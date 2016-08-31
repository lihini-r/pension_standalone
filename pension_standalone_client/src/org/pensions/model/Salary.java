package org.pensions.model;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Salary {

	private int step;
	private Double basicSalary;
	
	private Double firstAllowance;
	private Double secondAllowance;
	
	
	public Salary() {
		// TODO Auto-generated constructor stub
	}
	public int getStep() {
		return step;
	}
	public Double getBasicSalary() {
		return basicSalary;
	}
	public void setStep(int step) {
		this.step = step;
	}

	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public Double getFirstAllowance() {
		return firstAllowance;
	}

	public void setFirstAllowance(Double firstAllowance) {
		this.firstAllowance = firstAllowance;
	}
	public Double getSecondAllowance() {
		return secondAllowance;
	}

	public void setSecondAllowance(Double secondAllowance) {
		this.secondAllowance = secondAllowance;
	}


}
