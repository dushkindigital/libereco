/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Aleksandar
 * 
 */
@Entity
@Table(name = "marketplaceAuthorizations")
@SuppressWarnings("serial")
public class MarketplaceAuthorizations implements Serializable {

	@EmbeddedId
	private CompositeKey key;
	
	private User user;

	private Marketplace marketplace;

	// TODO: Replace individual token-related members with an AuthToken data member
	private String token;
	
	private String tokenSecret;
	
	private Timestamp expirationTime;
	
	
	public MarketplaceAuthorizations() {
		super();
	}

	public MarketplaceAuthorizations(User user, Marketplace marketplace,
			AuthToken authToken) {
		super();
		this.user = user;
		this.marketplace = marketplace;
		this.token = authToken.getToken();
		this.tokenSecret = authToken.getTokenSecret();
		this.expirationTime = authToken.getExpirationTime();
		
		if (user != null && marketplace != null) {
			key = new CompositeKey(user.getId(), marketplace.getId());
		}
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "marketplaceId", nullable = false)
	public Marketplace getMarketplace() {
		return marketplace;
	}

	public void setMarketplace(Marketplace marketplace) {
		this.marketplace = marketplace;
	}

	@Column(name = "token")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@Column(name = "tokenSecret")
	public String getTokenSecret() {
		return tokenSecret;
	}

	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}
	
	@Column(name = "expirationTime")
	public Timestamp getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Timestamp expirationTime) {
		this.expirationTime = expirationTime;
	}

	public CompositeKey getKey() {
		return key;
	}

	public void setKey(CompositeKey key) {
		this.key = key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		MarketplaceAuthorizations other = (MarketplaceAuthorizations) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}


	@Embeddable
	public class CompositeKey implements Serializable {
	    @Column
	    private Long userId;

	    @Column
	    private Long marketplaceId;

		public CompositeKey() {
			super();
		}

		public CompositeKey(Long userId, Long marketplaceId) {
			super();
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
			result = prime * result + getOuterType().hashCode();
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
			CompositeKey other = (CompositeKey) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
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

		private MarketplaceAuthorizations getOuterType() {
			return MarketplaceAuthorizations.this;
		}	    
	}
}
