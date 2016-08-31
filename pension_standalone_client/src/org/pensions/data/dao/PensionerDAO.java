package org.pensions.data.dao;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.controlsfx.control.Notifications;
import org.pensions.data.CallManager;
import org.pensions.model.DeceasedPensionerDTO;
import org.pensions.model.Message;
import org.pensions.model.PensionerDTO;
import org.pensions.model.RegularPensionerDTO;
import org.pensions.model.internal.SearchParams;
import org.pensions.model.lists.PensionerList;
import org.pensions.model.lists.PensionerOverviewList;
import org.pensions.model.lists.PensionersList;
import org.pensions.view.util.NotificationManager;

public class PensionerDAO {

	WebTarget baseTarget = null;

	public PensionerDAO() {
		baseTarget = CallManager.getRootTarget().path("pensioners");
	}

	public long addPensioner(DeceasedPensionerDTO dao) {

		Response response = baseTarget
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(dao, MediaType.APPLICATION_JSON));

		if(response.getStatus() != 200) {
			Notifications.create()
			.title("Pensioner Information Registration Unsuccessfull")
			.text("Please ensure that all the details are entered correctly")
			.showInformation();
			return -1;
		}


		Message m = (Message)response.readEntity(Message.class);
		System.out.println(m.getCode());
		System.out.println(m.getMessage());

		return (long)Integer.parseInt(m.getMessage());

	}

	public PensionerOverviewList getPensioners(String state) {

		PensionerOverviewList list = null;

		Response response = baseTarget
				.queryParam("state", state)
				.request(MediaType.APPLICATION_JSON)
				.get();

		if(response.getStatus() != 200) {
			System.out.println("Not working");
		}

		list = (PensionerOverviewList) response.readEntity(PensionerOverviewList.class);

		return list;
	}

	public PensionerDTO getPensioner(long pensionerNumber) {

		PensionerDTO dto = null;

		Response response = baseTarget
				.path(String.valueOf(pensionerNumber))
				.request(MediaType.APPLICATION_JSON)
				.get();

		if(response.getStatus() != 200) {
			System.out.println("Not working");
		}
		try {
			dto = (PensionerDTO) response.readEntity(PensionerDTO.class);
		}catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	public boolean updatePensionerStatus(boolean verify, long pensionerNo) {

		Response response = baseTarget
				.path(String.valueOf(pensionerNo))
				.path("state")
				.queryParam("verify", verify ? "true":"false")
				.request(MediaType.APPLICATION_JSON)
				.get();

		if(response.getStatus() != 200) {
			System.out.println("Not working");
		}

		return true;
	}

	public PensionersList getPensionersList(String state) {

		PensionersList list = null;

		Response response = baseTarget
				.queryParam("state", state)
				.request(MediaType.APPLICATION_JSON)
				.get();

		if(response.getStatus() != 200) {
			System.out.println("Not working");
		}

		list = (PensionersList) response.readEntity(PensionersList.class);

		return list;
	}
	
	
	/*public boolean updatePensionerStatusByRefNo(boolean verify, long refNo) {

		Response response = baseTarget
				.path(String.valueOf(refNo))
				.path("statePostal")
				.queryParam("verify", verify ? "true":"false")
				.request(MediaType.APPLICATION_JSON)
				.get();

		if(response.getStatus() != 200) {
			System.out.println("Not working");
		}

		return true;

	}*/

    /**
     * Search pensioner details
     * @param params
     * @return
     */
	public PensionerList searchPensioners(SearchParams params) {

        Response response = baseTarget
                .queryParam("state","search")
                .queryParam("name", params.getName())
                .queryParam("nic", params.getNic())
                .queryParam("refno", params.getRefNumber())
                .queryParam("wopnumber", params.getWopNumber())
                .request(MediaType.APPLICATION_JSON)
                .get();

        if(response.getStatus() != Response.Status.OK.getStatusCode()) {
            NotificationManager.info("No match found!", "No matched found for the search criteria");
        }

        PensionerList list = (PensionerList) response.readEntity(PensionerList.class);

        return list;
    }
	
	public PensionerList searchPensioners(String state) {
		PensionerList list = new PensionerList();

        Response response = baseTarget
                .queryParam("state",state)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if(response.getStatus() != Response.Status.OK.getStatusCode()) {
        	return list;
        }

		list = (PensionerList) response.readEntity(PensionerList.class);

        return list;
    }
	/**
     
     * @param params
     * @return
     */
	public boolean updatePensionerApproveStates(String status, long refNo){
		Response response = baseTarget
				.path(String.valueOf(refNo))
				.path("statePostal")
				.queryParam("state", status)
				.request(MediaType.APPLICATION_JSON)
				.get();

		if(response.getStatus() != 200) {
			System.out.println("Not working");
		}

		return true;
   }
	/**
	 * get regular pensioner details
	 * @param pensionerNumber
	 * @return
	 */
	
	public RegularPensionerDTO getRegularPensioner(long pensionerNumber) {

		RegularPensionerDTO dto = null;

		Response response = baseTarget
				.path(String.valueOf(pensionerNumber))
				.request(MediaType.APPLICATION_JSON)
				.get();

		if(response.getStatus() != 200) {
			System.out.println("Not working");
		}
		try {
			dto = (RegularPensionerDTO) response.readEntity(RegularPensionerDTO.class);
		}catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}
}	
