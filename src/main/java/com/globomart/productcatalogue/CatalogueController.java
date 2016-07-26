package com.globomart.productcatalogue;


import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globomart.exceptions.ProductNotFoundException;
import com.globomart.productcatalogue.entity.Catalogue;

/**
 * A RESTFul controller for accessing product catalogue information.
 * 
 * @author jazeer
 */
@RestController
public class CatalogueController {

	private Logger logger = Logger.getLogger(CatalogueController.class.getName());
	
	private CatalogueRepository catalogueRepo;
	
	@Autowired
	public CatalogueController(CatalogueRepository catalogueRepo){
		this.catalogueRepo = catalogueRepo;
	}
	
	@RequestMapping("/product/{productId}")
	public Catalogue findById(@PathVariable("productId") String productId){
		logger.info("catalogue-service by id invoked: " + productId);
		if(productId == null || productId.trim().length()<= 0){
			logger.warning("Invalid ProductId");
			return null;
		}
		Catalogue cat =  catalogueRepo.findById(Long.parseLong(productId));
		if(cat== null){
			logger.info("Empty Catalogue");
			return null;
		}else{
			return cat;
		}
	}
	
	@RequestMapping("/product/name/{name}")
	public List<Catalogue> byName(@PathVariable("name") String partialName) {
		logger.info("catalogue-service by name invoked: "
				+ catalogueRepo.getClass().getName() + " for "
				+ partialName);

		List<Catalogue> products = catalogueRepo
				.findByNameContainingIgnoreCase(partialName);
		logger.info("catalogue-service byName() found: " + products);

		if (products == null || products.size() == 0)
			throw new ProductNotFoundException(partialName);
		else {
			return products;
		}
	}
	
	@RequestMapping(value = "/product/save/", method=RequestMethod.GET)
	public String save(Catalogue catalogue){
		catalogueRepo.save(catalogue);
		return "OK";
	}
}
