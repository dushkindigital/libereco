/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.marketplace;

/**
 * @author Aleksandar
 *
 */
public class EtsyMarketplace implements Marketplace {

	/* (non-Javadoc)
	 * @see com.libereco.server.marketplace.Marketplace#getId()
	 */
	public Long getId() {
		// TODO: This is just temporary, id and name should be loaded/configured
		return 2L;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.marketplace.Marketplace#getName()
	 */
	public String getName() {
		// TODO: This is just temporary, id and name should be loaded/configured
		return "Etsy";
	}
}