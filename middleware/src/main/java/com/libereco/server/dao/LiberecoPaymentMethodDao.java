/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.dao;

import java.util.List;

import com.libereco.common.LiberecoPaymentType;
import com.libereco.server.model.LiberecoPaymentMethod;
import com.libereco.server.model.Marketplace;

/**
 * @author rrached
 *
 */
public interface LiberecoPaymentMethodDao extends AbstractDao<Long, LiberecoPaymentMethod> {
	
	boolean hasLiberecoPaymentType(LiberecoPaymentType type);
	
	public List<LiberecoPaymentMethod> getLiberecoPaymentMethods(LiberecoPaymentType type);
	
	public List<Long> getLiberecoPaymentMethodIds();
	
	public List<LiberecoPaymentType> getLiberecoPaymentMethodTypes();
	
	public List<LiberecoPaymentMethod> findLiberecoPaymentMethodsByMarketplace(Marketplace m);
}