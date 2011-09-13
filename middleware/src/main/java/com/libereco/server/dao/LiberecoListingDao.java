/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.dao;

import java.util.List;

import com.libereco.server.model.LiberecoListing;

/**
 * @author rrached
 *
 */
public interface LiberecoListingDao extends AbstractDao<Long, LiberecoListing> {

	public LiberecoListing findByListingId(Long listingId);
	
	public boolean hasLiberecoListingName(String name);
	
	public LiberecoListing getLiberecoListing(String name);
	
	public List<Long> getLiberecoListingIds();
	
	public List<String> getLiberecoListingNames();
}
