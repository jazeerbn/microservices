package com.globomart.pricing;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

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
	public List<Pricing> findAll();
}
