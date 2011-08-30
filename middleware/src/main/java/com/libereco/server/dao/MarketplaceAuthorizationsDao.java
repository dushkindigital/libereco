/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.dao;

import java.util.List;

import com.libereco.server.model.Marketplace;
import com.libereco.server.model.MarketplaceAuthorizations;
import com.libereco.server.model.User;

/**
 * @author Aleksandar
 * 
 */
public interface MarketplaceAuthorizationsDao
		extends
		AbstractDao<MarketplaceAuthorizations.CompositeKey, MarketplaceAuthorizations> {

	public MarketplaceAuthorizations getAuthorization(User user,
			Marketplace marketplace);

	public List<MarketplaceAuthorizations> getAuthorizations(User user);
	
	public boolean isAuthorized(String userName, String marketplaceName);	
}
