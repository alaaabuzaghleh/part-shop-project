package com.partsshop.rest.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "parts_category")
public class PartsCategory {

	@Id
	private String id;
	private String name;
	private String nameAr;

	public PartsCategory() {
	}

	public PartsCategory(String id, String name, String nameAr) {

		this.id = id;
		this.name = name;
		this.nameAr = nameAr;

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

}
