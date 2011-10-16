/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.libereco.common.ListingState;
import com.libereco.common.ReturnPolicy;

@Entity
@Table(name = "Ebay_Listing")
@SuppressWarnings("serial")
/**
 * @author rrached
 */
public class EbayListing implements Listing {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long listingId;
	
	@Enumerated(EnumType.STRING)
	private ListingState listingState;
	
	@Enumerated(EnumType.STRING)
	private ReturnPolicy returnPolicy;
	
	private Integer dispatchTimeMax;
	
	String itemTitle;
	String subTitle;
	String description;
	String listingDuration;
	Integer quantity;
	String categoryId;
	Double startPrice;
	Double reservePrice;
	Double buyItNowPrice;
	Float vatPercent;
	String payPalEmail;
	boolean borderChecked;
	boolean boldTitleChecked;
	boolean setAutoPay;
	Integer lotSize;
	Boolean bestOfferEnabled;
			
	/**
	 * amazingly enough the Hibernate JPA provider does NOT properly support the EAGER
	 * fetching strategy! Must rely on the default fetching strategy 
	 */
	@OneToMany(cascade = { CascadeType.PERSIST })
	private List<Marketplace> marketplaces = new ArrayList<Marketplace>();

	/**
	 * @return the listingId
	 */
	public Long getListingId() {
		return listingId;
	}

	/**
	 * @param listingId the listingId to set
	 */
	public void setListingId(Long listingId) {
		this.listingId = listingId;
	}

	/**
	 * @return the listingState
	 */
	public ListingState getListingState() {
		return listingState;
	}

	/**
	 * @param listingState the listingState to set
	 */
	public void setListingState(ListingState listingState) {
		this.listingState = listingState;
	}

	/**
	 * @return the returnPolicy
	 */
	public ReturnPolicy getReturnPolicy() {
		return returnPolicy;
	}

	/**
	 * @param returnPolicy
	 *            the returnPolicy to set
	 */
	public void setReturnPolicy(ReturnPolicy returnPolicy) {
		this.returnPolicy = returnPolicy;
	}

	/**
	 * @return the dispatchTimeMax
	 */
	public Integer getDispatchTimeMax() {
		return dispatchTimeMax;
	}

	/**
	 * @param dispatchTimeMax
	 *            the dispatchTimeMax to set
	 */
	public void setDispatchTimeMax(Integer dispatchTimeMax) {
		this.dispatchTimeMax = dispatchTimeMax;
	}
	
	@Override
	public List<Marketplace> getMarketplaces() {
		return marketplaces;
	}
	
	@Override
	public void addMarketplace(Marketplace marketplace) {
		getMarketplaces().add(marketplace);
	}	
	
	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getListingDuration() {
		return listingDuration;
	}

	public void setListingDuration(String listingDuration) {
		this.listingDuration = listingDuration;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Double startPrice) {
		this.startPrice = startPrice;
	}

	public Double getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(Double reservePrice) {
		this.reservePrice = reservePrice;
	}

	public Double getBuyItNowPrice() {
		return buyItNowPrice;
	}

	public void setBuyItNowPrice(Double buyItNowPrice) {
		this.buyItNowPrice = buyItNowPrice;
	}

	public Float getVatPercent() {
		return vatPercent;
	}

	public void setVatPercent(Float vatPercent) {
		this.vatPercent = vatPercent;
	}

	public String getPayPalEmail() {
		return payPalEmail;
	}

	public void setPayPalEmail(String payPalEmail) {
		this.payPalEmail = payPalEmail;
	}

	public boolean isBorderChecked() {
		return borderChecked;
	}

	public void setBorderChecked(boolean borderChecked) {
		this.borderChecked = borderChecked;
	}

	public boolean isBoldTitleChecked() {
		return boldTitleChecked;
	}

	public void setBoldTitleChecked(boolean boldTitleChecked) {
		this.boldTitleChecked = boldTitleChecked;
	}

	public boolean isSetAutoPay() {
		return setAutoPay;
	}

	public void setSetAutoPay(boolean setAutoPay) {
		this.setAutoPay = setAutoPay;
	}

	public Integer getLotSize() {
		return lotSize;
	}

	public void setLotSize(Integer lotSize) {
		this.lotSize = lotSize;
	}

	public Boolean getBestOfferEnabled() {
		return bestOfferEnabled;
	}

	public void setBestOfferEnabled(Boolean bestOfferEnabled) {
		this.bestOfferEnabled = bestOfferEnabled;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EbayListing == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		EbayListing rhs = (EbayListing) obj;
		return new EqualsBuilder()
				.append(returnPolicy, rhs.returnPolicy)
				.append(dispatchTimeMax, rhs.dispatchTimeMax)			
				.append(marketplaces, rhs.marketplaces)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(returnPolicy)
			.append(dispatchTimeMax)
			.append(marketplaces)
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}