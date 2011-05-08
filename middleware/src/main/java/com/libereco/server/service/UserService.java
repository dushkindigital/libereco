/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.service;

import com.libereco.server.dto.UserDto;

/**
 * @author Aleksandar
 * 
 */
public interface UserService extends BasicService<UserDto> {

	void addUser(UserDto userDto);

	void updateUser(UserDto userDto);

	void deleteUser(UserDto userDto);

	void deleteUser(String userName);

	boolean hasUser(String userName);
	
	UserDto getUser(String userName, boolean activeOnly);
}
