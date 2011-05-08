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
public class CreateListingResponse {
	@SerializedName("count")
	private Number count;

	// @SerializedName("params")
	private Params params;

	@SerializedName("results")
	private List<Results> results;

	// @SerializedName("type")
	private String type;

	public Number getCount() {
		return this.count;
	}

	public void setCount(Number count) {
		this.count = count;
	}

	public Params getParams() {
		return this.params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	public List<Results> getResults() {
		return this.results;
	}

	public void setResults(List<Results> results) {
		this.results = results;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CreateListingResponse [count=" + count + ", params=" + params
				+ ", results=" + results + ", type=" + type + "]";
	}

}
