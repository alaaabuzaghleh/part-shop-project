package com.partsshop.rest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class PartsRequestRest {
	private String id ; 
	@NotBlank
	private String title ; 
	@NotBlank
	private String description; 
	@Size(max=5242880)
	private String imageData ;
	@NotBlank
	private String language ; 
	@NotBlank
	private String countryCode ; 
	@NotBlank
	private String country ; 
	private String partsClubId ;
	
	public String getId() {
		return id ; 
	}
	public void setId(String id) {
		this.id=id ; 
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageData() {
		return imageData;
	}
	public void setImageData(String imageData) {
		this.imageData = imageData;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPartsClubId() {
		return partsClubId;
	}
	public void setPartsClubId(String partsClubId) {
		this.partsClubId = partsClubId;
	}
	
	
	

}
