/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.dto;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Aleksandar
 *
 */
@SuppressWarnings("serial")
public class AuthTokenDto implements Serializable {

	private String token;
	
	private String tokenSecret;
	
	private Timestamp expirationTime;

	
	public AuthTokenDto() {
		super();
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
