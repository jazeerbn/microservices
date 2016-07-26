package com.globomart;

import com.globomart.exceptions.UnknownServiceException;
import com.globomart.pricing.PricingService;
import com.globomart.productcatalogue.ProductCatalogueService;
import com.globomart.web.UIService;

/**
 * 
 * @author jazeer
 *
 */
public class ServiceLoader {

	public static final String EMPTY = "";
	public static final String CATALOGUE = "catalogue";
	public static final String PRICING = "price";
	public static final String WEB = "ui";
	
	public static void main(String[] args) {

		String server = EMPTY;
		
		switch(args.length){
		case 1:
			server = args[0].toLowerCase();
			break;
		case 2 :
			System.setProperty("server.port", args[1]);
		default:
			howToUse();
		}
		
		if(CATALOGUE.equalsIgnoreCase(server)){
			ProductCatalogueService.main(args);
		}else if (PRICING.equalsIgnoreCase(server)){
			PricingService.main(args);
		}else if(WEB.equalsIgnoreCase(server)){
			UIService.main(args);
		}else{
			howToUse();
			try {
				throw new UnknownServiceException("Invalid server type: " + server);
			} catch (UnknownServiceException e) {
				e.printStackTrace();
			}
		}
		
	}

	private static void howToUse() {
		
		StringBuilder usageMessage = new StringBuilder();
		System.out.println("How to execute: java -jar ... <server-name> [server-port]");
		usageMessage.append("server-name is  ").append(CATALOGUE).append("for product catalogue service or ")
		.append(PRICING).append(" for pricing service or ").append(WEB).append(" for user interface ").append("AND optional server-port is 1024");
		System.out.println(usageMessage.toString());
	}

}
