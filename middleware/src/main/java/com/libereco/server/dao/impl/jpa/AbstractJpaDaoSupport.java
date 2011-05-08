/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.dao.impl.jpa;

/**
 * @author Aleksandar
 *
 */

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.support.JpaDaoSupport;

public abstract class AbstractJpaDaoSupport<K, E> extends JpaDaoSupport {
	
	protected Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public AbstractJpaDaoSupport() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass
				.getActualTypeArguments()[1];
	}

	public void persist(E entity) {
		getJpaTemplate().persist(entity);
	}

	public void remove(E entity) {
		getJpaTemplate().remove(entity);
	}

	public E merge(E entity) {
		return getJpaTemplate().merge(entity);
	}

	public void refresh(E entity) {
		getJpaTemplate().refresh(entity);
	}

	public E findById(K id) {
		return getJpaTemplate().find(entityClass, id);
	}

	public E flush(E entity) {
		getJpaTemplate().flush();
		return entity;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<E> findAll() {
		Object res = getJpaTemplate().execute(new JpaCallback() {

			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query q = em.createQuery("SELECT h FROM "
						+ entityClass.getName() + " h");
				return q.getResultList();
			}

		});

		return (List<E>) res;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Integer removeAll() {
		return (Integer) getJpaTemplate().execute(new JpaCallback() {

			public Object doInJpa(EntityManager em) throws PersistenceException {
				Query q = em.createQuery("DELETE FROM " + entityClass.getName()
						+ " h");
				return q.executeUpdate();
			}

		});
	}
}