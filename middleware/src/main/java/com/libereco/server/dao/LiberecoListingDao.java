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
	
	public boolean hasLiberecoListingName(String name);
	
	public LiberecoListing getLiberecoListing(String name);
	
	public List<Long> getLiberecoListingIds();
	
	public List<String> getLiberecoListingNames();
}
