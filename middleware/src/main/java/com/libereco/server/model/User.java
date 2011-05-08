/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Aleksandar
 * 
 */
@Entity
@Table(name = "liberecoUser")
// @javax.persistence.SequenceGenerator(name = "SEQ_USER", sequenceName =
// Sequence.USER_ID_SEQ)
@SuppressWarnings("serial")
public class User implements Serializable {

	private Long id;
	private String userName;
	private String password;
	private Timestamp created;
	private Timestamp lastUpdated;

	// TODO: Update with an enum
	private Integer status;

	private Set<MarketplaceAuthorizations> marketplaceAuthorizations;

	// private Long role;

	public User() {
		created = new Timestamp(System.currentTimeMillis());
	}

	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	// "SEQ_USER")
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique = true, name = "userName", nullable = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", length = 64)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	@Column(name = "lastUpdated")
	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<MarketplaceAuthorizations> getMarketplaceAuthorizations() {
		return marketplaceAuthorizations;
	}

	public void setMarketplaceAuthorizations(
			Set<MarketplaceAuthorizations> marketplaceAuthorizations) {
		this.marketplaceAuthorizations = marketplaceAuthorizations;
	}
	
	public boolean isActive() {
		return (status == UserConstants.UserStatus.ACTIVE);
	}
}
