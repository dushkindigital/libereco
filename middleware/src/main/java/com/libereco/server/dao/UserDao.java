/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.dao;

import com.libereco.server.model.User;

/**
 * @author Aleksandar
 * 
 */
public interface UserDao extends AbstractDao<Long, User> {

	public boolean hasUserName(String userName);
	
	public User findByUserName(String userName);
	
	public void deleteByUserName(String userName);
}
