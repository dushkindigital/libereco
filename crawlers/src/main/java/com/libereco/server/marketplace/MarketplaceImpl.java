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
public class MarketplaceImpl implements Marketplace {

	// Hard-code markeplaces until we get those retrieved from a configuration
	// file or database
	public static final Marketplace EBAY = new MarketplaceImpl(1L, "ebay");
	public static final Marketplace ETSY = new MarketplaceImpl(2L, "etsy");
	public static final Marketplace AMAZON = new MarketplaceImpl(3L, "amazon");
	
	private Long id;

	private String name;

	public MarketplaceImpl(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MarketplaceImpl [id=" + id + ", name=" + name + "]";
	}
}
