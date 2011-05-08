/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.rest.model.resp;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author Aleksandar
 *
 */
@SuppressWarnings("serial")
public class GetAllMarketplacesResponse extends Response {

	@SerializedName("items")
	private List<String> marketplaceNames;

	public List<String> getMarketplaceNames() {
		return marketplaceNames;
	}

	public void setMarketplaceNames(List<String> marketplaceNames) {
		this.marketplaceNames = marketplaceNames;
	}
	
	public GetAllMarketplacesResponse() {
		this(new ArrayList<String>());
	}
	
	public GetAllMarketplacesResponse(List<String> marketplaceNames) {
		super();
		this.marketplaceNames = marketplaceNames;
	}
}
