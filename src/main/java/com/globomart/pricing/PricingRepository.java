package com.globomart.pricing;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import com.globomart.pricing.entity.Pricing;

/**
 * 
 * @author jazeer
 *
 */
public interface PricingRepository extends CrudRepository<Pricing, Long>{
	
	public Pricing findById(long priceId);
	public Pricing findByProductId(long productId);
	public Pricing save(Pricing price);
}
