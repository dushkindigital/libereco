/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.libereco.server.auth.ebay.EbayAuthorizer;
import com.libereco.server.auth.etsy.EtsyAuthorizer;

/**
 * @author Aleksandar
 * 
 */
public class ServiceManager {
	// We could initialize service manager in various ways. One approach would
	// be using bean reference factory. This would require beanRefFactory.xml
	// configuration but that approach is more flexible because it allows us to
	// change application context file that is used to initialize service
	// manager. An alternative approach is to configure service manager by
	// directly specifying application context file in this class.

	// private static BeanFactoryReference bf;

	private static String[] paths;

	private static ClassPathXmlApplicationContext ctx = null;

	static {
		paths = new String[] { "liberecoWeb-applicationContext.xml",  "liberecoMiddleware-applicationContext.xml"};
		ctx = new ClassPathXmlApplicationContext(paths);

		// BeanFactoryLocator bfl = SingletonBeanFactoryLocator.getInstance();
		// bf = bfl.useBeanFactory("com.libereco.server");
	}

	// TODO: Move to the marketplace service and expose a method in the service
	// that returns authorizer for a marketplace
	public static EbayAuthorizer getEbayAuthorizer() {
		return (EbayAuthorizer) ctx.getBean("ebayAuthorizer");
	}

	public static EtsyAuthorizer getEtsyAuthorizer() {
		return (EtsyAuthorizer) ctx.getBean("etsyAuthorizer");
	}

	public static UserService getUserService() {
		return (UserService) ctx.getBean("userService");
	}
	
	public static MarketplaceService getMarketplaceService() {
		return (MarketplaceService) ctx.getBean("marketplaceService");
	}
	
//	
//	public static CrawlingService getCrawlingService() {
//		return (CrawlingService) ctx.getBean("crawlingService");
//	}
	
//	public static MarketplaceAuthService getMarketplaceAuthService() {
//		return (MarketplaceAuthService) ctx.getBean("marketplaceAuthService");
//	}
}
