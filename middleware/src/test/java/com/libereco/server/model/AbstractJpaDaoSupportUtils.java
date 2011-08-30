/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.model;

import static java.sql.Types.*;

import java.util.Date;

import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.libereco.common.LiberecoCategory;
import com.libereco.common.LiberecoPaymentType;
import com.libereco.common.ListingCondition;
import com.libereco.common.ListingState;
import com.libereco.common.ReturnPolicy;

/**
 * @author rrached
 *
 */
public abstract class AbstractJpaDaoSupportUtils extends AbstractTransactionalJUnit4SpringContextTests {
	
	/**
	 * @return
	 */
	public int deleteFromTables() {
		return deleteFromTables("libereco_payment_method", "ebay_listing", "libereco_listing", "marketplace");
	}
	
	/**
	 * @param marketplaceId
	 * @param marketplaceName
	 * @param marketplaceShortName
	 * @return
	 */
	public int updateMarketplace(final Long marketplaceId,
								 final String marketplaceName, 
								 final String marketplaceShortName) {
		return simpleJdbcTemplate.update("insert into marketplace (id, marketplaceName, marketplaceShortName) "
					+ "values (?, ?, ?)", 
					marketplaceId,
					marketplaceName,
					marketplaceShortName);
	}
	
	/**
	 * @param marketplaceId
	 * @param listingId
	 * @param userId
	 * @param name
	 * @param price
	 * @param quantity
	 * @param category
	 * @param condition
	 * @param listingState
	 * @param description
	 * @param listingDuration
	 * @param picture
	 * @return
	 */
	public int updateLiberecoListing(final Long marketplaceId,
									 final Long listingId,
									 final String userId,
									 final String name,
									 final Double price,
									 final Integer quantity,
									 final LiberecoCategory category,
									 final ListingCondition condition,
									 final ListingState listingState,
									 final String description,
									 final Date listingDuration,
									 final byte[] picture) {
		return simpleJdbcTemplate.getJdbcOperations().update(
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
	}
	
	/**
	 * @param marketplaceId
	 * @param returnPolicy
	 * @param dispatchTimeMax
	 * @return
	 */
	public int updateEbayListing(final Long marketplaceId,
								 final ReturnPolicy returnPolicy,
								 final Integer dispatchTimeMax) {
		return simpleJdbcTemplate.getJdbcOperations().update(
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
	
	/**
	 * @param id
	 * @param paymentType
	 * @param marketplaceId
	 * @return
	 */
	public int updateLiberecoPaymentMethod(final Long id,
										   final LiberecoPaymentType paymentType,
										   final Long marketplaceId) {
		return simpleJdbcTemplate.getJdbcOperations().update(
					"insert into libereco_payment_method (id"
					+ ",paymentMethodType"
					+ ", marketplace_id) "
					+ "values (?, ?, ?)",
					new Object[] { id,
					paymentType.toString(),
					marketplaceId },
					new int[] { BIGINT,
					VARCHAR,
					BIGINT });	
	}

}
