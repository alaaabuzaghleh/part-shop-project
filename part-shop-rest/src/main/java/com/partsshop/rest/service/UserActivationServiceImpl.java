package com.partsshop.rest.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partsshop.rest.dto.ActivationType;
import com.partsshop.rest.dto.UserActivationRest;
import com.partsshop.rest.model.UserActivation;
import com.partsshop.rest.repo.UserActivationRepo;

@Service
public class UserActivationServiceImpl implements UserActivationService{

	private Logger log =LoggerFactory.getLogger(UserActivationServiceImpl.class);
	
	@Autowired
	private UserActivationRepo repo;
	
	
	@Override
	public UserActivationRest saveActivationCode(UserActivationRest userActivationRest) {
		
		UserActivation userActivation=new UserActivation();
		
		userActivation.setUserEmail(userActivationRest.getUserEmail());
		userActivation.setId(userActivationRest.getCode());
		userActivation.setCreationDate(userActivationRest.getCreationDate());
		userActivation.setActivationType(userActivation.getActivationType());
		
		repo.save(userActivation);
		
		return userActivationRest;
		
	}
	
	@Override
	public UserActivationRest findByEmail(String email) {
	
		
		return null;
	}

	@Override
	public UserActivationRest findByCode(String code) {
		UserActivation ac =  this.repo.findById(code).orElse(null) ; 
		if(ac == null) {
			return null ; 
		}
		UserActivationRest rest = new UserActivationRest() ; 
		rest.setActivationType(ac.getActivationType() == ActivationType.REGISTER ? ActivationType.REGISTER: ActivationType.FORGET_PASSWORD); 
		rest.setCode(ac.getId());
		rest.setCreationDate(ac.getCreationDate()); 
		rest.setUserEmail(ac.getUserEmail());	
		return rest;
	}
	
   @Override
   public void removeActivationCode(UserActivationRest rest) {
	   UserActivation activation = this.repo.findById(rest.getCode()).orElse(null) ; 
	   if(activation!=null) {
	   this.repo.delete(activation);
	   }
   }
	
	
	
	
	

}
