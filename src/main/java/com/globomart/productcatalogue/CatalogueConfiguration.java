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
 * The Catalogue service configuration.
 * 
 * @author jazeer
 *
 */
@Configuration
@ComponentScan
@EntityScan("com.globomart.productcatalogue.entity")
@EnableJpaRepositories("com.globomart.productcatalogue")
@PropertySource("classpath:db-config.properties")
public class CatalogueConfiguration {

	protected static Logger LOG =  Logger.getLogger(CatalogueConfiguration.class.getName());

	/**
	 * Creates an in-memory database populated with test data
	 */
	@Bean
	public DataSource dataSource() {
		LOG.info("dataSource() invoked");

		// Create an in-memory H2 relational database containing some demo
		// products.
		DataSource dataSource = (new EmbeddedDatabaseBuilder()).addScript("classpath:db/schema-catalogue.sql").build();
				//.addScript("classpath:db/data-catalogue.sql").build();

		LOG.info("dataSource = " + dataSource);

		// Test
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> products = jdbcTemplate.queryForList("SELECT id FROM PRODUCT_CATALOGUE");
		LOG.info("System has " + products.size() + " products");

		return dataSource;
	}
}
