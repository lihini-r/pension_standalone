package org.pensions.model;

import org.pensions.model.Period;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("regularPensionerDTO")
public class RegularPensionerDTO extends PensionerDTO{

	private String gender;
	private String lastOccupiedPlace;
	private String firstAppoinmentDate;
	private String dateOfBirth;
	private String mobileNumber;
	private String landNumber;
	private String email;
	private String skype;
	private Double salary;
	private String trainedPeriod;
	private String wnopDeductedPeriod;
	private String permanantPeriod;
	private Period servicePeriod;
	private String grade;
	private String dateOfPermanantAppoinment;
	private String retiredReason;
	private String sectionUnder;
	private String type;
	private String nextIncrementDate;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RegularPensionerDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getGrade() {
		return grade;
	}


	public String getRetiredReason() {
		return retiredReason;
	}

	public String getSectionUnder() {
		return sectionUnder;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDateOfPermanantAppoinment() {
		return dateOfPermanantAppoinment;
	}

	public void setDateOfPermanantAppoinment(String dateOfPermanantAppoinment) {
		this.dateOfPermanantAppoinment = dateOfPermanantAppoinment;
	}

	public void setRetiredReason(String retiredReason) {
		this.retiredReason = retiredReason;
	}

	public void setSectionUnder(String sectionUnder) {
		this.sectionUnder = sectionUnder;
	}

	public String getGender() {
		return gender;
	}

	public String getLastOccupiedPlace() {
		return lastOccupiedPlace;
	}

	public String getFirstAppoinmentDate() {
		return firstAppoinmentDate;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getLandNumber() {
		return landNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getSkype() {
		return skype;
	}

	public Double getSalary() {
		return salary;
	}

	public String getTrainedPeriod() {
		return trainedPeriod;
	}

	public String getWnopDeductedPeriod() {
		return wnopDeductedPeriod;
	}

	public String getPermanantPeriod() {
		return permanantPeriod;
	}

	public Period getServicePeriod() {
		return servicePeriod;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setLastOccupiedPlace(String lastOccupiedPlace) {
		this.lastOccupiedPlace = lastOccupiedPlace;
	}

	public void setFirstAppoinmentDate(String firstAppoinmentDate) {
		this.firstAppoinmentDate = firstAppoinmentDate;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setLandNumber(String landNumber) {
		this.landNumber = landNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public void setTrainedPeriod(String trainedPeriod) {
		this.trainedPeriod = trainedPeriod;
	}

	public void setWnopDeductedPeriod(String wnopDeductedPeriod) {
		this.wnopDeductedPeriod = wnopDeductedPeriod;
	}

	public void setPermanantPeriod(String permanantPeriod) {
		this.permanantPeriod = permanantPeriod;
	}

	public void setServicePeriod(Period servicePeriod) {
		this.servicePeriod = servicePeriod;
	}
	public String getNextIncrementDate() {
		return nextIncrementDate;
	}

	public void setNextIncrementDate(String nextIncrementDate) {
		this.nextIncrementDate = nextIncrementDate;
	}
	
}
