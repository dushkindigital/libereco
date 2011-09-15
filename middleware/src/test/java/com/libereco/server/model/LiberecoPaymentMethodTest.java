/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.model;

import static com.libereco.common.LiberecoPaymentType.*;
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

import com.libereco.common.LiberecoPaymentType;
import com.libereco.server.dao.LiberecoPaymentMethodDao;
import com.libereco.server.dao.MarketplaceDao;
import com.libereco.server.dao.impl.jpa.LiberecoPaymentMethodDaoImpl;
import com.libereco.server.dao.impl.jpa.MarketplaceDaoImpl;

/**
 * @author rrached
 * 
 * v0.2: Updated the test to reflect the new OO model; there is only one
 * field in the Libereco_Payment_Method table now (apart from the ID): the
 * payment type described in {@link LiberecoPaymentType}
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/liberecoMiddleware-applicationContext-test.xml" })
@TransactionConfiguration(transactionManager = "jpaTransactionManager", defaultRollback = true)
@Transactional
public class LiberecoPaymentMethodTest extends AbstractJpaDaoSupportUtils {
	@Autowired
	private LiberecoPaymentMethodDao liberecoPaymentMethodDao;
	@Autowired
	private MarketplaceDao marketplaceDao;
	
	private static final Long marketplaceId = 1L;
	private static final String marketplaceName = "eBay Full Name";
	private static final String marketplaceShortName = "eBay";
	
	private static final Long paymentMethodId = 1000L;
	private static final Long paymentMethodId2 = 1001L;
	private static final LiberecoPaymentType paymentType = AMERICAN_EXPRESS;
	
	@Before
	public final void verifyInitialDatabaseState() {
		deleteFromTables();
		updateMarketplace(marketplaceId, marketplaceName, marketplaceShortName);
		updateLiberecoPaymentMethod(paymentMethodId, paymentType);
		updateLiberecoPaymentMethod(paymentMethodId2, paymentType);
	}
	
	@Test
	public final void testPersistLiberecoPaymentMethod() {
		LiberecoPaymentMethod entity = new LiberecoPaymentMethod();
		entity.setPaymentMethodType(AMERICAN_EXPRESS);
		((LiberecoPaymentMethodDaoImpl) liberecoPaymentMethodDao).persist(entity);
		
		LiberecoPaymentMethod actual = liberecoPaymentMethodDao.find(entity);
		assertNotNull(actual);
		assertEquals(actual.toString(), entity, actual);
	}
	
	
	@Test
	public final void testFindLiberecoPaymentMethod() throws Exception {
		LiberecoPaymentMethod payment = new LiberecoPaymentMethod();
		payment.setPaymentMethodId(1000L);
		payment.setPaymentMethodType(AMERICAN_EXPRESS);
		LiberecoPaymentMethod actual = liberecoPaymentMethodDao.find(payment);
		assertNotNull(actual);
		assertEquals(payment, actual);
	}

	@Test
	public final void testFindByIdLiberecoPaymentMethod() throws Exception {
		LiberecoPaymentMethod payment = new LiberecoPaymentMethod();
		payment.setPaymentMethodId(1000L);
		payment.setPaymentMethodType(AMERICAN_EXPRESS);
		LiberecoPaymentMethod actual = liberecoPaymentMethodDao.findById(paymentMethodId);
		assertNotNull(actual);
		assertEquals(payment, actual);
	}

	@Test
	public final void testFindAllLiberecoPaymentMethod() throws Exception {
		LiberecoPaymentMethod payment = new LiberecoPaymentMethod();
		payment.setPaymentMethodId(1000L);
		payment.setPaymentMethodType(AMERICAN_EXPRESS);
		LiberecoPaymentMethod payment2 = new LiberecoPaymentMethod();
		payment2.setPaymentMethodId(1001L);
		payment2.setPaymentMethodType(AMERICAN_EXPRESS);
		List<LiberecoPaymentMethod> payments = new ArrayList<LiberecoPaymentMethod>();
		payments.add(payment);
		payments.add(payment2);
		
		List<LiberecoPaymentMethod> actual = liberecoPaymentMethodDao.findAll();
		assertNotNull(actual);
		assertTrue(actual.size() == payments.size());
		assertEquals(payments, actual);
	}
	
	@Test
	public final void testSaveOrUpdateLiberecoPaymentMethod() throws Exception {
		LiberecoPaymentMethod payment = new LiberecoPaymentMethod();
		payment.setPaymentMethodType(PAYPAL);
		liberecoPaymentMethodDao.saveOrUpdate(payment);
		LiberecoPaymentMethod actual = liberecoPaymentMethodDao.find(payment);
		assertNotNull(actual);
		assertEquals(payment, actual);
	}

	@Test
	public final void testGetLiberecoPaymentMethodsLiberecoPaymentMethod() throws Exception {
		LiberecoPaymentMethod payment = new LiberecoPaymentMethod();
		payment.setPaymentMethodId(1000L);
		payment.setPaymentMethodType(AMERICAN_EXPRESS);
		LiberecoPaymentMethod payment2 = new LiberecoPaymentMethod();
		payment2.setPaymentMethodId(1001L);
		payment2.setPaymentMethodType(AMERICAN_EXPRESS);
		List<LiberecoPaymentMethod> payments = new ArrayList<LiberecoPaymentMethod>();
		payments.add(payment);
		payments.add(payment2);
	
		List<LiberecoPaymentMethod> actual = liberecoPaymentMethodDao.getLiberecoPaymentMethods(AMERICAN_EXPRESS);
		assertNotNull(actual);
		assertEquals(payments, actual);
		
		actual = liberecoPaymentMethodDao.getLiberecoPaymentMethods(PERSONAL_CHECK);
		assertNull(actual);
	}
	
	@Test
	public final void testGetLiberecoPaymentMethodIdsLiberecoPaymentMethod() throws Exception {
		List<Long> actual = liberecoPaymentMethodDao.getLiberecoPaymentMethodIds();
		assertNotNull(actual);
		assertFalse(actual.isEmpty());
		assertTrue(actual.contains(paymentMethodId));
		assertTrue(actual.contains(paymentMethodId2));
	}
	
	@Test
	public final void testHasLiberecoPaymentTypeLiberecoPaymentMethod() throws Exception {
		assertTrue(liberecoPaymentMethodDao.hasLiberecoPaymentType(AMERICAN_EXPRESS));
		assertFalse(liberecoPaymentMethodDao.hasLiberecoPaymentType(MASTERCARD));
		assertFalse(liberecoPaymentMethodDao.hasLiberecoPaymentType(null));
	}
	
	@Test
	public final void testFindLiberecoPaymentMethodsByMarketplaceLiberecoPaymentMethod() throws Exception {
		Marketplace marketplace = createMarketplace("marketplaceName", "marketplaceShortName");
		((MarketplaceDaoImpl) marketplaceDao).persist(marketplace);
		
		List<LiberecoPaymentMethod> actual = 
			liberecoPaymentMethodDao.findLiberecoPaymentMethodsByMarketplace(marketplace);
		System.out.println(actual);
		assertNotNull(actual);
		assertFalse(actual.isEmpty());
		for (LiberecoPaymentMethod liberecoPaymentMethod : actual) {
			assertTrue(liberecoPaymentMethod.getPaymentMethodType().equals(AMERICAN_EXPRESS));
		}
	}
}
