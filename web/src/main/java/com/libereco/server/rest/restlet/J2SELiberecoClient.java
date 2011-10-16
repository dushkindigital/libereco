/**
 * 
 */
package com.libereco.server.rest.restlet;

import java.io.IOException;

import org.restlet.Client;
import org.restlet.Context;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Protocol;
import org.restlet.representation.ObjectRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import com.google.gson.Gson;
import com.libereco.server.rest.model.req.AddUserDetails;
import com.libereco.server.rest.restlet.resource.IUserResource;
import com.libereco.server.utils.json.GsonFactory;

/**
 * @author roger
 *
 */
public class J2SELiberecoClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Client client = new Client(new Context(), Protocol.HTTP);
		client.getContext().getParameters().add("tracing", "true");
		
//        ClientResource cr = new ClientResource(
//                "http://localhost:8182/libereco/users/user/add/userJ2SE001");
//        cr.setNext(client);
//        
//        // Get the UserDto object
//        IUserResource resource = cr.wrap(IUserResource.class);
//        String strUser = resource.getResource();
//        System.out.println("strUser: " + strUser);
//        
//        System.out.println("\nJSON representation");
//        try {
//			cr.get(MediaType.APPLICATION_JSON).write(System.out);
//			System.out.println("------------------------------");
//			cr.get(MediaType.APPLICATION_JAVA_OBJECT_XML).write(System.out);
//			System.out.println("------------------------------");
//		} catch (ResourceException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
        // Post 
        ClientResource userAddResource = new ClientResource(  
                "http://localhost:8182/libereco/users/user/add/userJ2SE002");
        userAddResource.setNext(client);
        
        AddUserDetails userDetails = new AddUserDetails();
        userDetails.setUserName("userName_userJ2SE002");
        userDetails.setPassword("password_userJ2SE002");
        Representation r = userAddResource.post(getRepresentation(userDetails), MediaType.APPLICATION_JSON);
        
//        IUserResource resource = userAddResource.wrap(IUserResource.class);
        
//        String r = resource.processPostRequest(getRepresentation(userDetails));
        if (userAddResource.getStatus().isSuccess()) {
        	System.out.println("userAddResource success...");
        	System.out.println(r);
        	try {
				System.out.println(r.getText());
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}

	/**
	 * Returns the Representation of an item.
	 * 
	 * @param item 
	 * @return The org.restlet.representation.ObjectRepresentation<T> of the item.
	 */
	public static Representation getRepresentation(AddUserDetails userDetails) {
		// Gathering informations into a Web form.
//		ObjectRepresentation<AddUserDetails> details = new ObjectRepresentation<AddUserDetails>(userDetails);
		Form form = new Form();
		form.add("id", userDetails.getUserName());
		form.add("password", userDetails.getPassword());
		
		Gson gson = new GsonFactory().createGson();
		String str = gson.toJson(userDetails);
		
//		try {
//			System.out.println("testing: " + details.getObject());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		return details;
//		return form.getWebRepresentation();
		return new StringRepresentation(str, MediaType.APPLICATION_JSON);
	}
}
