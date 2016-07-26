package com.globomart.pricing.entity;


import java.io.Serializable;
import java.math.BigDecimal;

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
@Table(name = "PRODUCT_PRICE")
public class Pricing implements Serializable {

	private static final long serialVersionUID = -5898464507015699272L;

	public static Long nextId = 0L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Long id;
	
	@Column(name = "PRODUCT_ID")
	protected Long productId;

	@Column(name = "PRICE")
	protected BigDecimal price;

	/*
	protected static Long getNextId() {
		synchronized (nextId) {
			return nextId++;
		}
	}*/

	/**
	 * Default constructor for JPA only.
	 */
	protected Pricing() {
	}

	public Pricing(Long productId) {
		this.productId = productId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}
