package com.partsshop.rest.dto;



public class UserActivationRest {

	
	private String userEmail;
	private String code;
	private Long creationDate;
	private ActivationType activationType;
	
	
	public UserActivationRest() {}
	
	public UserActivationRest(String userEmail,String code,Long creationDate,ActivationType activationType) {
		
		this.userEmail =userEmail;
		this.code =code;
		this.creationDate =creationDate;
		this.activationType=activationType;
		
		
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}
	
	public ActivationType getActivationType() {
	    return activationType;
	}

	public void setActivationType(ActivationType activationType) {
		this.activationType = activationType;
	}
	
	
	
	
	
	
}
