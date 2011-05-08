/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.libereco.server.dao.UserDao;
import com.libereco.server.dto.UserDto;
import com.libereco.server.model.User;
import com.libereco.server.model.UserConstants;
import com.libereco.server.service.UserService;

/**
 * @author Aleksandar
 *
 */

public class UserServiceImpl extends AbstractBasicService<Long, User, UserDto> implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public void addUser(UserDto userDto) {
		saveOrUpdate(userDto);
	}

	@Override
	public void updateUser(UserDto userDto) {
		saveOrUpdate(userDto);		
	}

	@Override
	public void deleteUser(UserDto userDto) {
		User userEntity = toEntity(userDto);
		// TODO: Update status to use enum type. This is just for the prototype.
		userEntity.setStatus(UserConstants.UserStatus.DELETED);
		userDao.saveOrUpdate(userEntity);
	}

	@Override
	public void deleteUser(String userName) {
		User userEntity = userDao.findByUserName(userName);
		userEntity.setStatus(UserConstants.UserStatus.DELETED);		
		userDao.saveOrUpdate(userEntity);
	}

	@Override
	public boolean hasUser(String userName) {
		return userDao.hasUserName(userName);
	}
	
	@Override
	public UserDto getUser(String userName, boolean activeOnly) {
		UserDto userDto = null;
		User userEntity = userDao.findByUserName(userName);

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
		return toDto(userDao.findById(id));
	}
}
