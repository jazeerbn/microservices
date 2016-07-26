package com.globomart.pricing;


import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globomart.pricing.entity.Pricing;

/**
 * A RESTFul controller for accessing account information.
 * 
 * @author jazeer
 */
@RestController
public class PricingController {

	private Logger logger = Logger.getLogger(PricingController.class.getName());
	
	private PricingRepository pricingRepo;
	
	@Autowired
	public PricingController(PricingRepository pricingRepo){
		this.pricingRepo = pricingRepo;
	}
	
	@RequestMapping("/product/price/{productId}")
	public Pricing findPriceByProductId(@PathVariable("productId") String productId){
		logger.info("pricing-service by id invoked: " + productId);
		if(productId == null || productId.trim().length()<= 0){
			logger.warning("Invalid ProductId");
			return null;
		}
		Pricing price =  pricingRepo.findByProductId(Long.parseLong(productId));
		if(price== null){
			logger.info("No Price Defined");
			return null;
		}else{
			return price;
		}
	}
}
