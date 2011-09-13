/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.dao.impl.hb;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.libereco.server.dao.MarketplaceDao;
import com.libereco.server.model.Marketplace;

/**
 * @author Aleksandar
 * 
 */
// RR
// @Repository("marketplaceDao")
public class MarketplaceDaoImpl extends AbstractDaoSupport<Long, Marketplace>
		implements MarketplaceDao {

	@Override
	public Marketplace find(Marketplace obj) {
		return find(Marketplace.class, obj.getId());
	}

	@Override
	public boolean hasMarketplaceName(String marketplaceName) {
		boolean found = false;
		Criteria criteria = getSession(false).createCriteria(Marketplace.class);

		criteria.add(Restrictions.eq("marketplaceName", marketplaceName));
		criteria.setProjection(Projections.count("id"));
		Long result = (Long) criteria.uniqueResult();

		if (result != null) {
			found = result.longValue() > 0;
		}

		return found;
	}

	@Override
	public Marketplace getMarketplace(String marketplaceName) {
		Marketplace entity = null;

		Criteria criteria = getSession(false).createCriteria(Marketplace.class);

		criteria.add(Restrictions.eq("marketplaceName", marketplaceName));
		entity = (Marketplace) criteria.uniqueResult();

		return entity;
	}

	@Override
	public List<Long> getMarketplaceIds() {
		List<Long> marketplaceIds = new ArrayList<Long>();

		// TODO: Update to run a more efficient query that doesn't pull all
		// marketplaces to extract ids
		List<Marketplace> marketplaces = findAll();
		if (marketplaces != null) {
			for (Marketplace mp : marketplaces) {
				marketplaceIds.add(mp.getId());
			}
		}

		return marketplaceIds;
	}

	@Override
	public List<String> getMarketplaceNames() {
		List<String> marketplaceIds = new ArrayList<String>();

		// TODO: Update to run a more efficient query that doesn't pull all
		// marketplaces to extract ids
		List<Marketplace> marketplaces = findAll();
		if (marketplaces != null) {
			for (Marketplace mp : marketplaces) {
				marketplaceIds.add(mp.getMarketplaceName());
			}
		}

		return marketplaceIds;
	}

}