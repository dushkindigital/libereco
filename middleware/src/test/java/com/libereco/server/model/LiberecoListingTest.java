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
import com.libereco.server.dao.LiberecoListingDao;

/**
 * @author rrached
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/liberecoMiddleware-applicationContext-test.xml" })
@TransactionConfiguration(transactionManager = "jpaTransactionManager", defaultRollback = true)
@Transactional
public class LiberecoListingTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private LiberecoListingDao liberecoListingDao;
	
	private static final Long marketplaceId = 1L;
	private static final String marketplaceName = "eBay Full Name";
	private static final String marketplaceShortName = "eBay";
	
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
		deleteFromTables("libereco_listing", "marketplace");
		
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
				category.toString(),
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
	}
	
	@Test
	public final void testFindLiberecoListing() throws Exception {
		LiberecoListing listing = new LiberecoListing();
		listing.setId(1L);
		listing.setMarketplaceName("eBay Full Name");
		listing.setMarketplaceShortName("eBay");
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
		LiberecoListing actual = liberecoListingDao.find(listing);
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
		listing.setName("Libereco Listing Name");
		listing.setPrice(5.67D);
		listing.setQuantity(1);
		listing.setCategory(LiberecoCategory.CAT_BOOKS);
		listing.setCondition(ListingCondition.GOOD);
		listing.setListingState( ListingState.NEW);
		listing.setDescription("desc");
		listing.setListingDuration(duration);
		listing.setPicture(null);
		LiberecoListing actual = liberecoListingDao.findById(marketplaceId);
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
		listing.setName("Libereco Listing Name");
		listing.setPrice(5.67D);
		listing.setQuantity(1);
		listing.setCategory(LiberecoCategory.CAT_BOOKS);
		listing.setCondition(ListingCondition.GOOD);
		listing.setListingState( ListingState.NEW);
		listing.setDescription("desc");
		listing.setListingDuration(duration);
		listing.setPicture(null);
		LiberecoListing actual = liberecoListingDao.findByListingId(listingId);
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
		listing.setName("Libereco Listing Name");
		listing.setPrice(5.67D);
		listing.setQuantity(1);
		listing.setCategory(LiberecoCategory.CAT_BOOKS);
		listing.setCondition(ListingCondition.GOOD);
		listing.setListingState( ListingState.NEW);
		listing.setDescription("desc");
		listing.setListingDuration(duration);
		listing.setPicture(null);
		List<LiberecoListing> actual = liberecoListingDao.findAll();
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
		listing.setName("Libereco Listing Name");
		listing.setPrice(5.67D);
		listing.setQuantity(1);
		listing.setCategory(LiberecoCategory.CAT_BOOKS);
		listing.setCondition(ListingCondition.GOOD);
		listing.setListingState( ListingState.NEW);
		listing.setDescription("desc");
		listing.setListingDuration(duration);
		listing.setPicture(null);
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
		listing.setId(1L);
		listing.setMarketplaceName("eBay Full Name");
		listing.setMarketplaceShortName("eBay");
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
		assertEquals(marketplaceId, actual.get(0));
	}
	
	@Test
	public final void testGetLiberecoListingNamesLiberecoListing() throws Exception {
		List<String> actual = liberecoListingDao.getLiberecoListingNames();
		assertNotNull(actual);
		assertFalse(actual.isEmpty());
		assertEquals(name, actual.get(0));
	}
	
}
