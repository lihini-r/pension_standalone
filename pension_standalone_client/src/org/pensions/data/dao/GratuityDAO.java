package org.pensions.data.dao;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.pensions.data.CallManager;
import org.pensions.model.GratuityDTO;
import org.pensions.model.lists.DashboardList;
import org.pensions.model.lists.GratuityList;
import org.pensions.model.lists.GratuityLogList;
import org.pensions.model.lists.History;
import org.pensions.view.util.NotificationManager;

public class GratuityDAO {
	WebTarget baseTarget = null;

	public GratuityDAO(long pensionerId) {
		baseTarget = CallManager.getRootTarget()
				.path("pensioners")
				.path(String.valueOf(pensionerId))
				.path("gratuities");
	}

	public GratuityDTO getGratuity(long id) {

		GratuityDTO dto = null;
		Response response = baseTarget
				.path(String.valueOf(id))
				.request(MediaType.APPLICATION_JSON)
				.get();

		if(response.getStatus() != 200) {
			NotificationManager.info("Thih", "this is the message");
			return dto;
		}
       
		dto = (GratuityDTO) response.readEntity(GratuityDTO.class);
		return dto;
	}
	
	public boolean updateGratuityStatus(String state, long id) {

		Response response = baseTarget
				.path(String.valueOf(id))
				.path("state")
				.queryParam("state", state)
				.request(MediaType.APPLICATION_JSON)
				.get();
		

		if(response.getStatus() != 200) {
			NotificationManager.info("Not working", "Not working");
		}

		return true;
	}
	
	public GratuityLogList getgratuityHistory() {

		Response response = baseTarget
				.path("history")
				.request(MediaType.APPLICATION_JSON)
				.get();

		GratuityLogList historyList = null;

		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			return historyList;
		}

		historyList = (GratuityLogList) response.readEntity(GratuityLogList.class);
		return historyList;
	}
	
	
	public GratuityList getGratuityList(int state) {

		Response response = baseTarget
				.queryParam("state" , "search")
				.queryParam("status", state)
				.request(MediaType.APPLICATION_JSON)
				.get();

		GratuityList gratuityList = new GratuityList();

		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			return gratuityList;
		}

		gratuityList = (GratuityList) response.readEntity(GratuityList.class);
		return gratuityList;
	}
	

}
