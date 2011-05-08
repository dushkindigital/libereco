/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.service;

import java.util.List;

import com.libereco.server.dto.AuthRequestTokenDto;
import com.libereco.server.dto.AuthTokenDto;
import com.libereco.server.dto.MarketplaceAuthorizationsDto;
import com.libereco.server.dto.MarketplaceDto;
import com.libereco.server.dto.UserDto;

/**
 * @author Aleksandar
 * 
 */
public interface MarketplaceService extends BasicService<MarketplaceDto> {

	void addMarketplace(MarketplaceDto marketplace);

	MarketplaceDto getMarketplace(String marketplaceName);

	MarketplaceAuthorizationsDto getAuthorization(UserDto user,
			MarketplaceDto marketplace);

	List<MarketplaceAuthorizationsDto> getAuthorizations(UserDto user);

	List<MarketplaceDto> getMarketplaces();

	List<Long> getMarketplaceIds();

	List<String> getMarketplaceNames();

	MarketplaceDto getMarketplace(Long id);

	boolean isAuthorized(String userName, String marketplaceName);

	void saveAuthorization(String userName, String marketplaceName, AuthTokenDto authorizationToken);

	void storeAuthRequestToken(String userName, String marketplaceName, AuthRequestTokenDto requestToken);

	AuthRequestTokenDto getAuthRequestToken(String userName, String marketplaceName);
	
	void deletePendingAuthorization(String userName, String marketplaceName, String requestToken);
}
