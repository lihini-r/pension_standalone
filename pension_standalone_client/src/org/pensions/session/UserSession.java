package org.pensions.session;

import javafx.stage.Stage;

public enum UserSession {

	INSTANCE;

	private String userName;
	private String sessionId;
	private String role;
	private String note;
	private Stage stage = null;

	public Stage getStage() {
		if(stage == null) {
			stage = new Stage();
		}
		return stage;
	}

	public String getUserName() {
		return userName;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setSession(String userName, String sessionId, String role) {
		this.sessionId = sessionId;
		this.userName = userName;
		this.setRole(role);
	}


}
