package org.pensions.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Period {

	private int years;
	private int months;
	private int days;
	
	public Period() {
		// TODO Auto-generated constructor stub
	}


	public int getYears() {
		return years;
	}

	public int getMonths() {
		return months;
	}

	public int getDays() {
		return days;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public void setDays(int days) {
		this.days = days;
	}
	
	
}
