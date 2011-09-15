/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.model;


import static com.libereco.common.UserStatus.*;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.libereco.common.UserStatus;
import com.libereco.server.dao.UserDao;
import com.libereco.server.dao.impl.jpa.UserDaoImpl;

/**
 * @author rrached
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/liberecoMiddleware-applicationContext-test.xml" })
@TransactionConfiguration(transactionManager = "jpaTransactionManager", defaultRollback = true)
@Transactional
public class UserTest extends AbstractJpaDaoSupportUtils {
	@Autowired
	UserDao userDao;

	private static final Long marketplaceId = 1L;
	private static final String marketplaceName = "eBay Full Name";
	private static final String marketplaceShortName = "eBay";
	
	private static final Date now = new Date();
	
	private static final Long userId = 6001L;
	private static final String userName = "Roger Rac";
	private static final String password = "password!";
	private static final Timestamp created = new Timestamp(now.getTime());
	private static final Timestamp lastUpdated = new Timestamp(now.getTime());
	private static final UserStatus status = ACTIVE;
	private static final MarketplaceAuthorizations marketplaceAuthorization =
		createMarketplaceAuthorizations(userId, marketplaceId, lastUpdated);
	
	
	@Before
	public final void verifyInitialDatabaseState() {
		deleteFromTables();
		updateMarketplace(marketplaceId, marketplaceName, marketplaceShortName);
		updateUser(userId, userName, password, created, lastUpdated, status, marketplaceAuthorization);
	}
	
	@After
	public final void shutDown() {
		((UserDaoImpl) userDao).getTemplates().clear();
	}
	
	@Test
	public final void testPersistUser() throws Exception {
		User entity = new User().userName(userName + "XX")
								.password(password)
								.lastUpdated(lastUpdated)
								.status(status)
								.marketplaceAuthorization(createMarketplaceAuthorizations(userId, marketplaceId, lastUpdated));
		((UserDaoImpl) userDao).persist(entity);
		
		User actual = userDao.find(entity);
		assertNotNull(actual);
		assertEquals(actual.toString(), entity, actual);
	}
	
	@Test
	public final void testCreateShippingTemplate() throws Exception {
		Template t = ((UserDaoImpl) userDao).createTemplate("Shipping");
		assertNotNull(t);
		assertTrue(t instanceof LiberecoShippingTemplate);
		assertTrue(Integer.toString(((UserDaoImpl) userDao).getTemplates().size()),
					((UserDaoImpl) userDao).getTemplates().size() == 1);
	}
	
	@Test
	public final void testCreatePaymentTemplate() throws Exception {
		Template t = ((UserDaoImpl) userDao).createTemplate("Payment");
		assertNotNull(t);
		assertTrue(t instanceof LiberecoPaymentTemplate);
		assertTrue(Integer.toString(((UserDaoImpl) userDao).getTemplates().size()),
					((UserDaoImpl) userDao).getTemplates().size() == 1);
	}
	
	@Test
	public final void testCreateMultipleTemplates() throws Exception {
		Template t1 = ((UserDaoImpl) userDao).createTemplate("Payment");
		assertNotNull(t1);
		assertTrue(t1 instanceof LiberecoPaymentTemplate);
		Template t2 = ((UserDaoImpl) userDao).createTemplate("Payment");
		assertNotNull(t2);
		assertTrue(t2 instanceof LiberecoPaymentTemplate);
		assertTrue(Integer.toString(((UserDaoImpl) userDao).getTemplates().size()),
					((UserDaoImpl) userDao).getTemplates().size() == 2);	
		Template tX = ((UserDaoImpl) userDao).createTemplate("XXX");
		assertNull(tX);
		assertTrue(Integer.toString(((UserDaoImpl) userDao).getTemplates().size()),
					((UserDaoImpl) userDao).getTemplates().size() == 2);
		Template t3 = ((UserDaoImpl) userDao).createTemplate("Shipping");
		assertNotNull(t3);
		assertTrue(t3 instanceof LiberecoShippingTemplate);
		assertTrue(Integer.toString(((UserDaoImpl) userDao).getTemplates().size()),
					((UserDaoImpl) userDao).getTemplates().size() == 3);
	}
	
	@Test
	public final void testFindUser() throws Exception {
		User u = new User().id(userId)
						   .userName(userName)
						   .password(password)
						   .lastUpdated(lastUpdated)
						   .status(status)
						   .marketplaceAuthorization(createMarketplaceAuthorizations(userId, marketplaceId, lastUpdated));
		User actual = userDao.find(u);
		assertNotNull(actual);
		assertEquals(u, actual);
	}

	@Test
	public final void testFindUserById() throws Exception {
		User u = new User().id(userId)
		   .userName(userName)
		   .password(password)
		   .lastUpdated(lastUpdated)
		   .status(status)
		   .marketplaceAuthorization(createMarketplaceAuthorizations(userId, marketplaceId, lastUpdated));
		User actual = userDao.findById(userId);		
		assertNotNull(actual);
		assertEquals(u, actual);
	}

	@Test
	public final void testFindAllUsers() throws Exception {
		User u = new User().id(userId)
		   .userName(userName)
		   .password(password)
		   .lastUpdated(lastUpdated)
		   .status(status)
		   .marketplaceAuthorization(createMarketplaceAuthorizations(userId, marketplaceId, lastUpdated));
		List<User> actual = userDao.findAll();
		assertNotNull(actual);
		assertTrue(actual.size() == 1);
		assertEquals(u, actual.get(0));
	}
	
	@Test
	public final void testSaveOrUpdateUser() throws Exception {
		User u = new User().userName(userName + "XX")
							.password(password)
							.lastUpdated(lastUpdated)
							.status(status)
							.marketplaceAuthorization(createMarketplaceAuthorizations(userId, marketplaceId, lastUpdated));
		userDao.saveOrUpdate(u);
		User actual = userDao.find(u);
		assertNotNull(actual);
		assertEquals(u, actual);
	}

	@Test
	public final void testHasUserName() throws Exception {
		assertTrue(userDao.hasUserName(userName));
		assertFalse(userDao.hasUserName("NameXXXX"));
		assertFalse(userDao.hasUserName(null));
	}

	@Test
	public final void testFindUserByUserName() throws Exception {
		User u = new User().id(userId)
						   .userName(userName)
						   .password(password)
						   .lastUpdated(lastUpdated)
						   .status(status)
						   .marketplaceAuthorization(createMarketplaceAuthorizations(userId, marketplaceId, lastUpdated));
		User actual = userDao.findByUserName(userName);
		assertNotNull(actual);
		assertEquals(u, actual);
		
		actual = userDao.findByUserName("UGUIJLJQYDVBHJKCQN");
		assertNull(actual);
	}
	
	@Test
	public final void testDeleteUser() throws Exception {
		User entity = new User().userName(userName + "XX")
								.password(password)
								.lastUpdated(lastUpdated)
								.status(status)
								.marketplaceAuthorization(createMarketplaceAuthorizations(userId, marketplaceId, lastUpdated));
		((UserDaoImpl) userDao).persist(entity);
		User actual = userDao.find(entity);
		assertNotNull(actual);
		assertEquals(actual.toString(), entity, actual);
		
		userDao.delete(entity);
		actual = userDao.find(entity);
		assertNull(actual);
	}
}
