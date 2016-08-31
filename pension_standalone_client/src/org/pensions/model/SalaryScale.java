package org.pensions.model;



public class SalaryScale {

	private String code;
	private String scale;
	private String grade;
	private String circular;
	
	public SalaryScale() {
		
	}

	public SalaryScale(String scale, String grade, String circular) {
		super();
		this.scale = scale;
		this.grade = grade;
		this.circular = circular;
	}
	public String getCode() {
		this.code = this.scale + "-" + this.circular + "-" + this.grade;
		return code;
	}

	public String getScale() {
		return scale;
	}

	
	public String getGrade() {
		return grade;
	}

	
	public String getCircular() {
		return circular;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setCircular(String circular) {
		this.circular = circular;
	}
	
}
