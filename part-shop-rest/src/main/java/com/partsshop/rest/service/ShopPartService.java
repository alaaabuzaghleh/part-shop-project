package com.partsshop.rest.service;

import java.util.List;

import com.partsshop.rest.dto.ShopsPartsRest;

public interface ShopPartService {
	
	ShopsPartsRest saveShopPart(ShopsPartsRest shopsPartsRest );
	ShopsPartsRest update(ShopsPartsRest shopsPartsRest);
	ShopsPartsRest getShopPartById(String string); // problem
	List<ShopsPartsRest> getShopsParts();
	List<ShopsPartsRest> getCarsByCriteria( String make,  String model, String partName,Integer year);
	void delete(String id);

}
