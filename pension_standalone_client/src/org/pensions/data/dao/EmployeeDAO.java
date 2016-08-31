package org.pensions.data.dao;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.pensions.data.CallManager;
import org.pensions.model.AuthResponse;
import org.pensions.model.UserCredential;
import org.pensions.model.UserPrivilege;
import org.pensions.view.util.NotificationManager;

public class EmployeeDAO {

	WebTarget baseTarget = null;
	
	public EmployeeDAO() {
		baseTarget = CallManager.getRootTarget();
	}
	
	public AuthResponse authenticate(UserCredential credential) {
		
		Response response = baseTarget
				.path("employees")
				.path("authenticate")
				.path("login")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(credential,MediaType.APPLICATION_JSON));
		
		if (response.getStatus() != 200) {
			
		}
		
		return (AuthResponse)response.readEntity(AuthResponse.class);		
		
	}
	
	public void changePensionerState(boolean accecpt, long pensionerNumber) {
		
		Response response = baseTarget
				.path("pensioners")
				.path(String.valueOf(pensionerNumber))
				.path("state")
				.queryParam("verify", accecpt ? "true" : "false")
				.request(MediaType.APPLICATION_JSON)
				.get();
		
		String se = response.readEntity(String.class);
		System.out.println(se);
	}
	
	public UserPrivilege getPrivilages() {

		UserPrivilege dto = null;
		Response response = baseTarget
				.path("employees")
				.queryParam("privilege", "true")
				.request(MediaType.APPLICATION_JSON)
				.get();

		if(response.getStatus() != 200) {
			NotificationManager.info("Thih", "this is the message");
			return dto;
		}
       
		dto = (UserPrivilege) response.readEntity(UserPrivilege.class);
		return dto;
	}
	
}
