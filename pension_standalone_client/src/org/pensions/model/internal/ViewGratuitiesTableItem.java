package org.pensions.model.internal;

public class ViewGratuitiesTableItem {
	
	private Long pensionNumber;
	private String name;
    private String nic;
    private String dateORetired;
    private String designation;
    
    public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public ViewGratuitiesTableItem() {
    }
    public Long getPensionNumber() {
		return pensionNumber;
	}
	public void setPensionNumber(Long pensionNumber) {
		this.pensionNumber = pensionNumber;
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
	public String getDateORetired() {
		return dateORetired;
	}
	public void setDateORetired(String dateORetired) {
		this.dateORetired = dateORetired;
	}
	
    
    
    

}
