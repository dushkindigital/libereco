/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
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
