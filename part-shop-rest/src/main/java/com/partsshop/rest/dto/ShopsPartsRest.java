package com.partsshop.rest.dto;


import javax.validation.constraints.NotNull;

import com.partsshop.rest.model.Car;
import com.partsshop.rest.model.PartsCategory;
import com.partsshop.rest.model.Shops;

public class ShopsPartsRest {
	
	private String id;
	@NotNull
	private PartsCategory part;
	@NotNull
	private Shops shop;
	@NotNull
	private Car car;
	private Integer year;
	private String currency;
	private double price;

	public ShopsPartsRest() {}

	public ShopsPartsRest(String id, PartsCategory part, Shops shop, Car car,Integer year, String currency, double price) {
		
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

	public PartsCategory getPart() {
		return part;
	}

	public void setPart(PartsCategory part) {
		this.part = part;
	}

	public Shops getShop() {
		return shop;
	}

	public void setShop(Shops shop) {
		this.shop = shop;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
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
