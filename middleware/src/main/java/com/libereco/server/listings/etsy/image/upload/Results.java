/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.listings.etsy.image.upload;

import com.google.gson.annotations.SerializedName;

/**
 * @author Aleksandar
 * 
 */
public class Results {

	@SerializedName("blue")
	private Number blue;

	@SerializedName("brightness")
	private Number brightness;

	@SerializedName("creation_tsz")
	private Number creationTsz;

	@SerializedName("green")
	private Number green;

	@SerializedName("hex_code")
	private String hexCode;

	@SerializedName("hue")
	private Number hue;

	@SerializedName("is_black_and_white")
	private boolean isBlackAndWhite;

	@SerializedName("listing_id")
	private Number listingId;

	@SerializedName("listing_image_id")
	private Number listingImageId;

	@SerializedName("rank")
	private String rank;

	@SerializedName("red")
	private Number red;

	@SerializedName("saturation")
	private Number saturation;

	@SerializedName("url_170x135")
	private String url170x135;

	@SerializedName("url_570xN")
	private String url570xN;

	@SerializedName("url_75x75")
	private String url75x75;

	@SerializedName("url_fullxfull")
	private String urlFullxfull;

	public Number getBlue() {
		return this.blue;
	}

	public void setBlue(Number blue) {
		this.blue = blue;
	}

	public Number getBrightness() {
		return this.brightness;
	}

	public void setBrightness(Number brightness) {
		this.brightness = brightness;
	}

	public Number getCreationTsz() {
		return this.creationTsz;
	}

	public void setCreationTsz(Number creation_tsz) {
		this.creationTsz = creation_tsz;
	}

	public Number getGreen() {
		return this.green;
	}

	public void setGreen(Number green) {
		this.green = green;
	}

	public String getHexCode() {
		return this.hexCode;
	}

	public void setHexCode(String hex_code) {
		this.hexCode = hex_code;
	}

	public Number getHue() {
		return this.hue;
	}

	public void setHue(Number hue) {
		this.hue = hue;
	}

	public boolean getIsBlackAndWhite() {
		return this.isBlackAndWhite;
	}

	public void setBlackAndWhite(boolean is_black_and_white) {
		this.isBlackAndWhite = is_black_and_white;
	}

	public Number getListingId() {
		return this.listingId;
	}

	public void setListingId(Number listing_id) {
		this.listingId = listing_id;
	}

	public Number getListingImageId() {
		return this.listingImageId;
	}

	public void setListingImageId(Number listing_image_id) {
		this.listingImageId = listing_image_id;
	}

	public String getRank() {
		return this.rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Number getRed() {
		return this.red;
	}

	public void setRed(Number red) {
		this.red = red;
	}

	public Number getSaturation() {
		return this.saturation;
	}

	public void setSaturation(Number saturation) {
		this.saturation = saturation;
	}

	public String getUrl170x135() {
		return this.url170x135;
	}

	public void setUrl170x135(String url_170x135) {
		this.url170x135 = url_170x135;
	}

	public String getUrl570xN() {
		return this.url570xN;
	}

	public void setUrl570xN(String url_570xN) {
		this.url570xN = url_570xN;
	}

	public String getUrl75x75() {
		return this.url75x75;
	}

	public void setUrl75x75(String url_75x75) {
		this.url75x75 = url_75x75;
	}

	public String getUrlFullxfull() {
		return this.urlFullxfull;
	}

	public void setUrlFullxfull(String url_fullxfull) {
		this.urlFullxfull = url_fullxfull;
	}

	@Override
	public String toString() {
		return "Results [blue=" + blue + ", brightness=" + brightness
				+ ", creationTsz=" + creationTsz + ", green=" + green
				+ ", hexCode=" + hexCode + ", hue=" + hue
				+ ", isBlackAndWhite=" + isBlackAndWhite + ", listingId="
				+ listingId + ", listingImageId=" + listingImageId + ", rank="
				+ rank + ", red=" + red + ", saturation=" + saturation
				+ ", url170x135=" + url170x135 + ", url570xN=" + url570xN
				+ ", url75x75=" + url75x75 + ", urlFullxfull=" + urlFullxfull
				+ "]";
	}
}
