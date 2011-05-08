/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.rest.model.req;

import com.google.gson.annotations.SerializedName;

/**
 * @author Aleksandar
 *
 */
public class RequestJson {
	
	// Annotation for Gson 
	@SerializedName("Request")
	private Request request;

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}
}
