package com.partsshop.rest.service;

import java.util.List;

import com.partsshop.rest.dto.PartsCategoryRest;

public interface PartsCategoryService {
	
	
	PartsCategoryRest savePart(PartsCategoryRest partsCategoryRest);
	PartsCategoryRest updatePart(PartsCategoryRest partsCategoryRest);
	PartsCategoryRest getNameById(String id);
	List<PartsCategoryRest> getNames();
	void deleteName(String id);
	
	
	
	
	

}
