package org.pensions.controllers;

import org.pensions.data.dao.EmployeeDAO;
import org.pensions.model.AuthResponse;
import org.pensions.model.UserCredential;
import org.pensions.session.UserSession;

public class AuthenticationController {

	public boolean login(UserCredential credential) {
		EmployeeDAO dao = new EmployeeDAO();
		UserSession session = UserSession.INSTANCE;
		
		AuthResponse response = dao.authenticate(credential);
		session.setSession(response.getUsername(),
				response.getSessionId(),
				response.getRole());
		
		return true;
	}
	
}
