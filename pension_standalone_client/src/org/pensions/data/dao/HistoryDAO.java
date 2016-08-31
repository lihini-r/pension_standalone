package org.pensions.data.dao;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.pensions.data.CallManager;
import org.pensions.model.lists.History;

public class HistoryDAO {
	
private WebTarget baseTarget = null;
	
	public HistoryDAO(long pensionerNumber , long pensionNumber) {
		baseTarget = CallManager.getRootTarget()
				.path("pensioners")
				.path(String.valueOf(pensionerNumber))
				.path("pensions")
				.path(String.valueOf(pensionNumber));
    }

	/**
	 * Get history of the selected pension
	 * @return
     */
	public History getHistory() {

		Response response = baseTarget
				.path("history")
				.request(MediaType.APPLICATION_JSON)
				.get();

		History historyList = null;

		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			return historyList;
		}

		historyList = (History) response.readEntity(History.class);
		return historyList;
	}

}
