/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.rest.restlet.resource;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.google.gson.Gson;
import com.libereco.server.auth.SignInDetails;
import com.libereco.server.auth.ebay.EbayAuthorizer;
import com.libereco.server.auth.ebay.EbayToken;
import com.libereco.server.auth.etsy.EtsyAuthorizer;
import com.libereco.server.auth.etsy.EtsyToken;
import com.libereco.server.dto.AuthRequestTokenDto;
import com.libereco.server.dto.AuthTokenDto;
import com.libereco.server.model.Marketplace.MarketplaceName;
import com.libereco.server.rest.model.req.Credentials;
import com.libereco.server.rest.model.resp.RedirectAuthResponse;
import com.libereco.server.rest.model.resp.ResponseHeader;
import com.libereco.server.rest.restlet.RestletConstants;
import com.libereco.server.service.MarketplaceService;
import com.libereco.server.service.ServiceManager;
import com.libereco.server.utils.json.GsonFactory;

/**
 * @author Aleksandar
 * 
 */
public class AuthDemoResource extends AbstractLiberecoResource {

	private static final Logger logger = LoggerFactory
			.getLogger(AuthDemoResource.class);

	protected Logger getLiberecoResourceLogger() {
		return logger;
	}

	@Override
	protected void doInit() throws ResourceException {
		super.doInit();

		// this.getVariants().add(RestletConstants.TEXT_XML_VARIANT);
		// this.getVariants().add(RestletConstants.APPLICATION_XML_VARIANT);
		// this.getVariants().add(RestletConstants.APPLICATION_JSON_VARIANT);

		logReferenceInfo();
	}

	// TODO: Make resource methods transactional
	// Find out if restlet has annotations to map methods with specific URLs so
	// that we don't have to process reference path to determine request type.
	@Post
	public String processPostRequest(Representation entity)
			throws ResourceException {

		String response = null;

		getLiberecoResourceLogger().debug(
				"processPostRequest, representation: " + entity);

		if (entity != null
				&& (entity
						.isCompatible(RestletConstants.APPLICATION_XML_VARIANT)
						|| entity
								.isCompatible(RestletConstants.APPLICATION_JSON_VARIANT) || entity
						.isCompatible(RestletConstants.TEXT_XML_VARIANT))) {

			try {

				// TODO: Confirm that entity's text doesn't get modified and
				// pass only entity to the getCredentials method
				String entityText = entity.getText().toString();
				MarketplaceService marketplaceService = ServiceManager
						.getMarketplaceService();

				Credentials creds = getCredentials(entity, entityText);
				logger.debug("Credentials = " + creds);

				String userName = null;
				if (creds != null) {
					userName = creds.getUserName();
				}

				if (userName != null) {
					if (isStartEbayAuthRequest()) {
						// Trigger eBay authorization.
						response = processStartEbayAuth(userName,
								marketplaceService);
					} else if (isStartEtsyAuthRequest()) {
						// Trigger Etsy authorization.
						response = processStartEtsyAuth(userName,
								marketplaceService);
					} else if (isFetchEbayTokenRequest()) {
						response = processFetchEbayToken(userName,
								marketplaceService);
					} else if (isFetchEtsyTokenRequest()) {
						String pin = getQuery().getValues("pin");
						response = processFetchEtsyToken(userName, pin,
								marketplaceService);
					} else {
						response = buildErrorResponse("Unsupported request");
					}
				} else {
					response = buildErrorResponse("User name not provided");
				}

			} catch (IOException e) {
				response = processException(e);
			} catch (JAXBException e) {
				response = processException(e);
			} catch (Exception e) {
				response = processException(e);
			}
		}

		return response;
	}

	private String processStartEbayAuth(String userName,
			MarketplaceService marketplaceService) throws Exception {

		String response = null;

		if (marketplaceService.isAuthorized(userName, MarketplaceName.EBAY) == false) {
			// TODO: Make a generic authorization service and migrate that functionality to the service			
			EbayAuthorizer ebayAuthorizer = ServiceManager.getEbayAuthorizer();
									
			SignInDetails signInDetails = ebayAuthorizer.getSignInDetails();
			String ebaySignInUrl = signInDetails.getSignInUrl(); 
			
			response = buildAuthRedirectResponse(ebaySignInUrl);

			logger.debug("Encoded redirect response: " + response);

			// Gson encodes characters, which we want to avoid so we have to
			// decode that response before sending it to the client.
			// TODO: Find out if there is a way to get Gson to do this
			// (disableHtmlEscaping doesn't seem to do the trick)
			response = decode(response);
			logger.debug("Decoded response: " + response);

			AuthRequestTokenDto requestToken = new AuthRequestTokenDto(signInDetails.getToken(),
					null);
			marketplaceService.storeAuthRequestToken(userName,
					MarketplaceName.EBAY, requestToken);
		} else {
			response = buildSuccessResponse("Already authorized");
		}

		return response;
	}

	private String processStartEtsyAuth(String userName,
			MarketplaceService marketplaceService) throws Exception {

		String response = null;

		if (marketplaceService.isAuthorized(userName, MarketplaceName.ETSY) == false) {
			EtsyAuthorizer etsyAuthorizer = ServiceManager.getEtsyAuthorizer();
			logger.debug("Etsy authorizer: " + etsyAuthorizer);

			SignInDetails signInResponse = etsyAuthorizer.getSignInDetails();
			logger.debug("SignIn response: " + signInResponse);

			String signInUrl = signInResponse.getSignInUrl();			
			response = buildAuthRedirectResponse(signInUrl);

			logger.debug("Encoded redirect response: " + response);

			// Gson encodes characters, which we want to avoid so we have to
			// decode that response before sending it to the client.
			// TODO: Find out if there is a way to get Gson to do this
			// (disableHtmlEscaping doesn't seem to do the trick)
			response = decode(response);
			logger.debug("Decoded response: " + response);

			AuthRequestTokenDto requestToken = new AuthRequestTokenDto(
					signInResponse.getToken(), signInResponse.getSecretToken());
			marketplaceService.storeAuthRequestToken(userName,
					MarketplaceName.ETSY, requestToken);
		} else {
			response = buildSuccessResponse("Already authorized");
		}

		return response;
	}

	private String processFetchEbayToken(String userName,
			MarketplaceService marketplaceService) throws Exception {
		String response = null;

		AuthRequestTokenDto authRequestToken = marketplaceService
				.getAuthRequestToken(userName, MarketplaceName.EBAY);

		if (authRequestToken != null) {
			String requestToken = authRequestToken.getToken();

			if (requestToken != null) {
				EbayAuthorizer ebayAuthorizer = ServiceManager
						.getEbayAuthorizer();
				EbayToken ebayToken = ebayAuthorizer.fetchToken(requestToken);

				if (ebayToken == null) {
					response = buildErrorResponse("User hasn't authorized application");
				} else {
					response = buildSuccessResponse(ebayToken.getToken());

					AuthTokenDto authToken = new AuthTokenDto();
					authToken.setToken(ebayToken.getToken());
					authToken.setExpirationTime(new Timestamp(ebayToken
							.getExpirationTime().getTimeInMillis()));

					marketplaceService.saveAuthorization(userName,
							MarketplaceName.EBAY, authToken);

					// Delete pending request
					marketplaceService.deletePendingAuthorization(userName,
							MarketplaceName.EBAY, requestToken);
				}
			} else {
				response = buildErrorResponse("Failed to retrieve request token");
			}
		} else {
			response = buildErrorResponse("Authorization process not initiated");
		}

		return response;
	}

	private String processFetchEtsyToken(String userName, String pin,
			MarketplaceService marketplaceService)
			throws OAuthMessageSignerException, OAuthNotAuthorizedException,
			OAuthExpectationFailedException, OAuthCommunicationException {

		String response = null;

		if (pin != null) {
			EtsyAuthorizer authorizer = ServiceManager.getEtsyAuthorizer();
			AuthRequestTokenDto authRequestToken = marketplaceService
					.getAuthRequestToken(userName, MarketplaceName.ETSY);

			String requestToken = authRequestToken.getToken();
			String requestTokenSecret = authRequestToken.getTokenSecret();

			EtsyToken etsyToken = authorizer.getToken(pin, requestToken,
					requestTokenSecret);
			String token = etsyToken.getAccessToken();

			if (token == null) {
				response = buildErrorResponse("User hasn't authorized application");
			} else {
				response = buildSuccessResponse(token);

				AuthTokenDto authToken = new AuthTokenDto();
				authToken.setToken(etsyToken.getAccessToken());
				authToken.setTokenSecret(etsyToken.getTokenSecret());

				marketplaceService.saveAuthorization(userName,
						MarketplaceName.ETSY, authToken);

				// Delete pending request
				marketplaceService.deletePendingAuthorization(userName,
						MarketplaceName.ETSY, requestToken);
			}
		} else {
			response = buildErrorResponse("Authorization process not initiated");
		}

		return response;
	}

	private String buildAuthRedirectResponse(String signInUrl) {
		String response = null;

		RedirectAuthResponse redirectAuthResponse = new RedirectAuthResponse();
		ResponseHeader responseHeader = new ResponseHeader();

		responseHeader.setCode(HttpStatus.MOVED_TEMPORARILY.toString());
		responseHeader.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
		responseHeader.setMessage("Authentication required");

		redirectAuthResponse.setHeader(responseHeader);
		redirectAuthResponse.setRedirectUrl(signInUrl);

		Gson gson = new GsonFactory().createGson();
		response = gson.toJson(redirectAuthResponse);

		return response;
	}

	// TODO: Do proper parsing/request type detection, this is just to get
	// things going
	private boolean isFetchEbayTokenRequest() {
		String path = getReference().getPath();
		return path != null && path.contains("fetchEbayToken");
	}

	private boolean isFetchEtsyTokenRequest() {
		String path = getReference().getPath();
		return path != null && path.contains("fetchEtsyToken");
	}

	private boolean isStartEbayAuthRequest() {
		String path = getReference().getPath();
		return path != null && path.contains("startEbayAuth");
	}

	private boolean isStartEtsyAuthRequest() {
		String path = getReference().getPath();
		return path != null && path.contains("startEtsyAuth");
	}
}
