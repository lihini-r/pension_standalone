package org.pensions.model.lists;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.pensions.model.ExtraPaymentDTO;

@XmlRootElement
public class ExtraPaymentList {
	private List<ExtraPaymentDTO> extraPayments = new ArrayList<>();

	public ExtraPaymentList() {
		// TODO Auto-generated constructor stub
	}

	public List<ExtraPaymentDTO> getExtraPayments() {
		return extraPayments;
	}

	public void setExtraPayments(List<ExtraPaymentDTO> extraPayments) {
		this.extraPayments = extraPayments;
	}



}
