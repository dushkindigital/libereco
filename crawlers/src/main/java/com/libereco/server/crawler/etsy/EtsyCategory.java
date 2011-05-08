/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.etsy;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import com.libereco.server.crawler.MarketplaceCategory;
import com.libereco.server.marketplace.EtsyMarketplace;

/**
 * @author Aleksandar
 *
 */
@SuppressWarnings("serial")
public class EtsyCategory extends MarketplaceCategory implements Serializable {

	@SerializedName("category_name")
	private String categoryName;

	@SerializedName("short_name")	
	private String shortName;
	
	@SerializedName("long_name")	
	private String longName;

	@SerializedName("meta_title")		
	private String metaTitle;
		
	@SerializedName("meta_keywords")		
	private String metaKeywords;
	
	@SerializedName("meta_description")		
	private String metaDescription;
	
	@SerializedName("page_title")		
	private String pageTitle;

	@SerializedName("num_children")			
	private Integer numChildren;
	
	public EtsyCategory() {
		super(new EtsyMarketplace());
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public String getMetaTitle() {
		return metaTitle;
	}

	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}

	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public Integer getNumChildren() {
		return numChildren;
	}

	public void setNumChildren(Integer numChildren) {
		this.numChildren = numChildren;
	}

	@Override
	public String toString() {
		return "EtsyCategory [categoryName=" + categoryName + ", shortName="
				+ shortName + ", longName=" + longName + ", metaTitle="
				+ metaTitle + ", metaKeywords=" + metaKeywords
				+ ", metaDescription=" + metaDescription + ", pageTitle="
				+ pageTitle + ", numChildren=" + numChildren + ", toString()="
				+ super.toString() + "]";
	}
}
