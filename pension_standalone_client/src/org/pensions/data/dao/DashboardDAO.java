package org.pensions.data.dao;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.pensions.data.CallManager;
import org.pensions.model.lists.DashboardList;


public class DashboardDAO {
	
	private WebTarget baseTarget = null;
	
	public DashboardDAO() {
		baseTarget = CallManager.getRootTarget();
				
	}
	
	public DashboardList getDashboardCount() {

		Response response = baseTarget
				.path("dashboard")
				.request(MediaType.APPLICATION_JSON)
				.get();

		DashboardList dashboardList = new DashboardList();

		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			return dashboardList;
		}

		dashboardList = (DashboardList) response.readEntity(DashboardList.class);
		return dashboardList;
	}

}
