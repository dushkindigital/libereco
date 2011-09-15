/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.dao.impl.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.libereco.server.dao.UserDao;
import com.libereco.server.model.Template;
import com.libereco.server.model.User;

/**
 * @author rrached
 *
 */
public class UserDaoImpl extends AbstractJpaDaoSupport<Long, User> implements UserDao {
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	private List<Template> templates = new ArrayList<Template>();

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#saveOrUpdate(java.lang.Object)
	 */
	@Override
	public User saveOrUpdate(User obj) {
		super.persist(obj);
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(User obj) {
		super.remove(obj);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#find(java.lang.Object)
	 */
	@Override
	public User find(User obj) {
		return entityManager.find(User.class, obj.getId());
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#findById(java.lang.Object)
	 */
	@Override
	public User findById(Long id) {
		return super.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#deleteAll()
	 */
	@Override
	public Integer deleteAll() {
		return super.removeAll();
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.UserDao#hasUserName(java.lang.String)
	 */
	@Override
	public boolean hasUserName(String userName) {
		boolean found = false;	
		Query q = entityManager.createQuery("from User where userName = :_name");
		q.setParameter("_name", userName);
		List<?> ls = null;
		found = (ls = q.getResultList()) != null && !ls.isEmpty() ? true : false;
		
		return found;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.UserDao#findByUserName(java.lang.String)
	 */
	@Override
	public User findByUserName(String userName) {
		Query q = entityManager.createQuery("from User where userName = :_name");
		q.setParameter("_name", userName);
		List<?> ls = null;
		return (User) ((ls = q.getResultList()) != null && !ls.isEmpty() ? ls.get(0) : null);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.UserDao#deleteByUserName(java.lang.String)
	 */
	@Override
	public void deleteByUserName(String userName) {
		User user = findByUserName(userName);
		if (userName != null) {
			delete(user);
		}
	}
	
	/**
	 * @param name
	 * @return {@link Template}
	 */
	public Template createTemplate(String name) {
		Template template = null;
		final String className = "com.libereco.server.model.Libereco" + name + "Template";
		try {
			template = (Template) Class.forName(className).newInstance();
			templates.add(template);
		} catch (Exception e) {
			logger.error("unable to create instance of class [" + className + "]", e);
		}
		return template;
	}
	
	/**
	 * @return {@link List<Template>}
	 */
	public List<Template> getTemplates() {
		return templates;
	}

}
