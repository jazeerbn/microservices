package com.globomart.productcatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Just enabling Spring boot and Eureka server to discover services
 * 
 * @author jazeer
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class ProductCatalogueService {

	/**
	 * Method will be executing using embedded servlet and run spring boot.
	 * Server will look for catalogue-service.yml to discover 
	 * services while setting the env variable.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("spring.config.name", "catalogue-service");
		SpringApplication.run(ProductCatalogueService.class, args);
	}

}
