package com.globomart.productcatalogue.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Persistent account entity with JPA markup. Accounts are stored in an H2
 * relational database.
 * 
 */
@Entity
@Table(name = "PRODUCT_CATALOGUE")
public class Catalogue implements Serializable {

	private static final long serialVersionUID = -5898464507015699272L;

	public static Long nextId = 0L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name = "NAME")
	protected String name;
	
	@Column(name = "CATEGORY")
	protected String category;
	
	@Column(name = "SUBCATEGORY")
	protected String subCategory;
	
	@Column(name = "MANUFACTUREDBY")
	protected String manufacturedBy;

	/*public static Long getNextId() {
		synchronized (nextId) {
			return nextId++;
		}
	}*/

	/**
	 * Default constructor for JPA only.Hence protected
	 */
	protected Catalogue() {
	}

	public Catalogue(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
