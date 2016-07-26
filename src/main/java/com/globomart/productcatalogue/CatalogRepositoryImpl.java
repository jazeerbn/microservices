package com.globomart.productcatalogue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.globomart.productcatalogue.entity.Catalogue;

/*@Repository
public class CatalogRepositoryImpl implements CatalogueRepository {

	
	
	@Override
	public Catalogue findById(long productId) {
		String query = "SELECT * FROM Catalogue where id = "+productId;
		Catalogue cat = new Catalogue("TEST");
		cat.setId(productId);
		return cat;
	}

	@Override
	public List<Catalogue> findByNameContainingIgnoreCase(String partialName) {
		Catalogue cat = new Catalogue(partialName);
		cat.setId(1);
		List<Catalogue> cats = new ArrayList<Catalogue>();
		cats.add(cat);
		return cats;
	}



}*/
