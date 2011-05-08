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

import com.libereco.server.dao.MarketplaceDao;
import com.libereco.server.model.Marketplace;

/**
 * @author Aleksandar
 * 
 */
//@Repository("marketplaceDao")
public class MarketplaceDaoJpaImpl extends
		AbstractJpaDaoSupport<Long, Marketplace> implements MarketplaceDao {

	// @PersistenceContext
	// private EntityManager em;

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@PostConstruct
	public void init() {
		super.setEntityManagerFactory(entityManagerFactory);
	}

	@Override
	public Marketplace find(Marketplace obj) {
		return findById(obj.getId());
	}

	@Override
	public boolean hasMarketplaceName(String marketplaceName) {
		EntityManager em = entityManagerFactory.createEntityManager();

		Query query = em
				.createQuery("SELECT mp FROM Marketplace mp WHERE mp.marketplaceName = :marketplaceName");

		query.setParameter("marketplaceName", marketplaceName);
		Marketplace entity = (Marketplace) query.getSingleResult();

		return entity != null;
	}

	@Override
	public Marketplace getMarketplace(String marketplaceName) {
		EntityManager em = entityManagerFactory.createEntityManager();

		Query query = em
				.createQuery("SELECT mp FROM Marketplace mp WHERE mp.marketplaceName = :marketplaceName");

		query.setParameter("marketplaceName", marketplaceName);
		Marketplace entity = (Marketplace) query.getSingleResult();

		return entity;
	}

	@Override
	public Marketplace saveOrUpdate(Marketplace obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Marketplace obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer deleteAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> getMarketplaceIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getMarketplaceNames() {
		// TODO Auto-generated method stub
		return null;
	}
}