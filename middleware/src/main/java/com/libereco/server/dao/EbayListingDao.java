/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.dao;

import java.util.List;

import com.libereco.server.model.EbayListing;

/**
 * @author rrached
 *
 */
public interface EbayListingDao extends AbstractDao<Long, EbayListing> {
	
	public EbayListing findByListingId(Long listingId);

	public EbayListing getEbayListing(String name);
	
	public List<Long> getEbayListingIds();
	
	public List<String> getEbayListingNames();
}
