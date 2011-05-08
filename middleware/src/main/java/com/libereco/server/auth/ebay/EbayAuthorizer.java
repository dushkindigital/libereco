/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.auth.ebay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiLogging;
import com.ebay.sdk.call.FetchTokenCall;
import com.ebay.sdk.call.GetSessionIDCall;
import com.libereco.server.auth.MarketplaceAuthorizationException;
import com.libereco.server.auth.MarketplaceAuthorizer;
import com.libereco.server.auth.SignInDetails;

/**
 * @author Aleksandar
 * 
 */
public class EbayAuthorizer implements MarketplaceAuthorizer {

	private Logger logger = LoggerFactory.getLogger(EbayAuthorizer.class);

	private ApiContext apiContext;

	private String ruName;

	private String signInUrl;

	public EbayAuthorizer(ApiContext apiContext, String signInUrl, String ruName) {
		this.apiContext = apiContext;
		ApiLogging apiLogging = initApiLogging();
		this.apiContext.setApiLogging(apiLogging);
		this.ruName = ruName;
		this.signInUrl = signInUrl;
	}

	private ApiLogging initApiLogging() {
		ApiLogging apiLogging = new ApiLogging();
		apiLogging.setLogSOAPMessages(false);
		apiLogging.setLogHTTPHeaders(true);

		return apiLogging;
	}

	public String getSessionId() throws Exception {
		GetSessionIDCall gsidCall = new GetSessionIDCall(apiContext);
		gsidCall.setRuName(this.ruName);
		String sessionId = gsidCall.getSessionID();
		logger.debug("Received sessionId: " + sessionId);

		return sessionId;
	}

	// TODO: Define a more specific exception, eBay's SDK throws generic
	// exception
	public EbayToken fetchToken(String sessionId) throws Exception {
		EbayToken token = null;
		FetchTokenCall ftCall = new FetchTokenCall(apiContext);
		ftCall.setSessionID(sessionId);

		// Invoke the method that fetches the token and populate token
		// information in the call object
		ftCall.fetchToken();

		token = new EbayToken(ftCall.getReturnedToken(),
				ftCall.getHardExpirationTime());

		logger.debug("Ebay token: " + token);

		return token;
	}

	public String buildSignInUrl(String sessionId) {
		return signInUrl + ruName + "&SessID=" + sessionId;
	}

	@Override
	public SignInDetails getSignInDetails() throws MarketplaceAuthorizationException {
		SignInDetails signInDetails = null;

		try {
			String sessionId = getSessionId();
			String signInUrl = buildSignInUrl(sessionId);
			signInDetails = new SignInDetails();
			signInDetails.setSignInUrl(signInUrl);
			signInDetails.setToken(sessionId);
		} catch (Exception e) {
			logger.warn("Failed to get session id", e);
			throw new MarketplaceAuthorizationException(e);
		}
		return signInDetails;
	}

}
