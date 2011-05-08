/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.dao;

import java.util.List;

/**
 * @author Aleksandar
 *
 */
public interface AbstractDao<K, E> {
	
	public E saveOrUpdate(E obj);

    public void delete(E obj);

    public E find(E obj);

	public E findById(K id);

    public List<E> findAll();
    
    public Integer deleteAll();    
}
