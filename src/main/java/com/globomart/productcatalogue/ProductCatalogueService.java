package com.globomart.productcatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Loading the application using Spring boot and Eureka server (service discovery).
 * <br>Since this service acts as server, made this as Eureka server.
 * 
 * @author jazeer
 *
 */
@EnableEurekaServer 
@SpringBootApplication
public class ProductCatalogueService {

	/**
	 * Method will be executing using embedded servlet and run spring boot.
	 * 
	 * <p>This method will 
	 * 	<br>1. Create new instance via spring bean initialization.</br>
	 * 	<br>2. Run the Spring application, creating and refreshing a new Application context.</br>
	 * 
	 * @param args 
	 */
	public static void main(String[] args) {
		System.setProperty("spring.config.name", "catalogue-service");
		SpringApplication.run(ProductCatalogueService.class, args); // Nothing to do with args in this context
	}

}
