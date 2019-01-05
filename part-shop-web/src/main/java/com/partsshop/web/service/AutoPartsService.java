package com.partsshop.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.partsshop.web.security.UserPrincipal;

@Component
public class AutoPartsService {

	@Value("${partshop.rest.endpint}")
	private String partShopRest ; 
	
	@Autowired
	private RestTemplate restTemplate ; 
	
	public ResponseEntity<String> getPartsFromRest() {
		UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ; 
		MultiValueMap<String, String> header = new HttpHeaders() ; 
		header.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		header.set("Accept-Language", "en");//LocaleContextHolder.getLocale().getLanguage());
		header.set(RestHeaderConstant.AUTHORIZATION, RestHeaderConstant.AUTHORIZATION_BEARER+user.getToken());
		HttpEntity<?> requestEntity = new HttpEntity<>(header); 
		ResponseEntity<String> res = null ; 
		res =  this.restTemplate.exchange(String.format("%s%s", this.partShopRest, "/parts-categories/"), HttpMethod.GET, requestEntity, String.class);
		return res ; 
	}
	
	
}
