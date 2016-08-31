package org.pensions.enumarations;

public enum WindowType {

	PD5("org/pensions/view/pd5/PD5.fxml"), PD3("org/pensions/view/pd5/PD3.fxml");
	
	private String url;
	
	private WindowType(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
}
