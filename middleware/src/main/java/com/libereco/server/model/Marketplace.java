/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Aleksandar
 * @author rrached
 */
@Entity
@Table(name = "marketplace")
@Inheritance(strategy = InheritanceType.JOINED)
@SuppressWarnings("serial")
public class Marketplace implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	private String marketplaceName;
	private String marketplaceShortName;
	/**
	 * 
	 * @element-type LiberecoShippingMethod
	 */
	@OneToMany(cascade = { CascadeType.PERSIST })
	public List<LiberecoShippingMethod> shippingMethods;
	/**
	 * 
	 * @element-type LiberecoPaymentMethod
	 */
	@OneToMany(cascade = { CascadeType.PERSIST })
	public List<LiberecoPaymentMethod> paymentMethods;

	// In case we need to keep track of marketplace authorizations
	//private Set<MarketplaceAuthorizations> marketplaceAuthorizations;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarketplaceName() {
		return marketplaceName;
	}

	public void setMarketplaceName(String marketplaceName) {
		this.marketplaceName = marketplaceName;
	}
	
	@Column(unique = true, nullable = true)
	public String getMarketplaceShortName() {
		return marketplaceShortName;
	}

	public void setMarketplaceShortName(String marketplaceShortName) {
		this.marketplaceShortName = marketplaceShortName;
	}
	
	public List<LiberecoPaymentMethod> getPaymentMethods() {
		if (paymentMethods == null) {
			paymentMethods = new ArrayList<LiberecoPaymentMethod>();
		}
		return this.paymentMethods;
	}
	
	public void setPaymentMethods(List<LiberecoPaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}
	
	/**
	 * Helper method...
	 * @param paymentMethod
	 */
	public void addPaymentMethod(LiberecoPaymentMethod paymentMethod) {
		getPaymentMethods().add(paymentMethod);
	}
	
	public List<LiberecoShippingMethod> getShippingMethods() {
		if (shippingMethods == null) {
			shippingMethods = new ArrayList<LiberecoShippingMethod>();
		}
		return this.shippingMethods;
	}
	
	public void setShippingMethods(List<LiberecoShippingMethod> shippingMethods) {
		this.shippingMethods = shippingMethods;
	}

	/**
	 * Helper method...
	 * @param shippingMethod
	 */
	public void addShippingMethod(LiberecoShippingMethod shippingMethod) {
		getShippingMethods().add(shippingMethod);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Marketplace == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Marketplace rhs = (Marketplace) obj;
		return new EqualsBuilder()
				.append(id, rhs.id)
				.append(marketplaceName, rhs.marketplaceName)
				.append(marketplaceShortName, rhs.marketplaceShortName)
				.append(paymentMethods, rhs.paymentMethods)
				.append(shippingMethods, rhs.shippingMethods)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(id)
			.append(marketplaceName)
			.append(marketplaceShortName)
			.append(paymentMethods)
			.append(shippingMethods)
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


	// TODO: We should load names, not hard-code them
	public interface MarketplaceName {		
		public final String EBAY = "ebay";
		public final String ETSY = "etsy";
	}
}
