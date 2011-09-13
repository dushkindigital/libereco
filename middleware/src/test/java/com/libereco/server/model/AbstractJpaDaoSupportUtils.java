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
								"ebay_listing_marketplace",
								"libereco_listing",
								"listing",
								"listing_marketplace",
								"libereco_payment_template",
								"libereco_shipping_template",
								"address",
//								"libereco_shipping_template_address",
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
	public int updateListing(final Long listingId,
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
					"insert into listing (id"
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
	 * @param listingId
	 * @return
	 */
	public int updateLiberecoListing(final Long listingId,
									 final Long listingAttributeId) {
		return simpleJdbcTemplate.getJdbcOperations().update(
					"insert into libereco_listing (id"
					+ ", listingattribute_id) "
					+ "values (?, ?)",
					new Object[] { listingId,
					listingAttributeId},
					new int[] { BIGINT,
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
											  final Address address) {
		int updateResult = simpleJdbcTemplate.getJdbcOperations().update(
				"insert into address (id"
				+ ", address1"
				+ ", address2"
				+ ", city"
				+ ", postcode"
				+ ", country"
				+ ", phoneNumber1"
				+ ", phoneNumber2) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[] { shippingId,
				address.getAddress1(),
				address.getAddress2(),
				address.getCity(),
				address.getPostcode(),
				address.getCountry(),
				address.getPhoneNumber1(),
				address.getPhoneNumber2()},
				new int[] { BIGINT,
				VARCHAR,
				VARCHAR,
				VARCHAR,
				VARCHAR,
				VARCHAR,
				VARCHAR,
				VARCHAR});
		
		updateResult += simpleJdbcTemplate.getJdbcOperations().update(
				"insert into libereco_shipping_template (id, address_id) "
				+ "values (?, ?)",
				new Object[] { shippingId,
				shippingId},
				new int[] { BIGINT,
				BIGINT});
		
//		updateResult += simpleJdbcTemplate.getJdbcOperations().update(
//				"insert into libereco_shipping_template _address(libereco_shipping_template_id" +
//				", addresses_id) "
//				+ "values (?, ?)",
//				new Object[] { shippingId,
//				shippingId},
//				new int[] { BIGINT,
//				BIGINT});
				
		return updateResult;
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
	
	public static GenericListing createGenericListing(final String userId,
													  final String name,
													  final Double price,
													  final Integer quantity,
													  final LiberecoCategory category,
													  final ListingCondition condition,
													  final ListingState listingState,
													  final String description,
													  final Date listingDuration,
													  final byte[] picture,
													  final LiberecoPaymentTemplate listingPayment,
													  final LiberecoShippingTemplate listingShipping) {
		GenericListing listing = new GenericListing();
		listing.setUserId(userId);
		listing.setName(name);
		listing.setPrice(price);
		listing.setQuantity(quantity);
		listing.setCategory(category);
		listing.setCondition(condition);
		listing.setListingState(listingState);
		listing.setDescription(description);
		listing.setListingDuration(listingDuration);
		listing.setPicture(picture);
		listing.setListingPayment(listingPayment);
		listing.setListingShipping(listingShipping);
		return listing;
	}
	
	public static GenericListing createGenericListingWithId(final Long listingId,
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
															final LiberecoPaymentTemplate listingPayment,
															final LiberecoShippingTemplate listingShipping) {
		GenericListing listing = createGenericListing(userId, name, price, quantity, category, condition, listingState, description, listingDuration, picture, listingPayment, listingShipping);
		listing.setListingId(listingId);
		return listing;
	}

	/**
	 * @param marketplaceName
	 * @param marketplaceShortName
	 * @return {@link Marketplace}
	 */
	public static Marketplace createMarketplace(final String marketplaceName,
												final String marketplaceShortName) {
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
	
	public static Address createAddress(final String address1,
										final String address2,
										final String city,
										final String postcode,
										final String country) {
		Address address = new Address();
		address.setAddress1(address1);
		address.setAddress2(address2);
		address.setCity(city);
		address.setPostcode(postcode);
		address.setCountry(country == null ? Locale.getDefault().getDisplayCountry() : country);
		return address;
	}

	public static LiberecoShippingTemplate createLiberecoShippingTemplate() {
		Address address = createAddress("2001 Garden St", "Apt #489", "HoHoKus", "08902", null);	
		LiberecoShippingTemplate template = new LiberecoShippingTemplate();
		template.setAddress(address);
		return template;
	}
	
}
