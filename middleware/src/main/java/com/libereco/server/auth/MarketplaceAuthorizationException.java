/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.auth;

/**
 * @author Aleksandar
 *
 */
@SuppressWarnings("serial")
public class MarketplaceAuthorizationException extends Exception {

	public MarketplaceAuthorizationException() {
		super();
	}

	public MarketplaceAuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}

	public MarketplaceAuthorizationException(String message) {
		super(message);
	}

	public MarketplaceAuthorizationException(Throwable cause) {
		super(cause);
	}
}
