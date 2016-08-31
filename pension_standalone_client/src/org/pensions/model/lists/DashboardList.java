package org.pensions.model.lists;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.pensions.model.DashboardDTO;
import org.pensions.model.Hetoes;

@XmlRootElement
public class DashboardList {
	
	private List<DashboardDTO> details = new ArrayList<DashboardDTO>();
	
	public DashboardList() {
		// TODO Auto-generated constructor stub
	}

	public List<DashboardDTO> getDetails() {
		return details;
	}

	public void setDetails(List<DashboardDTO> details) {
		this.details = details;
	}
	
	
}
