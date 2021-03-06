/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.dao.impl.jpa;

/**
 * @author Aleksandar
 * @author rrached
 * 
 * v0.2 drop JpaDaoSupport, this way we have "pure" POJO without direct
 * dependency on Spring classes; just use annotations such as
 * javax.persistence.PersistenceContext to inject the EntityManager object
 *
 * v0.3 extended the reflection mechanism to discover the type parameter at runtime
 * to support multiple inheritance
 */

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class AbstractJpaDaoSupport<K, E> {
	@PersistenceContext
	protected EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
	this.entityManager = entityManager;
	}
	
	protected Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public AbstractJpaDaoSupport() {
//		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
//				.getGenericSuperclass();
//		this.entityClass = (Class<E>) genericSuperclass
//				.getActualTypeArguments()[1];
		
		Class<?> cl = getClass();
		while (!cl.getSimpleName().equals(cl.getSuperclass().getSimpleName())) {
			if (cl.getGenericSuperclass() instanceof ParameterizedType) {
				break;
			}
			cl = cl.getSuperclass();
		}

		if (cl.getGenericSuperclass() instanceof ParameterizedType) {
			entityClass = 
				(Class<E>) ((ParameterizedType) cl.getGenericSuperclass()).getActualTypeArguments()[1];
		}
	}

	public void persist(E entity) {
		entityManager.persist(entity);
	}

	public void remove(E entity) {
		entityManager.remove(entity);
	}

	public E merge(E entity) {
		return entityManager.merge(entity);
	}

	public void refresh(E entity) {
		entityManager.refresh(entity);
	}

	public E findById(K id) {
		return entityManager.find(entityClass, id);
	}

	public E flush(E entity) {
		entityManager.flush();
		return entity;
	}

	@SuppressWarnings({ "unchecked" })
	public List<E> findAll() {
		Query q = entityManager.createQuery("SELECT h FROM " + entityClass.getName() + " h");
		return (List<E>) q.getResultList();
//		Object res = entityManager.execute(new JpaCallback() {
//
//			public Object doInJpa(EntityManager em) throws PersistenceException {
//				Query q = em.createQuery("SELECT h FROM "
//						+ entityClass.getName() + " h");
//				return q.getResultList();
//			}
//
//		});
//
//		return (List<E>) res;
	}

//	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Integer removeAll() {
		Query q = entityManager.createQuery("DELETE FROM " + entityClass.getName() + " h");
		return q.executeUpdate();
//		return (Integer) entityManager.execute(new JpaCallback() {
//
//			public Object doInJpa(EntityManager em) throws PersistenceException {
//				Query q = em.createQuery("DELETE FROM " + entityClass.getName()
//						+ " h");
//				return q.executeUpdate();
//			}
//
//		});
	}
}