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
public class Request {

	// Annotation for Gson 
	@SerializedName("Credentials")
	private Credentials credentials;

	// Annotation for JAXB
	@XmlElement(name = "Credentials")
	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
}
