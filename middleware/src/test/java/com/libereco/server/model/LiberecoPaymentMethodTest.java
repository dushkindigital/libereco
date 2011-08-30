/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
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

/**
 * @author rrached
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/liberecoMiddleware-applicationContext-test.xml" })
@TransactionConfiguration(transactionManager = "jpaTransactionManager", defaultRollback = true)
@Transactional
public class LiberecoPaymentMethodTest extends AbstractJpaDaoSupportUtils {
	@Autowired
	private LiberecoPaymentMethodDao liberecoPaymentMethodDao;
	
	private static final Long marketplaceId = 1L;
	private static final String marketplaceName = "eBay Full Name";
	private static final String marketplaceShortName = "eBay";
	
	private static final Long id = 1000L;
	private static final Long id2 = 1001L;
	private static final LiberecoPaymentType paymentType = AMERICAN_EXPRESS;
	
	@Before
	public final void verifyInitialDatabaseState() {
		deleteFromTables();
		updateMarketplace(marketplaceId, marketplaceName, marketplaceShortName);
		updateLiberecoPaymentMethod(id, paymentType, marketplaceId);
		updateLiberecoPaymentMethod(id2, paymentType, marketplaceId);
	}
	
	@Test
	public final void testFindLiberecoPaymentMethod() throws Exception {
		LiberecoPaymentMethod payment = new LiberecoPaymentMethod();
		payment.setId(1000L);
		payment.setPaymentMethodId(AMERICAN_EXPRESS);
		payment.setMarketplace(createMarketplace(marketplaceId, marketplaceName, marketplaceShortName));
		LiberecoPaymentMethod actual = liberecoPaymentMethodDao.find(payment);
		assertNotNull(actual);
		assertEquals(payment, actual);
	}

	@Test
	public final void testFindByIdLiberecoPaymentMethod() throws Exception {
		LiberecoPaymentMethod payment = new LiberecoPaymentMethod();
		payment.setId(1000L);
		payment.setPaymentMethodId(AMERICAN_EXPRESS);
		payment.setMarketplace(createMarketplace(marketplaceId, marketplaceName, marketplaceShortName));
		LiberecoPaymentMethod actual = liberecoPaymentMethodDao.findById(id);
		assertNotNull(actual);
		assertEquals(payment, actual);
	}

	@Test
	public final void testFindAllLiberecoPaymentMethod() throws Exception {
		LiberecoPaymentMethod payment = new LiberecoPaymentMethod();
		payment.setId(1000L);
		payment.setPaymentMethodId(AMERICAN_EXPRESS);
		payment.setMarketplace(createMarketplace(marketplaceId, marketplaceName, marketplaceShortName));
		LiberecoPaymentMethod payment2 = new LiberecoPaymentMethod();
		payment2.setId(1001L);
		payment2.setPaymentMethodId(AMERICAN_EXPRESS);
		payment2.setMarketplace(createMarketplace(marketplaceId, marketplaceName, marketplaceShortName));
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
		payment.setPaymentMethodId(PAYPAL);
		payment.setMarketplace(createMarketplace(marketplaceId, marketplaceName, marketplaceShortName));
		liberecoPaymentMethodDao.saveOrUpdate(payment);
		LiberecoPaymentMethod actual = liberecoPaymentMethodDao.find(payment);
		assertNotNull(actual);
		assertEquals(payment, actual);
	}

	@Test
	public final void testGetLiberecoPaymentMethodsLiberecoPaymentMethod() throws Exception {
		LiberecoPaymentMethod payment = new LiberecoPaymentMethod();
		payment.setId(1000L);
		payment.setPaymentMethodId(AMERICAN_EXPRESS);
		payment.setMarketplace(createMarketplace(marketplaceId, marketplaceName, marketplaceShortName));
		LiberecoPaymentMethod payment2 = new LiberecoPaymentMethod();
		payment2.setId(1001L);
		payment2.setPaymentMethodId(AMERICAN_EXPRESS);
		payment2.setMarketplace(createMarketplace(marketplaceId, marketplaceName, marketplaceShortName));
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
		assertTrue(actual.contains(id));
		assertTrue(actual.contains(id2));
	}
	
	@Test
	public final void testHasLiberecoPaymentTypeLiberecoPaymentMethod() throws Exception {
		assertTrue(liberecoPaymentMethodDao.hasLiberecoPaymentType(AMERICAN_EXPRESS));
		assertFalse(liberecoPaymentMethodDao.hasLiberecoPaymentType(MASTERCARD));
		assertFalse(liberecoPaymentMethodDao.hasLiberecoPaymentType(null));
	}
	
	@Test
	public final void testFindLiberecoPaymentMethodsByMarketplaceLiberecoPaymentMethod() throws Exception {
		LiberecoPaymentMethod payment = new LiberecoPaymentMethod();
		payment.setId(1000L);
		payment.setPaymentMethodId(AMERICAN_EXPRESS);
		payment.setMarketplace(createMarketplace(marketplaceId, marketplaceName, marketplaceShortName));
		LiberecoPaymentMethod payment2 = new LiberecoPaymentMethod();
		payment2.setId(1001L);
		payment2.setPaymentMethodId(AMERICAN_EXPRESS);
		payment2.setMarketplace(createMarketplace(marketplaceId, marketplaceName, marketplaceShortName));
		
		List<LiberecoPaymentMethod> actual = liberecoPaymentMethodDao.findLiberecoPaymentMethodsByMarketplace(
				createMarketplace(marketplaceId, marketplaceName, marketplaceShortName));
		assertNotNull(actual);
		assertFalse(actual.isEmpty());
		assertTrue(actual.contains(payment));
		assertTrue(actual.contains(payment2));
	}
	
	/**
	 * @param marketplaceId
	 * @param marketplaceName
	 * @param marketplaceShortName
	 * @return {@link Marketplace}
	 */
	private Marketplace createMarketplace(Long marketplaceId,
										  String marketplaceName,
										  String marketplaceShortName) {
		Marketplace marketplace = new Marketplace();
		marketplace.setId(marketplaceId);
		marketplace.setMarketplaceName(marketplaceName);
		marketplace.setMarketplaceShortName(marketplaceShortName);
		return marketplace;
	}
	
}
