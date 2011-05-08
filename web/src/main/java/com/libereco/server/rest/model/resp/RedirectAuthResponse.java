/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.rest.model.resp;

import com.google.gson.annotations.SerializedName;

/**
 * @author Aleksandar
 * 
 */
@SuppressWarnings("serial")
public class RedirectAuthResponse extends Response {

	// TODO: Do we want to return a list of marketplaces with URL for each one?
	@SerializedName("redirectUrl")
	private String redirectUrl;

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
}
