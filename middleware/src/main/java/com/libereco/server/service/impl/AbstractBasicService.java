/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.libereco.server.dao.AbstractDao;
import com.libereco.server.dto.dozer.DozerHelper;
import com.libereco.server.service.BasicService;

/**
 * @author Aleksandar
 * 
 */
public abstract class AbstractBasicService<K, E, T> implements BasicService<T> {

	protected Class<E> entityClass;
	protected Class<T> dtoClass;

	protected AbstractDao<K, E> abstractDao;

	protected AbstractBasicService() {
		this(null);
	}

	@SuppressWarnings("unchecked")
	protected AbstractBasicService(AbstractDao<K, E> abstractDao) {
		this.abstractDao = abstractDao;
		
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();

		this.entityClass = (Class<E>) genericSuperclass
				.getActualTypeArguments()[1];

		this.dtoClass = (Class<T>) genericSuperclass.getActualTypeArguments()[2];
	}
	
	@SuppressWarnings("unchecked")
	protected E toEntity(T dto) {
		// Need to explicitly cast to E, Sun's compiler doesn't seem to be able
		// to infer type
		// http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6302954
		return (E) new DozerHelper().map(dto, entityClass);
	}

	@SuppressWarnings("unchecked")
	protected T toDto(E entity) {
		// Need to explicitly cast to E, Sun's compiler doesn't seem to be able
		// to infer type
		// http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6302954
		return (T) new DozerHelper().map(entity, dtoClass);
	}

	protected List<E> toEntity(List<T> dto) {
		return new DozerHelper().map(dto, entityClass);
	}

	protected List<T> toDto(List<E> entity) {
		return new DozerHelper().map(entity, dtoClass);
	}

	@Override
	public T saveOrUpdate(T dto) {
		return saveOrUpdate(dto, abstractDao);
	}

	@Override
	public void delete(T dto) {
		delete(dto, abstractDao);
	}

	protected T saveOrUpdate(T dto, AbstractDao<K, E> dao) {
		// TODO: Add validations, exceptions
		if (dao == null) {
			throw new IllegalArgumentException("Dao not set");
		}
		E entity = toEntity(dto);
		entity = dao.saveOrUpdate(entity);
		return toDto(entity);
	}

	protected void delete(T dto, AbstractDao<K, E> dao) {
		// TODO: Add validations, exceptions
		if (dao == null) {
			throw new IllegalArgumentException("Dao not set");
		}
		
		E entity = toEntity(dto);
		dao.delete(entity);
	}
	
	// protected List<T> loadAll(AbstractDao<K, E> dao) {
	// List<E> entityList = dao.loadAll();
	// return toDto(entityList);
	// }
}
