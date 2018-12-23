package com.partsshop.rest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.partsshop.rest.dto.ActivationType;



@Document(collection = "user_activation")
public class UserActivation {
	@Id
	private String id;
	private String userEmail;
	private Long creationDate;
    private ActivationType activationType ; 
	


	public UserActivation() {}
	
	public UserActivation(String userEmail,String id,Long creationDate,ActivationType activationType) {
		
		this.userEmail =userEmail;
		this.id =id;
		this.creationDate =creationDate;
		this.activationType=activationType;
		
		
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}
	
	
	public void setActivationType(ActivationType activationType) {
		this.activationType = activationType;
	}

	public ActivationType getActivationType() {
	    return activationType;
	}
	
	
	

}
