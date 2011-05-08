/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler;

import com.libereco.server.crawler.result.CrawlingResult;


/**
 * @author Aleksandar
 * 
 */
public interface CrawlingService {

	CrawlingResult crawl();
}
