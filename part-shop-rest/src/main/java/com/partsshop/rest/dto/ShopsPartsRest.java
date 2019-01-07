package com.partsshop.rest.dto;


import javax.validation.constraints.NotNull;

import com.partsshop.rest.model.Car;
import com.partsshop.rest.model.PartsCategory;
import com.partsshop.rest.model.Shops;

public class ShopsPartsRest {
	
	private String id;
	@NotNull
	private PartsCategoryRest part;
	@NotNull
	private ShopsRest shop;
	@NotNull
	private CarRest car;
	private Integer year;
	private String currency;
	private double price;

	public ShopsPartsRest() {}

	public ShopsPartsRest(String id, PartsCategoryRest part, ShopsRest shop, CarRest car,Integer year, String currency, double price) {
		
		this.id = id;
		this.part = part;
		this.shop = shop;
		this.car = car;
		this.year = year;
		this.currency = currency;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PartsCategoryRest getPart() {
		return part;
	}

	public void setPart(PartsCategoryRest part) {
		this.part = part;
	}

	public ShopsRest getShop() {
		return shop;
	}

	public void setShop(ShopsRest shop) {
		this.shop = shop;
	}

	public CarRest getCar() {
		return car;
	}

	public void setCar(CarRest car) {
		this.car = car;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	

	
	
	
	

}
