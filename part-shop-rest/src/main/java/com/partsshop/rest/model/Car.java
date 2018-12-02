  package com.partsshop.rest.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="cars")
public class Car {
	
	@Id
	private String id;
	private String make ; 
	private String model ; 
	private String makeAr ; 
	private String modelAr;
	
	
	//No Argument Constructor 
	public Car() {} 
	//Argument Constuctor 
	public Car(String id, String make, String model, String makeAr, String modelAr) {
		this.id=id ; 
		this.make = make  ; 
		this.model = model ; 
		this.makeAr = makeAr ; 
		this.modelAr = modelAr ; 
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMakeAr() {
		return makeAr;
	}
	public void setMakeAr(String makeAr) {
		this.makeAr = makeAr;
	}
	public String getModelAr() {
		return modelAr;
	}
	public void setModelAr(String modelAr) {
		this.modelAr = modelAr;
	}
	
	
	
	
	
	

}
