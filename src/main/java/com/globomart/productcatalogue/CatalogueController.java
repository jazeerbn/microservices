package com.globomart.productcatalogue;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globomart.Product;
import com.globomart.exceptions.ProductNotFoundException;
import com.globomart.productcatalogue.entity.Catalogue;

/**
 * A RESTFul controller for accessing product catalogue information.
 * 
 * @author jazeer
 */
@RestController
public class CatalogueController {

	private Logger LOG = Logger.getLogger(CatalogueController.class.getName());
	
	private CatalogueRepository catalogueRepo;
	
	@Autowired
	public CatalogueController(CatalogueRepository catalogueRepo){
		this.catalogueRepo = catalogueRepo;
	}
	/**
	 * Sample URL : http://localhost:8093/product/1
	 * @param productId
	 * @return
	 */
	@RequestMapping(value ="/product/{productId}", produces="application/json")
	public Product findById(@PathVariable("productId") String productId){
		LOG.info("catalogue-service by id invoked: " + productId);
		if(productId == null || productId.trim().length()<= 0){
			LOG.warning("Invalid ProductId");
			return null;
		}
		Catalogue cat =  catalogueRepo.findById(Long.parseLong(productId));
		if(cat== null){
			LOG.info("Empty Catalogue");
			return null;
		}else{
			return buildProduct(cat);
		}
	}
	
	/**
	 * http://localhost:8093/product/name/Prod
	 * 
	 * @param partialName
	 * @return
	 */
	@RequestMapping(value="/product/name/{name}", produces="application/json")
	public List<Product> byName(@PathVariable("name") String partialName) {
		LOG.info("catalogue-service by name invoked: "
				+ catalogueRepo.getClass().getName() + " for "
				+ partialName);

		List<Catalogue> prodCats = catalogueRepo
				.findByNameContainingIgnoreCase(partialName);
		LOG.info("catalogue-service byName() found: " + prodCats);

		if (prodCats == null || prodCats.isEmpty())
			throw new ProductNotFoundException(partialName);
		else {
			return buildProducts(prodCats);
		}
	}
	
	/**
	 * http://localhost:8093/product/add?name=Prod&category=&subcategory=&owner=
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/product/add", params = {"name","category","subcategory","owner"})
	public String save(@RequestParam("name") String name, @RequestParam("category") String category,
			@RequestParam("subcategory") String subcategory, @RequestParam("owner") String manufacturedby) {
		Catalogue cat = new Catalogue(name);
		cat.setCategory(category);
		cat.setSubCategory(subcategory);
		cat.setManufacturedBy(manufacturedby);
		catalogueRepo.save(cat);
		return "DONE";
	}
	
	/**
	 * http://localhost:8093/product/add?id=1&name=Prod5
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/product/add", params = { "id","name" })
	public String save(@RequestParam("id") Long id, @RequestParam("name") String name){
		Catalogue cat = new Catalogue(name);
		cat.setId(id);
		catalogueRepo.save(cat);
		return "DONE";
	}
	
	/**
	 * Fetch all the products available.
	 *  http://localhost:8093/product/getall
	 * 
	 * @return
	 */
	@RequestMapping(value="/product/getall", produces="application/json")
	public List<Product> findAll() {
		LOG.info("Invoked find all");
		List<Catalogue> prodCats = catalogueRepo.findAll();
		LOG.info("Found something: " + prodCats);

		if (prodCats == null || prodCats.isEmpty())
			throw new ProductNotFoundException("Empty");
		else {
			return buildProducts(prodCats);
		}
	}
	
	/**
	 * Building List of products from the name passed.
	 * 
	 * @param catalogues
	 * @return
	 */
	private List<Product> buildProducts(List<Catalogue> catalogues) {
		 List<Product> products = new ArrayList<Product>();
		 for (Catalogue cat : catalogues) 
			 products.add(buildProduct(cat));
		 return products;
	}
	
	/**
	 * Building the final product to be delivered.
	 * 
	 * @param cat
	 * @return
	 */
	private Product buildProduct(Catalogue cat) {
		Product prod = new Product(cat.getId(), cat.getName(), cat.getCategory(), cat.getSubCategory(),
				cat.getManufacturedBy(), 0l);
		return prod;
	}
}
