package org.pensions.view.pd5.models;

import java.util.List;

import org.pensions.model.Hetoes;
import org.pensions.model.SpouseDTO;

public class Spouse extends SpouseDTO{

	private String bank;
	private List<Hetoes> branch;
	
	public Spouse() {
		// TODO Auto-generated constructor stub
	}
	
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
//	public List<Hetoes> getBranch() {
//		return branch;
//	}
	public void setBranch(List<Hetoes> branch) {
		this.branch = branch;
	}
}
