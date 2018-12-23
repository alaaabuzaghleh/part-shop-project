package com.partsshop.rest.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.partsshop.rest.model.UserActivation;

public interface UserActivationRepo extends MongoRepository<UserActivation, String>{
	
	 UserActivation findByUserEmail(String email);
	
	 
	 

}
