package org.pensions.model.lists;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.pensions.model.PensionLogDTO;



@XmlRootElement
public class History {

	private long pensionNumber;
	private List<PensionLogDTO> history = new ArrayList<PensionLogDTO>();
	
	public History() {
		// TODO Auto-generated constructor stub
	}

	public List<PensionLogDTO> getHistory() {
		return history;
	}

	public void setHistory(List<PensionLogDTO> history) {
		this.history = history;
	}

	public long getPensionNumber() {
		return pensionNumber;
	}

	public void setPensionNumber(long pensionNumber) {
		this.pensionNumber = pensionNumber;
	}
}
