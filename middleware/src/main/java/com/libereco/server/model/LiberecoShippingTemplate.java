/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author rrached
 * 
 */
@Entity
@Table(name = "Libereco_Shipping_Template")
@SuppressWarnings("serial")
public class LiberecoShippingTemplate implements Serializable {

	/**
	 * no need to repeat the paymentId attributes on children classes
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shippingId;

	private String postcode;

	private String country;

	/**
	 * @return the shippingId
	 */
	public Long getShippingId() {
		return shippingId;
	}

	/**
	 * @param shippingId
	 *            the shippingId to set
	 */
	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}

	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode
	 *            the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LiberecoShippingTemplate == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		LiberecoShippingTemplate rhs = (LiberecoShippingTemplate) obj;
		return new EqualsBuilder()
				.append(postcode, rhs.postcode)
				.append(country, rhs.country)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(postcode)
			.append(country)
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	

}