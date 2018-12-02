package com.partsshop.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.partsshop.rest.dto.RestMessage;
import com.partsshop.rest.dto.ShopsPartsRest;
import com.partsshop.rest.service.ShopPartService;

@RestController 
@RequestMapping("/api/v1/shops-parts")
public class ShopPartController {
	
	@Autowired
	private ShopPartService service;
	@Autowired 
	private MessageSource messageSource ; 
	
	@PostMapping(value= {"", "/"})
	@Secured("ROLE_ADMIN")
    public ResponseEntity<?> saveNewShopPart(@RequestBody @Valid ShopsPartsRest rest,@RequestHeader("Accept-Language") Locale locale){
		
		ShopsPartsRest toReturn = this.service.saveShopPart(rest); 
		if(toReturn == null) {
			List<String> ls = new ArrayList<>() ; 
			ls.add(this.messageSource.getMessage("Exception.unexpected", null, locale)) ; 
			return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.INTERNAL_SERVER_ERROR) ;
		}else {
			List<String> ls = new ArrayList<>() ; 
			ls.add(this.messageSource.getMessage("saved.success", null, locale)) ; 
			return new ResponseEntity<>(new RestMessage(true, ls), HttpStatus.CREATED) ;
		}
			
	}
	
	@PutMapping(value= {"", "/"})
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> updateShopPart(@RequestBody @Valid ShopsPartsRest rest,@RequestHeader("Accept-Language") Locale locale){
		
		ShopsPartsRest tempShopPart = this.service.getShopPartById(rest.getId());
		
		if(tempShopPart== null) {
			List<String> ls = new ArrayList<>() ; 
			ls.add(this.messageSource.getMessage("ShopsParts.notFound", null, locale)) ; 
			return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.NOT_FOUND) ;
		}else {
			 
		    this.service.update(rest);
			List<String> ls = new ArrayList<>() ; 
			ls.add(this.messageSource.getMessage("saved.success", null, locale)) ; 
			return new ResponseEntity<>(new RestMessage(true, ls), HttpStatus.ACCEPTED) ;
		}
			
	}
	
	@GetMapping(value= {"", "/"})
	public ResponseEntity<?> getAllShopsParts(@RequestHeader("Accept-Language") Locale locale){
		
		List<ShopsPartsRest> shopsParts=this.service.getShopsParts();
	
		if(shopsParts==null || shopsParts.isEmpty()) {
			List<String> ls = new ArrayList<>() ; 
			ls.add(this.messageSource.getMessage("ShopsParts.notFound", null, locale)) ; 
			return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.NOT_FOUND) ;
		}else {
			return new ResponseEntity<>(shopsParts, HttpStatus.OK) ;
		
		}
		
	}
	
	@GetMapping("/{make}/{model}/{partName}/{year}")
	public ResponseEntity<?> getCarsByCriteria(@PathVariable("make") String make,
			@PathVariable("model") String model,
			@PathVariable("partName") String partName,
			@PathVariable("year") Integer year,
			@RequestHeader("Accept-Language") Locale locale){
		
		List<ShopsPartsRest> shopsParts=this.service.getCarsByCriteria(make, model, partName, year);
		if(shopsParts==null || shopsParts.isEmpty()) {
			List<String> ls = new ArrayList<>() ; 
			ls.add(this.messageSource.getMessage("ShopsParts.notFound", null, locale)) ; 
			return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.NOT_FOUND) ;
		}else {
			return new ResponseEntity<>(shopsParts, HttpStatus.OK) ;
		
		}
		
	}

}
