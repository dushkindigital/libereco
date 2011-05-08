/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.rest.model.req;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

/**
 * @author Aleksandar
 *
 */
@XmlRootElement(name = "Request")
public class AddUserRequest {

	// Annotation for Gson 
	@SerializedName("user")
	private AddUserDetails userDetails;

	
	@XmlElement(name = "User")
	public AddUserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(AddUserDetails userDetails) {
		this.userDetails = userDetails;
	}
	
}
