package org.pensions.model.lists;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.pensions.model.BankDTO;

@XmlRootElement
public class BankList {

	private List<BankDTO> list = new ArrayList<BankDTO>();
	
	public BankList() {
		// TODO Auto-generated constructor stub
	}

	public List<BankDTO> getList() {
		return list;
	}

	public void setList(List<BankDTO> list) {
		this.list = list;
	}
	
}
