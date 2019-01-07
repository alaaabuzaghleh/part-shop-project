package com.partsshop.rest.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.partsshop.rest.model.Car;

public interface CarRepo extends  PagingAndSortingRepository<Car, String>{
	@Query("{ $or: [ { 'make': '?0' }, { 'makeAr': '?0' } ] }")
	public List<Car> findByMakeOrMakeAr(String mk); 
	
	@Query(value="{ $or: [ { 'make': '?0' }, { 'makeAr': '?0' } ] }" , count=true)
	public Long countByMake(String make);
	
	
	

}
