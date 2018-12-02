package com.partsshop.rest.dto;

import javax.validation.constraints.NotBlank;

public class CarRest {
	
	
	private String id;
	@NotBlank // for validation.
	private String make ; 
	@NotBlank
	private String model ; 
	private String makeAr ; 
	private String modelAr;
	
	public CarRest() {} 
	
	public CarRest(String id, String make, String model, String makeAr, String modelAr) {
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
