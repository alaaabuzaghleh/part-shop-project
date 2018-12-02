package com.partsshop.rest.service;

import java.util.List;

import com.partsshop.rest.dto.CarRest;

public interface CarService {

	CarRest saveCar(CarRest carRest );
	CarRest update(CarRest carRest);
	CarRest getCarById(String id);
	List<CarRest> getCars();
	List<CarRest> getCarByMake(String make);
	void deleteCar(String id);
	
	
}
