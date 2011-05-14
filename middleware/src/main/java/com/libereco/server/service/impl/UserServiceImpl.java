/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.service.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libereco.server.dao.UserDao;
import com.libereco.server.dto.UserDto;
import com.libereco.server.model.User;
import com.libereco.server.model.UserConstants;
import com.libereco.server.service.UserService;

/**
 * @author Aleksandar
 *
 */
@Service("userService")
@Transactional
public class UserServiceImpl extends AbstractBasicService<Long, User, UserDto> implements UserService {
	
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	//	@Autowired
//	private UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		super(userDao);
	}

	public UserDao getUserDao() {
		return (UserDao) getMainDao();
	}

	public void setUserDao(UserDao userDao) {
		setMainDao(userDao);
	}

	@Override
	public void addUser(UserDto userDto) {
		getLogger().debug("Add user [" + userDto + "]");
				
		// TODO: Add validations
		
		if (userDto.getCreated() == null) {
			userDto.setCreated(new Timestamp(System.currentTimeMillis()));
		}
		
		saveOrUpdate(userDto);
	}

	@Override
	public void updateUser(UserDto userDto) {
		getLogger().debug("Update user [" + userDto + "]");
		
		// TODO: Add validations
		userDto.setLastUpdated(new Timestamp(System.currentTimeMillis()));
		saveOrUpdate(userDto);		
	}

	@Override
	public void deleteUser(UserDto userDto) {
		User userEntity = toEntity(userDto);
		
		// TODO: Update status to use enum type. This is just for the prototype.
		userEntity.setStatus(UserConstants.UserStatus.DELETED);
		getUserDao().saveOrUpdate(userEntity);
	}

	@Override
	public void deleteUser(String userName) {
		User userEntity = getUserDao().findByUserName(userName);
		userEntity.setStatus(UserConstants.UserStatus.DELETED);		
		getUserDao().saveOrUpdate(userEntity);
	}

	@Override
	public boolean hasUser(String userName) {
		return getUserDao().hasUserName(userName);
	}
	
	@Override
	public UserDto getUser(String userName, boolean activeOnly) {
		UserDto userDto = null;
		User userEntity = getUserDao().findByUserName(userName);

		if (activeOnly && userEntity != null) {
			// TODO: Update status to use enum type. This is just for the prototype.
			if (userEntity.isActive() == false) {
				userEntity = null;
			}
		}
		
		userDto = toDto(userEntity);
		return userDto;
	}

	public UserDto get(Long id) {
		return toDto(getUserDao().findById(id));
	}
	
	protected Logger getLogger() {
		return logger;
	}
}
