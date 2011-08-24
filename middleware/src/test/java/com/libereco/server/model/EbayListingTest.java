/**
 * 
 */
package com.libereco.server.model;

import static java.sql.Types.*;
import static org.junit.Assert.*;

import java.util.Date;
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

import com.libereco.common.LiberecoCategory;
import com.libereco.common.ListingCondition;
import com.libereco.common.ListingState;
import com.libereco.common.ReturnPolicy;
import com.libereco.server.dao.EbayListingDao;

/**
 * @author rrached
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/liberecoMiddleware-applicationContext-test.xml" })
@TransactionConfiguration(transactionManager = "jpaTransactionManager", defaultRollback = true)
@Transactional
public class EbayListingTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private EbayListingDao ebayListingDao;
	
	private static final Long marketplaceId = 1L;
	private static final String marketplaceName = "eBay Full Name";
	private static final String marketplaceShortName = "eBay";
	
	private static final Date duration = new Date();
	
	private static final Long listingId = 1000L;
	private static final String userId = "UID";
	private static final String name = "Ebay Listing Name";
	private static final Double price = 5.67D;
	private static final Integer quantity = 1;
	private static final LiberecoCategory category = LiberecoCategory.CAT_BOOKS;
	private static final ListingCondition condition = ListingCondition.GOOD;
	private static final ListingState listingState = ListingState.NEW;
	private static final String description = "desc";
	private static final Date listingDuration = duration;
	private static final byte[] picture = null;
	
	private static final ReturnPolicy returnPolicy = ReturnPolicy.THIRTY_DAY_RETURN;
	private static final Integer dispatchTimeMax = 40;

	@Before
	public final void verifyInitialDatabaseState() {
		deleteFromTables("ebay_listing", "libereco_listing", "marketplace");
		
		simpleJdbcTemplate.update("insert into marketplace (id, marketplaceName, marketplaceShortName) "
				+ "values (?, ?, ?)", 
				marketplaceId,
				marketplaceName,
				marketplaceShortName);
		simpleJdbcTemplate.getJdbcOperations().update(
				"insert into libereco_listing (id"
				+ ",listing_id"
				+ ", userid"
				+ ", name"
				+ ", price"
				+ ", quantity"
				+ ", category"
				+ ", condition"
				+ ", listingstate"
				+ ", description"
				+ ", listingduration"
				+ ", picture) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[] { marketplaceId,
				listingId,
				userId,
				name,
				price,
				quantity,
				category.getCategory(),
				condition.toString(),
				listingState.toString(),
				description,
				listingDuration,
				picture},
				new int[] { BIGINT,
				BIGINT,
				VARCHAR,
				VARCHAR,
				DOUBLE,
				INTEGER,
				VARCHAR,
				VARCHAR,
				VARCHAR,
				VARCHAR,
				TIMESTAMP,
				BINARY});
		simpleJdbcTemplate.getJdbcOperations().update(
				"insert into ebay_listing (id"
				+ ", returnPolicy"
				+ ", dispatchTimeMax) "
				+ "values (?, ?, ?)",
				new Object[] { marketplaceId,
				returnPolicy.toString(),
				dispatchTimeMax},
				new int[] { BIGINT,
				VARCHAR,
				INTEGER});
	}
	
	@Test
	public final void testFindLiberecoListing() throws Exception {
		LiberecoListing listing = new LiberecoListing();
		listing.setId(1L);
		listing.setMarketplaceName("eBay Full Name");
		listing.setMarketplaceShortName("eBay");
		listing.setListingId(1000L);
		listing.setUserId("UID");
		listing.setName("Ebay Listing Name");
		listing.setPrice(5.67D);
		listing.setQuantity(1);
		listing.setCategory(LiberecoCategory.CAT_BOOKS);
		listing.setCondition(ListingCondition.GOOD);
		listing.setListingState( ListingState.NEW);
		listing.setDescription("desc");
		listing.setListingDuration(duration);
		listing.setPicture(null);
		LiberecoListing actual = ebayListingDao.find(listing);
		assertNotNull(actual);
		assertEquals(listing, actual);
	}

	@Test
	public final void testFindByIdLiberecoListing() throws Exception {
		LiberecoListing listing = new LiberecoListing();
		listing.setId(1L);
		listing.setMarketplaceName("eBay Full Name");
		listing.setMarketplaceShortName("eBay");
		listing.setListingId(1000L);
		listing.setUserId("UID");
		listing.setName("Ebay Listing Name");
		listing.setPrice(5.67D);
		listing.setQuantity(1);
		listing.setCategory(LiberecoCategory.CAT_BOOKS);
		listing.setCondition(ListingCondition.GOOD);
		listing.setListingState( ListingState.NEW);
		listing.setDescription("desc");
		listing.setListingDuration(duration);
		listing.setPicture(null);
		LiberecoListing actual = ebayListingDao.findById(marketplaceId);
		assertNotNull(actual);
		assertEquals(listing, actual);
	}

	@Test
	public final void testFindByListingIdLiberecoListing() throws Exception {
		LiberecoListing listing = new LiberecoListing();
		listing.setId(1L);
		listing.setMarketplaceName("eBay Full Name");
		listing.setMarketplaceShortName("eBay");
		listing.setListingId(1000L);
		listing.setUserId("UID");
		listing.setName("Ebay Listing Name");
		listing.setPrice(5.67D);
		listing.setQuantity(1);
		listing.setCategory(LiberecoCategory.CAT_BOOKS);
		listing.setCondition(ListingCondition.GOOD);
		listing.setListingState( ListingState.NEW);
		listing.setDescription("desc");
		listing.setListingDuration(duration);
		listing.setPicture(null);
		LiberecoListing actual = ebayListingDao.findByListingId(listingId);
		assertNotNull(actual);
		assertEquals(listing, actual);
	}
	
	@Test
	public final void testFindAllLiberecoListing() throws Exception {
		LiberecoListing listing = new LiberecoListing();
		listing.setId(1L);
		listing.setMarketplaceName("eBay Full Name");
		listing.setMarketplaceShortName("eBay");
		listing.setListingId(1000L);
		listing.setUserId("UID");
		listing.setName("Ebay Listing Name");
		listing.setPrice(5.67D);
		listing.setQuantity(1);
		listing.setCategory(LiberecoCategory.CAT_BOOKS);
		listing.setCondition(ListingCondition.GOOD);
		listing.setListingState( ListingState.NEW);
		listing.setDescription("desc");
		listing.setListingDuration(duration);
		listing.setPicture(null);
		List<LiberecoListing> actual = ebayListingDao.findAll();
		assertNotNull(actual);
		assertTrue(actual.size() == 1);
		assertEquals(listing, actual.get(0));
	}
	
	@Test
	public final void testSaveOrUpdateLiberecoListing() throws Exception {
		LiberecoListing listing = new LiberecoListing();
		listing.setMarketplaceName("eBay Full Name Updated");
		listing.setMarketplaceShortName("eBay Updated");
		listing.setUserId("UID Updated");
		listing.setName("Ebay Listing Name");
		listing.setPrice(5.67D);
		listing.setQuantity(1);
		listing.setCategory(LiberecoCategory.CAT_BOOKS);
		listing.setCondition(ListingCondition.GOOD);
		listing.setListingState( ListingState.NEW);
		listing.setDescription("desc");
		listing.setListingDuration(duration);
		listing.setPicture(null);
		ebayListingDao.saveOrUpdate(listing);
		LiberecoListing actual = ebayListingDao.find(listing);
		assertNotNull(actual);
		assertEquals(listing, actual);
	}

	@Test
	public final void testHasLiberecoListingNameLiberecoListing() throws Exception {
		assertTrue(ebayListingDao.hasLiberecoListingName("Ebay Listing Name"));
		assertFalse(ebayListingDao.hasLiberecoListingName("Ebay Listing NameXXXX"));
		assertFalse(ebayListingDao.hasLiberecoListingName(null));
	}

	@Test
	public final void testGetLiberecoListingLiberecoListing() throws Exception {
		LiberecoListing listing = new LiberecoListing();
		listing.setId(1L);
		listing.setMarketplaceName("eBay Full Name");
		listing.setMarketplaceShortName("eBay");
		listing.setListingId(1000L);
		listing.setUserId("UID");
		listing.setName("Ebay Listing Name");
		listing.setPrice(5.67D);
		listing.setQuantity(1);
		listing.setCategory(LiberecoCategory.CAT_BOOKS);
		listing.setCondition(ListingCondition.GOOD);
		listing.setListingState( ListingState.NEW);
		listing.setDescription("desc");
		listing.setListingDuration(duration);
		listing.setPicture(null);
		LiberecoListing actual = ebayListingDao.getLiberecoListing(name);
		assertNotNull(actual);
		assertEquals(listing, actual);
		
		actual = ebayListingDao.getLiberecoListing("UGUIJLJQYDVBHJKCQN");
		assertNull(actual);
	}
	
	@Test
	public final void testGetEbayListingEbayListing() throws Exception {
		EbayListing listing = new EbayListing();
		listing.setId(1L);
		listing.setMarketplaceName("eBay Full Name");
		listing.setMarketplaceShortName("eBay");
		listing.setListingId(1000L);
		listing.setUserId("UID");
		listing.setName("Ebay Listing Name");
		listing.setPrice(5.67D);
		listing.setQuantity(1);
		listing.setCategory(LiberecoCategory.CAT_BOOKS);
		listing.setCondition(ListingCondition.GOOD);
		listing.setListingState( ListingState.NEW);
		listing.setDescription("desc");
		listing.setListingDuration(duration);
		listing.setPicture(null);
		listing.setReturnPolicy(returnPolicy);
		listing.setDispatchTimeMax(dispatchTimeMax);
		EbayListing actual = ebayListingDao.getEbayListing(name);
		assertNotNull(actual);
		assertEquals(listing, actual);
		
		actual = ebayListingDao.getEbayListing("JKHIUQWBNJKW");
		assertNull(actual);
	}
	
	@Test
	public final void testGetLiberecoListingIdsLiberecoListing() throws Exception {
		List<Long> actual = ebayListingDao.getLiberecoListingIds();
		assertNotNull(actual);
		assertFalse(actual.isEmpty());
		assertEquals(marketplaceId, actual.get(0));
	}
	
	@Test
	public final void testGetEbayListingIdsEbayListing() throws Exception {
		List<Long> actual = ebayListingDao.getEbayListingIds();
		assertNotNull(actual);
		assertFalse(actual.isEmpty());
		assertEquals(marketplaceId, actual.get(0));
	}
	
	@Test
	public final void testGetLiberecoListingNamesLiberecoListing() throws Exception {
		List<String> actual = ebayListingDao.getLiberecoListingNames();
		assertNotNull(actual);
		assertFalse(actual.isEmpty());
		assertEquals(name, actual.get(0));
	}
	
	@Test
	public final void testGetEbayListingNamesEbayListing() throws Exception {
		List<String> actual = ebayListingDao.getEbayListingNames();
		assertNotNull(actual);
		assertFalse(actual.isEmpty());
		assertEquals(name, actual.get(0));
	}
}
