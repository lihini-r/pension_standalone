package org.pensions.model.lists;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.pensions.model.PensionerOverviewDTO;

@XmlRootElement
public class PensionerOverviewList {

	private List<PensionerOverviewDTO> pensionersList = new ArrayList<PensionerOverviewDTO>();
	
	public PensionerOverviewList() {
		// TODO Auto-generated constructor stub
	}

	public List<PensionerOverviewDTO> getPensionersList() {
		return pensionersList;
	}

	public void setPensionersList(List<PensionerOverviewDTO> pensionersList) {
		this.pensionersList = pensionersList;
	}
	
}
