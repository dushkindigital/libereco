/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
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
