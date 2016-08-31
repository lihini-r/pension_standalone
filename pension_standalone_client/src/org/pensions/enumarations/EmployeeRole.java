package org.pensions.enumarations;

public enum EmployeeRole {

	ADMIN("admin"), DATA_ENTRY("DATA_ENTRY_OFFICER"), REGISTRATION_AD("REGISTRATION_AD"), POSTAL("POSTAL"), PRINT_LETTER("PRINT_LETTER"), SATHKARA_OFFICER("SATHKARA_OFFICER");
	
	private String role;
	
	EmployeeRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return this.role;
	}
}
