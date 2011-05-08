/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.listings.etsy.create;

import com.google.gson.annotations.SerializedName;

/**
 * @author Aleksandar
 * 
 */
public class ShippingInfo {

	@SerializedName("currency_code")
	private String currencyCode;

	@SerializedName("destination_country_id")
	private String destinationCountryId;

	@SerializedName("destination_country_name")
	private String destinationCountryName;

	@SerializedName("listing_id")
	private Number listingId;

	@SerializedName("origin_country_id")
	private Number originCountryId;

	@SerializedName("origin_country_name")
	private String originCountryName;

	@SerializedName("primary_cost")
	private String primaryCost;

	@SerializedName("region_id")
	private String regionId;

	@SerializedName("secondary_cost")
	private String secondaryCost;

	@SerializedName("shipping_info_id")
	private Number shippingInfoId;

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currency_code) {
		this.currencyCode = currency_code;
	}

	public String getDestinationCountryId() {
		return this.destinationCountryId;
	}

	public void setDestinationCountryId(String destination_country_id) {
		this.destinationCountryId = destination_country_id;
	}

	public String getDestinationCountryName() {
		return this.destinationCountryName;
	}

	public void setDestinationCountryName(String destination_country_name) {
		this.destinationCountryName = destination_country_name;
	}

	public Number getListingId() {
		return this.listingId;
	}

	public void setListingId(Number listing_id) {
		this.listingId = listing_id;
	}

	public Number getOriginCountryId() {
		return this.originCountryId;
	}

	public void setOriginCountryId(Number origin_country_id) {
		this.originCountryId = origin_country_id;
	}

	public String getOriginCountryName() {
		return this.originCountryName;
	}

	public void setOriginCountryName(String origin_country_name) {
		this.originCountryName = origin_country_name;
	}

	public String getPrimaryCost() {
		return this.primaryCost;
	}

	public void setPrimaryCost(String primary_cost) {
		this.primaryCost = primary_cost;
	}

	public String getRegionId() {
		return this.regionId;
	}

	public void setRegionId(String region_id) {
		this.regionId = region_id;
	}

	public String getSecondaryCost() {
		return this.secondaryCost;
	}

	public void setSecondaryCost(String secondary_cost) {
		this.secondaryCost = secondary_cost;
	}

	public Number getShippingInfoId() {
		return this.shippingInfoId;
	}

	public void setShippingInfoId(Number shipping_info_id) {
		this.shippingInfoId = shipping_info_id;
	}

	@Override
	public String toString() {
		return "ShippingInfo [currencyCode=" + currencyCode
				+ ", destinationCountryId=" + destinationCountryId
				+ ", destinationCountryName=" + destinationCountryName
				+ ", listingId=" + listingId + ", originCountryId="
				+ originCountryId + ", originCountryName=" + originCountryName
				+ ", primaryCost=" + primaryCost + ", regionId=" + regionId
				+ ", secondaryCost=" + secondaryCost + ", shippingInfoId="
				+ shippingInfoId + "]";
	}
}
