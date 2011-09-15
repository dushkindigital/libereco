/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.libereco.server.model.MarketplaceAuthorizationsCompositeKey;

/**
 * @author Aleksandar
 *
 */
@SuppressWarnings("serial")
public class MarketplaceAuthorizationsDto implements Serializable {

	// TODO: Update with a DTO-related class
	private MarketplaceAuthorizationsCompositeKey key;
	
	private UserDto user;

	private MarketplaceDto marketplace;

	// TODO: Replace individual token-related members with an AuthToken data member
	private String token;
	
	private String tokenSecret;
	
	private Timestamp expirationTime;

	public MarketplaceAuthorizationsCompositeKey getKey() {
		return key;
	}

	public void setKey(MarketplaceAuthorizationsCompositeKey key) {
		this.key = key;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public MarketplaceDto getMarketplace() {
		return marketplace;
	}

	public void setMarketplace(MarketplaceDto marketplace) {
		this.marketplace = marketplace;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenSecret() {
		return tokenSecret;
	}

	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}

	public Timestamp getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Timestamp expirationTime) {
		this.expirationTime = expirationTime;
	}
}
