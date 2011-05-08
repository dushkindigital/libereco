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
public class Params {

	@SerializedName("image")
	private Image image;

	@SerializedName("listing_id")
	private String listingId;

	@SerializedName("rank")
	private Number rank;

	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getListingId() {
		return this.listingId;
	}

	public void setListingId(String listing_id) {
		this.listingId = listing_id;
	}

	public Number getRank() {
		return this.rank;
	}

	public void setRank(Number rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Params [image=" + image + ", listingId=" + listingId
				+ ", rank=" + rank + "]";
	}
}
