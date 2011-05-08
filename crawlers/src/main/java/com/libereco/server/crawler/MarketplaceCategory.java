/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler;

import com.libereco.server.marketplace.Marketplace;

/**
 * @author Aleksandar
 *
 */
public abstract class MarketplaceCategory {

	// Unique identifier for category in Libreco
	private Long id;
	
	// Parent identifier in Libreco
	private Long parentId;
	
	private Marketplace marketplace;
	
	// Marketplace-specific identification number	
	private String marketplaceCategoryId;
	
	// Category name (marketplace)
	private String name;


	protected MarketplaceCategory(Marketplace marketplace) {
		this.marketplace = marketplace;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Marketplace getMarketplace() {
		return marketplace;
	}

	public void setMarketplace(Marketplace marketplace) {
		this.marketplace = marketplace;
	}

	public String getMarketplaceCategoryId() {
		return marketplaceCategoryId;
	}

	public void setMarketplaceCategoryId(String marketplaceCategoryId) {
		this.marketplaceCategoryId = marketplaceCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MarketplaceCategory [id=" + id + ", parentId=" + parentId
				+ ", marketplace=" + marketplace + ", marketplaceCategoryId="
				+ marketplaceCategoryId + ", name=" + name + "]";
	}	
}
