package com.globomart.pricing;


import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globomart.pricing.entity.Pricing;

/**
 * A RESTFul controller for accessing account information.
 * 
 * @author jazeer
 */
@RestController
public class PricingController {

	private static Logger LOG = Logger.getLogger(PricingController.class.getName());
	
	private PricingRepository pricingRepo;
	
	@Autowired
	public PricingController(PricingRepository pricingRepo){
		this.pricingRepo = pricingRepo;
	}
	
	@RequestMapping("/product/price/{productId}")
	public Pricing findPriceByProductId(@PathVariable("productId") String productId){
		LOG.info("pricing-service by id invoked: " + productId);
		if(productId == null || productId.trim().length()<= 0){
			LOG.warning("Invalid ProductId");
			return null;
		}
		Pricing price =  pricingRepo.findByProductId(Long.parseLong(productId));
		if(price== null){
			LOG.info("No Price Defined");
			return null;
		}else{
			return price;
		}
	}
	
	@RequestMapping(value = "/product/price/add", params = { "productid","price" })
	public String save(@RequestParam("productid") Long productid, @RequestParam("price") Long price){
		Pricing pricing = new Pricing(productid, price);
		pricingRepo.save(pricing);
		return "DONE";
	}
}
