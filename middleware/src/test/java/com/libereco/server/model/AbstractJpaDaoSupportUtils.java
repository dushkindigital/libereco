/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.model;

import static java.sql.Types.*;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.libereco.common.LiberecoCategory;
import com.libereco.common.LiberecoPaymentType;
import com.libereco.common.ListingCondition;
import com.libereco.common.ListingState;
import com.libereco.common.ReturnPolicy;
import com.libereco.common.ShippingLevelType;

/**
 * @author rrached
 *
 */
public abstract class AbstractJpaDaoSupportUtils extends AbstractTransactionalJUnit4SpringContextTests {
	
	/**
	 * @return
	 */
	public int deleteFromTables() {
		return deleteFromTables("libereco_shipping_method",
								"libereco_payment_method",
								"libereco_listing_marketplace",
								"ebay_listing", 
								"libereco_listing",
								"libereco_payment_template",
								"libereco_shipping_template",
								"marketplace_libereco_payment_method",
								"marketplace_libereco_shipping_method",
								"marketplace");
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
	 * @param listingPaymentId
	 * @param listingShippingId
	 * @return
	 */
	public int updateLiberecoListing(final Long listingId,
									 final String userId,
									 final String name,
									 final Double price,
									 final Integer quantity,
									 final LiberecoCategory category,
									 final ListingCondition condition,
									 final ListingState listingState,
									 final String description,
									 final Date listingDuration,
									 final byte[] picture,
									 final Long  listingPaymentId,
									 final Long listingShippingId) {
		return simpleJdbcTemplate.getJdbcOperations().update(
					"insert into libereco_listing (id"
					+ ", userid"
					+ ", name"
					+ ", price"
					+ ", quantity"
					+ ", category"
					+ ", condition"
					+ ", listingstate"
					+ ", description"
					+ ", listingduration"
					+ ", picture"
					+ ", listingPayment_Id"
					+ ", listingShipping_Id) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					new Object[] { listingId,
					userId,
					name,
					price,
					quantity,
					category.getCategory(),
					condition.toString(),
					listingState.toString(),
					description,
					listingDuration,
					picture,
					listingPaymentId,
					listingShippingId},
					new int[] { BIGINT,
					VARCHAR,
					VARCHAR,
					DOUBLE,
					INTEGER,
					VARCHAR,
					VARCHAR,
					VARCHAR,
					VARCHAR,
					TIMESTAMP,
					BINARY,
					BIGINT,
					BIGINT});
	}
	
	/**
	 * @param paymentId
	 * @return
	 */
	public int updateLiberecoPaymentTemplate(final Long paymentId) {
		return simpleJdbcTemplate.getJdbcOperations().update(
				"insert into libereco_payment_template (id"
				+ ", currency) "
				+ "values (?, ?)",
				new Object[] { paymentId,
				Currency.getInstance(Locale.getDefault()).getCurrencyCode()},
				new int[] { BIGINT,
				VARCHAR});
	}
	
	/**
	 * @param shippingId
	 * @param postcode
	 * @return
	 */
	public int updateLiberecoShippingTemplate(final Long shippingId,
											  final String postcode) {
		return simpleJdbcTemplate.getJdbcOperations().update(
				"insert into libereco_shipping_template (id"
				+ ", country"
				+ ", postcode) "
				+ "values (?, ?, ?)",
				new Object[] { shippingId,
				Locale.getDefault().getDisplayCountry(),
				postcode},
				new int[] { BIGINT,
				VARCHAR,
				VARCHAR});
	}
	
	public int updateEbayListing(final Long listingId,
								 final ListingState listingState,
								 final ReturnPolicy returnPolicy,
								 final Integer dispatchTimeMax) {
		return simpleJdbcTemplate.getJdbcOperations().update(
				"insert into ebay_listing (listingId"
				+ ", listingState"
				+ ", returnPolicy"
				+ ", dispatchTimeMax) "
				+ "values (?, ?, ?, ?)",
				new Object[] { listingId,
				listingState.toString(),
				returnPolicy.toString(),
				dispatchTimeMax},
				new int[] { BIGINT,
				VARCHAR,
				VARCHAR,
				INTEGER});
	}
	
	/**
	 * @param id
	 * @param paymentType
	 * @return
	 */
	public int updateLiberecoPaymentMethod(final Long id,
										   final LiberecoPaymentType paymentType) {
		return simpleJdbcTemplate.getJdbcOperations().update(
					"insert into libereco_payment_method (id"
					+ ",paymentMethodType) "
					+ "values (?, ?)",
					new Object[] { id,
					paymentType.toString() },
					new int[] { BIGINT,
					VARCHAR });	
	}
	
	/**
	 * @param id
	 * @param shippingType
	 * @return
	 */
	public int updateLiberecoShippingMethod(final Long id,
										    final ShippingLevelType shippingType) {
		return simpleJdbcTemplate.getJdbcOperations().update(
					"insert into libereco_shipping_method (id"
					+ ",shippingLevelType) "
					+ "values (?, ?)",
					new Object[] { id,
					shippingType.toString() },
					new int[] { BIGINT,
					VARCHAR });	
	}
	
	/**
	 * @param marketplaceName
	 * @param marketplaceShortName
	 * @return {@link Marketplace}
	 */
	public static Marketplace createMarketplace(String marketplaceName,
												String marketplaceShortName) {
		Marketplace marketplace = new Marketplace();
		marketplace.setMarketplaceName(marketplaceName);
		marketplace.setMarketplaceShortName(marketplaceShortName);
		marketplace.setPaymentMethods(createLiberecoPaymentMethodList());
		marketplace.setShippingMethods(createLiberecoShippingMethodList());
		return marketplace;
	}	
	
	
	/**
	 * @return {@link List<LiberecoPaymentMethod>}
	 */
	public static List<LiberecoPaymentMethod> createLiberecoPaymentMethodList() {
		LiberecoPaymentMethod paymentMethod = new LiberecoPaymentMethod();
		paymentMethod.setPaymentMethodType(LiberecoPaymentType.AMERICAN_EXPRESS);
		List<LiberecoPaymentMethod> paymentMethods = new ArrayList<LiberecoPaymentMethod>();
		paymentMethods.add(paymentMethod);
		return paymentMethods;
	}
	
	/**
	 * @return {@link List<LiberecoShippingMethod>}
	 */
	public static List<LiberecoShippingMethod> createLiberecoShippingMethodList() {
		LiberecoShippingMethod shippingMethod = new LiberecoShippingMethod();
		shippingMethod.setName("name");
		shippingMethod.setShippingLevelType(ShippingLevelType.OVERNIGHT);
		List<LiberecoShippingMethod> shippingMethods = new ArrayList<LiberecoShippingMethod>();
		shippingMethods.add(shippingMethod);
		return shippingMethods;
	}
	
	public static LiberecoPaymentTemplate createLiberecoPaymentTemplate() {
		LiberecoPaymentTemplate template = new LiberecoPaymentTemplate();
		template.setCurrency(Currency.getInstance(Locale.getDefault()));
		return template;
	}

	public static LiberecoShippingTemplate createLiberecoShippingTemplate() {
		LiberecoShippingTemplate template = new LiberecoShippingTemplate();
		template.setCountry(Locale.getDefault().getDisplayCountry());
		template.setPostcode("07030");
		return template;
	}
	
}
