/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.dto.dozer;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

/**
 * @author Aleksandar
 * 
 */
public class DozerHelper {

	@SuppressWarnings("unchecked")
	public <D, S> D map(S source, Class<?> destClz) {
		D dest = null;
		Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
		if (source != null) {			
			dest = (D) mapper.map(source, destClz);
		}
		return dest;
	}

	public <D, S> List<D> map(List<S> source, Class<?> destClz) {
		List<D> destCollection = new ArrayList<D>();
		Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

		if (source != null) {
			for (S sourceItem : source) {
				@SuppressWarnings("unchecked")
				D dest = (D) mapper.map(sourceItem, destClz);
				destCollection.add(dest);
			}
		}

		return destCollection;
	}
}
