/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
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
