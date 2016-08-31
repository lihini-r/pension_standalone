package org.pensions.model.lists;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.pensions.model.RejectionReasonDTO;

@XmlRootElement
public class RejectionReasonList {

	private List<RejectionReasonDTO> reasons = new ArrayList<RejectionReasonDTO>();
	
	public RejectionReasonList() {
		// TODO Auto-generated constructor stub
	}

	public List<RejectionReasonDTO> getReasons() {
		return reasons;
	}

	public void setReasons(List<RejectionReasonDTO> reasons) {
		this.reasons = reasons;
	}
	
	
	
}
