package com.partsshop.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.partsshop.web.dto.JwtAuthenticationResponse;
import com.partsshop.web.dto.UserSignInRest;

@Component
public class UserService {
	@Value("${partshop.rest.endpint}")
	private String partShopRest ; 
	
	@Autowired
	private RestTemplate restTemplate ; 
	
	public ResponseEntity<?> getUserFromRest(String email, String password) {
		UserSignInRest signUpObj = new UserSignInRest() ; 
		signUpObj.setUserEmail(email);
		signUpObj.setPassword(password);
		ResponseEntity<?> response = this.restTemplate.postForEntity(String.format("%s%s", this.partShopRest, "/auth/signin"),
				signUpObj, 
				JwtAuthenticationResponse.class); 
		return response ;
	}
	
	public List<String> getUserRoles(String jwtToken){
		ResponseEntity<T>
		return null ; 
	}

}
