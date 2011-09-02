/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
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
