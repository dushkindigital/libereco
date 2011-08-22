/**
 * 
 */
package com.libereco.server.dao;

import java.util.List;

import com.libereco.server.model.LiberecoListing;

/**
 * @author rrached
 *
 */
public interface LiberecoListingDao extends AbstractDao<Long, LiberecoListing> {

	public LiberecoListing findByListingId(Long listingId);
	
	boolean hasLiberecoListingName(String name);
	
	LiberecoListing getLiberecoListing(String name);
	
	List<Long> getLiberecoListingIds();
	
	List<String> getLiberecoListingNames();
}
