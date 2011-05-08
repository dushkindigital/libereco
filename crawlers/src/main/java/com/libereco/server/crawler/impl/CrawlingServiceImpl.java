/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.libereco.server.crawler.CrawlerOutputConfig;
import com.libereco.server.crawler.CrawlingContext;
import com.libereco.server.crawler.CrawlingException;
import com.libereco.server.crawler.CrawlingService;
import com.libereco.server.crawler.MarketplaceCrawler;
import com.libereco.server.crawler.result.CrawlingErrorDetails;
import com.libereco.server.crawler.result.CrawlingResult;
import com.libereco.server.crawler.result.CrawlingResultType;
import com.libereco.server.crawler.result.MarketplaceCrawlerResult;

/**
 * @author Aleksandar
 * 
 */
public class CrawlingServiceImpl implements CrawlingService {

	private Logger logger = LoggerFactory.getLogger(CrawlingServiceImpl.class);

	private CrawlerOutputConfig outputConfig;

	private List<MarketplaceCrawler> crawlers;

	public CrawlingServiceImpl() {
		this(new ArrayList<MarketplaceCrawler>());
	}

	public CrawlingServiceImpl(List<MarketplaceCrawler> crawlers) {
		this(crawlers, null);
	}

	public CrawlingServiceImpl(List<MarketplaceCrawler> crawlers,
			CrawlerOutputConfig outputConfig) {
		this.outputConfig = outputConfig;
		this.crawlers = crawlers;
	}

	public List<MarketplaceCrawler> getCrawlers() {
		return crawlers;
	}

	public void setCrawlers(List<MarketplaceCrawler> crawlers) {
		this.crawlers = crawlers;
	}

	public CrawlerOutputConfig getOutputConfig() {
		return outputConfig;
	}

	public void setOutputConfig(CrawlerOutputConfig outputConfig) {
		this.outputConfig = outputConfig;
	}

	public CrawlingResult crawl() {
		CrawlingResult crawlingResult = new CrawlingResult();
		
		if (crawlers != null) {
			CrawlingContext crawlingContext = new CrawlingContext();
			crawlingContext.setOutputConfig(outputConfig);

			for (MarketplaceCrawler mpc : crawlers) {
				MarketplaceCrawlerResult mpcResult = new MarketplaceCrawlerResult(CrawlingResultType.SUCCESS);
				
				try {
					// TODO: Implement N retries if a failure occurs?
					mpc.crawl(crawlingContext);
					logger.info("Marketplace crawler [" + mpc.getMarketplace().getName() + "] successful");					
				} catch (CrawlingException ce) {
					logger.warn("Marketplace crawler [" + mpc.getMarketplace().getName() + "] failed", ce);
					mpcResult.setStatus(CrawlingResultType.FAILURE);
					mpcResult.setErrorDetails(new CrawlingErrorDetails(ce));					
				}
				
				crawlingResult.addCrawlingResult(mpc.getMarketplace(), mpcResult);
			}
			
			logger.info("Crawing result: " + crawlingResult);
		}
		
		return crawlingResult;
	}
}
