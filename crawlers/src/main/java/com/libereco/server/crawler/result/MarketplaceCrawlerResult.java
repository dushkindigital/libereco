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
public class MarketplaceCrawlerResult {
	
	private CrawlingResultType status;
	
	private CrawlingErrorDetails errorDetails;

	public MarketplaceCrawlerResult() {
		this(CrawlingResultType.FAILURE);
	}
	
	public MarketplaceCrawlerResult(CrawlingResultType status) {
		this(status, null);
	}
	
	public MarketplaceCrawlerResult(CrawlingResultType status,
			CrawlingErrorDetails errorDetails) {
		super();
		this.status = status;
		this.errorDetails = errorDetails;
	}

	public CrawlingResultType getStatus() {
		return status;
	}

	public void setStatus(CrawlingResultType status) {
		this.status = status;
	}

	public CrawlingErrorDetails getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(CrawlingErrorDetails errorDetails) {
		this.errorDetails = errorDetails;
	}
	
	public boolean isSuccess() {
		return CrawlingResultType.SUCCESS.equals(status);
	}

	@Override
	public String toString() {
		return "MarketplaceCrawlerResult [status=" + status + ", errorDetails="
				+ errorDetails + "]";
	}
}
