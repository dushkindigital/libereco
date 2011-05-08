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
public class AddUserRequestJson {

	// Annotation for Gson 
	@SerializedName("Request")
	private AddUserRequest request;

	public AddUserRequest getRequest() {
		return request;
	}

	public void setRequest(AddUserRequest request) {
		this.request = request;
	}
}
