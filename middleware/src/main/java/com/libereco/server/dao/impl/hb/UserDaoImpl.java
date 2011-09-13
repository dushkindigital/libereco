/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.dao.impl.hb;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.libereco.server.dao.UserDao;
import com.libereco.server.model.User;

/**
 * @author Aleksandar
 * 
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDaoSupport<Long, User> implements
		UserDao {

	@Override
	public User find(User obj) {
		return find(User.class, obj.getId());
	}

	@Override	
	public boolean hasUserName(String userName) {
		boolean found = false;
		Criteria criteria = getSession(false).createCriteria(User.class);

		criteria.add(Restrictions.eq("userName", userName));
		criteria.setProjection(Projections.count("id"));
		Long result = (Long) criteria.uniqueResult();

		if (result != null) {
			found = result.longValue() > 0;
		}

		return found;
	}

	@Override	
	public User findByUserName(String userName) {
		User entity = null;
		Criteria criteria = getSession(false).createCriteria(User.class);

		criteria.add(Restrictions.eq("userName", userName));
		entity = (User) criteria.uniqueResult();
		
		return entity;
	}

	@Override
	public void deleteByUserName(String userName) {
		User user = findByUserName(userName);
		if (user != null) {
			delete(user);
		}
	}
}
