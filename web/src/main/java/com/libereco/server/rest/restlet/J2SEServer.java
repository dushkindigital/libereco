/**
 * 
 */
package com.libereco.server.rest.restlet;

import org.restlet.Component;
import org.restlet.data.Protocol;

import com.libereco.server.rest.restlet.RestletApplication;

/**
 * @author roger
 *
 * A Restlet application can run inside a regular JVM or JRE using a single
 * "org.restlet.jar" JAR in the classpath. For this we only need to create a
 * Restlet component and associate an HTTP server connector.
 * Once you have launched the main class, type the following URL:
 * http://localhost:8182/firstSteps/hello
 * the server will happily welcome you with a nice "hello, world"
 */
public class J2SEServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 *  create a new Component
		 *  A Component is a Restlet managing a set of Connectors, VirtualHosts,
		 *  Services and Applications. Applications are expected to be directly 
		 *  attached to virtual hosts or to the internal router (see RIAP
		 *  pseudo-protocol for usage). Components also expose several services:
		 *  access logging and status setting.
		 *  From an architectural point of view, here is the REST definition:
		 *  "A component is an abstract unit of software instructions and internal
		 *  state that provides a transformation of data via its interface." Roy T. Fielding
		 *  The configuration of a Component can be done programmatically or by using a
		 *  XML document. There are dedicated constructors that accept either an URI
		 *  reference to such XML document or a representation of such XML document,
		 *  allowing easy configuration of the list of supported client and server
		 *  connectors as well as services. In addition, you can add and configure virtual
		 *  hosts (including the default one). Finally, you can attach applications either
		 *  using their fully qualified class name or by pointing to a descriptor document
		 *  (at this time only WADL description are supported, see the WADL Restlet extension
		 *  for details).
		 */
		Component component = new Component();
		
		// add a new HTTP server listening on port 8182
		component.getServers().add(Protocol.HTTP, 8182);
		
		// attach the sample application
		// component.getDefaultHost().attach("/firstSteps", new FirstStepsApplication());
		component.getDefaultHost().attach("/libereco", new RestletApplication());
		// component.getDefaultHost().attach("/contact", new ContactServerApplication());
		
		// start the component
		try {
			component.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
