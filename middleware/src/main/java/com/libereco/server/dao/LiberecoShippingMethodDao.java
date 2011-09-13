/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.dao;

import java.util.List;

import com.libereco.common.ShippingLevelType;
import com.libereco.server.model.LiberecoShippingMethod;
import com.libereco.server.model.Marketplace;

/**
 * @author rrached
 *
 */
public interface LiberecoShippingMethodDao extends AbstractDao<Long, LiberecoShippingMethod> {

	boolean hasLiberecoShippingType(ShippingLevelType type);
	
	public List<LiberecoShippingMethod> getLiberecoShippingMethods(ShippingLevelType type);
	
	public List<Long> getLiberecoShippingMethodIds();
	
	public List<ShippingLevelType> getLiberecoShippingMethodTypes();
	
	public List<LiberecoShippingMethod> findLiberecoShippingMethodsByMarketplace(Marketplace m);
}
