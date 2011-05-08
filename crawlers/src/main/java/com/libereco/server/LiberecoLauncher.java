/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Aleksandar
 *
 */
public class LiberecoLauncher {

	private static final Logger logger = LoggerFactory.getLogger(LiberecoLauncher.class);
	
	public static void main(final String[] args) {
		
		// TODO: Add parsing of command line to allow for passing of alternate context
		final String[] paths = { "liberecoCrawlers-applicationContext.xml"};
		
		Thread appThread = new Thread(new Runnable() {
			public void run() {
				// Need to load context to initialize Cron triggers
				new ClassPathXmlApplicationContext(paths);
				
				while (true) {// run forever
					try {
						Thread.sleep(10000);
						//wait(10000);
					} catch(InterruptedException ie) {
						logger.warn("Application thread interrupted while sleeping", ie);
					}
				}
			}
		});
		
		appThread.setDaemon(true);
		appThread.start();
		
		try {
			appThread.join();
		} catch(InterruptedException ie) {
			logger.warn("Application thread interrupted in join", ie);
		}
	}
}
