/**
 * 
 */
package com.libereco.server.auth.ebay;

import junit.framework.TestCase;

import com.ebay.sdk.ApiAccount;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;

/**
 * @author Aleksandar
 *
 */
public class AbstractEbayTestCase extends TestCase {
	
	protected static final String SANDBOX_TOKEN = "AgAAAA**AQAAAA**aAAAAA**HxRnTQ**nY+sHZ2PrBmdj6wVnY+sEZ2PrA2dj6wFk4CoAZKCqQ6dj6x9nY+seQ**nH4BAA**AAMAAA**R6VAGmaJi7R8srLSECpuKlBLFXG1O6QbAQTy+BZB7DNPWJm/VhfgJSLG7rRitmRfnmAoze71r2nzRDBhoMqtKEsbFmDONxfhDYJ88JamvmdvWkKvbKpSywcxJD2mP483SEBx4jDfPhKlP1y2MviVrrcdOPlbLNKUcUMa4oKe4RaN4YLSrdjiQP7znLLdccpWGYY5flDpsRXTsgkkORm/7zZW3hLwg4PAjsYSQ7cJWr2dcaFz9ePhw4qbyC5tOuBzqvV9yEm4jNHP2JAnGxMBEfrbtsrwgPiDAZP13qQWy6lI6PckRfEysu6R3V17yShd3J/FZoygz2jFNFWp8n7rZDSR/fiy40sH0bNUXpSwC0AzTXOGOQ7K/XPk2DehSNIhxeY+UG4Vj+owz7v5RTqYMSzNWVMPNjSPuvGG9xuCbWOVmLSaG/xEXLv/lVMCK+tjuuxStmfJuBdAKqTimR/jwuQRawrkOW6/m1ZOwRcq1Qiuz9XMZI3v2rKO9EEBYPTqHB7Wy6BEkc77RyPvWzKktvrYzf5iR7RF6KfNVgyQ4+SM7FQNMLsUocU/EmbEWSy9v5Yd253R/wfP0LrLieVCX2byjjb/za1s+6YTln/Ns7wRdZQIzaKnGUlMgHYXvKVry3eT0P5hdtXhPf/3gz/lWj/wcNZG6atc23CDpG2cfjQyG8ql2qbriWo+l38nRcC2IhprgBRbllF1D897aK+2Y/BGSJmSZPmWFPCJ2MmRMzQN+pDm2Ah+GhwZQMWCMrSu";

	protected static final String SANDBOX_SERVER_URL = "https://api.sandbox.ebay.com/wsapi";

	protected static final String SANDBOX_RUNAME = "Aleksandar_Milj-Aleksand-6699-4-aymgtotbu";

	protected static final String SANDBOX_SIGNIN_URL = "https://signin.sandbox.ebay.com/ws/eBayISAPI.dll?SignIn&RuName=";
    
	protected static final String SANDBOX_EPS_SERVER_URL = "https://api.sandbox.ebay.com/ws/api.dll";
		
    //private static final String PRODUCTION_SIGNIN_URL = "https://signin.ebay.com/ws/eBayISAPI.dll?SignIn&RuName=";

    
	protected ApiContext apiContextSandbox;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		apiContextSandbox = buildApiContextSandbox();
	}
	
	protected ApiAccount initApiAccountSandbox() {
		ApiAccount apiAccount = new ApiAccount();
		apiAccount.setDeveloper("6c424592-6983-4bb1-b9f8-274e91ff1761");
		apiAccount.setApplication("Aleksand-6699-40c8-91ae-cc29c24be427");
		apiAccount.setCertificate("ebbe888d-741c-48c9-bfff-f43cd1fb76b4");

		return apiAccount;
	}

	protected ApiContext buildApiContextSandbox() {
		ApiCredential apiCredential = new ApiCredential();

		ApiAccount apiAccount = initApiAccountSandbox();
		apiCredential.setApiAccount(apiAccount);

		ApiContext apiContext = new ApiContext();

		apiCredential.seteBayToken(SANDBOX_TOKEN);

		apiContext.setApiCredential(apiCredential);
		apiContext.setApiServerUrl(SANDBOX_SERVER_URL);

		apiContext.setEpsServerUrl(SANDBOX_EPS_SERVER_URL);
		return apiContext;
	}

	protected ApiContext getApiContextSandbox() {
		return apiContextSandbox;
	}

}
