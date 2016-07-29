package com.globomart.pricing;


import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globomart.pricing.entity.Pricing;
import static com.globomart.Constants.*;

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
	
	@RequestMapping(GENERIC_PRODUCT_URL+"price/{productid}")
	public Pricing findPriceByProductId(@PathVariable(PRODUCTID) String productId){
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
	
	@RequestMapping(value =GENERIC_PRODUCT_URL+"price/add", params = { PRODUCTID, PRICE })
	public String save(@RequestParam(PRODUCTID) Long productid, @RequestParam(PRICE) Long price){
		Pricing pricing = new Pricing(productid, price);
		pricingRepo.save(pricing);
		return DONE;
	}
	
	@RequestMapping(GENERIC_PRODUCT_URL+"price/")
	public List<Pricing> findAll(){
		LOG.info("find all pricing-service invoked: ");
		return  pricingRepo.findAll();
	}
}
