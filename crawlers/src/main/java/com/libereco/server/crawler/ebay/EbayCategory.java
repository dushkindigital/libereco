/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.ebay;

import com.libereco.server.crawler.MarketplaceCategory;
import com.libereco.server.marketplace.EbayMarketplace;

/**
 * @author Aleksandar
 *
 */
public class EbayCategory extends MarketplaceCategory {

	private String siteId;
	
	private String categoryVersion;
	
	public EbayCategory() {
		super(new EbayMarketplace());
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getCategoryVersion() {
		return categoryVersion;
	}

	public void setCategoryVersion(String categoryVersion) {
		this.categoryVersion = categoryVersion;
	}

	@Override
	public String toString() {
		return "EbayCategory [siteId=" + siteId + ", categoryVersion="
				+ categoryVersion + ", toString()=" + super.toString() + "]";
	}	
}
