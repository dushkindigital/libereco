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
public class Image {
	@SerializedName("error")
	private Number error;

	@SerializedName("name")
	private String name;

	@SerializedName("size")
	private Number size;

	@SerializedName("tmp_name")
	private String tmpName;

	@SerializedName("type")
	private String type;

	public Number getError() {
		return this.error;
	}

	public void setError(Number error) {
		this.error = error;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Number getSize() {
		return this.size;
	}

	public void setSize(Number size) {
		this.size = size;
	}

	public String getTmpName() {
		return this.tmpName;
	}

	public void setTmpName(String tmp_name) {
		this.tmpName = tmp_name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Image [error=" + error + ", name=" + name + ", size=" + size
				+ ", tmpName=" + tmpName + ", type=" + type + "]";
	}
}
