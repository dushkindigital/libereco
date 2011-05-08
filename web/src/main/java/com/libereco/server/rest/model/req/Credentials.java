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
@XmlRootElement(name = "Credentials")
public class Credentials {

	@SerializedName("username")
	private String userName;

	@SerializedName("timestamp")
	private String timestamp;

	@SerializedName("timestamp")	
	private String random;

	@SerializedName("digest")
	private String digest;

	
	@XmlElement(name = "username")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@XmlElement(name = "timestamp")
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@XmlElement(name = "random")
	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	@XmlElement(name = "digest")
	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}
}
