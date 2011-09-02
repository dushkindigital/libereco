/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.model;

/**
 * @author rrached
 * 
 */
@SuppressWarnings("serial")
public class EtsyShippingTemplate extends LiberecoShippingTemplate {

	/**
	 * see: http://developer.etsy.com/docs/resource_shippinginfo
	 */
	private Integer shippingTemplateId;

	private Double secondaryCost;

	/**
	 * @return the shippingTemplateId
	 */
	public Integer getShippingTemplateId() {
		return shippingTemplateId;
	}

	/**
	 * @param shippingTemplateId
	 *            the shippingTemplateId to set
	 */
	public void setShippingTemplateId(Integer shippingTemplateId) {
		this.shippingTemplateId = shippingTemplateId;
	}

	/**
	 * @return the secondaryCost
	 */
	public Double getSecondaryCost() {
		return secondaryCost;
	}

	/**
	 * @param secondaryCost
	 *            the secondaryCost to set
	 */
	public void setSecondaryCost(Double secondaryCost) {
		this.secondaryCost = secondaryCost;
	}

}