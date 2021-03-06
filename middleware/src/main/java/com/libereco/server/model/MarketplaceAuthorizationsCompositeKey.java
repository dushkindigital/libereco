/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author rrached
 *
 */
@Embeddable
@SuppressWarnings("serial")
public class MarketplaceAuthorizationsCompositeKey implements Serializable {
    @Column
    private Long userId;

    @Column
    private Long marketplaceId;

	public MarketplaceAuthorizationsCompositeKey() {
	}

	public MarketplaceAuthorizationsCompositeKey(Long userId, Long marketplaceId) {
		this.userId = userId;
		this.marketplaceId = marketplaceId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMarketplaceId() {
		return marketplaceId;
	}

	public void setMarketplaceId(Long marketplaceId) {
		this.marketplaceId = marketplaceId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
//		result = prime * result + getOuterType().hashCode();
		result = prime * result
				+ ((marketplaceId == null) ? 0 : marketplaceId.hashCode());
		result = prime * result
				+ ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarketplaceAuthorizationsCompositeKey other = (MarketplaceAuthorizationsCompositeKey) obj;
		if (marketplaceId == null) {
			if (other.marketplaceId != null)
				return false;
		} else if (!marketplaceId.equals(other.marketplaceId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
}
