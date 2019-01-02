package com.partsshop.web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.partsshop.web.dto.UserSignInRest;

@Component
public class UserService {
	@Value("${partshop.rest.endpint}")
	private String partShopRest ; 
	
	@Autowired
	private RestTemplate restTemplate ; 
	
	public ResponseEntity<String> getUserFromRest(String email, String password) {
		try {
		System.out.println(restTemplate + " " + email + password);
		UserSignInRest userSignUp = new UserSignInRest() ; 
		userSignUp.setUserEmail(email);
		userSignUp.setPassword(password);
		
		MultiValueMap<String, String> header = new HttpHeaders() ; 
		header.set(RestHeaderConstant.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		header.set(RestHeaderConstant.ACCEPT_LANGUAGE, "en");//LocaleContextHolder.getLocale().getLanguage());
		HttpEntity<?> requestEntity = new HttpEntity<>(userSignUp, header); 
		ResponseEntity<String> res = null ; 
		res =  this.restTemplate.exchange(String.format("%s%s", this.partShopRest, "/auth/signin"), HttpMethod.POST, requestEntity, String.class);
		return res ;
		}catch(Exception exp) {
			exp.printStackTrace();
		}
		return null;
	}
	
	public ResponseEntity<String> getUserRoles(String jwtToken){		
		MultiValueMap<String, String> header = new HttpHeaders() ; 
		header.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		header.set("Accept-Language", "en");//LocaleContextHolder.getLocale().getLanguage());
		header.set(RestHeaderConstant.AUTHORIZATION, RestHeaderConstant.AUTHORIZATION_BEARER+jwtToken);
		HttpEntity<?> requestEntity = new HttpEntity<>(header); 
		ResponseEntity<String> res = null ; 
		res =  this.restTemplate.exchange(String.format("%s%s", this.partShopRest, "/users/roles"), HttpMethod.GET, requestEntity, String.class);
		return res ; 
	}

}
