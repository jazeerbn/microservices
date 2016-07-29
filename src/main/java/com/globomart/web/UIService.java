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

import static com.globomart.Constants.*;

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

		public static void main(String[] args) {
			System.setProperty(SPRING_CONF_NAME, UI_SERVICE);
			SpringApplication.run(UIService.class, args);
		}

		/**
		 * A customized RestTemplate that has a built in ribbon load.
		 * @return
		 */
		@LoadBalanced
		@Bean
		RestTemplate restTemplate() {
			return new RestTemplate();
		}

		@Bean
		public UICatalogueService catalogService() {
			return new UICatalogueService(CATALOGUE_SERVICE_URL);
		}

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
