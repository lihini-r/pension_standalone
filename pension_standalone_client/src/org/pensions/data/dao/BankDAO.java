package org.pensions.data.dao;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.pensions.data.CallManager;
import org.pensions.model.BranchDTO;
import org.pensions.model.lists.BankList;
import org.pensions.view.util.NotificationManager;

public class BankDAO {

	WebTarget baseTarget = null;

	public BankDAO() {
		baseTarget = CallManager.getRootTarget().path("banks");
	}

	/**
	 * Check for updates of bank and banch
	 * @param bankCount
	 * @param branchCount
	 * @return
	 */
	public BankList getUpdates(int bankCount, int branchCount) {
		BankList list = null;
		Response respons = baseTarget.path("updates")
				.queryParam("bankCount", bankCount)
				.queryParam("branchCount", branchCount)
				.request(MediaType.APPLICATION_JSON)
				.get();
		
		if(respons.getStatus() == 200) {
			list = (BankList) respons.readEntity(BankList.class);
		}
		
		return list;
	}
	
	public BranchDTO getBank(int branchId) {
		BranchDTO dto = null;
		Response response = baseTarget.path("1")
				.path("branches")
				.path(String.valueOf(branchId))
				.request(MediaType.APPLICATION_JSON)
				.get();
		
		if(response.getStatus() != Status.OK.getStatusCode()) {
			NotificationManager.info("No bank detail found", "No bank detail found for the provided id");
			return dto;
		}
		
		return (BranchDTO) response.readEntity(BranchDTO.class);
	}
}
