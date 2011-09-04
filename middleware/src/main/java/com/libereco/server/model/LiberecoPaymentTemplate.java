/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.model;

import java.io.Serializable;
import java.util.Currency;

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
@Table(name = "Libereco_Payment_Template")
@SuppressWarnings("serial")
public class LiberecoPaymentTemplate implements Serializable {

	/**
	 * no need to repeat the paymentId attributes on children classes
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;

	/**
	 * use java.util.Currency to represent a currency. Currencies are identified
	 * by their ISO 4217 currency codes. A good description is given in
	 * Wikipedia at http://en.wikipedia.org/wiki/ISO_4217 e.g. US Dollar:
	 * Code=USD, Num=840 (three-digit code number assigned to each currency,
	 * usually the same as the country code), E=2 (Number of digits after the
	 * decimal separator), Currency="United States dollar"
	 */
	private Currency currency;

	/**
	 * @return the paymentId
	 */
	public Long getPaymentId() {
		return paymentId;
	}

	/**
	 * @param paymentId the paymentId to set
	 */
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LiberecoPaymentTemplate == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		LiberecoPaymentTemplate rhs = (LiberecoPaymentTemplate) obj;
		return new EqualsBuilder()
				.append(currency, rhs.currency)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(currency)
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}