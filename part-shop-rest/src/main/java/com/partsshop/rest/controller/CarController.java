package com.partsshop.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
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

import com.partsshop.rest.dto.CarRest;
import com.partsshop.rest.dto.RestMessage;
import com.partsshop.rest.service.CarService;

@RestController 
@RequestMapping("/api/v1/cars")
public class CarController {
	
	@Autowired 
	private CarService service ; 
	@Autowired 
	private MessageSource messageSource ; 
	
	@PostMapping(value= {"", "/"})
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> saveNewCar(@RequestBody @Valid CarRest rest,@RequestHeader("Accept-Language") Locale locale){
		
		CarRest toReturn = this.service.saveCar(rest) ; 
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
	public ResponseEntity<?> updateCar(@RequestBody @Valid CarRest rest,@RequestHeader("Accept-Language") Locale locale){
		
		CarRest tempCar = this.service.getCarById(rest.getId());
		
		if(tempCar== null) {
			List<String> ls = new ArrayList<>() ; 
			ls.add(this.messageSource.getMessage("Cars.notFound", null, locale)) ; 
			return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.NOT_FOUND) ;
		}else {
			
		    this.service.update(rest) ; 
			List<String> ls = new ArrayList<>() ; 
			ls.add(this.messageSource.getMessage("saved.success", null, locale)) ; 
			return new ResponseEntity<>(new RestMessage(true, ls), HttpStatus.ACCEPTED) ;
		}
		
		
	}
	
	@GetMapping("/{make}")
	public ResponseEntity<?> getCarByMake(@PathVariable("make") String make,@RequestHeader("Accept-Language") Locale locale){
		System.out.println(make);
		List<CarRest> cars=this.service.getCarByMake(make);
		if(cars==null || cars.isEmpty()) {
			List<String> ls = new ArrayList<>() ; 
			ls.add(this.messageSource.getMessage("Cars.notFound", null, locale)) ; 
			return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.NOT_FOUND) ;
		}else {
			return new ResponseEntity<>(cars, HttpStatus.OK) ;
		
		}
		
	}
	
	@GetMapping(value= {"", "/"})
	public ResponseEntity<?> getAllCars(@RequestHeader("Accept-Language") Locale locale){
		List<CarRest> cars=this.service.getCars();
		if(cars==null || cars.isEmpty()) {
			List<String> ls = new ArrayList<>() ; 
			ls.add(this.messageSource.getMessage("Cars.notFound", null, locale)) ; 
			return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.NOT_FOUND) ;
		}else {
			return new ResponseEntity<>(cars, HttpStatus.OK) ;
		
		}
		
	}











}
