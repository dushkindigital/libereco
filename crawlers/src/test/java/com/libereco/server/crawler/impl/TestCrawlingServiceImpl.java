/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.impl;

import junit.framework.TestCase;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.libereco.server.crawler.CrawlingException;
import com.libereco.server.crawler.CrawlingService;

/**
 * @author Aleksandar
 *
 */
public class TestCrawlingServiceImpl extends TestCase {

	ClassPathXmlApplicationContext ctx;
	
	/*
	static{
        String[] paths = {"TEST-liberecoCrawlers-applicationContext.xml"};
        ctx = new ClassPathXmlApplicationContext(paths);
        testData = (TestData) ctx.getBean("testData");
        testData.setCtx(ctx);
	}
	*/
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		//super.setUp();
		//String[] paths = {"TEST-liberecoCrawlers-applicationContext.xml"};
		String[] paths = {"liberecoCrawlers-applicationContext.xml"};
        ctx = new ClassPathXmlApplicationContext(paths);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}


	public void testCrawl() throws CrawlingException {		
		CrawlingService cs = (CrawlingService) ctx.getBean("crawlingService");
		cs.crawl();
	}
}
