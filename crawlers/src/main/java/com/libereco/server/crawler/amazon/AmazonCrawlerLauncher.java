/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.amazon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.libereco.server.crawler.CrawlingContext;
import com.libereco.server.crawler.CrawlingException;

/**
 * @author Aleksandar
 *
 */
public class AmazonCrawlerLauncher {

	private static Logger logger = LoggerFactory.getLogger(AmazonCrawlerLauncher.class);
	
	/*
	 * Your AWS Access Key ID, as taken from the AWS Your Account page.
	 */
	private static final String AWS_ACCESS_KEY_ID = "AKIAIWX5T6SPU6XOACJQ";

	/*
	 * Your AWS Secret Key corresponding to the above ID, as taken from the AWS
	 * Your Account page.
	 */
	private static final String AWS_SECRET_KEY = "b/O3Zt1cRvUxg5k6lB0T+uIUVUzL/WKHE68tqhCj";

	/*
	 * Use one of the following end-points, according to the region you are
	 * interested in:
	 * 
	 * US: ecs.amazonaws.com CA: ecs.amazonaws.ca UK: ecs.amazonaws.co.uk DE:
	 * ecs.amazonaws.de FR: ecs.amazonaws.fr JP: ecs.amazonaws.jp
	 */
	private static final String ENDPOINT = "ecs.amazonaws.com";
	
	public static void main(String[] args) {

		double requestIntervalMillis = 5000;
		if (args.length > 0) {
			try {
				requestIntervalMillis = Double.parseDouble(args[0]);
			} catch (NumberFormatException nfe) {
				logger.warn("Invalid input format: " + args[0], nfe);
			}
		}

		logger.debug("Crawling with request interval [" + requestIntervalMillis + "]");
		AmazonCrawler amazonCrawler = new AmazonCrawler(AWS_ACCESS_KEY_ID,
				AWS_SECRET_KEY, ENDPOINT, requestIntervalMillis);
		
		amazonCrawler.setMaxRequestRetries(5);
		try {
			amazonCrawler.crawl(new CrawlingContext());
		} catch (CrawlingException e) {
			logger.warn("Exception while crawling", e);
		}
	}
}
