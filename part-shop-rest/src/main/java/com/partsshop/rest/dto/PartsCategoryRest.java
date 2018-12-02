package com.partsshop.rest.dto;

import javax.validation.constraints.NotBlank;

public class PartsCategoryRest {

	private String id;
	@NotBlank
	private String name;
	//@NotBlank
	private String nameAr;

	public PartsCategoryRest() {
	}

	public PartsCategoryRest(String id, String name, String nameAr) {

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
