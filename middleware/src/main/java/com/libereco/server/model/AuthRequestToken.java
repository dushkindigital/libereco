/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.model;

/**
 * @author Aleksandar
 *
 */
public class AuthRequestToken {

	private String token;
	private String tokenSecret;

	public AuthRequestToken() {		
	}
	
	public AuthRequestToken(String token) {
		this(token, null);
	}
	
	public AuthRequestToken(String token, String tokenSecret) {
		this.token = token;
		this.tokenSecret = tokenSecret;
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
}
