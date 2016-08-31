package org.pensions.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Percentage {

	private int id;
	private int years;
	private int months;
	private int reduced;
	private int unreduced;
	private int upperLimit;
	private int lowerLimit;
	
	public Percentage() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public int getYears() {
		return years;
	}

	public int getMonths() {
		return months;
	}

	public int getReduced() {
		return reduced;
	}

	public int getUnreduced() {
		return unreduced;
	}

	public int getUpperLimit() {
		return upperLimit;
	}

	public int getLowerLimit() {
		return lowerLimit;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public void setReduced(int reduced) {
		this.reduced = reduced;
	}

	public void setUnreduced(int unreduced) {
		this.unreduced = unreduced;
	}

	public void setUpperLimit(int upperLimit) {
		this.upperLimit = upperLimit;
	}

	public void setLowerLimit(int lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	
	
	
}
