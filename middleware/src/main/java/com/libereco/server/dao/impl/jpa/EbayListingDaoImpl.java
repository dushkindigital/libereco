/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.dao.impl.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.libereco.server.dao.EbayListingDao;
import com.libereco.server.model.EbayListing;

/**
 * @author rrached
 *
 */
public class EbayListingDaoImpl extends AbstractJpaDaoSupport<Long, EbayListing> implements EbayListingDao {

	/**
	 * 
	 */
	public EbayListingDaoImpl() {}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.EbayListingDao#getEbayListingListing(java.lang.String)
	 */
	@Override
	public EbayListing getEbayListing(String name) {
		Query q = entityManager.createQuery("from EbayListing where name = :name");
		q.setParameter("name", name);
		List<?> ls = null;
		return (EbayListing) ((ls = q.getResultList()) != null && !ls.isEmpty() ? ls.get(0) : null);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.EbayListingDao#getEbayListingIds()
	 */
	@Override
	public List<Long> getEbayListingIds() {
		List<Long> ebayListingIds = new ArrayList<Long>();
		@SuppressWarnings("unchecked")
		List<Long> ids = entityManager.createQuery("select id from EbayListing").getResultList();
		if (ids != null && !ids.isEmpty()) {
			ebayListingIds.addAll(ids);		
		}
		
		return ebayListingIds;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.EbayListingDao#getEbayListingNames()
	 */
	@Override
	public List<String> getEbayListingNames() {
		List<String> ebayListingNames = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<String> names = entityManager.createQuery("select name from EbayListing").getResultList();
		if (names != null && !names.isEmpty()) {
			ebayListingNames.addAll(names);		
		}
		return ebayListingNames;
	}

	@Override
	public EbayListing saveOrUpdate(EbayListing obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(EbayListing obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EbayListing find(EbayListing obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EbayListing findByListingId(Long listingId) {
		// TODO Auto-generated method stub
		return null;
	}

}
