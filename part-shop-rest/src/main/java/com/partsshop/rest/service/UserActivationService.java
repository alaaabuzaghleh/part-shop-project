package com.partsshop.rest.service;


import com.partsshop.rest.dto.UserActivationRest;

public interface UserActivationService {
	
	
	UserActivationRest findByEmail(String email);

	UserActivationRest saveActivationCode(UserActivationRest userActivationRest);
	
	UserActivationRest findByCode(String code);
	
	void removeActivationCode(UserActivationRest rest) ; 
	
	
	
	

}
