package org.pensions.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Hetoes {
	
	private String rel;
	private String url;
	
	public Hetoes() {
		// TODO Auto-generated constructor stub
	}
	

	public Hetoes(String rel, String url) {
		super();
		this.rel = rel;
		this.url = url;
	}


	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
