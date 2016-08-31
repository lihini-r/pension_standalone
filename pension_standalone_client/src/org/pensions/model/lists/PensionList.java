package org.pensions.model.lists;

import java.util.ArrayList;
import java.util.List;

import org.pensions.model.PensionDTO;

public class PensionList {

	private List<PensionDTO> pensions = new ArrayList<PensionDTO>();
	
	public PensionList() {
		// TODO Auto-generated constructor stub
	}

	public List<PensionDTO> getPensions() {
		return pensions;
	}

	public void setPensions(List<PensionDTO> pensions) {
		this.pensions = pensions;
	}
	
	
}
