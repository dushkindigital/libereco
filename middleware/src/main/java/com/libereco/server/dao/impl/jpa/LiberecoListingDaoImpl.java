/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.dao.impl.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.PluralAttribute;

import com.libereco.server.dao.LiberecoListingDao;
import com.libereco.server.model.LiberecoListing;

/**
 * @author rrached
 *
 */
public class LiberecoListingDaoImpl extends AbstractJpaDaoSupport<Long, LiberecoListing> implements
		LiberecoListingDao {

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#saveOrUpdate(java.lang.Object)
	 */
	@Override
	public LiberecoListing saveOrUpdate(LiberecoListing obj) {
		super.persist(obj);
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(LiberecoListing obj) {
		super.remove(obj);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#find(java.lang.Object)
	 */
	@Override
	public LiberecoListing find(LiberecoListing obj) {
		return entityManager.find(LiberecoListing.class, obj.getListingId());
	}
	
	public List<LiberecoListing> findByCriteriaAllLiberecoListing() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<LiberecoListing> cq = cb.createQuery(entityClass);
		Root<LiberecoListing> liberecoListing = cq.from(entityClass);
		cq.select(liberecoListing);
		
		TypedQuery<LiberecoListing> q = entityManager.createQuery(cq);
		List<LiberecoListing> allLiberecoListings = q.getResultList();
		
		/*
		 * 
		 */
		Metamodel m = entityManager.getMetamodel();
		EntityType<LiberecoListing> LiberecoListing_ = m.entity(entityClass);
		for (PluralAttribute<LiberecoListing, ?, ?> pluralAttribute : LiberecoListing_.getDeclaredPluralAttributes()) {
			System.out.println(pluralAttribute.getName());
		}
		
		return allLiberecoListings;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#findById(java.lang.Object)
	 */
	@Override
	public LiberecoListing findById(Long id) {
		return super.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#deleteAll()
	 */
	@Override
	public Integer deleteAll() {
		return super.removeAll();
	}
	
	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#findById(java.lang.Long)
	 */
	@Override
	public LiberecoListing findByListingId(Long listingId) {
		Query q = entityManager.createQuery("from LiberecoListing where listingId = :listingId");
		q.setParameter("listingId", listingId);
		List<?> ls = null;
		return (LiberecoListing) ((ls = q.getResultList()) != null && !ls.isEmpty() ? ls.get(0) : null);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#hasLiberecoListingName(java.lang.String)
	 */
	@Override
	public boolean hasLiberecoListingName(String name) {
		boolean found = false;	
		Query q = entityManager.createQuery("from LiberecoListing where listingAttribute.name = :_name");
		q.setParameter("_name", name);
		List<?> ls = null;
		found = (ls = q.getResultList()) != null && !ls.isEmpty() ? true : false;
		
		return found;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getLiberecoListing(java.lang.String)
	 */
	@Override
	public LiberecoListing getLiberecoListing(String name) {
		Query q = entityManager.createQuery("from LiberecoListing where listingAttribute.name = :_name");
		q.setParameter("_name", name);
		List<?> ls = null;
		return (LiberecoListing) ((ls = q.getResultList()) != null && !ls.isEmpty() ? ls.get(0) : null);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getLiberecoListingIds()
	 */
	@Override
	public List<Long> getLiberecoListingIds() {
		List<Long> liberecoListingIds = new ArrayList<Long>();
		@SuppressWarnings("unchecked")
		List<Long> ids = entityManager.createQuery("select id from LiberecoListing").getResultList();
		if (ids != null && !ids.isEmpty()) {
			liberecoListingIds.addAll(ids);		
		}
		
		return liberecoListingIds;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getLiberecoListingNames()
	 */
	@Override
	public List<String> getLiberecoListingNames() {
		List<String> liberecoListingNames = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<String> names = entityManager.createQuery("select name from GenericListing").getResultList();
		if (names != null && !names.isEmpty()) {
			liberecoListingNames.addAll(names);		
		}
		return liberecoListingNames;
	}

}
