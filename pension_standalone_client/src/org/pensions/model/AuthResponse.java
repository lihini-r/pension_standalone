package org.pensions.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthResponse {

	private String username;
	private String role;
	private String sessionId;
	
	public AuthResponse() {
	}

	
	
	public AuthResponse(String username, String role, String sessionId) {
		super();
		this.username = username;
		this.role = role;
		this.sessionId = sessionId;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
