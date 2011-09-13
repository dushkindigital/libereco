/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
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
