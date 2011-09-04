/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.model;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.libereco.server.dao.MarketplaceDao;
import com.libereco.server.dao.impl.jpa.MarketplaceDaoImpl;

/**
 * @author rrached
 * 
 * v0.2: Migrated the test case from {@link AbstractJpaTests} (Spring v2.0) to
 * {@link AbstractTransactionalJUnit4SpringContextTests} (Spring v3)
 * This means using simpleJdbcTemplate instead of jdbcTemplate and using the Spring 3
 * annotations along with JUnit 4 {@link SpringJUnit4ClassRunner} test runner
 * Note that there is no need for an explicit DAO setter as in:
 * public void setMarketplaceDao(MarketplaceDao marketplaceDao)
 * Every method is transactional by default
 * 
 * v0.3: Updated Marketplace model classes to reflect the one-to-many relationship
 * with shipping methods and payment methods
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/liberecoMiddleware-applicationContext-test.xml" })
@TransactionConfiguration(transactionManager = "jpaTransactionManager", defaultRollback = true)
@Transactional
public class MarketplaceTest extends AbstractJpaDaoSupportUtils {
	@Autowired
	private MarketplaceDao marketplaceDao;
	
	private static final Long marketplaceId = 1L;
	private static final String marketplaceName = "eBay Full Name";
	private static final String marketplaceShortName = "eBay";
	
	@Before
	public final void verifyInitialDatabaseState() {
		deleteFromTables();
		updateMarketplace(marketplaceId, marketplaceName, marketplaceShortName);
	}
	
	@Test
	public final void testPersistMarketplace() {
		Marketplace entity = new Marketplace();
		entity.setMarketplaceName("marketplaceName");
		entity.setMarketplaceShortName("marketplaceShortName");
		entity.setPaymentMethods(createLiberecoPaymentMethodList());
		entity.setShippingMethods(createLiberecoShippingMethodList());
		((MarketplaceDaoImpl) marketplaceDao).persist(entity);
		
		Marketplace actual = marketplaceDao.find(entity);
		assertNotNull(actual);
		assertEquals(actual.toString(), entity, actual);
	}
	
	@Test
	public final void testFindMarketplace() throws Exception {
		Marketplace marketplace = new Marketplace();
		marketplace.setId(1L);
		marketplace.setMarketplaceName("eBay Full Name");
		marketplace.setMarketplaceShortName("eBay");
		marketplace.setPaymentMethods(new ArrayList<LiberecoPaymentMethod>());
		marketplace.setShippingMethods(new ArrayList<LiberecoShippingMethod>());
		Marketplace actual = marketplaceDao.find(marketplace);
		assertNotNull(actual);
		assertEquals(marketplace, actual);
	}

	@Test
	public final void testFindByIdMarketplace() throws Exception {
		Marketplace marketplace = new Marketplace();
		marketplace.setId(1L);
		marketplace.setMarketplaceName("eBay Full Name");
		marketplace.setMarketplaceShortName("eBay");
		marketplace.setPaymentMethods(new ArrayList<LiberecoPaymentMethod>());
		marketplace.setShippingMethods(new ArrayList<LiberecoShippingMethod>());		
		Marketplace actual = marketplaceDao.findById(marketplaceId);
		assertNotNull(actual);
		assertEquals(marketplace, actual);
	}

	@Test
	public final void testFindAllMarketplace() throws Exception {
		Marketplace marketplace = new Marketplace();
		marketplace.setId(1L);
		marketplace.setMarketplaceName("eBay Full Name");
		marketplace.setMarketplaceShortName("eBay");
		marketplace.setPaymentMethods(new ArrayList<LiberecoPaymentMethod>());
		marketplace.setShippingMethods(new ArrayList<LiberecoShippingMethod>());		
		List<Marketplace> actual = marketplaceDao.findAll();
		assertNotNull(actual);
		assertTrue(actual.size() == 1);
		assertEquals(marketplace, actual.get(0));
	}

	@Test
	public final void testSaveOrUpdateMarketplace() throws Exception {
		Marketplace marketplace = new Marketplace();
		marketplace.setMarketplaceName("eBay Full Name Updated");
		marketplace.setMarketplaceShortName("eBay Updated");
		marketplaceDao.saveOrUpdate(marketplace);
		Marketplace actual = marketplaceDao.find(marketplace);
		assertNotNull(actual);
		assertEquals(marketplace, actual);
	}

	@Test
	public final void testHasMarketplaceNameMarketplace() throws Exception {
		assertTrue(marketplaceDao.hasMarketplaceName("eBay Full Name"));
		assertFalse(marketplaceDao.hasMarketplaceName("eBay Full NameXXXX"));
		assertFalse(marketplaceDao.hasMarketplaceName(null));
	}

	@Test
	public final void testGetMarketplaceMarketplace() throws Exception {
		Marketplace marketplace = new Marketplace();
		marketplace.setId(1L);
		marketplace.setMarketplaceName("eBay Full Name");
		marketplace.setMarketplaceShortName("eBay");
		marketplace.setPaymentMethods(new ArrayList<LiberecoPaymentMethod>());
		marketplace.setShippingMethods(new ArrayList<LiberecoShippingMethod>());		
		Marketplace actual = marketplaceDao.getMarketplace(marketplaceName);
		assertNotNull(actual);
		assertEquals(marketplace, actual);
		
		actual = marketplaceDao.getMarketplace("XXX");
		assertNull(actual);
	}
	
	@Test
	public final void testGetMarketplaceIdsMarketplace() throws Exception {
		List<Long> actual = marketplaceDao.getMarketplaceIds();
		assertNotNull(actual);
		assertFalse(actual.isEmpty());
		assertEquals(marketplaceId, actual.get(0));
	}
	
	@Test
	public final void testGetMarketplaceNamesMarketplace() throws Exception {
		List<String> actual = marketplaceDao.getMarketplaceNames();
		assertNotNull(actual);
		assertFalse(actual.isEmpty());
		assertEquals(marketplaceName, actual.get(0));
	}
}
