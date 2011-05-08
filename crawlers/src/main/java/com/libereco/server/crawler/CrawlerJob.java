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

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.libereco.server.service.ServiceManager;

/**
 * This class implements <code>StatefultJob</code> to prevent multiple job
 * processor instances from running concurrently.
 *  
 * @author Aleksandar
 * 
 */
public class CrawlerJob extends QuartzJobBean implements StatefulJob {

	// private static final Logger logger = Logger.getLogger(CrawlerJob.class);

	// private int timeout;

	/**
	 * Setter called after the new CrawlerJob is instantiated with the value
	 * from the JobDetailBean (5)
	 */
	// public void setTimeout(int timeout) {
	// this.timeout = timeout;
	// }
	
	protected void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {

		ServiceManager.getCrawlingService().crawl();
	}
}
