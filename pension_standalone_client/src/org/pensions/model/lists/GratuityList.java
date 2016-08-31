package org.pensions.model.lists;

import java.util.ArrayList;
import java.util.List;


import org.pensions.model.GratuityListDTO;

public class GratuityList {
private List<GratuityListDTO> list = new ArrayList<GratuityListDTO>();
	
	public GratuityList() {
		// TODO Auto-generated constructor stub
	}
	public List<GratuityListDTO> getList() {
		return list;
	}

	public void setList(List<GratuityListDTO> list) {
		this.list = list;
	}

}
