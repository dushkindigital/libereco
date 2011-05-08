/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.dto;

/**
 * @author Aleksandar
 * 
 */
public class AuthRequestTokenDto {

	private String token;
	private String tokenSecret;

	public AuthRequestTokenDto() {		
	}
	
	public AuthRequestTokenDto(String token) {
		this(token, null);
	}
	
	public AuthRequestTokenDto(String token, String tokenSecret) {
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
