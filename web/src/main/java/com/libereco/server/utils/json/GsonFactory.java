/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Aleksandar
 *
 */
public class GsonFactory {

	public Gson createGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();

		// TODO: Add method(s) that allow caller to register type adapters if required		
		// gsonBuilder.registerTypeAdapter(EtsyCategory.class, new EtsyCategoryDeserializer());

		// If we want to disable HTML escaping in response disableHtmlEscaping
		// method needs to be invoked on the builder
		Gson gson = gsonBuilder.disableHtmlEscaping().setPrettyPrinting().create();
		
		return gson;
	}
}
