package org.pensions.data.dao;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.pensions.data.CallManager;
import org.pensions.model.lists.ExtraPaymentList;

public class ExtraPaymentDAO {

	private WebTarget baseTarget = null;
	
	public ExtraPaymentDAO(long pensionerNumber , long pensionNumber) {
        baseTarget = CallManager.getRootTarget().path("pensioners").path(String.valueOf(pensionerNumber)).path("pensions").path(String.valueOf(pensionNumber));
    }
	/**
	 * list extra payment details
	 * @return
	 */
	public ExtraPaymentList getPayments() {

		Response response = baseTarget
				.path("extras")
				.request(MediaType.APPLICATION_JSON)
				.get();

		ExtraPaymentList paymentList = new ExtraPaymentList();

		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			return paymentList;
		}

		paymentList = (ExtraPaymentList) response.readEntity(ExtraPaymentList.class);
		return paymentList;
	}

	
}
