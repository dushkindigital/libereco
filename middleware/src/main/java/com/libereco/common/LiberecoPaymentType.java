/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.common;

/** * 
 * Abstracts out the various payment modes accepted for a given listing.
 * Each market place treats these payments slightly differently, using a different naming
 * convention, requiring more identifiers, etc...
 * 
 * From UML model, ideally these should be persisted
 * 
 * @author rrached
 *
 */
public enum LiberecoPaymentType {
	AMERICAN_EXPRESS,
	MASTERCARD,
	PAYPAL,
	PERSONAL_CHECK,
	VISA;
}
