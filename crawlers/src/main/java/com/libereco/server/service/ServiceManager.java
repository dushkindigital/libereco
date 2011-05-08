/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.libereco.server.crawler.CrawlingService;

/**
 * @author Aleksandar
 *
 */
public class ServiceManager {

	// We could initialize service manager in various ways. One approach would
	// be using bean reference factory. This would require beanRefFactory.xml
	// configuration but that approach is more flexible because it allows us to
	// change application context file that is used to initialize service
	// manager. An alternative approach is to configure service manager by
	// directly specifying application context file in this class.

	// private static BeanFactoryReference bf;

	private static String[] paths;
	
	private static ClassPathXmlApplicationContext ctx = null;

	static {
		paths = new String[] { "liberecoCrawlers-applicationContext.xml" };
		ctx = new ClassPathXmlApplicationContext(paths);

		// BeanFactoryLocator bfl = SingletonBeanFactoryLocator.getInstance();
		// bf = bfl.useBeanFactory("com.libereco.server");
	}

	public static CrawlingService getCrawlingService() {
		return (CrawlingService) ctx.getBean("crawlingService");
	}
}
