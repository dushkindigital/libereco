/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.model;

import java.util.Currency;

public class LiberecoPaymentTemplate {

	/**
	 * no need to repeat the paymentId attributes on children classes
	 */
	private String paymentId;

	private String paymentMethodId;

	private Currency currency;

	private LiberecoListing listing;
//	private LiberecoPaymentMethod paymentByMarketplace;
	
	/**
	 * @return the paymentId
	 */
	public String getPaymentId() {
		return paymentId;
	}
	/**
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	/**
	 * @return the paymentMethodId
	 */
	public String getPaymentMethodId() {
		return paymentMethodId;
	}
	/**
	 * @param paymentMethodId the paymentMethodId to set
	 */
	public void setPaymentMethodId(String paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}
	/**
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	/**
	 * @return the listing
	 */
	public LiberecoListing getListing() {
		return listing;
	}
	/**
	 * @param listing the listing to set
	 */
	public void setListing(LiberecoListing listing) {
		this.listing = listing;
	}
//	/**
//	 * @return the paymentByMarketplace
//	 */
//	public LiberecoPaymentMethod getPaymentByMarketplace() {
//		return paymentByMarketplace;
//	}
//	/**
//	 * @param paymentByMarketplace the paymentByMarketplace to set
//	 */
//	public void setPaymentByMarketplace(LiberecoPaymentMethod paymentByMarketplace) {
//		this.paymentByMarketplace = paymentByMarketplace;
//	}
	
	

}