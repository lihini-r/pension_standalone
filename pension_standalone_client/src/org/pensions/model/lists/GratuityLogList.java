package org.pensions.model.lists;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.pensions.model.GratuityLogDTO;


@XmlRootElement
public class GratuityLogList {

	private long pensionNumber;
	private List<GratuityLogDTO> history = new ArrayList<GratuityLogDTO>();
	
	public GratuityLogList() {
		// TODO Auto-generated constructor stub
	}
	
	public List<GratuityLogDTO> getHistory() {
		return history;
	}

	public void setHistory(List<GratuityLogDTO> history) {
		this.history = history;
	}

	public long getPensionNumber() {
		return pensionNumber;
	}

	public void setPensionNumber(long pensionNumber) {
		this.pensionNumber = pensionNumber;
	}
	
	
}
