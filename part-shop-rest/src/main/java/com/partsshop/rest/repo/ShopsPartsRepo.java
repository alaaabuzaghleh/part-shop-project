package com.partsshop.rest.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.partsshop.rest.model.ShopsParts;

public interface ShopsPartsRepo extends MongoRepository<ShopsParts,String>{
	
	@Query ("{ $and: ["
						+ "{ $or : [ { 'car.make' : '?0' }, { 'car.makeAr' : '?0' } ] },"
						+ "{ $or : [ { 'car.model' : '?1' },{ 'car.modelAr': '?1' } ] },"
						+ "{ $or : [ { 'part.name' : '?2' },{ 'part.nameAr': '?2' } ] }, "
						+ "{ 'year' : ?3 }"
					+"]"
						
			+"}")		
	public List<ShopsParts> findCarsByCriteria( String make, String model, String partName,Integer year);

}
