/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	private ListingState listingState;
	
	@Enumerated(EnumType.STRING)
	private ReturnPolicy returnPolicy;
	
	private Integer dispatchTimeMax;
	
	private List<Marketplace> marketplaces;

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
		if (marketplaces == null) {
			marketplaces = new ArrayList<Marketplace>();
		}
		return marketplaces;
	}
	
	@Override
	public void addMarketplace(Marketplace marketplace) {
		getMarketplaces().add(marketplace);
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
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(returnPolicy)
			.append(dispatchTimeMax)
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}