package com.partsshop.rest.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.partsshop.rest.model.Car;

public interface CarRepo extends MongoRepository<Car, String>{
	@Query("{ $or: [ { 'make': '?0' }, { 'makeAr': '?0' } ] }")
	public List<Car> findByMakeOrMakeAr(String mk); 

}
