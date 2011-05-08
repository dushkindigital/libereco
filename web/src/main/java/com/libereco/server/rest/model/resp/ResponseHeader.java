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
public class ResponseHeader implements Serializable {

	@SerializedName("status")
	private Integer status;

	@SerializedName("code")
	private String code;

	@SerializedName("message")
	private String message;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
