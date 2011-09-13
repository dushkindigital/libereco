/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.libereco.common.LiberecoPaymentType;

/** 
 *  meta-data class encapsulating the LiberecoPaymentType and other details,
 *  and maintains a relationship with the LiberecoListing used by each payment template class
 *  We won't list the marketplaces attribute, we have a unidirectional association between 
 *  Marketplace and LiberecoPaymentMethod.
 */
@Entity
@Table(name = "Libereco_Payment_Method")
@SuppressWarnings("serial")
public class LiberecoPaymentMethod implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentMethodId;
	
	@Enumerated(EnumType.STRING)
	private LiberecoPaymentType paymentMethodType;
	
	@SuppressWarnings("unused")
	@Transient
	private String name;

	/**
	 * @return the paymentMethodId
	 */
	public Long getPaymentMethodId() {
		return paymentMethodId;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setPaymentMethodId(Long id) {
		this.paymentMethodId = id;
	}
	
	/**
	 * @return the paymentMethodType
	 */
	public LiberecoPaymentType getPaymentMethodType() {
		return paymentMethodType;
	}
	
	/**
	 * @param paymentMethodType the paymentMethodType to set
	 */
	public void setPaymentMethodType(LiberecoPaymentType paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return paymentMethodType.name();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LiberecoPaymentMethod == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		LiberecoPaymentMethod rhs = (LiberecoPaymentMethod) obj;
		return new EqualsBuilder()
				.append(paymentMethodId, rhs.paymentMethodId)
				.append(paymentMethodType, rhs.paymentMethodType)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(paymentMethodId)
			.append(paymentMethodType)
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}