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

import com.partsshop.rest.dto.CarRest;
import com.partsshop.rest.dto.PartsCategoryRest;
import com.partsshop.rest.dto.RestMessage;
import com.partsshop.rest.service.PartsCategoryService;

@RestController 
@RequestMapping("/api/v1/parts-categories")
public class PartsCategoryController {
	
	
	@Autowired 
	private PartsCategoryService service ; 
	@Autowired 
	private MessageSource messageSource ; 
	
	@PostMapping(value= {"", "/"})
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> saveNewPart(@RequestBody @Valid PartsCategoryRest rest,@RequestHeader("Accept-Language") Locale locale){
		
		PartsCategoryRest toReturn = this.service.savePart(rest) ; 
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
	public ResponseEntity<?> updatePart(@RequestBody @Valid PartsCategoryRest rest,@RequestHeader("Accept-Language") Locale locale){
		
		PartsCategoryRest tempPart = this.service.getNameById(rest.getId());
		
		if(tempPart== null) {
			List<String> ls = new ArrayList<>() ; 
			ls.add(this.messageSource.getMessage("Parts.notFound", null, locale)) ; 
			return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.NOT_FOUND) ;
		}else {
			
		    this.service.updatePart(rest) ; 
			List<String> ls = new ArrayList<>() ; 
			ls.add(this.messageSource.getMessage("saved.success", null, locale)) ; 
			return new ResponseEntity<>(new RestMessage(true, ls), HttpStatus.ACCEPTED) ;
		}
		
		
	}
	
	@GetMapping(value= {"", "/"})
	public ResponseEntity<?> getAllParts(@RequestHeader("Accept-Language") Locale locale){
		
		List<PartsCategoryRest> parts=this.service.getNames();
		
		if(parts==null || parts.isEmpty()) {
			List<String> ls = new ArrayList<>() ; 
			ls.add(this.messageSource.getMessage("Parts.notFound", null, locale)) ; 
			return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.NOT_FOUND) ;
		}else {
			return new ResponseEntity<>(parts, HttpStatus.OK) ;
		
		}
		
	}
	
	
	
	
	
	
	

}
