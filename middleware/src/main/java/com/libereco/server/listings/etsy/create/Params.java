/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.listings.etsy.create;

import com.google.gson.annotations.SerializedName;

/**
 * @author Aleksandar
 *
 */
public class Params {
	@SerializedName("description")
	private String description;
	
	@SerializedName("image")
	private String image;
	
	@SerializedName("materials")
	private String materials;
	
	@SerializedName("price")
	private String price;
	
	@SerializedName("quantity")
	private String quantity;
	
	@SerializedName("shipping_template_id")
	private String shippingTemplateId;
	
	@SerializedName("shop_section_id")
	private String shopSectionId;
	
	@SerializedName("tags")
	private String tags;
	
	@SerializedName("title")
	private String title;

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMaterials() {
		return this.materials;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
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

	public String getShippingTemplateId() {
		return this.shippingTemplateId;
	}

	public void setShippingTemplateId(String shippingTemplateId) {
		this.shippingTemplateId = shippingTemplateId;
	}

	public String getShopSectionId() {
		return this.shopSectionId;
	}

	public void setShopSectionId(String shopSectionId) {
		this.shopSectionId = shopSectionId;
	}
	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "Params [description=" + description + ", image=" + image
				+ ", materials=" + materials + ", price=" + price
				+ ", quantity=" + quantity + ", shippingTemplateId="
				+ shippingTemplateId + ", shopSectionId=" + shopSectionId
				+ ", tags=" + tags + ", title=" + title + "]";
	}
}
