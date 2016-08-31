package org.pensions.model.lists;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.pensions.model.PensionerDTO;

@XmlRootElement
public class PensionerList {

	private List<PensionerListItem> pensioners = new ArrayList<>();
	
	public PensionerList() {
		// TODO Auto-generated constructor stub
	}

	public List<PensionerListItem> getPensioners() {
		return pensioners;
	}

	public void setPensioners(List<PensionerListItem> pensioners) {
		this.pensioners = pensioners;
	}

	public List<PensionerDTO> getList() {
		List<PensionerDTO> list = new ArrayList<>();
		pensioners.stream()
				.forEach(e -> {
					PensionerDTO dto = new PensionerDTO();
					dto.setDateOfRetired(e.getDateOfRetired());
					dto.setDesignation(e.getDesignation());
					dto.setId(e.getId());
					dto.setName(e.getName());
					dto.setwOPNumber(e.getwOPNumber());
                    dto.setNic(e.getNic());

					list.add(dto);
				});
		return list;
	}
}

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
class PensionerListItem {

	private long id;
	private String dateOfRetired;
	private String designation;
	private String name;
	private String nic;
	private String wOPNumber;
	private String grade;
	private String mobileNumber;

	public PensionerListItem() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDateOfRetired() {
		return dateOfRetired;
	}

	public void setDateOfRetired(String dateOfRetired) {
		this.dateOfRetired = dateOfRetired;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getwOPNumber() {
		return wOPNumber;
	}

	public void setwOPNumber(String wOPNumber) {
		this.wOPNumber = wOPNumber;
	}
}