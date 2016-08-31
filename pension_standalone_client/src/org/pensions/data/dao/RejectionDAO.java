package org.pensions.data.dao;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.controlsfx.control.Notifications;
import org.pensions.data.CallManager;
import org.pensions.model.RejectionDTO;
import org.pensions.model.lists.RejectionReasonList;

public class RejectionDAO {
	WebTarget baseTarget = null;

	public RejectionDAO() {
		baseTarget = CallManager.getRootTarget().path("rejections");
	}

	/**
	 * Get all rejection reasons based on a type
	 * @param type
	 * @return
	 */
	public RejectionReasonList getReasons(String type) {

		RejectionReasonList list = null;

		Response response = baseTarget
				.path("reasons")
				.queryParam("type", type)
				.request(MediaType.APPLICATION_JSON)
				.get();

		if(response.getStatus() != 200) {
			System.out.println("Not working");
		}

		list = (RejectionReasonList) response.readEntity(RejectionReasonList.class);
		
		return list;
	}

	/**
	 * Add new rejection to a pension
	 * @param dto
	 */
	public void addRejection(RejectionDTO dto) {
		
		Response response = baseTarget
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity( dto, MediaType.APPLICATION_JSON));

		if(response.getStatus() != Status.CREATED.getStatusCode()) {
			Notifications.create()
			.title("Pensioner Information Registration Unsuccessfull")
			.text("Please ensure that all the details are entered correctly")
			.showInformation();
		}
		
	}

}
