package com.globomart.web;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author jazeer
 *
 */
@Service
public class UICatalogueService {

	protected static Logger LOG = Logger.getLogger(UICatalogueService.class.getName());
	
	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	public UICatalogueService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}
	
	public Catalogue findByID(String productId) {

		LOG.info("findByID() invoked: for " + productId);
		return restTemplate.getForObject(serviceUrl + "/product/{id}", Catalogue.class, productId);
	}
	
	public List<Catalogue> byNameContains(String name) {
		LOG.info("byNameContains() invoked:  for " + name);
		Catalogue[] products = null;

		try {
			products = restTemplate.getForObject(serviceUrl + "/prduct/name/{name}", Catalogue[].class, name);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
		}

		if (products == null || products.length == 0)
			return null;
		else
			return Arrays.asList(products);
	}
}
