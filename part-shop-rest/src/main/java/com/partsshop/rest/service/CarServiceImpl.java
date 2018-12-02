package com.partsshop.rest.service;

import java.util.ArrayList;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partsshop.rest.dto.CarRest;
import com.partsshop.rest.model.Car;
import com.partsshop.rest.repo.CarRepo;

@Service
public class CarServiceImpl implements CarService {

	private Logger log = LoggerFactory.getLogger(CarServiceImpl.class);

	@Autowired
	private CarRepo repo;

	@Override
	public CarRest saveCar(CarRest carRest) {

		Car car = new Car();
		car.setMake(carRest.getMake());
		car.setModel(carRest.getModel());
		car.setMakeAr(carRest.getMakeAr());
		car.setModelAr(carRest.getModelAr());

		repo.save(car);

		if (car.getId() == null) {
			return null;
		} else {
			carRest.setId(car.getId());
			return carRest;
		}

	}

	@Override
	public CarRest update(CarRest carRest) {
		try {
			Car car = new Car();
			car.setId(carRest.getId());
			car.setMake(carRest.getMake());
			car.setModel(carRest.getModel());
			car.setMakeAr(carRest.getMakeAr());
			car.setModelAr(carRest.getModelAr());
			repo.save(car);
			return carRest;
		} catch (Exception exp) {
			log.error(exp.getMessage());
			return null;
		}

	}

	@Override
	public CarRest getCarById(String id) {

		Car car = repo.findById(id).orElse(null);
		if (car != null) {
			CarRest carRest = new CarRest();
			carRest.setId(car.getId());
			carRest.setMake(car.getMake());
			carRest.setModel(car.getModel());
			carRest.setMakeAr(car.getMakeAr());
			carRest.setModelAr(car.getModelAr());

			return carRest;
		} else {
			return null;
		}
	}

	@Override
	public List<CarRest> getCars() {
		
		List<Car> cars=repo.findAll();
		
		/*List<CarRest> ls = new ArrayList<>() ; 
		
		for(Car car : cars) {
			CarRest carRest = new CarRest();
			carRest.setId(car.getId());
			carRest.setMake(car.getMake());
			carRest.setType(car.getType());
			carRest.setMakeAr(car.getMakeAr());
			carRest.setTypeAr(car.getTypeAr());
			ls.add(carRest) ; 
			
		}
		
		return ls ; */
		
		return cars.stream().map(car -> 
		new CarRest(car.getId(), 
				car.getMake(), car.getModel(),
				car.getMakeAr(),
				car.getModelAr())).collect(Collectors.toList()) ; 	
	}

	@Override
	public List<CarRest> getCarByMake(String make) {
		
		List<Car> cars=repo.findByMakeOrMakeAr(make);
		
		return cars.stream().map(car -> 
		new CarRest(car.getId(), 
				car.getMake(), car.getModel(),
				car.getMakeAr(),
				car.getModelAr())).collect(Collectors.toList()) ; 	
		
		}

	@Override
	public void deleteCar(String id) {
	

	}

}
