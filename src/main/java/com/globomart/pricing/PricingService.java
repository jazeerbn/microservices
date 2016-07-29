package com.globomart.pricing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import static com.globomart.Constants.*;
/**
 * 
 * @author jazeer
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class PricingService {

	@Autowired
	protected PricingRepository pricingRepo;
	
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
		System.setProperty(SPRING_CONF_NAME, PRICING_SERVICE);
		SpringApplication.run(PricingService.class, args);
	}

}
