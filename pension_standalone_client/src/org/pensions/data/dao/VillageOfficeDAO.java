package org.pensions.data.dao;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.pensions.data.CallManager;
import org.pensions.model.VillageOffice;

public class VillageOfficeDAO {
	WebTarget baseTarget = null;

	public VillageOfficeDAO() {
		baseTarget = CallManager.getRootTarget().path("supportive").path("vo");
	}
	
	public VillageOffice getVillageOffice(Long villageOfficeId) {
        Response response = baseTarget
                .path(String.valueOf(villageOfficeId))
                .request(MediaType.APPLICATION_JSON)
                .get();

        if(response.getStatus() != Response.Status.OK.getStatusCode()) {
            return null;
        }

        return (VillageOffice) response.readEntity(VillageOffice.class);
    }
}
