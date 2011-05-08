/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.dao.impl.jpa;

import java.util.List;

/**
 * @author Aleksandar
 *
 */
public interface JpaDao<K, E> {
	
	public void persist(E entity);

	public void remove(E entity);

	public E merge(E entity);

	public void refresh(E entity);

	public E findById(K id);

	public E flush(E entity);

	public List<E> findAll();
	
	public Integer removeAll();
}
