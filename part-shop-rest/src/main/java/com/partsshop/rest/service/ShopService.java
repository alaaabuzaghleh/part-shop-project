

package com.partsshop.rest.service;

import java.util.List;
import com.partsshop.rest.dto.ShopsRest;

public interface ShopService {
	
	
	ShopsRest saveShop(ShopsRest shopsRest );
	ShopsRest update(ShopsRest shopsRest);
	ShopsRest getShopById(String id);
	List<ShopsRest> getShops();
	List<ShopsRest> getShopsByName(String name);
	void deleteShop(String id);
	
}
