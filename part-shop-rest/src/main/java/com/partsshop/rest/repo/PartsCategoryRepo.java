package com.partsshop.rest.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.partsshop.rest.model.PartsCategory;

public interface PartsCategoryRepo extends MongoRepository<PartsCategory, String> {
	
	
	@Query("{ $or: [ { 'name': '?0' }, { 'nameAr': '?0' } ] }")
	public List<PartsCategory> findByNameOrNameAr(String name);
	
	

}
