package com.partsshop.rest.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;

public class ShopsRest {
	
	
	
	private String id;
	@NotBlank
	private String name ; 
	@NotBlank
	private String address ;
	private String geoLocation;
	@NotBlank
	private String nameAr ;
	@NotBlank
	private String addressAr;
	
	
	public ShopsRest() {}
	public ShopsRest(String id, String name, String address, String geoLocation, String nameAr, String addressAr) {
		
		this.id = id;
		this.name = name;
		this.address = address;
		this.geoLocation = geoLocation;
		this.nameAr = nameAr;
		this.addressAr = addressAr;
			
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGeoLocation() {
		return geoLocation;
	}
	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}
	public String getNameAr() {
		return nameAr;
	}
	public void setNameAr(String nameAr) {
		this.nameAr = nameAr;
	}
	public String getAddressAr() {
		return addressAr;
	}
	public void setAddressAr(String addressAr) {
		this.addressAr = addressAr;
	}
	

}
