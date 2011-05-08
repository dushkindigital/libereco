/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.result;

/**
 * @author Aleksandar
 *
 */
public class CrawlingErrorDetails {

	private String message;
	
	private Exception exception;

	// Needed for Spring
	@SuppressWarnings("unused")
	private CrawlingErrorDetails() {}
	
	public CrawlingErrorDetails(String message) {
		this.message = message;
	}
	
	/**
	 * @param message
	 * @param exception
	 */
	public CrawlingErrorDetails(String message, Exception exception) {
		this.message = message;
		this.exception = exception;		
	}

	/**
	 * @param exception
	 */
	public CrawlingErrorDetails(Exception exception) {
		this(exception.getMessage(), exception);		
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * @param exception the exception to set
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}

	@Override
	public String toString() {
		return "CrawlingErrorDetails [message=" + message + ", exception="
				+ exception + "]";
	}
}
