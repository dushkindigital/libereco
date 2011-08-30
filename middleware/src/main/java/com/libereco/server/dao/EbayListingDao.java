/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.dao;

import java.util.List;

import com.libereco.server.model.EbayListing;

/**
 * @author rrached
 *
 */
public interface EbayListingDao extends LiberecoListingDao {
	
	public EbayListing getEbayListing(String name);
	
	public List<Long> getEbayListingIds();
	
	public List<String> getEbayListingNames();
}
