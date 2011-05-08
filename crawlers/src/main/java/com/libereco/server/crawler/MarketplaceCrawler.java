/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler;

import com.libereco.server.marketplace.Marketplace;


/**
 * @author Aleksandar
 *
 */
public interface MarketplaceCrawler {
	
	void crawl (CrawlingContext crawlingContext) throws CrawlingException;
	
	public Marketplace getMarketplace();
	
//	public <T extends MarketplaceCategory> void generateOwl(
//			List<T> marketplaceCategories, CrawlingContext crawlingContext)
//			throws OWLOntologyCreationException, OWLOntologyStorageException;
}
