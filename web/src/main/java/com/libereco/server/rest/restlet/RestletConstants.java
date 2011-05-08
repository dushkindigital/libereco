/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.rest.restlet;

import org.restlet.data.MediaType;
import org.restlet.representation.Variant;

/**
 * @author Aleksandar
 * 
 */
public interface RestletConstants {

	public static final Variant APPLICATION_XML_VARIANT = new Variant(
			MediaType.APPLICATION_XML);

	public static final Variant TEXT_XML_VARIANT = new Variant(
			MediaType.TEXT_XML);
	
	public static final Variant APPLICATION_JSON_VARIANT = new Variant(
			MediaType.APPLICATION_JSON);

}
