package com.globomart;



import java.io.Serializable;

/**
 * Final wrapper product.
 * @author jazeer
 *
 */
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1410297031162993598L;

	private Long productId;

	protected String productName;
	
	protected String category;
	
	protected String subCategory;
	
	protected String manufacturedBy;
	
	protected Long price;

	/**
	 * 
	 * @param productId
	 * @param productName
	 * @param category
	 * @param subCategory
	 * @param manufacturedBy
	 * @param price
	 */
	public Product(Long productId, String productName, String category, String subCategory, String manufacturedBy, Long price){
		this.productId = productId;
		this.productName = productName;
		this.category= category;
		this.subCategory= subCategory;
		this.manufacturedBy = manufacturedBy;
		this.price = price;
	}
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

}
