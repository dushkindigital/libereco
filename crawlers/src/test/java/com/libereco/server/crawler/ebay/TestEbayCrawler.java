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

//	private static final String SANDBOX_TOKEN = "AgAAAA**AQAAAA**aAAAAA**HxRnTQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4CoAZKCqQ6dj6x9nY+seQ**nH4BAA**AAMAAA**R6VAGmaJi7R8srLSECpuKlBLFXG1O6QbAQTy+BZB7DNPWJm/VhfgJSLG7rRitmRfnmAoze71r2nzRDBhoMqtKEsbFmDONxfhDYJ88JamvmdvWkKvbKpSywcxJD2mP483SEBx4jDfPhKlP1y2MviVrrcdOPlbLNKUcUMa4oKe4RaN4YLSrdjiQP7znLLdccpWGYY5flDpsRXTsgkkORm/7zZW3hLwg4PAjsYSQ7cJWr2dcaFz9ePhw4qbyC5tOuBzqvV9yEm4jNHP2JAnGxMBEfrbtsrwgPiDAZP13qQWy6lI6PckRfEysu6R3V17yShd3J/FZoygz2jFNFWp8n7rZDSR/fiy40sH0bNUXpSwC0AzTXOGOQ7K/XPk2DehSNIhxeY+UG4Vj+owz7v5RTqYMSzNWVMPNjSPuvGG9xuCbWOVmLSaG/xEXLv/lVMCK+tjuuxStmfJuBdAKqTimR/jwuQRawrkOW6/m1ZOwRcq1Qiuz9XMZI3v2rKO9EEBYPTqHB7Wy6BEkc77RyPvWzKktvrYzf5iR7RF6KfNVgyQ4+SM7FQNMLsUocU/EmbEWSy9v5Yd253R/wfP0LrLieVCX2byjjb/za1s+6YTln/Ns7wRdZQIzaKnGUlMgHYXvKVry3eT0P5hdtXhPf/3gz/lWj/wcNZG6atc23CDpG2cfjQyG8ql2qbriWo+l38nRcC2IhprgBRbllF1D897aK+2Y/BGSJmSZPmWFPCJ2MmRMzQN+pDm2Ah+GhwZQMWCMrSu";
	protected static final String SANDBOX_TOKEN = "AgAAAA**AQAAAA**aAAAAA**bM+OTg**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4GhCJCHogmdj6x9nY+seQ**NJUBAA**AAMAAA**Ev2/Y/LSrSH/vSP2RHsnHzn21Fqj5U2QheUqyQ0z9CgBIJbdZ3mXFYo44gVKlecsuRg+mpJb8o5IV1hRRs233VVdFc47PychK1WKkYy3hzaYo8amSW9VmM5sYFc71HZKel4CCxjlj/N12eeWgqMcYJ1334s/7LS+rRRtMYIV2kGYn0FTMx6Rd4dyz59H+SrHOCfM13FP6UV7NJy+kCF6vEefnJagmvKV2yu6FEW16UHWg+Kdi08R3BmUAbtEV76fib2psgsM/Syws1xNTplhpF3EkjAVcA7VmCJwzJn2ZbZONvs0bVOChQ4CZozUIc0UUQjlCPDX6OsRqpBfDdHxydnG6+kkGCyXcAIFYEIQJAr7lVvchcjERE2f9BOfltkHMTHeF44RTjw/+7rpsAWNieKhH3YPyIG4Jclk3dAL0LXD4RRu/zdMNcZhIuXJRVCVoEp5ek8pdAd7jwHCjHBhp82+/ofJgbwa3FgBPFRf4bJzVV2CkVYXIxecXBN46ynNVyE8TdGYASkn6Kde7dQDRDyYfT5386LzIARKtNg9sRwczJey/uFfspb+4Zcuvkb3OeIUM6hb9d9GnRg6XyvmGj+mntJDgNyrrnLe9F4mOyWXSvyDXT1PZQxte68l9I4Aw26ukqLs+LCmFa7ikgsDSPHwF1e4Kss/IoXA1FH/NcO79MB04xxvfsXGsy/leevws4xRTTuwCNnfYIlpnogjiRqXdvdQ/0h4aT3AI6bhjWBtvzBM0ZLzA6ocUSa7g++2";

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
		apiAccount.setDeveloper("74c52e41-c565-4b16-8989-44b4a1d7fb2d");
		apiAccount.setApplication("RegCared-f1ed-4eb7-923d-cb91e99d8518");
		apiAccount.setCertificate("83ae77ea-4b93-48f6-9535-4d7b60c417d8");

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
