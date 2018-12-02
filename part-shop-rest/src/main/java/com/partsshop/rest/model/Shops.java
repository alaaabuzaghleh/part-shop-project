package com.partsshop.rest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="shops")
public class Shops {
	
	
	@Id
	private String id;
	private String name ; 
	private String address ;
	private String geoLocation;
	private String nameAr ; 
	private String addressAr;
	
	public Shops() {}
	public Shops(String id, String name, String address, String geoLocation, String nameAr, String addressAr) {
		
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
	public String getNameAr() {
		return nameAr;
	}
	public void setNameAr(String nameAr) {
		this.nameAr = nameAr;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressAr() {
		return addressAr;
	}
	public void setAddressAr(String addressAr) {
		this.addressAr = addressAr;
	}
	public String getGeoLocation() {
		return geoLocation;
	}
	public void setGeoLocation(String geoLocation) {
		this.geoLocation = geoLocation;
	}
	

}
