package com.globomart.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Works as a microservice client, fetching data from the other Services. 
 * Uses the Discovery Server (Eureka) to find the microservice.
 * 
 * @author jazeer
 *
 */
@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false)// Disable component scanner
public class UIService {

		/**
		 * URL uses the logical name of account-service - upper or lower case,
		 * doesn't matter.
		 */
		public static final String CATALOGUE_SERVICE_URL = "http://catalogue-service";
		public static final String PRICING_SERVICE_URL = "http://pricing-service";

		public static void main(String[] args) {
			// Tell server to look for ui-service.yml
			System.setProperty("spring.config.name", "ui-service");
			SpringApplication.run(UIService.class, args);
		}

		/**
		 * A customized RestTemplate that has the ribbon load balancer build in.
		 * prior to the "Brixton" 
		 * 
		 * @return
		 */
		@LoadBalanced
		@Bean
		RestTemplate restTemplate() {
			return new RestTemplate();
		}

		/**
		 * The UICatalogueService encapsulates the interaction with the micro-service.
		 * 
		 * @return A new service instance.
		 */
		@Bean
		public UICatalogueService catalogService() {
			return new UICatalogueService(CATALOGUE_SERVICE_URL);
		}

		/**
		 * Create the controller, passing it the {@link UICatalogueService} to use.
		 * 
		 * @return
		 */
		@Bean
		public UICatalogController catalogController() {
			return new UICatalogController(catalogService());
		}

		@Bean
		public HomeController homeController() {
			return new HomeController();
		}
		
		@Bean
		public UIPricingService pricingService() {
			return new UIPricingService(PRICING_SERVICE_URL);
		}

		@Bean
		public UIPricingController pricingController() {
			return new UIPricingController(pricingService());
		}

		
		

}
