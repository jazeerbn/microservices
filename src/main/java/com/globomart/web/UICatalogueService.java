package com.globomart.web;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UICatalogueService {

	protected static Logger logger = Logger.getLogger(UICatalogueService.class.getName());
	
	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	public UICatalogueService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}
	
	/**
	 * The RestTemplate works because it uses a custom request-factory that uses
	 * Ribbon to look-up the service to use. This method simply exists to show
	 * this.
	 */
	@PostConstruct
	public void demoOnly() {
		// Can't do this in the constructor because the RestTemplate injection
		// happens afterwards.
		logger.warning("The RestTemplate request factory is "
				+ restTemplate.getRequestFactory().getClass());
	}
	
	public Catalogue findByID(String productId) {

		logger.info("findByID() invoked: for " + productId);
		return restTemplate.getForObject(serviceUrl + "/product/{id}",
				Catalogue.class, productId);
	}
	
	public List<Catalogue> byNameContains(String name) {
		logger.info("byNameContains() invoked:  for " + name);
		Catalogue[] products = null;

		try {
			products = restTemplate.getForObject(serviceUrl
					+ "/prduct/name/{name}", Catalogue[].class, name);
		} catch (HttpClientErrorException e) { // 404
			e.printStackTrace();
		}

		if (products == null || products.length == 0)
			return null;
		else
			return Arrays.asList(products);
	}
}
