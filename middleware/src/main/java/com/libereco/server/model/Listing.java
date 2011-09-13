/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author rrached
 *
 */
public interface Listing extends Serializable {
	
	public List<Marketplace> getMarketplaces();
	
	public void addMarketplace(Marketplace marketplace);
}
