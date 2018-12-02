package com.partsshop.rest.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.partsshop.rest.model.Shops;

public interface ShopsRepo extends MongoRepository<Shops, String>{
	
	@Query("{ $or: [ { 'name': '?0' }, { 'nameAr': '?0' } ] }")
	public List<Shops> findByNameOrNameAr(String name);

}
