package org.pensions.data.dao;

import javax.management.RuntimeErrorException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.pensions.data.CallManager;
import org.pensions.model.Percentage;

public class PercentageDAO {
	
private static final Error Exception = null;
WebTarget baseTarget = null;
	
	public PercentageDAO() {
		baseTarget = CallManager.getRootTarget();
	}
	
	public Percentage getPercentage(int years, int months, double salary) {
		Response response = baseTarget
				.path("pensions")
				.path("percentages")
				.queryParam("years", years)
				.queryParam("months", months)
				.queryParam("salary", salary)
				.request(MediaType.APPLICATION_JSON)
				.get();
		
		if(response.getStatus() != 200) {
			throw new RuntimeErrorException(Exception);
		}
		
		Percentage p = (Percentage) response.readEntity(Percentage.class);
		
		return p;
		
	}

}
