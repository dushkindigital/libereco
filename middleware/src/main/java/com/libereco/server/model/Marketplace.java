/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Aleksandar
 *
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
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(id)
			.append(marketplaceName)
			.append(marketplaceShortName)
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
