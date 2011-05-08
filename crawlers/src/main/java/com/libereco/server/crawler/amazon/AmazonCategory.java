/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.amazon;

import java.io.Serializable;

import com.libereco.server.crawler.MarketplaceCategory;
import com.libereco.server.marketplace.AmazonMarketplace;

/**
 * @author Aleksandar
 * 
 */
@SuppressWarnings("serial")
public class AmazonCategory extends MarketplaceCategory implements Serializable {

	private String categoryName;
	
	private Integer numChildren;
	
	public AmazonCategory() {
		super(new AmazonMarketplace());
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getNumChildren() {
		return numChildren;
	}

	public void setNumChildren(Integer numChildren) {
		this.numChildren = numChildren;
	}
}
