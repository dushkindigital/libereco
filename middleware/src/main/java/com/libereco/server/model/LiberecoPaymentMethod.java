/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.libereco.common.LiberecoPaymentType;

/** 
 *  meta-data class encapsulating the LiberecoPaymentType and other details,
 *  and maintains a relationship with the LiberecoListing used by each payment template class
 */
@Entity
@Table(name = "Libereco_Payment_Method")
@SuppressWarnings("serial")
public class LiberecoPaymentMethod implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private LiberecoPaymentType paymentMethodType;
	
	@ManyToOne
	private Marketplace marketplace;

	@SuppressWarnings("unused")
	@Transient
	private String name;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the paymentMethodId
	 */
	public LiberecoPaymentType getPaymentMethodId() {
		return paymentMethodType;
	}

	/**
	 * @param paymentMethodType the paymentMethodType to set
	 */
	public void setPaymentMethodId(LiberecoPaymentType paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}

	/**
	 * @return the marketplace
	 */
	public Marketplace getMarketplace() {
		return marketplace;
	}

	/**
	 * @param marketplace the marketplace to set
	 */
	public void setMarketplace(Marketplace marketplace) {
		this.marketplace = marketplace;
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
				.append(id, rhs.id)
				.append(paymentMethodType, rhs.paymentMethodType)
				.append(marketplace, rhs.marketplace)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(id)
			.append(paymentMethodType)
			.append(marketplace)
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}