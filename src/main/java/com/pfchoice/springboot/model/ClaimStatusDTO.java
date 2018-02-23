package com.pfchoice.springboot.model;

import java.io.Serializable;


import org.springframework.stereotype.Component;




@Component
public class ClaimStatusDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Integer statusId;

	
	
	private String statusDescription;

	
	
	private String roles;


	public Integer getStatusId() {
		return statusId;
	}


	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}


	public String getStatusDescription() {
		return statusDescription;
	}


	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}


	public String getRoles() {
		return roles;
	}


	public void setRoles(String roles) {
		this.roles = roles;
	}
	
}
