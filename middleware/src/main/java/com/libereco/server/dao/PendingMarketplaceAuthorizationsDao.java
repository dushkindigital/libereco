/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.dao;

import com.libereco.server.model.Marketplace;
import com.libereco.server.model.PendingMarketplaceAuthorizations;
import com.libereco.server.model.User;
import com.libereco.server.model.UserMarketplaceKey;

/**
 * @author Aleksandar
 * 
 */
public interface PendingMarketplaceAuthorizationsDao extends
		AbstractDao<UserMarketplaceKey, PendingMarketplaceAuthorizations> {

	public PendingMarketplaceAuthorizations getPendingAuthorization(User user,
			Marketplace marketplace);	
}
