/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.rest.model.resp;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * @author Aleksandar
 * 
 */
@SuppressWarnings("serial")
public class Response implements Serializable {

	@SerializedName("header")
	private ResponseHeader header;

	public ResponseHeader getHeader() {
		return header;
	}

	public void setHeader(ResponseHeader header) {
		this.header = header;
	}
}
