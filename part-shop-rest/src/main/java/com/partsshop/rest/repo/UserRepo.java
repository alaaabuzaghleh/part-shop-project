package com.partsshop.rest.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.partsshop.rest.model.User;

public interface UserRepo extends MongoRepository<User, String> {
	
	public Optional<User> findByEmail(String email) ; 

}
