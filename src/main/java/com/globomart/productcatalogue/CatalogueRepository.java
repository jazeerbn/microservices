package com.globomart.productcatalogue;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import com.globomart.productcatalogue.entity.Catalogue;

/**
 * Spring JPA Interface to enable all DB operations.
 * On making this as {@link Repository} type Spring will take care of all repository activities.
 * 
 * @author jazeer
 *
 */
public interface CatalogueRepository extends CrudRepository<Catalogue, Long>{
	
	public Catalogue findById(long productId);
	
	public Catalogue findByName(String name);

	public List<Catalogue> findByNameContainingIgnoreCase(String partialName);
	
	public Catalogue save(Catalogue cat);
	
	@Modifying
	@Query("update Catalogue c set c.name = ?1 where c.id = ?3")
	void setCatalogueId(String name, Long id);
	
	public List<Catalogue> findAll();
	
	public void delete(Catalogue cat);

}
