package com.libereco.server.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

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
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EbayListing == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		EbayListing rhs = (EbayListing) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(rhs))
				.append(returnPolicy, rhs.returnPolicy)
				.append(dispatchTimeMax, rhs.dispatchTimeMax)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.appendSuper(super.hashCode())
			.append(returnPolicy)
			.append(dispatchTimeMax)
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}