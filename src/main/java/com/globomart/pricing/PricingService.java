package com.globomart.pricing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PricingService {

	@Autowired
	protected PricingRepository pricingRepo;
	
	public static void main(String[] args) {
		System.setProperty("spring.config.name", "pricing-service");
		SpringApplication.run(PricingService.class, args);
	}

}
