package org.pensions.model;

import java.util.ArrayList;
import java.util.List;

public class RejectionDTO {

	private long id;
	private String rejectedOn;
	private String rejectedBy;
	private Hetoes pension;
	private List<RejectionReasonDTO> reasons = null;
	
	public RejectionDTO() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public String getRejectedOn() {
		return rejectedOn;
	}

	public String getRejectedBy() {
		return rejectedBy;
	}

	public List<RejectionReasonDTO> getReasons() {
		if(reasons == null) {
			reasons = new ArrayList<>();
		}
		return reasons;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setRejectedOn(String rejectedOn) {
		this.rejectedOn = rejectedOn;
	}

	public void setRejectedBy(String rejectedBy) {
		this.rejectedBy = rejectedBy;
	}

	public void setReasons(List<RejectionReasonDTO> reasons) {
		this.reasons = reasons;
	}

	public Hetoes getPension() {
		return pension;
	}

	public void setPension(Hetoes pension) {
		this.pension = pension;
	}

	
}
