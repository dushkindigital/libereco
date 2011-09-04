/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.model;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.libereco.common.LiberecoCategory;
import com.libereco.common.ListingCondition;
import com.libereco.common.ListingState;
import com.libereco.server.dao.LiberecoListingDao;
import com.libereco.server.dao.impl.jpa.LiberecoListingDaoImpl;

/**
 * @author rrached
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/liberecoMiddleware-applicationContext-test.xml" })
@TransactionConfiguration(transactionManager = "jpaTransactionManager", defaultRollback = true)
@Transactional
public class LiberecoListingTest extends AbstractJpaDaoSupportUtils {
	@Autowired
	private LiberecoListingDao liberecoListingDao;
	
	private static final Long marketplaceId = 1L;
	private static final String marketplaceName = "eBay Full Name";
	private static final String marketplaceShortName = "eBay";
	
	private static final Long paymentId = 3001L;
	
	private static final Long shippingId = 4001L;
	private static final String postcode = "07030";
	
	private static final Date duration = new Date();
	
	private static final Long listingId = 1000L;
	private static final String userId = "UID";
	private static final String name = "Libereco Listing Name";
	private static final Double price = 5.67D;
	private static final Integer quantity = 1;
	private static final LiberecoCategory category = LiberecoCategory.CAT_BOOKS;
	private static final ListingCondition condition = ListingCondition.GOOD;
	private static final ListingState listingState = ListingState.NEW;
	private static final String description = "desc";
	private static final Date listingDuration = duration;
	private static final byte[] picture = null;

	@Before
	public final void verifyInitialDatabaseState() {
		deleteFromTables();
		updateMarketplace(marketplaceId, marketplaceName, marketplaceShortName);
		updateLiberecoPaymentTemplate(paymentId);
		updateLiberecoShippingTemplate(shippingId, postcode);
		updateLiberecoListing(listingId, userId, name,
							  price, quantity, category,
							  condition, listingState, description,
							  listingDuration, picture, paymentId,
							  shippingId);
	}
	
	@Test
	public final void testPersistLiberecoListing() {
		LiberecoListing entity = new LiberecoListing();
		entity.setUserId(userId);
		entity.setName(name);
		entity.setPrice(price);
		entity.setQuantity(quantity);
		entity.setCategory(category);
		entity.setCondition(condition);
		entity.setListingState(listingState);
		entity.setDescription(description);
		entity.setListingDuration(listingDuration);
		entity.setPicture(picture);
		entity.addMarketplace(createMarketplace(marketplaceName + "XX", marketplaceShortName + "XX"));
		entity.setListingPayment(createLiberecoPaymentTemplate());
		entity.setListingShipping(createLiberecoShippingTemplate());
		((LiberecoListingDaoImpl) liberecoListingDao).persist(entity);
		
		LiberecoListing actual = liberecoListingDao.find(entity);
		assertNotNull(actual);
		assertEquals(actual.toString(), entity, actual);
	}
	
	@Test
	public final void testFindLiberecoListing() throws Exception {
		LiberecoListing listing = new LiberecoListing();
		listing.setListingId(1000L);
		listing.setUserId("UID");
		listing.setName("Libereco Listing Name");
		listing.setPrice(5.67D);
		listing.setQuantity(1);
		listing.setCategory(LiberecoCategory.CAT_BOOKS);
		listing.setCondition(ListingCondition.GOOD);
		listing.setListingState( ListingState.NEW);
		listing.setDescription("desc");
		listing.setListingDuration(duration);
		listing.setPicture(null);
		listing.setListingPayment(createLiberecoPaymentTemplate());
		listing.setListingShipping(createLiberecoShippingTemplate());
		LiberecoListing actual = liberecoListingDao.find(listing);
		assertNotNull(actual);
		assertEquals(listing, actual);
	}

	@Test
	public final void testFindByIdLiberecoListing() throws Exception {
		LiberecoListing listing = new LiberecoListing();
		listing.setListingId(1000L);
		listing.setUserId("UID");
		listing.setName("Libereco Listing Name");
		listing.setPrice(5.67D);
		listing.setQuantity(1);
		listing.setCategory(LiberecoCategory.CAT_BOOKS);
		listing.setCondition(ListingCondition.GOOD);
		listing.setListingState( ListingState.NEW);
		listing.setDescription("desc");
		listing.setListingDuration(duration);
		listing.setPicture(null);
		listing.setListingPayment(createLiberecoPaymentTemplate());
		listing.setListingShipping(createLiberecoShippingTemplate());
		LiberecoListing actual = liberecoListingDao.findById(listingId);
		assertNotNull(actual);
		assertEquals(listing, actual);
	}

	@Test
	public final void testFindAllLiberecoListing() throws Exception {
		LiberecoListing listing = new LiberecoListing();
		listing.setListingId(1000L);
		listing.setUserId("UID");
		listing.setName("Libereco Listing Name");
		listing.setPrice(5.67D);
		listing.setQuantity(1);
		listing.setCategory(LiberecoCategory.CAT_BOOKS);
		listing.setCondition(ListingCondition.GOOD);
		listing.setListingState( ListingState.NEW);
		listing.setDescription("desc");
		listing.setListingDuration(duration);
		listing.setPicture(null);
		listing.setListingPayment(createLiberecoPaymentTemplate());
		listing.setListingShipping(createLiberecoShippingTemplate());
		List<LiberecoListing> actual = liberecoListingDao.findAll();
		assertNotNull(actual);
		assertTrue(actual.size() == 1);
		assertEquals(listing, actual.get(0));
	}
	
	@Test
	public final void testSaveOrUpdateLiberecoListing() throws Exception {
		LiberecoListing listing = new LiberecoListing();
		listing.setUserId("UID Updated");
		listing.setName("Libereco Listing Name");
		listing.setPrice(5.67D);
		listing.setQuantity(1);
		listing.setCategory(LiberecoCategory.CAT_BOOKS);
		listing.setCondition(ListingCondition.GOOD);
		listing.setListingState( ListingState.NEW);
		listing.setDescription("desc");
		listing.setListingDuration(duration);
		listing.setPicture(null);
		listing.setListingPayment(createLiberecoPaymentTemplate());
		listing.setListingShipping(createLiberecoShippingTemplate());
		liberecoListingDao.saveOrUpdate(listing);
		LiberecoListing actual = liberecoListingDao.find(listing);
		assertNotNull(actual);
		assertEquals(listing, actual);
	}

	@Test
	public final void testHasLiberecoListingNameLiberecoListing() throws Exception {
		assertTrue(liberecoListingDao.hasLiberecoListingName("Libereco Listing Name"));
		assertFalse(liberecoListingDao.hasLiberecoListingName("Libereco Listing NameXXXX"));
		assertFalse(liberecoListingDao.hasLiberecoListingName(null));
	}

	@Test
	public final void testGetLiberecoListingLiberecoListing() throws Exception {
		LiberecoListing listing = new LiberecoListing();
		listing.setListingId(1000L);
		listing.setListingId(1000L);
		listing.setUserId("UID");
		listing.setName("Libereco Listing Name");
		listing.setPrice(5.67D);
		listing.setQuantity(1);
		listing.setCategory(LiberecoCategory.CAT_BOOKS);
		listing.setCondition(ListingCondition.GOOD);
		listing.setListingState( ListingState.NEW);
		listing.setDescription("desc");
		listing.setListingDuration(duration);
		listing.setPicture(null);
		listing.setListingPayment(createLiberecoPaymentTemplate());
		listing.setListingShipping(createLiberecoShippingTemplate());
		LiberecoListing actual = liberecoListingDao.getLiberecoListing(name);
		assertNotNull(actual);
		assertEquals(listing, actual);
		
		actual = liberecoListingDao.getLiberecoListing("UGUIJLJQYDVBHJKCQN");
		assertNull(actual);
	}
	
	@Test
	public final void testGetLiberecoListingIdsLiberecoListing() throws Exception {
		List<Long> actual = liberecoListingDao.getLiberecoListingIds();
		assertNotNull(actual);
		assertFalse(actual.isEmpty());
		assertEquals(listingId, actual.get(0));
	}
	
	@Test
	public final void testGetLiberecoListingNamesLiberecoListing() throws Exception {
		List<String> actual = liberecoListingDao.getLiberecoListingNames();
		assertNotNull(actual);
		assertFalse(actual.isEmpty());
		assertEquals(name, actual.get(0));
	}
	
}
