package org.pensions.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserPrivilege {
	
	private int accecptingState;
    private int correctintState;
    private int pendingListState;
    private int rejectedListState;
    private int rejectingState;
    private String userRole;
    
    public UserPrivilege() {
		// TODO Auto-generated constructor stub
	}

	public int getAccecptingState() {
		return accecptingState;
	}

	public void setAccecptingState(int accecptingState) {
		this.accecptingState = accecptingState;
	}

	public int getCorrectintState() {
		return correctintState;
	}

	public void setCorrectintState(int correctintState) {
		this.correctintState = correctintState;
	}

	public int getPendingListState() {
		return pendingListState;
	}

	public void setPendingListState(int pendingListState) {
		this.pendingListState = pendingListState;
	}

	public int getRejectedListState() {
		return rejectedListState;
	}

	public void setRejectedListState(int rejectedListState) {
		this.rejectedListState = rejectedListState;
	}

	public int getRejectingState() {
		return rejectingState;
	}

	public void setRejectingState(int rejectingState) {
		this.rejectingState = rejectingState;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
    
}
