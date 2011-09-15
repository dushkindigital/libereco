/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.libereco.server.dao.MarketplaceAuthorizationsDao;
import com.libereco.server.dao.MarketplaceDao;
import com.libereco.server.dao.PendingMarketplaceAuthorizationsDao;
import com.libereco.server.dao.UserDao;
import com.libereco.server.dto.AuthRequestTokenDto;
import com.libereco.server.dto.AuthTokenDto;
import com.libereco.server.dto.MarketplaceAuthorizationsDto;
import com.libereco.server.dto.MarketplaceDto;
import com.libereco.server.dto.UserDto;
import com.libereco.server.dto.dozer.DozerHelper;
import com.libereco.server.model.AuthRequestToken;
import com.libereco.server.model.AuthToken;
import com.libereco.server.model.Marketplace;
import com.libereco.server.model.MarketplaceAuthorizations;
import com.libereco.server.model.PendingMarketplaceAuthorizations;
import com.libereco.server.model.User;
import com.libereco.server.service.MarketplaceService;

/**
 * @author Aleksandar
 * 
 */
@Service("marketplaceService")
@Transactional
// @TransactionConfiguration
public class MarketplaceServiceImpl extends
		AbstractBasicService<Long, Marketplace, MarketplaceDto> implements
		MarketplaceService {

	// TODO: Inject UserService instead of userDao
	@Autowired
	private UserDao userDao;

	@Autowired
	private MarketplaceDao marketplaceDao;

	@Autowired
	private MarketplaceAuthorizationsDao marketplaceAuthorizationsDao;

	@Autowired
	private PendingMarketplaceAuthorizationsDao pendingMarketplaceAuthorizationsDao;

	
	@PostConstruct
	public void init() throws Exception {
	}

	@PreDestroy
	public void destroy() {
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void addMarketplace(MarketplaceDto marketplace) {
		saveOrUpdate(marketplace, marketplaceDao);
		// marketplaceDao.saveOrUpdate(marketplace);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public MarketplaceDto getMarketplace(String marketplaceName) {
		Marketplace entity = marketplaceDao.getMarketplace(marketplaceName);
		return toDto(entity);
	}

	@Override
	public MarketplaceAuthorizationsDto getAuthorization(UserDto user,
			MarketplaceDto marketplace) {
		
		// TODO: Expose toDto and toEntity methods in generic service and invoke
		// them that way rather than calling DozerHelper and passing class as
		// parameter
		
		DozerHelper dh = new DozerHelper();
		User userEntity = dh.map(user, User.class);
		MarketplaceAuthorizations mpaEntity = marketplaceAuthorizationsDao
				.getAuthorization(userEntity, toEntity(marketplace));

		return dh.map(mpaEntity, MarketplaceAuthorizationsDto.class);
	}

	@Override
	public List<MarketplaceAuthorizationsDto> getAuthorizations(UserDto user) {
		
		// TODO: Expose toDto and toEntity methods in generic service and invoke
		// them that way rather than calling DozerHelper and passing class as
		// parameter
		DozerHelper dh = new DozerHelper();
		User userEntity = dh.map(user, User.class);
		
		List<MarketplaceAuthorizations> mpaEntities = marketplaceAuthorizationsDao.getAuthorizations(userEntity);
		return dh.map(mpaEntities, MarketplaceAuthorizationsDto.class);
	}

	@Override
	public List<MarketplaceDto> getMarketplaces() {
		return toDto(marketplaceDao.findAll());
	}

	@Override
	public List<Long> getMarketplaceIds() {
		return marketplaceDao.getMarketplaceIds();
	}

	@Override
	public List<String> getMarketplaceNames() {
		return marketplaceDao.getMarketplaceNames();
	}

	@Override
	public MarketplaceDto getMarketplace(Long id) {
		return toDto(marketplaceDao.findById(id));
	}

	@Override
	public boolean isAuthorized(String userName, String marketplaceName) {
		boolean authorized = false;

		User user = userDao.findByUserName(userName);
		if (user != null) {
			Marketplace marketplace = marketplaceDao
					.getMarketplace(marketplaceName);

			MarketplaceAuthorizations mpa = marketplaceAuthorizationsDao
					.getAuthorization(user, marketplace);

			// TODO: Test
			// return marketplaceAuthorizationsDao.isAuthorized(userName,
			// marketplaceName);

			authorized = (mpa != null && mpa.getToken() != null);
		}
		return authorized;
	}

	@Override
	public void saveAuthorization(String userName, String marketplaceName,
			AuthTokenDto authorizationToken) {
		
		User user = userDao.findByUserName(userName);
		Marketplace marketplace = marketplaceDao
				.getMarketplace(marketplaceName);

		AuthToken authorizationTokenEntity = new DozerHelper().map(
				authorizationToken, AuthToken.class);
		MarketplaceAuthorizations mpa = new MarketplaceAuthorizations(user,
				marketplace, authorizationTokenEntity, true);

		marketplaceAuthorizationsDao.saveOrUpdate(mpa);
	}

	@Override
	public void storeAuthRequestToken(String userName, String marketplaceName,
			AuthRequestTokenDto requestToken) {
		User user = userDao.findByUserName(userName);

		if (user != null) {
			Marketplace marketplace = marketplaceDao
					.getMarketplace(marketplaceName);
			PendingMarketplaceAuthorizations pa = new PendingMarketplaceAuthorizations(
					user, marketplace, requestToken.getToken(),
					requestToken.getTokenSecret());

			pendingMarketplaceAuthorizationsDao.saveOrUpdate(pa);
		}
	}

	@Override
	public AuthRequestTokenDto getAuthRequestToken(String userName,
			String marketplaceName) {
		AuthRequestTokenDto dto = null;
		AuthRequestToken authRequestTokenEntity = null;

		User user = userDao.findByUserName(userName);
		Marketplace marketplace = marketplaceDao
				.getMarketplace(marketplaceName);

		PendingMarketplaceAuthorizations pa = pendingMarketplaceAuthorizationsDao
				.getPendingAuthorization(user, marketplace);

		if (pa != null) {
			authRequestTokenEntity = new AuthRequestToken();
			authRequestTokenEntity.setToken(pa.getRequestToken());
			authRequestTokenEntity.setTokenSecret(pa.getRequestTokenSecret());
			dto = new DozerHelper().map(authRequestTokenEntity,
					AuthRequestTokenDto.class);
		}

		return dto;
	}

	@Override
	public void deletePendingAuthorization(String userName,
			String marketplaceName, String requestToken) {

		User user = userDao.findByUserName(userName);
		Marketplace marketplace = marketplaceDao
				.getMarketplace(marketplaceName);
		PendingMarketplaceAuthorizations pa = new PendingMarketplaceAuthorizations(
				user, marketplace, requestToken);

		pendingMarketplaceAuthorizationsDao.delete(pa);
	}
}
