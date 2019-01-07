package com.partsshop.rest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shops_parts")
public class ShopsParts {

	@Id
	private String id;
	private String partId;
	private String shopId;
	private String carId;
	private Integer year;
	private String currency;
	private double price;

	public ShopsParts() {}

	public ShopsParts(String id, String partId, String shopId, String carId,Integer year, String currency, double price) {
		this.id = id;
		this.partId = partId;
		this.shopId = shopId;
		this.carId = carId;
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


	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
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
