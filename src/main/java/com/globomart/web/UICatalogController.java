package com.globomart.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//TODO : Yet to implement the complete class.
@Controller
public class UICatalogController {
	
	protected static Logger logger = Logger.getLogger(UICatalogController.class.getName());

	@Autowired
	protected UICatalogueService catalogueService;
	
	public UICatalogController(UICatalogueService catalogueService) {
		this.catalogueService = catalogueService;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("productId", "searchText");
	}

	@RequestMapping("/product")
	public String goHome() {
		return "index";
	}
	
	@RequestMapping("/product/{id}")
	public String byId(Model model,
			@PathVariable("id") String productId) {

		Catalogue cat = catalogueService.findByID(productId);
		logger.info("web-service byNumber() found: " + cat);
		model.addAttribute("product", cat);
		return "product";
	}
}
