package com.libereco.server.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.libereco.common.ReturnPolicy;

@Entity
@Table(name = "Ebay_Listing")
@SuppressWarnings("serial")
/**
 * @author rrached
 */
public class EbayListing extends LiberecoListing {

	@Enumerated(EnumType.STRING)
	private ReturnPolicy returnPolicy;

	private Integer dispatchTimeMax;

	/**
	 * @return the returnPolicy
	 */
	public ReturnPolicy getReturnPolicy() {
		return returnPolicy;
	}

	/**
	 * @param returnPolicy
	 *            the returnPolicy to set
	 */
	public void setReturnPolicy(ReturnPolicy returnPolicy) {
		this.returnPolicy = returnPolicy;
	}

	/**
	 * @return the dispatchTimeMax
	 */
	public Integer getDispatchTimeMax() {
		return dispatchTimeMax;
	}

	/**
	 * @param dispatchTimeMax
	 *            the dispatchTimeMax to set
	 */
	public void setDispatchTimeMax(Integer dispatchTimeMax) {
		this.dispatchTimeMax = dispatchTimeMax;
	}

}