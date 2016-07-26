package com.globomart.productcatalogue;


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
 * The Catalogue Spring configuration.
 */
@Configuration
@ComponentScan
@EntityScan("com.globomart.productcatalogue.entity")
@EnableJpaRepositories("com.globomart.productcatalogue")
@PropertySource("classpath:db-config.properties")
public class CatalogueConfiguration {

	protected static Logger logger =  Logger.getLogger(CatalogueConfiguration.class.getName());

	/**
	 * Creates an in-memory database populated with test data
	 */
	@Bean
	public DataSource dataSource() {
		logger.info("dataSource() invoked");

		// Create an in-memory H2 relational database containing some demo
		// products.
		DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:db/schema-catalogue.sql")
				.addScript("classpath:db/data-catalogue.sql").build();

		logger.info("dataSource = " + dataSource);

		// Test
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> products = jdbcTemplate.queryForList("SELECT id FROM PRODUCT_CATALOGUE");
		logger.info("System has " + products.size() + " products");

		return dataSource;
	}
}
