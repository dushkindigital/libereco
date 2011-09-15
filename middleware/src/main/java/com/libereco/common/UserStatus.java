/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.common;

import com.libereco.server.model.UserConstants;

/**
 * @author rrached
 * 
 * @see UserConstants we replace the inner interface with an enum
 */
public enum UserStatus {
	ACTIVE,
	DELETED;
}
