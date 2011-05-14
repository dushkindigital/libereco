/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import com.libereco.server.model.UserConstants;

/**
 * @author Aleksandar
 * 
 */
@SuppressWarnings("serial")
public class UserDto implements Serializable {

	private Long id;
	private String userName;
	private String password;
	private Timestamp created;
	private Timestamp lastUpdated;

	// TODO: Update with an enum
	private Integer status;

	private Set<MarketplaceAuthorizationsDto> marketplaceAuthorizations;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set<MarketplaceAuthorizationsDto> getMarketplaceAuthorizations() {
		return marketplaceAuthorizations;
	}

	public void setMarketplaceAuthorizations(
			Set<MarketplaceAuthorizationsDto> marketplaceAuthorizations) {
		this.marketplaceAuthorizations = marketplaceAuthorizations;
	}

	public boolean isActive() {
		return (status == UserConstants.UserStatus.ACTIVE);
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", userName=" + userName + ", password="
				+ password + ", created=" + created + ", lastUpdated="
				+ lastUpdated + ", status=" + status
				+ ", marketplaceAuthorizations=" + marketplaceAuthorizations
				+ "]";
	}
}
