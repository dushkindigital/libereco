/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.amazon;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.TestCase;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
/**
 * @author Aleksandar
 *
 */
public class TestAmazonRestSample extends TestCase {
	
	@Test
	public void testSample() throws InterruptedException {
		ItemLookupSample lookupSample = new ItemLookupSample();
		for (int i = 0; i < 10000; i++) {
			System.out.println("Run [" + i + "]");
			lookupSample.runSampleQuery();
			Thread.sleep(1000);
		}		
	}

	class ItemLookupSample {
		public ItemLookupSample() {}
		
	    /*
	     * Your AWS Access Key ID, as taken from the AWS Your Account page.
	     */
	    private static final String AWS_ACCESS_KEY_ID = "AKIAIWX5T6SPU6XOACJQ";

	    /*
	     * Your AWS Secret Key corresponding to the above ID, as taken from the AWS
	     * Your Account page.
	     */
	    private static final String AWS_SECRET_KEY = "b/O3Zt1cRvUxg5k6lB0T+uIUVUzL/WKHE68tqhCj";

	    /*
	     * Use one of the following end-points, according to the region you are
	     * interested in:
	     * 
	     *      US: ecs.amazonaws.com 
	     *      CA: ecs.amazonaws.ca 
	     *      UK: ecs.amazonaws.co.uk 
	     *      DE: ecs.amazonaws.de 
	     *      FR: ecs.amazonaws.fr 
	     *      JP: ecs.amazonaws.jp
	     * 
	     */
	    private static final String ENDPOINT = "ecs.amazonaws.com";

	    /*
	     * The Item ID to lookup. The value below was selected for the US locale.
	     * You can choose a different value if this value does not work in the
	     * locale of your choice.
	     */
	    private static final String ITEM_ID = "0545010225";

	    public void runSampleQuery() {
	    	/*
	         * Set up the signed requests helper 
	         */
	        SignedRequestsHelper helper;
	        try {
	            helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return;
	        }
	        
	        String requestUrl = null;
	        String title = null;

	        /* The helper can sign requests in two forms - map form and string form */
	        
	        /*
	         * Here is an example in map form, where the request parameters are stored in a map.
	         */
	        System.out.println("Map form example:");
	        Map<String, String> params = new HashMap<String, String>();
	        params.put("Service", "AWSECommerceService");
	        params.put("Version", "2009-03-31");
	        params.put("Operation", "ItemLookup");
	        params.put("ItemId", ITEM_ID);
	        params.put("ResponseGroup", "Small");

	        requestUrl = helper.sign(params);
	        System.out.println("Signed Request is \"" + requestUrl + "\"");

	        title = fetchTitle(requestUrl);
	        System.out.println("Signed Title is \"" + title + "\"");
	        System.out.println();

	        /* Here is an example with string form, where the requests parameters have already been concatenated
	         * into a query string. */
	        System.out.println("String form example:");
	        String queryString = "Service=AWSECommerceService&Version=2009-03-31&Operation=ItemLookup&ResponseGroup=Small&ItemId="
	                + ITEM_ID;
	        requestUrl = helper.sign(queryString);
	        System.out.println("Request is \"" + requestUrl + "\"");

	        title = fetchTitle(requestUrl);
	        System.out.println("Title is \"" + title + "\"");
	        System.out.println();

	        testBrowseNode(helper);        
	    }
	    
//	    public static void main(String[] args) {
//	        /*
//	         * Set up the signed requests helper 
//	         */
//	        SignedRequestsHelper helper;
//	        try {
//	            helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	            return;
//	        }
//	        
//	        String requestUrl = null;
//	        String title = null;
//
//	        /* The helper can sign requests in two forms - map form and string form */
//	        
//	        /*
//	         * Here is an example in map form, where the request parameters are stored in a map.
//	         */
//	        System.out.println("Map form example:");
//	        Map<String, String> params = new HashMap<String, String>();
//	        params.put("Service", "AWSECommerceService");
//	        params.put("Version", "2009-03-31");
//	        params.put("Operation", "ItemLookup");
//	        params.put("ItemId", ITEM_ID);
//	        params.put("ResponseGroup", "Small");
//
//	        requestUrl = helper.sign(params);
//	        System.out.println("Signed Request is \"" + requestUrl + "\"");
//
//	        title = fetchTitle(requestUrl);
//	        System.out.println("Signed Title is \"" + title + "\"");
//	        System.out.println();
//
//	        /* Here is an example with string form, where the requests parameters have already been concatenated
//	         * into a query string. */
//	        System.out.println("String form example:");
//	        String queryString = "Service=AWSECommerceService&Version=2009-03-31&Operation=ItemLookup&ResponseGroup=Small&ItemId="
//	                + ITEM_ID;
//	        requestUrl = helper.sign(queryString);
//	        System.out.println("Request is \"" + requestUrl + "\"");
//
//	        title = fetchTitle(requestUrl);
//	        System.out.println("Title is \"" + title + "\"");
//	        System.out.println();
//
//	        testBrowseNode(helper);        
//	    }

	    private void testBrowseNode(SignedRequestsHelper helper) {
	              String queryString = "Service=AWSECommerceService&Version=2009-03-31&Operation=BrowseNodeLookup&BrowseNodeId=1000";
	        String requestUrl = helper.sign(queryString);
	        System.out.println("Request is \"" + requestUrl + "\"");

	        String nodeName = fetchNodeName(requestUrl);
	        System.out.println("Name is \"" + nodeName + "\"");
	        System.out.println();
	    }
	    
	    /*
	     * Utility function to fetch the response from the service and extract the
	     * title from the XML.
	     */
	    private String fetchTitle(String requestUrl) {
	        String title = null;
	        try {
	            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.parse(requestUrl);
	            Node titleNode = doc.getElementsByTagName("Title").item(0);
	            title = titleNode.getTextContent();
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	        return title;
	    }

	  private String fetchNodeName(String requestUrl) {
	        String title = null;
	        try {
	            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.parse(requestUrl);
	            Node titleNode = doc.getElementsByTagName("Name").item(0);
	            title = titleNode.getTextContent();
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	        return title;
	    }
	}
}

