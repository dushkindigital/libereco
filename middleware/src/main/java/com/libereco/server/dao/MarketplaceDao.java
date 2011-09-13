/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.dao;

import java.util.List;

import com.libereco.server.model.Marketplace;

/**
 * @author Aleksandar
 *
 */
public interface MarketplaceDao extends AbstractDao<Long, Marketplace> {

	public boolean hasMarketplaceName(String marketplaceName);
	
	public Marketplace getMarketplace(String marketplaceName);
	
	public List<Long> getMarketplaceIds();
	
	public List<String> getMarketplaceNames();
}
