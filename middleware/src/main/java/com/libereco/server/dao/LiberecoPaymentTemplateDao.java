/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.dao;

import com.libereco.common.LiberecoPaymentType;
import com.libereco.server.model.LiberecoPaymentTemplate;

/**
 * @author rrached
 *
 */
public interface LiberecoPaymentTemplateDao extends AbstractDao<Long, LiberecoPaymentTemplate> {
	
	boolean allowsLiberecoPaymentMethod(LiberecoPaymentType type);	
}
