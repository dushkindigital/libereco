/**
 * 
 */
package com.libereco.server.model;


import java.util.List;

import org.junit.Test;
import org.springframework.test.jpa.AbstractJpaTests;

import com.libereco.server.dao.MarketplaceDao;

/**
 * @author rrached
 *
 */
public class MarketplaceTest extends AbstractJpaTests {
	private MarketplaceDao marketplaceDao;
	
	private static final Long marketplaceId = 1L;
	private static final String marketplaceName = "eBay Full Name";
	private static final String marketplaceShortName = "eBay";

	public void setMarketplaceDao(MarketplaceDao marketplaceDao) {
		this.marketplaceDao = marketplaceDao;
	}
	
	@Override
	protected String[] getConfigLocations() {
		return new String[] {"classpath:/liberecoMiddleware-applicationContext-test.xml"};
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onSetUpInTransaction() throws Exception {
		jdbcTemplate.execute(
				"delete from marketplace where marketplacename = '" +
					marketplaceName + "' and marketplaceShortName = '" +
					marketplaceShortName + "'");
		jdbcTemplate.execute(
				"insert into marketplace (id, marketplaceName, marketplaceShortName) values (" +
					marketplaceId + ", '" +
					marketplaceName + "', '" +
					marketplaceShortName + "')");
	}
	
	@Test
	public final void testFindMarketplace() throws Exception {
		Marketplace marketplace = new Marketplace();
		marketplace.setId(1L);
		marketplace.setMarketplaceName("eBay Full Name");
		marketplace.setMarketplaceShortName("eBay");
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
