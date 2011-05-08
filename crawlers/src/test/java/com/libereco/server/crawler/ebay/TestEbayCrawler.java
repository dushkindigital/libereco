/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.ebay;

import java.util.List;

import junit.framework.TestCase;

import com.ebay.sdk.ApiAccount;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.libereco.server.crawler.CrawlingContext;

public class TestEbayCrawler extends TestCase {

	private static final String SANDBOX_TOKEN = "AgAAAA**AQAAAA**aAAAAA**HxRnTQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4CoAZKCqQ6dj6x9nY+seQ**nH4BAA**AAMAAA**R6VAGmaJi7R8srLSECpuKlBLFXG1O6QbAQTy+BZB7DNPWJm/VhfgJSLG7rRitmRfnmAoze71r2nzRDBhoMqtKEsbFmDONxfhDYJ88JamvmdvWkKvbKpSywcxJD2mP483SEBx4jDfPhKlP1y2MviVrrcdOPlbLNKUcUMa4oKe4RaN4YLSrdjiQP7znLLdccpWGYY5flDpsRXTsgkkORm/7zZW3hLwg4PAjsYSQ7cJWr2dcaFz9ePhw4qbyC5tOuBzqvV9yEm4jNHP2JAnGxMBEfrbtsrwgPiDAZP13qQWy6lI6PckRfEysu6R3V17yShd3J/FZoygz2jFNFWp8n7rZDSR/fiy40sH0bNUXpSwC0AzTXOGOQ7K/XPk2DehSNIhxeY+UG4Vj+owz7v5RTqYMSzNWVMPNjSPuvGG9xuCbWOVmLSaG/xEXLv/lVMCK+tjuuxStmfJuBdAKqTimR/jwuQRawrkOW6/m1ZOwRcq1Qiuz9XMZI3v2rKO9EEBYPTqHB7Wy6BEkc77RyPvWzKktvrYzf5iR7RF6KfNVgyQ4+SM7FQNMLsUocU/EmbEWSy9v5Yd253R/wfP0LrLieVCX2byjjb/za1s+6YTln/Ns7wRdZQIzaKnGUlMgHYXvKVry3eT0P5hdtXhPf/3gz/lWj/wcNZG6atc23CDpG2cfjQyG8ql2qbriWo+l38nRcC2IhprgBRbllF1D897aK+2Y/BGSJmSZPmWFPCJ2MmRMzQN+pDm2Ah+GhwZQMWCMrSu";

	private static final String SANDBOX_SERVER_URL = "https://api.sandbox.ebay.com/wsapi";

	private ApiContext apiContext;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		apiContext = setupApiContext();
	}

	public void testGetCategoriesSingleCall() throws Exception {
		
		EbayCrawler ebayCrawler = new EbayCrawler(apiContext);

		List<EbayCategory> ebayCategories = ebayCrawler
				.getCategoriesSingleCall();
		System.out
				.println("--------------------- testGetCategoriesSingleCall --------------------- ");
		int cnt = 1;
		for (EbayCategory ebc : ebayCategories) {
			System.out.println("[" + cnt++ + "] - " + ebc);
		}
	}

	public void testGetCategories() throws Exception {
		EbayCrawler ebayCrawler = new EbayCrawler(apiContext);

		List<EbayCategory> ebayCategories = ebayCrawler
				.getCategories();

		System.out
				.println("--------------------- testGetCategoriesBestPractice --------------------- ");
		int cnt = 1;
		for (EbayCategory ebc : ebayCategories) {
			System.out.println("[" + cnt++ + "] - " + ebc);
		}
	}

	public void testGenerateOwl() throws Exception {
		EbayCrawler ebayCrawler = new EbayCrawler(apiContext);

		List<EbayCategory> ebayCategories = ebayCrawler
				.getCategories();
		ebayCrawler.generateOwl(ebayCategories, new CrawlingContext());
	}

	public void testGetCategoriesVersion() throws Exception {
		EbayCrawler ebayCrawler = new EbayCrawler(apiContext);
		String version = ebayCrawler.getCategoriesVersion();

		System.out.println("Categories version: " + version);
	}
	
	private ApiAccount initApiAccount() {
		ApiAccount apiAccount = new ApiAccount();
		apiAccount.setDeveloper("6c424592-6983-4bb1-b9f8-274e91ff1761");
		apiAccount.setApplication("Aleksand-6699-40c8-91ae-cc29c24be427");
		apiAccount.setCertificate("ebbe888d-741c-48c9-bfff-f43cd1fb76b4");

		return apiAccount;
	}

	private ApiContext setupApiContext() {
		ApiCredential apiCredential = new ApiCredential();

		ApiAccount apiAccount = initApiAccount();
		apiCredential.setApiAccount(apiAccount);

		ApiContext apiContext = new ApiContext();

		apiCredential.seteBayToken(SANDBOX_TOKEN);
		
		apiContext.setApiCredential(apiCredential);		
		apiContext.setApiServerUrl(SANDBOX_SERVER_URL);

		return apiContext;
	}

}
