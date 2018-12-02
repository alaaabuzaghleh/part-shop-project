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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partsshop.rest.dto.RestMessage;
import com.partsshop.rest.dto.ShopsRest;
import com.partsshop.rest.service.ShopService;

@RestController 
@RequestMapping("/api/v1/shops")
public class ShopController {
	
	@Autowired
	private ShopService service;
	
	@Autowired 
	private MessageSource messageSource ; 
	
	
	@PostMapping(value= {"", "/"})
	@Secured("ROLE_ADMIN")
    public ResponseEntity<?> saveNewShop(@RequestBody @Valid ShopsRest rest,@RequestHeader("Accept-Language") Locale locale){
		
		ShopsRest toReturn = this.service.saveShop(rest); 
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
	public ResponseEntity<?> updateShop(@RequestBody @Valid ShopsRest rest,@RequestHeader("Accept-Language") Locale locale){
		
		ShopsRest tempShop = this.service.getShopById(rest.getId());
		
		if(tempShop== null) {
			List<String> ls = new ArrayList<>() ; 
			ls.add(this.messageSource.getMessage("Shops.notFound", null, locale)) ; 
			return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.NOT_FOUND) ;
		}else {
			 
		    this.service.update(rest);
			List<String> ls = new ArrayList<>() ; 
			ls.add(this.messageSource.getMessage("saved.success", null, locale)) ; 
			return new ResponseEntity<>(new RestMessage(true, ls), HttpStatus.ACCEPTED) ;
		}
			
	}
	
	@GetMapping(value= {"", "/"})
	public ResponseEntity<?> getAllShops(@RequestHeader("Accept-Language") Locale locale){
		
		List<ShopsRest> shops=this.service.getShops();
	
		if(shops==null || shops.isEmpty()) {
			List<String> ls = new ArrayList<>() ; 
			ls.add(this.messageSource.getMessage("Shops.notFound", null, locale)) ; 
			return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.NOT_FOUND) ;
		}else {
			return new ResponseEntity<>(shops, HttpStatus.OK) ;
		
		}
		
	}
	
	
	
	
	
	

}
