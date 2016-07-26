package com.globomart.productcatalogue;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.globomart.productcatalogue.entity.Catalogue;

/**
 * 
 * @author jazeer
 *
 */
public interface CatalogueRepository extends CrudRepository<Catalogue, Long>{
	//Repository<Catalogue, Long>,CustomCatalogue{
	
	public Catalogue findById(long productId);
	
	public Catalogue findByName(String name);

	public List<Catalogue> findByNameContainingIgnoreCase(String partialName);
	
	public Catalogue save(Catalogue catalog);
	
	@Modifying
	@Query("update Catalogue c set c.name = ?1 where c.id = ?3")
	void setCatalogueId(String name, Long id);

}
