/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.rest.model.req;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import com.google.gson.annotations.SerializedName;

/**
 * @author Aleksandar
 *
 */
@SuppressWarnings("serial")
public class AddUserDetails implements Serializable {
	
	@SerializedName("id")
	private String userName;
	
	@SerializedName("password")
	private String password;
	
	
	@XmlElement(name = "id")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@XmlElement(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
