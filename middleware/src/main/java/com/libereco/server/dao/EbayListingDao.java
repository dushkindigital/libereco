/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
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
