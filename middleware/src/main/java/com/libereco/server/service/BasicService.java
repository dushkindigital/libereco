/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.service;

/**
 * @author Aleksandar
 * 
 */
public interface BasicService<T> {

	// TODO: Add validations, exceptions
	
	public T saveOrUpdate(T dto);

	public void delete(T dto);

	//public T get(Long id);
}
