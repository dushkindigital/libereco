/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.rest.restlet.resource;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.restlet.data.Reference;
import org.restlet.representation.Representation;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.google.gson.Gson;
import com.libereco.server.rest.model.req.Credentials;
import com.libereco.server.rest.model.req.Request;
import com.libereco.server.rest.model.req.RequestJson;
import com.libereco.server.rest.model.resp.RedirectAuthResponse;
import com.libereco.server.rest.model.resp.ResponseHeader;
import com.libereco.server.rest.restlet.RestletConstants;
import com.libereco.server.utils.json.GsonFactory;

/**
 * @author Aleksandar
 * 
 */
public abstract class AbstractLiberecoResource extends ServerResource {

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractLiberecoResource.class);

	protected Logger getLiberecoResourceLogger() {
		return logger;
	}

	protected Credentials getCredentials(Representation entity,
			String entityText) throws IOException, JAXBException {

		Credentials creds = null;
		getLiberecoResourceLogger().debug("Entity text: " + entityText);

		if (entity.isCompatible(RestletConstants.APPLICATION_XML_VARIANT)) {
			JAXBContext jc = JAXBContext.newInstance(Request.class);
			Unmarshaller um = jc.createUnmarshaller();
			StringReader sr = new StringReader(entityText);

			Request req = (Request) um.unmarshal(sr);
			creds = req.getCredentials();
		} else if (entity
				.isCompatible(RestletConstants.APPLICATION_JSON_VARIANT)) {

			Gson gson = new GsonFactory().createGson();
			RequestJson reqJson = gson.fromJson(entityText, RequestJson.class);

			if (reqJson != null) {
				Request req = reqJson.getRequest();
				if (req != null) {
					creds = req.getCredentials();
				}
			}
		}

		getLiberecoResourceLogger().debug("Credentials = " + creds);

		return creds;
	}

	protected String processException(Exception e) {
		String response = buildErrorResponse(e);
		getLiberecoResourceLogger().warn("User request failed", e);
		return response;
	}

	protected String buildErrorResponse(Exception ex) {
		return buildErrorResponse("Processing Error ["
				+ ex.getLocalizedMessage() + "]");
	}

	protected String buildErrorResponse(String message) {
		String response = null;

		RedirectAuthResponse redirectAuthResponse = new RedirectAuthResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setCode(HttpStatus.METHOD_FAILURE.toString());
		responseHeader.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		responseHeader.setMessage(message);
		redirectAuthResponse.setHeader(responseHeader);

		Gson gson = new GsonFactory().createGson();

		response = gson.toJson(redirectAuthResponse);
		return response;
	}

	protected String buildSuccessResponse(String message) {
		String response = null;

		RedirectAuthResponse redirectAuthResponse = new RedirectAuthResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		responseHeader.setCode(HttpStatus.OK.toString());
		responseHeader.setStatus(HttpServletResponse.SC_OK);
		responseHeader.setMessage(message);
		redirectAuthResponse.setHeader(responseHeader);

		Gson gson = new GsonFactory().createGson();
		response = gson.toJson(redirectAuthResponse);

		return response;
	}

	protected void logReferenceInfo() {
		Reference reference = getReference();
		getLiberecoResourceLogger().debug(
				"Reference, path [" + reference.getPath() + "], identifier: "
						+ reference.getIdentifier() + ", last segment: "
						+ reference.getLastSegment() + ", path: "
						+ reference.getPath() + ", relative part: "
						+ reference.getRelativePart() + ", remaining part: "
						+ reference.getRemainingPart() + ", fragment: "
						+ reference.getFragment() + ", segments: "
						+ reference.getSegments());

		List<String> segments = reference.getSegments();

		if (segments != null) {
			for (String segment : segments) {
				getLiberecoResourceLogger().debug("Segment: " + segment);
			}
		}
	}

	protected String decode(String response)
			throws UnsupportedEncodingException {
		return decode(response, "UTF-8");
	}

	protected String decode(String response, String encoding)
			throws UnsupportedEncodingException {
		return URLDecoder.decode(response, encoding);
	}
}
