/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.model;

/**
 * @author rrached
 *
 */
@SuppressWarnings("serial")
public class EbayShippingMethod extends LiberecoShippingMethod {
	 private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	 
}