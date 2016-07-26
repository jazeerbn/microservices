package com.globomart.pricing;


import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

/**
 * The Pricing Spring configuration.
 */
@Configuration
@ComponentScan
@EntityScan("com.globomart.pricing.entity")
@EnableJpaRepositories("com.globomart.pricing")
@PropertySource("classpath:db-config.properties")
public class PricingConfiguration {

	protected static Logger logger =  Logger.getLogger(PricingConfiguration.class.getName());

	/**
	 * Creates an in-memory database populated with test data
	 */
	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		// Create an in-memory H2 relational database containing some demo
		// products.
		DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:db/schema-pricing.sql")
				.addScript("classpath:db/data-pricing.sql").build();

		logger.info("dataSource = " + dataSource);

		// Test
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> products = jdbcTemplate.queryForList("SELECT PRICE FROM PRODUCT_PRICE");
		logger.info("System has " + products.size() + " products");

		return dataSource;
	}
}
