package org.pensions.data.dao;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.pensions.data.CallManager;
import org.pensions.model.PensionDTO;
import org.pensions.model.lists.PensionList;
import org.pensions.view.util.NotificationManager;

/**
 * Created by Dinesh Liyanage on 5/25/2016.
 */
public class PensionsDAO {

    private WebTarget baseTarget = null;

    public PensionsDAO(long pensionerNumber) {
        baseTarget = CallManager.getRootTarget().path("pensioners").path(String.valueOf(pensionerNumber)).path("pensions");
    }

    public PensionList getAllPensions() {
        Response response = baseTarget
                .request(MediaType.APPLICATION_JSON)
                .get();

        PensionList list = new PensionList();

        if(response.getStatus() != Response.Status.OK.getStatusCode()) {
            return list;
        }

        list= (PensionList) response.readEntity(PensionList.class);
        return  list;
    }

    /**
     * Get a pension using the pension number
     * @param pensionNumber
     * @return
     */
    public PensionDTO getPension(Long pensionNumber) {
        Response response = baseTarget
                .path(String.valueOf(pensionNumber))
                .request(MediaType.APPLICATION_JSON)
                .get();

        if(response.getStatus() != Response.Status.OK.getStatusCode()) {
            return null;
        }

        return (PensionDTO) response.readEntity(PensionDTO.class);
    }
    /**
     * update pension state
     * @param verify
     * @param pensionNo
     * @return
     */
    public boolean updatePensionStatus(boolean verify, long pensionNo) {

		Response response = baseTarget
				.path(String.valueOf(pensionNo))
				.path("state")
				.queryParam("verify", verify ? "true":"false")
				.request(MediaType.APPLICATION_JSON)
				.get();

		if(response.getStatus() != 200) {
			NotificationManager.info("Not working", "Not working");
		}

		return true;
	}
    
    
}
