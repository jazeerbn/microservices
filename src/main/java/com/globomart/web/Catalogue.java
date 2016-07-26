package com.globomart.web;


import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Catalogue DTO - used to interact with the {@link UICatalogueService}.
 * @author jazeer
 */
@JsonRootName("Catalogue")
public class Catalogue {

	protected Long id;
	protected String name;
	protected Long price;
	protected String category;
	protected String subCategory;
	protected String manufacturedBy;
	
	protected Catalogue() {
	}

	public long getId() {
		return id;
	}
	protected void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getManufacturedBy() {
		return manufacturedBy;
	}

	public void setManufacturedBy(String manufacturedBy) {
		this.manufacturedBy = manufacturedBy;
	}
	

}
