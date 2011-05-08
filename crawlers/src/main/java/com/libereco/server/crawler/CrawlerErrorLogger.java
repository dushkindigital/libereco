/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Aleksandar
 *
 */
public class CrawlerErrorLogger {

	private static final Logger logger = LoggerFactory.getLogger(CrawlerErrorLogger.class);
	
	public static void logError(String errorMessage) {
		logger.error(errorMessage);
	}
	
	public static void logError(String errorMessage, Exception e) {
		logger.error(errorMessage, e);
	}
}
