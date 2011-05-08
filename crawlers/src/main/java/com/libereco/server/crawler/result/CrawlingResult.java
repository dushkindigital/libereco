/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.result;

import java.util.HashMap;
import java.util.Map;

import com.libereco.server.marketplace.Marketplace;

/**
 * @author Aleksandar
 * 
 */
public class CrawlingResult {

	private Map<Marketplace, MarketplaceCrawlerResult> crawlingResults;

	public CrawlingResult() {
		this(new HashMap<Marketplace, MarketplaceCrawlerResult>());
	}

	public CrawlingResult(
			Map<Marketplace, MarketplaceCrawlerResult> crawlingResults) {
		super();
		this.crawlingResults = crawlingResults;
	}

	public void addCrawlingResult(Marketplace marketplace,
			MarketplaceCrawlerResult result) {
		crawlingResults.put(marketplace, result);
	}

	@Override
	public String toString() {
		return "CrawlingResult [crawlingResults=" + crawlingResults + "]";
	}
}
