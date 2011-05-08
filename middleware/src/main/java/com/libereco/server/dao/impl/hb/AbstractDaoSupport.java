/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.dao.impl.hb;

/**
 * @author Aleksandar
 *
 */

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Aleksandar
 * 
 */
public abstract class AbstractDaoSupport<K extends Serializable, T> extends
		HibernateDaoSupport {

	protected Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public AbstractDaoSupport() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass
				.getActualTypeArguments()[1];
	}

	public T saveOrUpdate(T obj) {
		getHibernateTemplate().saveOrUpdate(obj);
		return obj;
	}

	public void delete(T obj) {
		getHibernateTemplate().delete(obj);
	}

	public T findById(K id) {
		return find(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	protected List<T> findAll(Class<T> clazz) {
		return getHibernateTemplate().find("from " + clazz.getName());
	}

	public List<T> findAll() {
		return findAll(entityClass);
	}

	protected T find(Class<T> clazz, K id) {
		return (T) getHibernateTemplate().load(clazz, id);
	}

	protected boolean hasId(Class<T> clazz, K id) {
		T entity = find(clazz, id);
		return (entity != null);
	}

	public void delete(Class<T> clazz, K id) {
		T entity = find(clazz, id);
		if (entity != null) {
			delete(entity);
		}
	}

	@SuppressWarnings("unchecked")
	protected List<K> getAllIds(String queryName) {
		Query query = getSession(false).getNamedQuery(queryName);
		return (List<K>) query.list();
	}

	@SuppressWarnings("unchecked")
	protected T getById(String queryName, K id) {
		Query query = getSession(false).getNamedQuery(queryName);
		query.setParameter(0, id);
		return (T) query.uniqueResult();
	}

	protected void deleteAll(String queryName) {
		Query query = getSession(false).getNamedQuery(queryName);
		query.executeUpdate();
	}

	public Long getCount(String queryName) {
		Query query = getSession(false).getNamedQuery(queryName);
		return (Long) query.uniqueResult();
	}

	public Long getCount(String queryName, Object[] values, Type[] types) {
		Query query = getSession(false).getNamedQuery(queryName);
		query.setParameters(values, types);
		return (Long) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<K> get(String queryName, Object[] values, Type[] types) {
		Query query = getSession(false).getNamedQuery(queryName);
		query.setParameters(values, types);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public K getUnique(String queryName, Object[] values, Type[] types) {
		Query query = getSession(false).getNamedQuery(queryName);
		query.setParameters(values, types);
		return (K) query.uniqueResult();
	}

	public int delete(String queryName, Object[] values, Type[] types) {
		Query query = getSession(false).getNamedQuery(queryName);
		query.setParameters(values, types);
		int updateCount = query.executeUpdate();
		return updateCount;
	}

	public int executeUpdate(String queryName, Object[] values, Type[] types) {
		Query query = getSession(false).getNamedQuery(queryName);
		query.setParameters(values, types);
		int updateCount = query.executeUpdate();
		return updateCount;
	}

	public Integer deleteAll() {
		Query query = getSession(false).createQuery(
				"DELETE FROM " + entityClass.getName() + " h");
		int updateCount = query.executeUpdate();
		return updateCount;
	}
}
