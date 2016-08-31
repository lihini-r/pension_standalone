package org.pensions.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type"
		)
@JsonSubTypes({
	@Type(value = DeceasedPensionerDTO.class, name = "deceasedPensionerDTO"),
	@Type(value = RegularPensionerDTO.class, name = "regularPensionerDTO")
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class PensionerDTO {

	private long id;
	private String name;
	private String salutation;
	private String address;
	private String nic;
	private String designation;
	private String dateOfRetired;
	private String dateOfDeceased;
	private String pensionerType;
	private Boolean pensionable;
	private Boolean permanant;
	private String serviceDomain;
	private String wOPNumber;
	
	

	private List<Hetoes> dependents = new ArrayList<Hetoes>();
	private List<Hetoes> pensions = new ArrayList<Hetoes>();
	private Hetoes gratuity;
	private Hetoes villageOffice;
	private Hetoes pensionPoint;

	public PensionerDTO() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSalutation() {
		return salutation;
	}

	public String getAddress() {
		return address;
	}

	public String getNic() {
		return nic;
	}

	public String getDesignation() {
		return designation;
	}

	public String getDateOfRetired() {
		return dateOfRetired;
	}

	public String getDateOfDeceased() {
		return dateOfDeceased;
	}

	public Boolean getPensionable() {
		return pensionable;
	}

	public Boolean getPermanant() {
		return permanant;
	}

	public String getServiceDomain() {
		return serviceDomain;
	}

	public List<Hetoes> getDependents() {
		return dependents;
	}

	public List<Hetoes> getPensions() {
		return pensions;
	}

	public Hetoes getGratuity() {
		return gratuity;
	}

	public Hetoes getVillageOffice() {
		return villageOffice;
	}

	public Hetoes getPensionPoint() {
		return pensionPoint;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setDateOfRetired(String dateOfRetired) {
		this.dateOfRetired = dateOfRetired;
	}

	public void setDateOfDeceased(String dateOfDeceased) {
		this.dateOfDeceased = dateOfDeceased;
	}

	public void setPensionable(Boolean pensionable) {
		this.pensionable = pensionable;
	}

	public void setPermanant(Boolean permanant) {
		this.permanant = permanant;
	}

	public void setServiceDomain(String serviceDomain) {
		this.serviceDomain = serviceDomain;
	}

	public void setDependents(List<Hetoes> dependents) {
		this.dependents = dependents;
	}

	public void setPensions(List<Hetoes> pensions) {
		this.pensions = pensions;
	}

	public void setGratuity(Hetoes gratuity) {
		this.gratuity = gratuity;
	}

	public void setVillageOffice(Hetoes villageOffice) {
		this.villageOffice = villageOffice;
	}

	public void setPensionPoint(Hetoes pensionPoint) {
		this.pensionPoint = pensionPoint;
	}

	public String getwOPNumber() {
		return wOPNumber;
	}

	public void setwOPNumber(String wOPNumber) {
		this.wOPNumber = wOPNumber;
	}

	public String getWOPNumber() {
		return wOPNumber;
	}

	public String getPensionerType() {
		return pensionerType;
	}

	public void setPensionerType(String pensionerType) {
		this.pensionerType = pensionerType;
	}
}
