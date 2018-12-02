package com.partsshop.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.partsshop.rest.dto.ShopsRest;
import com.partsshop.rest.model.Shops;
import com.partsshop.rest.repo.ShopsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ShopServiceImpl implements ShopService {

	private Logger log =LoggerFactory.getLogger(ShopServiceImpl.class);
	
	
	@Autowired
	private ShopsRepo repo;
	
	
	@Override
	public ShopsRest saveShop(ShopsRest shopsRest) {
		
		Shops shop= new Shops();
		
		shop.setName(shopsRest.getName());
		shop.setNameAr(shopsRest.getNameAr());
		shop.setAddress(shopsRest.getAddress());
		shop.setAddressAr(shopsRest.getAddressAr());
		shop.setGeoLocation(shopsRest.getGeoLocation());
		
		repo.save(shop);
		
		if (shop.getId() == null) {
			return null;
		} else {
			shopsRest.setId(shop.getId());
			return shopsRest;
		}
		
	}

	@Override
	public ShopsRest update(ShopsRest shopsRest) {
		try {
			Shops shop= new Shops();

			shop.setId(shopsRest.getId());
			shop.setName(shopsRest.getName());
			shop.setNameAr(shopsRest.getNameAr());
			shop.setAddress(shopsRest.getAddress());
			shop.setAddressAr(shopsRest.getAddressAr());
			shop.setGeoLocation(shopsRest.getGeoLocation());

			repo.save(shop);
			return shopsRest;
		} catch (Exception exp) {
			log.error(exp.getMessage());
			return null;
		}
	}

	@Override
	public ShopsRest getShopById(String id) {
		
		Shops shop = repo.findById(id).orElse(null);
		if (shop != null) {
			ShopsRest shopsRest = new ShopsRest();

			shopsRest.setId(shop.getId());
			shopsRest.setName(shop.getName());
			shopsRest.setNameAr(shop.getNameAr());
			shopsRest.setAddress(shop.getAddress());
			shopsRest.setAddressAr(shop.getAddressAr());
			shopsRest.setGeoLocation(shop.getGeoLocation());
		

			return shopsRest;
		} else {
			return null;
		}
	}

	@Override
	public List<ShopsRest> getShops() {

		List<Shops> shops = repo.findAll();

		return shops.stream().map(shop -> new ShopsRest(shop.getId(), shop.getName(),shop.getNameAr(),shop.getAddress(),shop.getAddressAr(),shop.getGeoLocation()))
				.collect(Collectors.toList());
	}

	@Override
	public List<ShopsRest> getShopsByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteShop(String id) {
		// TODO Auto-generated method stub

	}

}
