/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.libereco.common.ListingState;

/**
 * @author rrached
 *
 */
@SuppressWarnings("serial")
public class EtsyListing implements Listing {

	private Long listingId;
	
	private String tags;

	private ListingState listingState;
	
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
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
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
		if (obj instanceof EtsyListing == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		EtsyListing rhs = (EtsyListing) obj;
		return new EqualsBuilder()
				.append(listingId, rhs.listingId)
				.append(tags, rhs.tags)
				.append(listingState, rhs.listingState)
				.append(marketplaces, rhs.marketplaces)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(listingId)
			.append(tags)
			.append(listingState)
			.append(marketplaces)
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}	
}