/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.model;

import static com.libereco.common.UserStatus.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.libereco.common.UserStatus;

/**
 * @author Aleksandar
 * 
 * @version 0.2
 * @author rrached
 * Modified User for actual persistence
 */
@Entity
@Table(name = "liberecoUser")
// @javax.persistence.SequenceGenerator(name = "SEQ_USER", sequenceName =
// Sequence.USER_ID_SEQ)
@SuppressWarnings("serial")
public class User implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, name = "userName", nullable = false)
	private String userName;
	@Column(name = "password", length = 64)
	private String password;
	@Column(name = "created")
	private Timestamp created;
	@Column(name = "lastUpdated")
	private Timestamp lastUpdated;

	// TODO: Update with an enum - DONE
//	private Integer status;
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private UserStatus status;

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	/*
	 * can't quite use mappedBy, causes the following exception:
	 * Caused by: org.hibernate.AnnotationException: mappedBy reference an unknown target entity property: com.libereco.server.model.MarketplaceAuthorizations.user in com.libereco.server.model.User.marketplaceAuthorizations
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
	private Set<MarketplaceAuthorizations> marketplaceAuthorizations;

	// private Long role;

	public User() {
		created = new Timestamp(System.currentTimeMillis());
	}
	
	public User id(final Long id) {
		this.id = id;
		return this;
	}

	public User userName(final String userName) {
		this.userName = userName;
		return this;
	}
	
	public User password(final String password) {
		this.password = password;
		return this;
	}
	
	public User lastUpdated(final Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
		return this;
	}
	
	public User status(final UserStatus status) {
		this.status = status;
		return this;
	}
	
	public User marketplaceAuthorizations(final Set<MarketplaceAuthorizations> marketplaceAuthorizations) {
		this.marketplaceAuthorizations = marketplaceAuthorizations;
		return this;
	}
	
	public User marketplaceAuthorization(final MarketplaceAuthorizations marketplaceAuthorization) {
		if (this.marketplaceAuthorizations == null) {
			this.marketplaceAuthorizations = new HashSet<MarketplaceAuthorizations>();
		}
		this.marketplaceAuthorizations.add(marketplaceAuthorization);
		return this;
	}
	
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	// "SEQ_USER")
//	@Id
//	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	@Column(unique = true, name = "userName", nullable = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

//	@Column(name = "password", length = 64)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// @Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "created")
	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

//	@Column(name = "lastUpdated")
	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

//	@Column(name = "status")
//	@Enumerated(EnumType.STRING)
	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<MarketplaceAuthorizations> getMarketplaceAuthorizations() {
		return marketplaceAuthorizations;
	}

	public void setMarketplaceAuthorizations(
			Set<MarketplaceAuthorizations> marketplaceAuthorizations) {
		this.marketplaceAuthorizations = marketplaceAuthorizations;
	}
	
	public boolean isActive() {
		return (status.equals(ACTIVE));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		User rhs = (User) obj;
		return new EqualsBuilder()
				.append(userName, rhs.userName)
				.append(password, rhs.password)
				.append(lastUpdated, rhs.lastUpdated)
				.append(status, rhs.status)
				.append(marketplaceAuthorizations, rhs.marketplaceAuthorizations)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(userName)
			.append(password)
			.append(lastUpdated)
			.append(status)
			.append(marketplaceAuthorizations)		
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}	
	
}
