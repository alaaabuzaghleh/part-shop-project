package com.partsshop.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.partsshop.rest.dto.CarRest;
import com.partsshop.rest.dto.PartsCategoryRest;
import com.partsshop.rest.dto.ShopsPartsRest;
import com.partsshop.rest.dto.ShopsRest;
import com.partsshop.rest.model.Car;
import com.partsshop.rest.model.PartsCategory;
import com.partsshop.rest.model.Shops;
import com.partsshop.rest.model.ShopsParts;
import com.partsshop.rest.repo.CarRepo;
import com.partsshop.rest.repo.PartsCategoryRepo;
import com.partsshop.rest.repo.ShopsPartsRepo;
import com.partsshop.rest.repo.ShopsRepo;


@Service
public class ShopPartServiceImpl implements ShopPartService {

	private Logger log =LoggerFactory.getLogger(ShopPartServiceImpl.class);
	
	@Autowired
	private ShopsPartsRepo repo;
	
	@Autowired
	private PartsCategoryRepo partRepo;
	
	@Autowired
	private ShopsRepo shopRepo;
	
	@Autowired
	private CarRepo carRepo;
	
	
	@Override
	public ShopsPartsRest saveShopPart(ShopsPartsRest shopsPartsRest) {
		
		ShopsParts sp = new ShopsParts() ; 
		sp.setCarId(shopsPartsRest.getCar().getId());
		sp.setCurrency(shopsPartsRest.getCurrency());
		sp.setPartId(shopsPartsRest.getPart().getId());
		sp.setPrice(shopsPartsRest.getPrice());
		sp.setShopId(shopsPartsRest.getShop().getId());
		sp.setYear(shopsPartsRest.getYear());
		
        repo.save(sp);
		
		if (sp.getId() == null) {
			return null;
		} else {
			shopsPartsRest.setId(sp.getId());
			return shopsPartsRest;
		}
		
		
		
	}

	@Override
	public ShopsPartsRest update(ShopsPartsRest shopsPartsRest) {
		try {

			ShopsParts sp = new ShopsParts() ; 
			sp.setCarId(shopsPartsRest.getCar().getId());
			sp.setCurrency(shopsPartsRest.getCurrency());
			sp.setPartId(shopsPartsRest.getPart().getId());
			sp.setPrice(shopsPartsRest.getPrice());
			sp.setShopId(shopsPartsRest.getShop().getId());
			sp.setYear(shopsPartsRest.getYear());
			sp.setId(shopsPartsRest.getId());	
	        repo.save(sp);
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
			ShopsPartsRest rest = new ShopsPartsRest() ; 
			Car carModel = this.carRepo.findById(shopsParts.getCarId()).orElse(null);
			if(carModel!=null) {
				CarRest car = new CarRest() ; 
				car.setId(carModel.getId());
				car.setMake(carModel.getMake());
				car.setMakeAr(carModel.getMakeAr());
				car.setModel(carModel.getModel());
				car.setModelAr(carModel.getModelAr());
				rest.setCar(car);
			}
			
			rest.setCurrency(shopsParts.getCurrency());
			rest.setId(shopsParts.getId());
			PartsCategory partModel = this.partRepo.findById(shopsParts.getPartId()).orElse(null) ; 
			if(partModel!=null) {
				PartsCategoryRest part = new PartsCategoryRest() ; 
				part.setId(partModel.getId());
				part.setName(partModel.getName());
				part.setNameAr(partModel.getNameAr());
				rest.setPart(part);
			}
			
			rest.setPrice(shopsParts.getPrice());
			Shops shopModel = this.shopRepo.findById(shopsParts.getShopId()).orElse(null) ; 
			if(shopModel!=null) {
				ShopsRest shop = new ShopsRest() ; 
				shop.setAddress(shopModel.getAddress());
				shop.setAddressAr(shopModel.getAddressAr());
				shop.setGeoLocation(shopModel.getGeoLocation());
				shop.setId(shopModel.getId()) ; 
				shop.setName(shopModel.getName());
				shop.setNameAr(shopModel.getNameAr()); 
				rest.setShop(shop);
			}
			rest.setYear(shopsParts.getYear());
			return rest;
		} else {
			return null;
		}
	}

	@Override
	public List<ShopsPartsRest> getShopsParts(Integer start, Integer count, List<String> sortByList) {
		Sort sort = null ; 
		Pageable pageable = null ; 
		Page<ShopsParts> shopsPartsLs  = null ; 
		ArrayList<ShopsParts> allShopParts = null ; 
		List<ShopsPartsRest> listToReturn = new ArrayList<>() ; 
		if(sortByList!= null && sortByList.size()>0) {
			List<Order> orders = new ArrayList<>() ; 
			for(String s : sortByList) {
				String [] orderArr = s.split(" ") ; 
				Order element = new Order(orderArr[1].equalsIgnoreCase("DESC")? 
						Sort.Direction.DESC: Sort.Direction.ASC, orderArr[0]);
				orders.add(element);
			}		 
		    sort = new Sort(orders); 
		}
		if(start != null && count!= null ) {
			pageable = PageRequest.of(start, count);
			 shopsPartsLs =  repo.findAll(pageable);
			if(sort!= null) {
				pageable = PageRequest.of(start, count, sort);
				shopsPartsLs =  repo.findAll(pageable);
			}
		}else {
			allShopParts =   (ArrayList<ShopsParts>) repo.findAll();
		}
		
		
		
		for(ShopsParts  shopsParts : shopsPartsLs==null? allShopParts: shopsPartsLs) {
			ShopsPartsRest rest = new ShopsPartsRest() ; 
			Car carModel = this.carRepo.findById(shopsParts.getCarId()).orElse(null);
			if(carModel!=null) {
				CarRest car = new CarRest() ; 
				car.setId(carModel.getId());
				car.setMake(carModel.getMake());
				car.setMakeAr(carModel.getMakeAr());
				car.setModel(carModel.getModel());
				car.setModelAr(carModel.getModelAr());
				rest.setCar(car);
			}
			
			rest.setCurrency(shopsParts.getCurrency());
			rest.setId(shopsParts.getId());
			PartsCategory partModel = this.partRepo.findById(shopsParts.getPartId()).orElse(null) ; 
			if(partModel!=null) {
				PartsCategoryRest part = new PartsCategoryRest() ; 
				part.setId(partModel.getId());
				part.setName(partModel.getName());
				part.setNameAr(partModel.getNameAr());
				rest.setPart(part);
			}
			
			rest.setPrice(shopsParts.getPrice());
			Shops shopModel = this.shopRepo.findById(shopsParts.getShopId()).orElse(null) ; 
			if(shopModel!=null) {
				ShopsRest shop = new ShopsRest() ; 
				shop.setAddress(shopModel.getAddress());
				shop.setAddressAr(shopModel.getAddressAr());
				shop.setGeoLocation(shopModel.getGeoLocation());
				shop.setId(shopModel.getId()) ; 
				shop.setName(shopModel.getName());
				shop.setNameAr(shopModel.getNameAr()); 
				rest.setShop(shop);
			}
			rest.setYear(shopsParts.getYear());
			listToReturn.add(rest) ; 
		}

		return listToReturn ; 
	}

	@Override
	public List<ShopsPartsRest> getCarsByCriteria( String make,  String model, String partName,Integer year) {
         
		List<ShopsParts> shopsParts=repo.findCarsByCriteria(make, model, partName, year);
		
		PartsCategoryRest partsCategoryRest = new PartsCategoryRest();	
		ShopsRest shopsRest= new ShopsRest();
		CarRest carRest= new CarRest();
		
		//Note:not understand why give me error if i remove getCurrency and getPrice!!!
		//return shopsParts.stream().map(shopPart -> new ShopsPartsRest(shopPart.getId(), shopPart.getPart(),shopPart.getShop()
		//		,shopPart.getCar(),shopPart.getYear(),shopPart.getCurrency(),shopPart.getPrice()))
	  //			.collect(Collectors.toList());
		
		return shopsParts.stream().map(shopPart -> new ShopsPartsRest(shopPart.getId(), partsCategoryRest,shopsRest,carRest,shopPart.getYear(),shopPart.getCurrency(),shopPart.getPrice()))
			   .collect(Collectors.toList());
		}
	

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

}
