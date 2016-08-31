package org.pensions.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DashboardDTO {
	
	private String rel;
	private int url;
	
	
	public DashboardDTO() {
		
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public int getUrl() {
		return url;
	}
	public void setUrl(int url) {
		this.url = url;
	}
	

}
