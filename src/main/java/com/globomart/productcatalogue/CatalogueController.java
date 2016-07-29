package com.globomart.productcatalogue;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.globomart.Product;
import com.globomart.exceptions.ProductNotFoundException;
import com.globomart.pricing.entity.Pricing;
import com.globomart.productcatalogue.entity.Catalogue;
import static com.globomart.Constants.*;

/**
 * A RESTFul controller for accessing product catalogue information.
 * 
 * @author jazeer
 */
@RestController
public class CatalogueController {

	private Logger LOG = Logger.getLogger(CatalogueController.class.getName());
	
	private CatalogueRepository catalogueRepo;
	
	@Autowired
	public CatalogueController(CatalogueRepository catalogueRepo){
		this.catalogueRepo = catalogueRepo;
	}
	/**
	 * Sample URL : http://localhost:8093/product/1
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = GENERIC_PRODUCT_URL+"{productid}", produces=JSON_APP)
	public Product findById(@PathVariable(PRODUCTID) String productId){
		LOG.info("catalogue-service by id invoked: " + productId);
		if(productId == null || productId.trim().length()<= 0){
			LOG.warning("Invalid ProductId");
			return null;
		}
		Catalogue cat =  catalogueRepo.findById(Long.parseLong(productId));
		Pricing price = findPriceByProductID(productId);
		if(cat== null){
			LOG.info("Empty Catalogue");
			return null;
		}else{
			Long mrp = (price != null && price.getPrice() != null) 
					? price.getPrice().longValue() : 0l;
			return buildProduct(cat, mrp);
		}
	}
	
	/**
	 * sample url: http://localhost:8093/product/name/Prod
	 * 
	 * @param partialName
	 * @return
	 */
	@RequestMapping(value=GENERIC_PRODUCT_URL+"name/{name}", produces=JSON_APP)
	public List<Product> byName(@PathVariable(NAME) String partialName) {
		LOG.info("catalogue-service by name invoked: "
				+ catalogueRepo.getClass().getName() + " for "
				+ partialName);

		List<Catalogue> prodCats = catalogueRepo
				.findByNameContainingIgnoreCase(partialName);
		LOG.info("catalogue-service byName() found: " + prodCats);

		if (prodCats == null || prodCats.isEmpty())
			throw new ProductNotFoundException(partialName);
		else {
			List<Long> prodIds = new ArrayList<Long>();
			for (Long id : prodIds) {
				prodIds.add(id);
			}
			return buildProducts(prodCats, findPriceByIds(prodIds));
		}
	}
	
	/**
	 * sample url : http://localhost:8093/product/add?name=Prod&category=&subcategory=&owner=
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = GENERIC_PRODUCT_URL+"add", params = {NAME,CATEGORY,SUBCATEGORY,OWNER})
	public String save(@RequestParam(NAME) String name, @RequestParam(CATEGORY) String category,
			@RequestParam(SUBCATEGORY) String subcategory, @RequestParam(OWNER) String manufacturedby) {
		Catalogue cat = new Catalogue(name);
		cat.setCategory(category);
		cat.setSubCategory(subcategory);
		cat.setManufacturedBy(manufacturedby);
		catalogueRepo.save(cat);
		return DONE;
	}
	
	/**
	 * http://localhost:8093/product/add?id=1&name=Prod5
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = GENERIC_PRODUCT_URL+"add", params = { ID,NAME })
	public String save(@RequestParam(ID) Long id, @RequestParam(NAME) String name){
		Catalogue cat = new Catalogue(name);
		cat.setId(id);
		catalogueRepo.save(cat);
		return DONE;
	}
	
	/**
	 * Fetch all the products available.
	 *  http://localhost:8093/product/getall
	 * 
	 * @return
	 */
	@RequestMapping(value=GENERIC_PRODUCT_URL+"getall", produces="application/json")
	public List<Product> findAll() {
		LOG.info("Invoked find all");
		List<Catalogue> prodCats = catalogueRepo.findAll();
		LOG.info("Found something: " + prodCats);

		if (prodCats == null || prodCats.isEmpty())
			throw new ProductNotFoundException("No products defined in system");
		else
			return buildProducts(prodCats, findAllPrice());
	}
	
	/**
	 * Building List of products from the name passed.
	 * 
	 * @param catalogues
	 * @return
	 */
	private List<Product> buildProducts(List<Catalogue> catalogues, Map<Long, Long> productIdPriceMap) {
		 List<Product> products = new ArrayList<Product>();
		 for (Catalogue cat : catalogues){
			 products.add(buildProduct(cat, productIdPriceMap.get(cat.getId())));
		 }
		 return products;
	}
	
	/**
	 * Building the final product to be delivered.
	 * 
	 * @param cat
	 * @return
	 */
	private Product buildProduct(Catalogue cat, long price) {
		Product prod = new Product(cat.getId(), cat.getName(), cat.getCategory(), cat.getSubCategory(),
				cat.getManufacturedBy(), price);
		return prod;
	}
	
	/////*****************************************************************************************////////////////

	@LoadBalanced
	@Bean
	@Autowired
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	/**
	 * http://localhost:8093/product/price/1
	 * @param productId
	 * @return
	 */
	@RequestMapping(value=GENERIC_PRODUCT_URL+"price/{id}", produces=JSON_APP)
	public Pricing findPriceByProductID(@PathVariable(ID) String productId) {

		LOG.info("findByID() invoked: for pricing service" + productId);
		return restTemplate().getForObject(PRICING_SERVICE_URL + GENERIC_PRODUCT_URL+"price/{productId}",
				Pricing.class, productId);
	}
	
	@SuppressWarnings ("unchecked")
	@RequestMapping(value=GENERIC_PRODUCT_URL+"price/", produces=JSON_APP)
	public Map<Long, Long> findAllPrice() {

		LOG.info("invoked: for get all price");
		List<Pricing> pricingList =  restTemplate().getForObject(PRICING_SERVICE_URL + GENERIC_PRODUCT_URL+"price/",
				List.class);
		Map<Long, Long> productIdPriceMap = null;
		if(pricingList != null){
			productIdPriceMap = new HashMap<Long, Long>();
			for (Pricing price : pricingList) {
				productIdPriceMap.put(price.getProductId(),
						price.getPrice() != null ? price.getPrice().longValue() : 0l);
			}
		}
		return productIdPriceMap;
	}
	
	/**
	 * TBD
	 * 
	 * @return
	 */
	@SuppressWarnings ("unchecked")
	@RequestMapping(value=GENERIC_PRODUCT_URL+"price/{priceList}", produces=JSON_APP)
	public Map<Long, Long> findPriceByIds(List<Long> productIds) {

		LOG.info("invoked: for get all price");
		List<Pricing> pricingList =  restTemplate().getForObject(PRICING_SERVICE_URL + GENERIC_PRODUCT_URL+"price/",
				List.class);
		Map<Long, Long> productIdPriceMap = null;
		if(pricingList != null){
			productIdPriceMap = new HashMap<Long, Long>();
			for (Pricing price : pricingList) {
				productIdPriceMap.put(price.getProductId(),
						price.getPrice() != null ? price.getPrice().longValue() : 0l);
			}
		}
		return productIdPriceMap;
	}
}
