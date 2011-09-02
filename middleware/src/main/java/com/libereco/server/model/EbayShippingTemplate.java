/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.model;

@SuppressWarnings("serial")
public class EbayShippingTemplate extends LiberecoShippingTemplate {

	private String shippingType;

	private String shippingService;

	private Integer shippingPriority;

	private Double shippingCost;

	/**
	 * @return the shippingType
	 */
	public String getShippingType() {
		return shippingType;
	}

	/**
	 * @param shippingType
	 *            the shippingType to set
	 */
	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	/**
	 * @return the shippingService
	 */
	public String getShippingService() {
		return shippingService;
	}

	/**
	 * @param shippingService
	 *            the shippingService to set
	 */
	public void setShippingService(String shippingService) {
		this.shippingService = shippingService;
	}

	/**
	 * @return the shippingPriority
	 */
	public Integer getShippingPriority() {
		return shippingPriority;
	}

	/**
	 * @param shippingPriority
	 *            the shippingPriority to set
	 */
	public void setShippingPriority(Integer shippingPriority) {
		this.shippingPriority = shippingPriority;
	}

	/**
	 * @return the shippingCost
	 */
	public Double getShippingCost() {
		return shippingCost;
	}

	/**
	 * @param shippingCost
	 *            the shippingCost to set
	 */
	public void setShippingCost(Double shippingCost) {
		this.shippingCost = shippingCost;
	}

}