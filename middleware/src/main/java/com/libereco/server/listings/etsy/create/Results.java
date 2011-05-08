/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.listings.etsy.create;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author Aleksandar
 *
 */
public class Results {
	
	@SerializedName("ShippingInfo")
	private List<ShippingInfo> shippingInfo;
	
	@SerializedName("brightness")
	private String brightness;
	
	@SerializedName("creation_tsz")
	private Number creationTsz;
	
	@SerializedName("currency_code")
	private String currencyCode;
	
	@SerializedName("description")
	private String description;
	
	@SerializedName("ending_tsz")
	private Number endingTsz;
	
//	@SerializedName("featured_rank")
	private String featuredRank;
	
	@SerializedName("hue")
	private String hue;
	
	@SerializedName("is_black_and_white")
	private String isBlackAndWhite;
	
	@SerializedName("last_modified_tsz")
	private Number lastModifiedTsz;
	
	@SerializedName("listing_id")
	private Number listingId;
	// private List<Materials> materials;
	
	@SerializedName("num_favorers")
	private Number num_favorers;
	
	@SerializedName("original_creation_tsz")
	private Number originalCreationTsz;
	
	@SerializedName("price")
	private String price;
	
	@SerializedName("quantity")
	private String quantity;
	
	@SerializedName("saturation")
	private String saturation;
	
	@SerializedName("shop_id")
	private Number shopId;
	
	//@SerializedName("shop_section_id")
	private String shopSectionId;
	
	@SerializedName("state")
	private String state;
	
	@SerializedName("state_tsz")
	private Number stateTsz;
		
	// private List<Tags> tags;
	
	@SerializedName("title")
	private String title;
	
//	@SerializedName("url")
//	private String url;
	
	@SerializedName("user_id")
	private Number userId;
	
	@SerializedName("views")
	private Number views;

	public List<ShippingInfo> getShippingInfo() {
		return this.shippingInfo;
	}

	public void setShippingInfo(List<ShippingInfo> shippingInfo) {
		this.shippingInfo = shippingInfo;
	}

	public String getBrightness() {
		return this.brightness;
	}

	public void setBrightness(String brightness) {
		this.brightness = brightness;
	}

	public Number getCreationTsz() {
		return this.creationTsz;
	}

	public void setCreationTsz(Number creation_tsz) {
		this.creationTsz = creation_tsz;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currency_code) {
		this.currencyCode = currency_code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Number getEndingTsz() {
		return this.endingTsz;
	}

	public void setEndingTsz(Number ending_tsz) {
		this.endingTsz = ending_tsz;
	}

	public String getFeaturedRank() {
		return this.featuredRank;
	}

	public void setFeaturedRank(String featured_rank) {
		this.featuredRank = featured_rank;
	}

	public String getHue() {
		return this.hue;
	}

	public void setHue(String hue) {
		this.hue = hue;
	}

	public String getIsBlackAndWhite() {
		return this.isBlackAndWhite;
	}

	public void setIsBlackAndWhite(String is_black_and_white) {
		this.isBlackAndWhite = is_black_and_white;
	}

	public Number getLastModifiedTsz() {
		return this.lastModifiedTsz;
	}

	public void setLastModifiedTsz(Number last_modified_tsz) {
		this.lastModifiedTsz = last_modified_tsz;
	}

	public Number getListingId() {
		return this.listingId;
	}

	public void setListingId(Number listing_id) {
		this.listingId = listing_id;
	}

	// public List<Materials> getMaterials(){
	// return this.materials;
	// }
	// public void setMaterials(List<Materials> materials){
	// this.materials = materials;
	// }
	public Number getNum_favorers() {
		return this.num_favorers;
	}

	public void setNum_favorers(Number num_favorers) {
		this.num_favorers = num_favorers;
	}

	public Number getOriginalCreationTsz() {
		return this.originalCreationTsz;
	}

	public void setOriginalCreationTsz(Number original_creation_tsz) {
		this.originalCreationTsz = original_creation_tsz;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return this.quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSaturation() {
		return this.saturation;
	}

	public void setSaturation(String saturation) {
		this.saturation = saturation;
	}

	public Number getShopId() {
		return this.shopId;
	}

	public void setShopId(Number shop_id) {
		this.shopId = shop_id;
	}

	public String getShopSectionId() {
		return this.shopSectionId;
	}

	public void setShopSectionId(String shop_section_id) {
		this.shopSectionId = shop_section_id;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Number getStateTsz() {
		return this.stateTsz;
	}

	public void setStateTsz(Number state_tsz) {
		this.stateTsz = state_tsz;
	}

	// public List<Tags> getTags(){
	// return this.tags;
	// }
	// public void setTags(List<Tags> tags){
	// this.tags = tags;
	// }
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

//	public String getUrl() {
//		return this.url;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}

	public Number getUserId() {
		return this.userId;
	}

	public void setUserId(Number user_id) {
		this.userId = user_id;
	}

	public Number getViews() {
		return this.views;
	}

	public void setViews(Number views) {
		this.views = views;
	}

	@Override
	public String toString() {
		return "Results [shippingInfo=" + shippingInfo + ", brightness="
				+ brightness + ", creationTsz=" + creationTsz
				+ ", currencyCode=" + currencyCode + ", description="
				+ description + ", endingTsz=" + endingTsz + ", featuredRank="
				+ featuredRank + ", hue=" + hue + ", isBlackAndWhite="
				+ isBlackAndWhite + ", lastModifiedTsz=" + lastModifiedTsz
				+ ", listingId=" + listingId + ", num_favorers=" + num_favorers
				+ ", original_creation_tsz=" + originalCreationTsz
				+ ", price=" + price + ", quantity=" + quantity
				+ ", saturation=" + saturation + ", shopId=" + shopId
				+ ", shopSectionId=" + shopSectionId + ", state=" + state
				+ ", stateTsz=" + stateTsz + ", title=" + title + ", userId="
				+ userId + ", views=" + views + "]";
	}
}
