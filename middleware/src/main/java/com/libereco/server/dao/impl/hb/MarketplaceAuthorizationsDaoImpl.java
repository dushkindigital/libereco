/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.dao.impl.hb;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.libereco.server.dao.MarketplaceAuthorizationsDao;
import com.libereco.server.model.Marketplace;
import com.libereco.server.model.MarketplaceAuthorizations;
import com.libereco.server.model.MarketplaceAuthorizations.CompositeKey;
import com.libereco.server.model.User;

/**
 * @author Aleksandar
 * 
 */
@Repository("marketplaceAuthorizationsDao")
public class MarketplaceAuthorizationsDaoImpl extends
		AbstractDaoSupport<CompositeKey, MarketplaceAuthorizations> implements
		MarketplaceAuthorizationsDao {


	@Override
	public MarketplaceAuthorizations find(MarketplaceAuthorizations obj) {
		return find(MarketplaceAuthorizations.class, obj.getKey());
	}

	@Override
	public List<MarketplaceAuthorizations> findAll() {
		return findAll(MarketplaceAuthorizations.class);
	}

	@Override
	public MarketplaceAuthorizations getAuthorization(User user,
			Marketplace marketplace) {
		
		MarketplaceAuthorizations entity = null;
		
		Criteria criteria = getSession(false).createCriteria(MarketplaceAuthorizations.class);

		criteria.add(Restrictions.eq("user.id", user.getId()));
		criteria.add(Restrictions.eq("marketplace.id", marketplace.getId()));

		entity = (MarketplaceAuthorizations) criteria.uniqueResult();
				
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MarketplaceAuthorizations> getAuthorizations(User user) {
		List<MarketplaceAuthorizations> entities = null;
		
		Criteria criteria = getSession(false).createCriteria(MarketplaceAuthorizations.class);
		criteria.add(Restrictions.eq("user.id", user.getId()));
		entities = (List<MarketplaceAuthorizations>) criteria.list();
		return entities;
	}

	@Override
	public boolean isAuthorized(String userName, String marketplaceName) {
		MarketplaceAuthorizations entity = null;
		
		Criteria criteria = getSession(false).createCriteria(MarketplaceAuthorizations.class);

		// TODO: Use count instead of entity retrieval
		criteria.add(Restrictions.eq("user.userName", userName));
		criteria.add(Restrictions.eq("marketplace.marketplaceName", marketplaceName));

		entity = (MarketplaceAuthorizations) criteria.uniqueResult();
		
		return (entity != null);
	}
}