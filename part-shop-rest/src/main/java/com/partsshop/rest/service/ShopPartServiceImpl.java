package com.partsshop.rest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partsshop.rest.dto.CarRest;
import com.partsshop.rest.dto.ShopsPartsRest;
import com.partsshop.rest.dto.ShopsRest;
import com.partsshop.rest.model.Car;
import com.partsshop.rest.model.PartsCategory;
import com.partsshop.rest.model.Shops;
import com.partsshop.rest.model.ShopsParts;
import com.partsshop.rest.repo.ShopsPartsRepo;
import com.partsshop.rest.repo.ShopsRepo;


@Service
public class ShopPartServiceImpl implements ShopPartService {

	private Logger log =LoggerFactory.getLogger(ShopPartServiceImpl.class);
	
	@Autowired
	private ShopsPartsRepo repo;
	
	
	@Override
	public ShopsPartsRest saveShopPart(ShopsPartsRest shopsPartsRest) {
		
		ShopsParts shopsParts = new ShopsParts();
		
		shopsParts.setPart(shopsPartsRest.getPart());
		shopsParts.setShop(shopsPartsRest.getShop());
		shopsParts.setCar(shopsPartsRest.getCar());
		shopsParts.setYear(shopsPartsRest.getYear());
		shopsParts.setCurrency(shopsPartsRest.getCurrency());
		shopsParts.setPrice(shopsPartsRest.getPrice());
		
        repo.save(shopsParts);
		
		if (shopsParts.getId() == null) {
			return null;
		} else {
			shopsPartsRest.setId(shopsParts.getId());
			return shopsPartsRest;
		}
		
		
		
	}

	@Override
	public ShopsPartsRest update(ShopsPartsRest shopsPartsRest) {
		try {
			ShopsParts shopsParts = new ShopsParts();

			shopsParts.setId(shopsPartsRest.getId());
			shopsParts.setPart(shopsPartsRest.getPart());
			shopsParts.setShop(shopsPartsRest.getShop());
			shopsParts.setCar(shopsPartsRest.getCar());
			shopsParts.setYear(shopsPartsRest.getYear());
			shopsParts.setCurrency(shopsPartsRest.getCurrency());
			shopsParts.setPrice(shopsPartsRest.getPrice());

			repo.save(shopsParts);
			return shopsPartsRest;
		} catch (Exception exp) {
			log.error(exp.getMessage());
			return null;
		}
	}

	@Override
	public ShopsPartsRest getShopPartById(String id) { //problem

		ShopsParts shopsParts = repo.findById(id).orElse(null); // problem in findById
		if (shopsParts != null) {
			ShopsPartsRest shopsPartsRest = new ShopsPartsRest();

			shopsPartsRest.setId(shopsParts.getId());
			shopsPartsRest.setPart(shopsParts.getPart());
			shopsPartsRest.setShop(shopsParts.getShop());
			shopsPartsRest.setCar(shopsParts.getCar());
			shopsPartsRest.setYear(shopsParts.getYear());
			shopsPartsRest.setCurrency(shopsParts.getCurrency());
			shopsPartsRest.setPrice(shopsParts.getPrice());

			return shopsPartsRest;
		} else {
			return null;
		}
	}

	@Override
	public List<ShopsPartsRest> getShopsParts() {
		List<ShopsParts> shopsParts = repo.findAll();

		return shopsParts.stream().map(shopPart -> new ShopsPartsRest(shopPart.getId(), shopPart.getPart(),shopPart.getShop()
				,shopPart.getCar(),shopPart.getYear(),shopPart.getCurrency(),shopPart.getPrice()))
				.collect(Collectors.toList());
	}

	@Override
	public List<ShopsPartsRest> getCarsByCriteria( String make,  String model, String partName,Integer year) {
         
		List<ShopsParts> shopsParts=repo.findCarsByCriteria(make, model, partName, year);
		
		//Note:not understand why give me error if i remove getCurrency and getPrice!!!
		return shopsParts.stream().map(shopPart -> new ShopsPartsRest(shopPart.getId(), shopPart.getPart(),shopPart.getShop()
				,shopPart.getCar(),shopPart.getYear(),shopPart.getCurrency(),shopPart.getPrice()))
				.collect(Collectors.toList());
		}
	

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

}
