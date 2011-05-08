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
import javax.persistence.Table;

/**
 * @author Aleksandar
 *
 */
@Entity
@Table(name = "marketplace")
@SuppressWarnings("serial")
public class Marketplace implements Serializable {

	private Long id;
	private String marketplaceName;

	// In case we need to keep track of marketplace authorizations
	//private Set<MarketplaceAuthorizations> marketplaceAuthorizations;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique = true, nullable = false)
	public String getMarketplaceName() {
		return marketplaceName;
	}

	public void setMarketplaceName(String marketplaceName) {
		this.marketplaceName = marketplaceName;
	}
	
	// TODO: We should load names, not hard-code them
	public interface MarketplaceName {		
		public final String EBAY = "ebay";
		public final String ETSY = "etsy";
	}
}
