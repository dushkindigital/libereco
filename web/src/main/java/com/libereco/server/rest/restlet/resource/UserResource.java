/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.rest.restlet.resource;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Timestamp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.libereco.server.dto.UserDto;
import com.libereco.server.model.UserConstants;
import com.libereco.server.rest.model.req.AddUserDetails;
import com.libereco.server.rest.model.req.AddUserRequest;
import com.libereco.server.rest.model.req.AddUserRequestJson;
import com.libereco.server.rest.model.req.Credentials;
import com.libereco.server.rest.restlet.RestletConstants;
import com.libereco.server.service.ServiceManager;
import com.libereco.server.service.UserService;
import com.libereco.server.utils.json.GsonFactory;

/**
 * @author Aleksandar
 * 
 */
public class UserResource extends AbstractLiberecoResource {

	private Logger logger = LoggerFactory.getLogger(UserResource.class);

	private String userId = null;

	public UserResource() {
	}

	@Override
	protected void doInit() throws ResourceException {
		super.doInit();

		// this.getVariants().add(RestletConstants.TEXT_XML_VARIANT);
		// this.getVariants().add(RestletConstants.APPLICATION_XML_VARIANT);
		// this.getVariants().add(RestletConstants.APPLICATION_JSON_VARIANT);

		logReferenceInfo();

		userId = (String) getRequest().getAttributes().get("id");
	}

	@Get
	public String getResource() {
		return represent();
	}

	@Get
	public String represent() {
		String queryUserId = getQuery().getValues("userId");

		logReferenceInfo();
		logger.debug("processGetRequest, isDelete [" + isDeleteRequest()
				+ "], isEdit [" + isEditRequest() + "], isAdd ["
				+ isAddRequest() + "]");

		return "User - " + " queryId [" + queryUserId + "], userId ["
				+ (userId != null ? userId : "") + "]";
	}

	// TODO: Make resource methods transactional
	// Find out if restlet has annotations to map methods with specific URLs so
	// that we don't have to process reference path to determine request type.
	@Post
	public String processPostRequest(Representation entity)
			throws ResourceException {

		String response = null;

		logger.debug("processPostRequest, representation: " + entity);

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
				UserService userService = ServiceManager.getUserService();

				if (isAddRequest()) {
					response = processAddRequest(entityText, entity,
							userService);
				} else {
					Credentials creds = getCredentials(entity, entityText);
					logger.debug("Credentials = " + creds);

					String userName = null;
					if (creds != null) {
						userName = creds.getUserName();
					}

					if (userName != null) {
						if (isDeleteRequest()) {
							response = processDeleteRequest(userName,
									userService);
						} else if (isEditRequest()) {
							response = processEditRequest(entityText, entity,
									userService);
						} 
					} else {
						response = buildErrorResponse("User name not provided");
					}
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

	private String processAddRequest(String entityText, Representation entity,
			UserService userService) throws IOException, JAXBException {
		String response = null;

		AddUserRequest addUserRequest = getAddUserRequest(entity, entityText);

		if (addUserRequest != null) {
			AddUserDetails addDetails = addUserRequest.getUserDetails();
			String userName = addDetails.getUserName();
						
			UserDto liberecoUser = userService.getUser(userName, false);

			if (liberecoUser != null) {
				if (liberecoUser.isActive() == false) {
					// TODO: Do we want to allow user reactivation this way?
					liberecoUser.setPassword(addDetails.getPassword());

					Timestamp ts = new Timestamp(System.currentTimeMillis());
					liberecoUser.setLastUpdated(ts);
					liberecoUser.setStatus(UserConstants.UserStatus.ACTIVE);

					userService.updateUser(liberecoUser);

					response = buildSuccessResponse("Added");
				} else {
					// TODO: Do we want to report this as success or failure
					response = buildSuccessResponse("User with same username already exists");
				}
			} else {
				liberecoUser = new UserDto();
				liberecoUser.setUserName(userName);
				liberecoUser.setPassword(addDetails.getPassword());

				Timestamp ts = new Timestamp(System.currentTimeMillis());
				liberecoUser.setLastUpdated(ts);
				liberecoUser.setStatus(UserConstants.UserStatus.ACTIVE);

				userService.addUser(liberecoUser);
				response = buildSuccessResponse("Added");
			}
		} else {
			response = buildErrorResponse("Failed to parse request");
		}

		return response;
	}

	private String processEditRequest(String entityText, Representation entity,
			UserService userService) throws IOException, JAXBException {
		String response = null;

		if (userId != null) {
			// Add and edit user requests use the same structure so for the time
			// being we'll use the same class for both requests
			AddUserRequest userRequest = getAddUserRequest(entity, entityText);

			if (userRequest != null) {
				AddUserDetails userDetails = userRequest.getUserDetails();

				if (userDetails != null) {
					UserDto liberecoUser = userService.getUser(
							userDetails.getUserName(), false);

					if (liberecoUser != null) {
						liberecoUser.setPassword(userDetails.getPassword());

						userService.updateUser(liberecoUser);
						response = buildSuccessResponse("Edited");
					} else {
						response = buildErrorResponse("User does not exist");
					}
				} else {
					response = buildErrorResponse("Invalid user request");
				}
			} else {
				// TODO: Send proper status and code
				response = buildErrorResponse("User id not specified");
			}
		} else {
			response = buildErrorResponse("Failed to parse request");
		}

		return response;
	}

	private String processDeleteRequest(String userName, UserService userService) {
		String response = null;

		if (userId != null) {
			// TODO: Authorization of the request action
			userService.deleteUser(userId);
			response = buildSuccessResponse("Deleted");
		} else {
			// TODO: Send proper status and code
			response = buildErrorResponse("User id not specified");
		}

		return response;
	}

	private AddUserRequest getAddUserRequest(Representation entity,
			String entityText) throws IOException, JAXBException {
		AddUserRequest request = null;
		logger.debug("Entity text: " + entityText);

		if (entity.isCompatible(RestletConstants.APPLICATION_XML_VARIANT)) {

			JAXBContext jc = JAXBContext.newInstance(AddUserRequest.class);
			Unmarshaller um = jc.createUnmarshaller();
			StringReader sr = new StringReader(entityText);

			request = (AddUserRequest) um.unmarshal(sr);

		} else if (entity
				.isCompatible(RestletConstants.APPLICATION_JSON_VARIANT)) {

			Gson gson = new GsonFactory().createGson();
			AddUserRequestJson reqJson = gson.fromJson(entityText,
					AddUserRequestJson.class);

			if (reqJson != null) {
				request = reqJson.getRequest();
			}
		}

		logger.debug("Add user request = " + request);
		return request;
	}

	// TODO: Do proper parsing/request type detection, this is just to get
	// things going
	private boolean isDeleteRequest() {
		String path = getReference().getPath();
		return path != null && path.contains("remove");
	}

	private boolean isEditRequest() {
		String path = getReference().getPath();
		return path != null && path.contains("edit");
	}

	private boolean isAddRequest() {
		String path = getReference().getPath();
		return path != null && path.contains("add");
	}
}
