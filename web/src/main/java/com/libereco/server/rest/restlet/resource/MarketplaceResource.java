/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.rest.restlet.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.restlet.resource.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.google.gson.Gson;
import com.libereco.server.dto.MarketplaceDto;
import com.libereco.server.rest.model.resp.GetAllMarketplacesResponse;
import com.libereco.server.rest.model.resp.ResponseHeader;
import com.libereco.server.service.ServiceManager;
import com.libereco.server.utils.json.GsonFactory;

/**
 * @author Aleksandar
 * 
 */
public class MarketplaceResource extends AbstractLiberecoResource {

	private Logger logger = LoggerFactory.getLogger(MarketplaceResource.class);

	@Get
	public String getResource() {
		String response = null;

		if (isAllRequest()) {
			List<MarketplaceDto> marketplaces = ServiceManager
					.getMarketplaceService().getMarketplaces();
			response = buildGetAllMarketplacesResponse(marketplaces);
		} else {
			logger.warn("Unsupported request");
			response = buildErrorResponse("Unsupported request");
		}

		return response;
	}
	
	private String buildGetAllMarketplacesResponse(
			List<MarketplaceDto> marketplaces) {
		
		String response = null;
		List<String> marketplaceNames = new ArrayList<String>();

		if (marketplaces != null) {
			for (MarketplaceDto mp : marketplaces) {
				marketplaceNames.add(mp.getMarketplaceName());
			}
		}

		GetAllMarketplacesResponse mpResponse = new GetAllMarketplacesResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setCode(HttpStatus.OK.toString());
		responseHeader.setStatus(HttpServletResponse.SC_OK);
		//responseHeader.setMessage(message);
		mpResponse.setHeader(responseHeader);

		mpResponse.setMarketplaceNames(marketplaceNames);
				
		Gson gson = new GsonFactory().createGson();		
		response = gson.toJson(mpResponse);
		
		return response;
	}

	private boolean isAllRequest() {
		String path = getReference().getPath();
		return path != null && path.contains("all");
	}
}
