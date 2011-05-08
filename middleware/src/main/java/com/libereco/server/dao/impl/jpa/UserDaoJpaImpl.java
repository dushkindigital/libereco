/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.dao.impl.jpa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.libereco.server.dao.UserDao;
import com.libereco.server.model.User;
import com.libereco.server.model.jpa.User_;

/**
 * @author Aleksandar
 * 
 */
// @Repository("userDao")
public class UserDaoJpaImpl extends AbstractJpaDaoSupport<Long, User> implements
		UserDao {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@PostConstruct
	public void init() {
		super.setEntityManagerFactory(entityManagerFactory);
	}

	@Override
	public boolean hasUserName(String userName) {
		CriteriaBuilder qb = entityManagerFactory.getCriteriaBuilder();
		CriteriaQuery<User> c = qb.createQuery(User.class);
		Root<User> p = c.from(User.class);
		Predicate condition = qb.equal(p.get(User_.userName), userName);
		c.where(condition);
		TypedQuery<User> q = entityManagerFactory.createEntityManager()
				.createQuery(c);
		List<User> result = q.getResultList();
		System.out.println("Result: " + result);

		return (result != null) && (result.size() > 0);
	}

	@Override
	public User saveOrUpdate(User obj) {
		super.persist(obj);
		return obj;
	}

	@Override
	public void delete(User obj) {
		super.remove(obj);
	}

	@Override
	public User find(User obj) {
		return findById(obj.getId());
	}

	@Override
	public Integer deleteAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteByUserName(String userName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
}
