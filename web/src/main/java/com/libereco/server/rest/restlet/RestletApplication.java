/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.rest.restlet;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.Reference;
import org.restlet.routing.Filter;
import org.restlet.routing.Router;
import org.restlet.routing.Template;

import com.libereco.server.rest.restlet.resource.AuthDemoResource;
import com.libereco.server.rest.restlet.resource.MarketplaceResource;
import com.libereco.server.rest.restlet.resource.UserResource;

/**
 * @author Aleksandar
 * 
 */
public class RestletApplication extends Application {

	public RestletApplication() {
		super();
	}

	public RestletApplication(Context context) {
		super(context);
	}

	// Implemented to handle double slash at the start of URL
	@Override
	public Restlet getRoot() {
		Filter doubleSlashFilter = new Filter() {
			@Override
			protected int beforeHandle(Request request, Response response) {
				Reference ref = request.getResourceRef();
				String originalPath = ref.getPath();
				if (originalPath.contains("//")) {
					String newPath = originalPath.replaceAll("//", "/");
					ref.setPath(newPath);
				}
				return Filter.CONTINUE;
			}
		};

		Router router = new Router();
		attachRoutes(router);
		doubleSlashFilter.setNext(router);
		
		return doubleSlashFilter;
	}

	/**
	 * Creates a root Restlet that will receive all incoming calls.
	 */
	// TODO: Wire mappings if we decide to manually load Spring

	@Override
	public synchronized Restlet createInboundRoot() {

		Router router = new Router(getContext());
		attachRoutes(router);

		return router;
	}

	private void attachRoutes(Router router) {
		// User-related methods
		router.attach("/users/user/edit/id/{id}", UserResource.class);
		router.attach("/users/user/remove/id/{id}", UserResource.class);
		router.attach("/users/user/add", UserResource.class);
		router.attach("/users/user/", UserResource.class).setMatchingMode(
				Template.MODE_STARTS_WITH);

		// User-related authorization demo methods
		router.attach("/users/authDemo/startEbayAuth", AuthDemoResource.class);				
		router.attach("/users/authDemo/fetchEbayToken", AuthDemoResource.class);
		router.attach("/users/authDemo/startEtsyAuth", AuthDemoResource.class);				
		router.attach("/users/authDemo/fetchEtsyToken", AuthDemoResource.class);
		
		// Marketplace methods		
		router.attach("/marketplaces/all", MarketplaceResource.class);	
	}
}
