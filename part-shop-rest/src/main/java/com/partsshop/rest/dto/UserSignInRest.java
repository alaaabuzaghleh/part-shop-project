package com.partsshop.rest.dto;

import javax.validation.constraints.NotBlank;

public class UserSignInRest {
	
	@NotBlank
    private String userEmail;

    @NotBlank
    private String password;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    

}
