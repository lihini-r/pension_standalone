package org.pensions.data.dao;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.pensions.data.CallManager;
import org.pensions.model.PensionPointDTO;
import org.pensions.view.util.NotificationManager;

public class PensionPointDAO {
	WebTarget baseTarget = null;
	
	public PensionPointDAO() {
		baseTarget = CallManager.getRootTarget();
				
	}
	
	public PensionPointDTO getPensionPoint(String penPointId) {

		PensionPointDTO dto = null;
		Response response = baseTarget
				.path("pensionpoints")
				.path(penPointId)
				.request(MediaType.APPLICATION_JSON)
				.get();

		if(response.getStatus() != 200) {
			NotificationManager.info("Error", "this is the message");
			return dto;
		}

		dto = (PensionPointDTO) response.readEntity(PensionPointDTO.class);
		return dto;
	}

}
