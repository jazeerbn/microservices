package com.globomart.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UIPricingController {

	@Autowired
	protected UIPricingService pricingService;
	
	protected Logger logger = Logger.getLogger(UIPricingController.class
			.getName());

	public UIPricingController(UIPricingService pricingService) {
		this.pricingService = pricingService;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("productId", "searchText");
	}

	@RequestMapping("/products/price")
	public String goHome() {
		return "index";
	}
	
	/*@RequestMapping("/products/price/{productId}")
	public String byNumber(Model model,
			@PathVariable("productId") String productId) {

		Catalogue account = pricingService.findByID(productId);
		logger.info("web-service byNumber() found: " + account);
		model.addAttribute("product", account);
		return "account";
	}*/
}
