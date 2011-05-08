/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.etsy;

import java.util.List;

import com.libereco.server.crawler.CrawlingContext;

import junit.framework.TestCase;

/**
 * @author Aleksandar
 * 
 */
public class TestEtsyCrawler extends TestCase {

	private static final String SANDBOX_CATEGORIES_URL = "http://openapi.etsy.com/v2/sandbox/public/taxonomy/categories";

	private static final String SANDBOX_API_KEY = "a9vtvmrj8ypqhaevuch4d9rs";

	private static final int SANDBOX_MAX_REQUESTS_PER_SECOND = 10;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetCategories() throws Exception {

		EtsyCrawler etsyCrawler = new EtsyCrawler(SANDBOX_CATEGORIES_URL,
				SANDBOX_API_KEY, SANDBOX_MAX_REQUESTS_PER_SECOND);

		List<EtsyCategory> etsyCategories = etsyCrawler.getCategories();
		System.out
				.println("--------------------- testGetCategories --------------------- ");
		int cnt = 1;
		for (EtsyCategory ec : etsyCategories) {
			System.out.println("[" + cnt++ + "] - " + ec);
		}

	}
	
	public void testGenerateOwl() throws Exception {
		EtsyCrawler etsyCrawler = new EtsyCrawler(SANDBOX_CATEGORIES_URL,
				SANDBOX_API_KEY, SANDBOX_MAX_REQUESTS_PER_SECOND);

		List<EtsyCategory> etsyCategories = etsyCrawler
				.getCategories();
		etsyCrawler.generateOwl(etsyCategories, new CrawlingContext());
	}
}
