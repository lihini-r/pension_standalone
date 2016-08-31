package org.pensions.data.dao;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.pensions.data.CallManager;
import org.pensions.model.DependentDTO;
import org.pensions.model.SpouseDTO;
import org.pensions.model.lists.DependentList;

public class DependentDAO {

	WebTarget baseTarget = null;

	public DependentDAO(long pensionerId) {
		baseTarget = CallManager.getRootTarget()
				.path("pensioners")
				.path(String.valueOf(pensionerId))
				.path("dependents");
	}



	public DependentDTO getDependent(String url) {

		Response response = baseTarget

				.path(String.valueOf(url))
				.request(MediaType.APPLICATION_JSON)
				.get();

		if(response.getStatus() != 200) {
			throw new RuntimeException();
		}

		DependentDTO dto = (DependentDTO) response.readEntity(DependentDTO.class);

		return dto;

	}

	public DependentList getDependents() {
		Response response = baseTarget
				.request(MediaType.APPLICATION_JSON)
				.get();

		DependentList dependentList = new DependentList();

		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			return dependentList;
		}

		dependentList = (DependentList) response.readEntity(DependentList.class);
		return dependentList;
	}
	
	public SpouseDTO getSpouse(long id) {

		Response response = baseTarget

				.path(String.valueOf(id))
				.request(MediaType.APPLICATION_JSON)
				.get();

		if(response.getStatus() != 200) {
			throw new RuntimeException();
		}

		SpouseDTO dto = (SpouseDTO) response.readEntity(SpouseDTO.class);

		return dto;
	}

}
