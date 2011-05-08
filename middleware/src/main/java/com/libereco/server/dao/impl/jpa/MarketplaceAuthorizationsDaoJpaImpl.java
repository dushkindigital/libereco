/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.dao.impl.jpa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.libereco.server.dao.MarketplaceAuthorizationsDao;
import com.libereco.server.model.Marketplace;
import com.libereco.server.model.MarketplaceAuthorizations;
import com.libereco.server.model.MarketplaceAuthorizations.CompositeKey;
import com.libereco.server.model.User;

/**
 * @author Aleksandar
 * 
 */
//@Repository("marketplaceAuthorizationsDao")
public class MarketplaceAuthorizationsDaoJpaImpl
		extends
		AbstractJpaDaoSupport<CompositeKey, MarketplaceAuthorizations>
		implements MarketplaceAuthorizationsDao {

	// @PersistenceContext
	// private EntityManager em;

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@PostConstruct
	public void init() {
		super.setEntityManagerFactory(entityManagerFactory);
	}

	@Override
	public MarketplaceAuthorizations find(MarketplaceAuthorizations obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MarketplaceAuthorizations getAuthorization(User user,
			Marketplace marketplace) {

		EntityManager em = entityManagerFactory.createEntityManager();

		Query query = em
				.createQuery("SELECT mpa FROM MarketplaceAuthorizations mpa WHERE mpa.user.id = :userId AND mpa.marketplace.id = : marketplaceId");

		query.setParameter("userId", user.getId());
		query.setParameter("marketplaceId", marketplace.getId());

		MarketplaceAuthorizations entity = (MarketplaceAuthorizations) query
				.getSingleResult();

		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MarketplaceAuthorizations> getAuthorizations(User user) {
		List<MarketplaceAuthorizations> entities = null;

		EntityManager em = entityManagerFactory.createEntityManager();

		Query query = em
				.createQuery("SELECT mpa FROM MarketplaceAuthorizations mpa WHERE mpa.user.id = :userId");

		query.setParameter("userId", user.getId());

		entities = (List<MarketplaceAuthorizations>) query.getResultList();

		return entities;
	}

	@Override
	public MarketplaceAuthorizations saveOrUpdate(MarketplaceAuthorizations obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(MarketplaceAuthorizations obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public Integer deleteAll() {
		return removeAll();
	}

	@Override
	public boolean isAuthorized(String userName, String marketplace) {
		// TODO Auto-generated method stub
		return false;
	}
}