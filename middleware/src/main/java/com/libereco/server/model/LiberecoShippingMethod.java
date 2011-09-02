/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.libereco.common.ShippingLevelType;

/**
 * meta-data class encapsulating the ShippingLevelType and other details, and
 * maintains a relationship with the LiberecoListing used by each payment
 * template class The marketplaceId attribute is not used - for simplicity we
 * have a unidirectional association between Marketplace and
 * LiberecoShippingMethod
 */
@SuppressWarnings("serial")
public class LiberecoShippingMethod implements Serializable {
	private Long shippingMethodId;

	private ShippingLevelType shippingLevelType;

	/**
	 * name the marketplace uses to refer to this kind of shipping
	 */
	private String name;

	/**
	 * @return the shippingMethodId
	 */
	public Long getShippingMethodId() {
		return shippingMethodId;
	}

	/**
	 * @param shippingMethodId the shippingMethodId to set
	 */
	public void setShippingMethodId(Long shippingMethodId) {
		this.shippingMethodId = shippingMethodId;
	}

	/**
	 * @return the shippingLevelType
	 */
	public ShippingLevelType getShippingLevelType() {
		return shippingLevelType;
	}

	/**
	 * @param shippingLevelType the shippingLevelType to set
	 */
	public void setShippingLevelType(ShippingLevelType shippingLevelType) {
		this.shippingLevelType = shippingLevelType;
	}

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
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LiberecoShippingMethod == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		LiberecoShippingMethod rhs = (LiberecoShippingMethod) obj;
		return new EqualsBuilder()
				.append(shippingMethodId, rhs.shippingMethodId)
				.append(shippingLevelType, rhs.shippingLevelType)
				.append(name, rhs.name)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(shippingMethodId)
			.append(shippingLevelType)
			.append(name)
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}