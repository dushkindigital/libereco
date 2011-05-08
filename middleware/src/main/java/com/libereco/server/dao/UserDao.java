/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.dao;

import com.libereco.server.model.User;

/**
 * @author Aleksandar
 * 
 */
public interface UserDao extends AbstractDao<Long, User> {

	boolean hasUserName(String userName);
	
	User findByUserName(String userName);
	
	void deleteByUserName(String userName);
}
