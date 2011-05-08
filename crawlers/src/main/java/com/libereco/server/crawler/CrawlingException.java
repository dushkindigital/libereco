/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler;

/**
 * @author Aleksandar
 *
 */
public class CrawlingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public CrawlingException() {
	}

	/**
	 * @param message
	 */
	public CrawlingException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CrawlingException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CrawlingException(String message, Throwable cause) {
		super(message, cause);
	}
}
