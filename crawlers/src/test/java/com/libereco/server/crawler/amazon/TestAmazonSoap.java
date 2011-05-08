/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.amazon;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.libereco.server.crawler.amazon.soap.client.jax.Item;
import com.libereco.server.crawler.amazon.soap.client.jax.Items;

/**
 * @author Aleksandar
 * 
 */
public class TestAmazonSoap extends TestCase {

	private static final String AWS_ACCESS_KEY_ID = "AKIAIWX5T6SPU6XOACJQ";
	private static final String AWS_SECRET_KEY = "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";

	@Test
	public void testSample() {
		com.libereco.server.crawler.amazon.soap.client.jax.AWSECommerceService service = new com.libereco.server.crawler.amazon.soap.client.jax.AWSECommerceService();
		service.setHandlerResolver(new AwsHandlerResolver(AWS_SECRET_KEY));

		// Set the service port:
		com.libereco.server.crawler.amazon.soap.client.jax.AWSECommerceServicePortType port = service
				.getAWSECommerceServicePort();
		// Get the operation object:
		com.libereco.server.crawler.amazon.soap.client.jax.ItemSearchRequest itemRequest = new com.libereco.server.crawler.amazon.soap.client.jax.ItemSearchRequest();
		// Fill in the request object:
		itemRequest.setSearchIndex("Books");
		itemRequest.setKeywords("dog");
		// itemRequest.setVersion("2010-11-01");
		com.libereco.server.crawler.amazon.soap.client.jax.ItemSearch itemElement = new com.libereco.server.crawler.amazon.soap.client.jax.ItemSearch();
		itemElement.setAWSAccessKeyId(AWS_ACCESS_KEY_ID);
		itemElement.getRequest().add(itemRequest);
		// Call the Web service operation and store the response
		// in the response object:
		long tsStart = System.currentTimeMillis();

		com.libereco.server.crawler.amazon.soap.client.jax.ItemSearchResponse response = port
				.itemSearch(itemElement);

		long tsEnd = System.currentTimeMillis();
		System.out.println("Request, duration [" + (tsEnd - tsStart)
				+ "], response: " + response);

		if (response != null) {
			List<Items> items = response.getItems();
			if (items != null) {
				for (Items it : items) {
					if (it != null) {
						List<Item> itemList = it.getItem();
						if (itemList != null) {
							for (Item item : itemList) {
								System.out.println("Item - ASIN: "
										+ item.getASIN() + ", pageUrl: "
										+ item.getDetailPageURL());
							}
						}
					}
				}
			}
		}
	}

	@Test
	public void testBrowseNodeLookup() {
		com.libereco.server.crawler.amazon.soap.client.jax.AWSECommerceService service = new com.libereco.server.crawler.amazon.soap.client.jax.AWSECommerceService();
		service.setHandlerResolver(new AwsHandlerResolver(AWS_SECRET_KEY));

		// Set the service port:
		com.libereco.server.crawler.amazon.soap.client.jax.AWSECommerceServicePortType port = service
				.getAWSECommerceServicePort();
		// Get the operation object:
		com.libereco.server.crawler.amazon.soap.client.jax.BrowseNodeLookupRequest browseNodeLookupRequest = new com.libereco.server.crawler.amazon.soap.client.jax.BrowseNodeLookupRequest();
		browseNodeLookupRequest.getBrowseNodeId().add("1036682");
		// Fill in the request object:

		// browseNodeLookupRequest.setSearchIndex("Books");
		// browseNodeLookupRequest.setKeywords("dog");
		// itemRequest.setVersion("2010-11-01");
		com.libereco.server.crawler.amazon.soap.client.jax.BrowseNodeLookup itemElement = new com.libereco.server.crawler.amazon.soap.client.jax.BrowseNodeLookup();
		itemElement.setAWSAccessKeyId(AWS_ACCESS_KEY_ID);

		itemElement.getRequest().add(browseNodeLookupRequest);

		// Call the Web service operation and store the response
		// in the response object:
		long tsStart = System.currentTimeMillis();

		com.libereco.server.crawler.amazon.soap.client.jax.BrowseNodeLookupResponse response = port
				.browseNodeLookup(itemElement);

		long tsEnd = System.currentTimeMillis();
		System.out.println("Request, duration [" + (tsEnd - tsStart)
				+ "], response: " + response);

		if (response != null) {
			List<com.libereco.server.crawler.amazon.soap.client.jax.BrowseNodes> items = response
					.getBrowseNodes();
			if (items != null) {
				for (com.libereco.server.crawler.amazon.soap.client.jax.BrowseNodes it : items) {
					if (it != null) {
						List<com.libereco.server.crawler.amazon.soap.client.jax.BrowseNode> itemList = it
								.getBrowseNode();
						if (itemList != null) {
							for (com.libereco.server.crawler.amazon.soap.client.jax.BrowseNode item : itemList) {
								System.out.println("Item - id: "
										+ item.getBrowseNodeId() + ", name: "
										+ item.getName());

								com.libereco.server.crawler.amazon.soap.client.jax.BrowseNode.Children children = item
										.getChildren();

								if (children != null) {
									List<com.libereco.server.crawler.amazon.soap.client.jax.BrowseNode> childrenNodes = children
											.getBrowseNode();

									if (childrenNodes != null) {
										for (com.libereco.server.crawler.amazon.soap.client.jax.BrowseNode cn : childrenNodes) {
											System.out.println("Child ["
													+ cn.getBrowseNodeId()
													+ "]: " + cn.getName());
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
