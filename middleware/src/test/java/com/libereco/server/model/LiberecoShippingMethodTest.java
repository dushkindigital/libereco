/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.model;

import static com.libereco.common.ShippingLevelType.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.libereco.common.ShippingLevelType;
import com.libereco.server.dao.LiberecoShippingMethodDao;
import com.libereco.server.dao.MarketplaceDao;
import com.libereco.server.dao.impl.jpa.LiberecoShippingMethodDaoImpl;
import com.libereco.server.dao.impl.jpa.MarketplaceDaoImpl;

/**
 * @author rrached
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/liberecoMiddleware-applicationContext-test.xml" })
@TransactionConfiguration(transactionManager = "jpaTransactionManager", defaultRollback = true)
@Transactional
public class LiberecoShippingMethodTest extends AbstractJpaDaoSupportUtils {
	@Autowired
	private LiberecoShippingMethodDao liberecoShippingMethodDao;
	@Autowired
	private MarketplaceDao marketplaceDao;
	
	private static final Long marketplaceId = 1L;
	private static final String marketplaceName = "eBay Full Name";
	private static final String marketplaceShortName = "eBay";
	
	private static final Long shippingMethodId = 2000L;
	private static final Long shippingMethodId2 = 2001L;
	private static final ShippingLevelType shippingType = OVERNIGHT;
	
	@Before
	public final void verifyInitialDatabaseState() {
		deleteFromTables();
		updateMarketplace(marketplaceId, marketplaceName, marketplaceShortName);
		updateLiberecoShippingMethod(shippingMethodId, shippingType);
		updateLiberecoShippingMethod(shippingMethodId2, shippingType);
	}
	
	@Test
	public final void testPersistLiberecoShippingMethod() {
		LiberecoShippingMethod entity = new LiberecoShippingMethod();
		entity.setShippingLevelType(TWODAY);
		((LiberecoShippingMethodDaoImpl) liberecoShippingMethodDao).persist(entity);
		
		LiberecoShippingMethod actual = liberecoShippingMethodDao.find(entity);
		assertNotNull(actual);
		assertEquals(actual.toString(), entity, actual);
	}
	
	
	@Test
	public final void testFindLiberecoShippingMethod() throws Exception {
		LiberecoShippingMethod shipping = new LiberecoShippingMethod();
		shipping.setShippingMethodId(2000L);
		shipping.setShippingLevelType(OVERNIGHT);
		LiberecoShippingMethod actual = liberecoShippingMethodDao.find(shipping);
		assertNotNull(actual);
		assertEquals(shipping, actual);
	}

	@Test
	public final void testFindByIdLiberecoShippingMethod() throws Exception {
		LiberecoShippingMethod shipping = new LiberecoShippingMethod();
		shipping.setShippingMethodId(2000L);
		shipping.setShippingLevelType(OVERNIGHT);
		LiberecoShippingMethod actual = liberecoShippingMethodDao.findById(shippingMethodId);
		assertNotNull(actual);
		assertEquals(shipping, actual);
	}

	@Test
	public final void testFindAllLiberecoShippingMethod() throws Exception {
		LiberecoShippingMethod shipping = new LiberecoShippingMethod();
		shipping.setShippingMethodId(2000L);
		shipping.setShippingLevelType(OVERNIGHT);
		LiberecoShippingMethod shipping2 = new LiberecoShippingMethod();
		shipping2.setShippingMethodId(2001L);
		shipping2.setShippingLevelType(OVERNIGHT);
		List<LiberecoShippingMethod> shippings = new ArrayList<LiberecoShippingMethod>();
		shippings.add(shipping);
		shippings.add(shipping2);
		
		List<LiberecoShippingMethod> actual = liberecoShippingMethodDao.findAll();
		assertNotNull(actual);
		assertTrue(actual.size() == shippings.size());
		assertEquals(shippings, actual);
	}
	
	@Test
	public final void testSaveOrUpdateLiberecoShippingMethod() throws Exception {
		LiberecoShippingMethod shipping = new LiberecoShippingMethod();
		shipping.setShippingLevelType(THREEDAY);
		liberecoShippingMethodDao.saveOrUpdate(shipping);
		LiberecoShippingMethod actual = liberecoShippingMethodDao.find(shipping);
		assertNotNull(actual);
		assertEquals(shipping, actual);
	}

	@Test
	public final void testGetLiberecoShippingMethodsLiberecoShippingMethod() throws Exception {
		LiberecoShippingMethod shipping = new LiberecoShippingMethod();
		shipping.setShippingMethodId(2000L);
		shipping.setShippingLevelType(OVERNIGHT);
		LiberecoShippingMethod shipping2 = new LiberecoShippingMethod();
		shipping2.setShippingMethodId(2001L);
		shipping2.setShippingLevelType(OVERNIGHT);
		List<LiberecoShippingMethod> shippings = new ArrayList<LiberecoShippingMethod>();
		shippings.add(shipping);
		shippings.add(shipping2);
	
		List<LiberecoShippingMethod> actual = liberecoShippingMethodDao.getLiberecoShippingMethods(OVERNIGHT);
		assertNotNull(actual);
		assertEquals(shippings, actual);
		
		actual = liberecoShippingMethodDao.getLiberecoShippingMethods(STANDARD);
		assertNull(actual);
	}
	
	@Test
	public final void testGetLiberecoShippingMethodIdsLiberecoShippingMethod() throws Exception {
		List<Long> actual = liberecoShippingMethodDao.getLiberecoShippingMethodIds();
		assertNotNull(actual);
		assertFalse(actual.isEmpty());
		assertTrue(actual.contains(shippingMethodId));
		assertTrue(actual.contains(shippingMethodId2));
	}
	
	@Test
	public final void testHasLiberecoShippingTypeLiberecoShippingMethod() throws Exception {
		assertTrue(liberecoShippingMethodDao.hasLiberecoShippingType(OVERNIGHT));
		assertFalse(liberecoShippingMethodDao.hasLiberecoShippingType(STANDARD));
		assertFalse(liberecoShippingMethodDao.hasLiberecoShippingType(null));
	}
	
	@Test
	public final void testFindLiberecoShippingMethodsByMarketplaceLiberecoShippingMethod() throws Exception {
		Marketplace marketplace = createMarketplace("marketplaceName", "marketplaceShortName");
		((MarketplaceDaoImpl) marketplaceDao).persist(marketplace);
		
		List<LiberecoShippingMethod> actual = 
			liberecoShippingMethodDao.findLiberecoShippingMethodsByMarketplace(marketplace);
		System.out.println(actual);
		assertNotNull(actual);
		assertFalse(actual.isEmpty());
		for (LiberecoShippingMethod liberecoShippingMethod : actual) {
			assertTrue(liberecoShippingMethod.getShippingLevelType().equals(OVERNIGHT));
		}
	}
}
