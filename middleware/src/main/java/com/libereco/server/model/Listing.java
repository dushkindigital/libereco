/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
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
