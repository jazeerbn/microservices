package com.globomart.pricing;

import org.springframework.data.repository.Repository;

import com.globomart.pricing.entity.Pricing;

/**
 * 
 * @author jazeer
 *
 */
public interface PricingRepository extends Repository<Pricing, Long>{
	
	public Pricing findById(long priceId);
	public Pricing findByProductId(long productId);
}
