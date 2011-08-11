/**
 * 
 */
package com.libereco.server.dao;

import com.libereco.common.ShippingLevel;

/**
 * @author rrached
 *
 */
public interface EbayShippingMethodDao {
	public String getShippingMethodId();
	public void setShippingMethodId(String id);
	
	public ShippingLevel getShippingLevel();
	public void getShippingLevel(ShippingLevel level);
	
	public String getName();
	public void setName(String name);
}
