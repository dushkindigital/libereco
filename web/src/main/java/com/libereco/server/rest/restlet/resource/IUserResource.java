/**
 * 
 */
package com.libereco.server.rest.restlet.resource;

import java.io.IOException;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ResourceException;

/**
 * @author roger
 *
 */
public interface IUserResource {

	@Get
	public String getResource();
	
	@Get
	public String represent();
	
	@Post
	public String processPostRequest(Representation entity) throws ResourceException, IOException;

}
