/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.dao.impl.hb;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.libereco.server.dao.PendingMarketplaceAuthorizationsDao;
import com.libereco.server.model.Marketplace;
import com.libereco.server.model.PendingMarketplaceAuthorizations;
import com.libereco.server.model.User;
import com.libereco.server.model.UserMarketplaceKey;

/**
 * @author Aleksandar
 * 
 */
@Repository("pendingMarketplaceAuthorizationsDao")
public class PendingMarketplaceAuthorizationsDaoImpl
		extends
		AbstractDaoSupport<UserMarketplaceKey, PendingMarketplaceAuthorizations>
		implements PendingMarketplaceAuthorizationsDao {

	@Override
	public PendingMarketplaceAuthorizations find(
			PendingMarketplaceAuthorizations obj) {
		return find(PendingMarketplaceAuthorizations.class, obj.getKey());
	}

	@Override
	public PendingMarketplaceAuthorizations getPendingAuthorization(User user,
			Marketplace marketplace) {
		
		PendingMarketplaceAuthorizations entity = null;
		
		Criteria criteria = getSession(false).createCriteria(PendingMarketplaceAuthorizations.class);

		criteria.add(Restrictions.eq("user.id", user.getId()));
		criteria.add(Restrictions.eq("marketplace.id", marketplace.getId()));

		entity = (PendingMarketplaceAuthorizations) criteria.uniqueResult();
				
		return entity;
	}
}
