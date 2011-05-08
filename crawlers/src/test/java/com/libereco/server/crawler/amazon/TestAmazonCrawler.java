/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.amazon;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import junit.framework.TestCase;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.libereco.server.crawler.CrawlingContext;
import com.libereco.server.crawler.CrawlingException;

/**
 * @author Aleksandar
 * 
 */
public class TestAmazonCrawler extends TestCase {

	/*
	 * Browse Node IDs
	 * 
	 * The following table presents browse node IDs by search index and locale.
	 * These IDs represent the top level browse nodes only , You can use these
	 * IDs in a BrowseNodeLookup request to get additional browse node IDs.
	 * 
	 * These IDs were valid as of the publication date of this guide. CA DE FR
	 * JP UK US Apparel 78689031 340855031 361299011 83451031 1036682 Automotive
	 * 78194031 2017304051 248877031 15690151 Baby 357577011 206617031 13331821
	 * 60032031 1036682 Beauty 64257031 197858031 52391051 66280031 11055981
	 * Books 927726 541686 468256 465610 1025612 1000 Classical 962454 542676
	 * 537366 562032 505510 301668 DigitalMusic 195208011 DVD 14113311 547664
	 * 578608 562002 283926 130 Electronics 677211011 569604 1058082 3210991
	 * 560800 493964 ForeignBooks 927726 54071011 69633011 388316011 GourmetFood
	 * 3580501 Grocery 340846031 57239051 340834031 16310101 HealthPersonalCare
	 * 64257031 197861031 161669011 66280031 3760931 Hobbies 13331821 HomeGarden
	 * 10925241 11052591 285080 HomeImprovement 2016929051 Industrial 228239
	 * Jewelry 327473011 193711031 85896051 193717031 3880591 Kitchen 2206275011
	 * 3169011 57686031 3839151 11052591 1063498 Lighting 213083031 213080031
	 * 213077031 Magazines 1198526 599872 Merchants 493964 Miscellaneous
	 * 10304191 MP3Downloads 77256031 206442031 2128134051 77198031 195211011
	 * Music 962454 542676 537366 562032 505510 301668 MusicalInstruments
	 * 11965861 11965861 11965861 11965861 11965861 OfficeProducts 16291311
	 * 192420031 560800 1084128 OutdoorLiving 10925051 11052591 1063498
	 * PCHardware 569604 493964 PetSupplies 1063498 Photo 569604 493964 Shoes
	 * 215934031 2016926051 Software 3234171 542064 548012 637630 1025614 409488
	 * SoftwareVideoGames 3323751 541708 548014 1025616 SportingGoods 16435121
	 * 14315361 319530011 1079730 Tools 11052591 468240 Toys 12950661 548014
	 * 13331821 712832 493964 VHS 962072 547082 578610 561972 283926 404272
	 * Video 962454 547664 578608 561972 283926 130 VideoGames 110218011 541708
	 * 548014 637872 1025616 493964 Watches 193708031 60937031 14315361 595312
	 * 1079730 Wireless 508494 WirelessAccessories 13900851
	 */

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
	 * US: ecs.amazonaws.com CA: ecs.amazonaws.ca UK: ecs.amazonaws.co.uk DE:
	 * ecs.amazonaws.de FR: ecs.amazonaws.fr JP: ecs.amazonaws.jp
	 */
	private static final String ENDPOINT = "ecs.amazonaws.com";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testCrawl() throws CrawlingException {
		AmazonCrawler amazonCrawler = new AmazonCrawler(AWS_ACCESS_KEY_ID,
				AWS_SECRET_KEY, ENDPOINT, 1);
		amazonCrawler.crawl(new CrawlingContext());
	}

	@Test
	public void testBrowseNode() throws InvalidKeyException,
			IllegalArgumentException, NoSuchAlgorithmException, JAXBException,
			ParserConfigurationException, SAXException, IOException {
		SignedRequestsHelper helper = SignedRequestsHelper.getInstance(
				ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
		testBrowseNode(helper, 1036682);
	}

	@Test
	public void testBrowseRootNodes() throws InvalidKeyException,
			IllegalArgumentException, NoSuchAlgorithmException, JAXBException,
			ParserConfigurationException, SAXException, IOException {
		Integer[] rootIds = { 1036682, 15690151, 11055981, 1000, 301668,
				195208011, 130, 493964 };

		SignedRequestsHelper helper = SignedRequestsHelper.getInstance(
				ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);

		for (Integer rid : rootIds) {
			testBrowseNode(helper, rid);
		}
	}

	void testBrowseNode(SignedRequestsHelper helper, Integer nodeId)
			throws JAXBException, ParserConfigurationException, SAXException,
			IOException {

		// String queryString =
		// "Service=AWSECommerceService&Version=2009-03-31&Operation=BrowseNodeLookup&BrowseNodeId="
		// + nodeId.toString();
		String queryString = "Service=AWSECommerceService&Version=2010-11-01&Operation=BrowseNodeLookup&BrowseNodeId="
				+ nodeId.toString();
		String requestUrl = helper.sign(queryString);
		System.out.println("Request is \"" + requestUrl + "\"");

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(requestUrl);

		parseBrowseNodeLookupResponse(doc);

		// parseBrowseNodeLookupResponse(response);

		// String nodeName = fetchNodeName(requestUrl);
		// System.out.println("Name is \"" + nodeName + "\"");
		// System.out.println();
	}

	// private void parseBrowseNodeLookupResponse(String url) throws
	// JAXBException {
	// JAXBContext jc = JAXBContext
	// .newInstance(BrowseNodeLookupResponse.class);
	//
	// Unmarshaller um = jc.createUnmarshaller();
	// StringReader sr = new StringReader(url);
	//
	// BrowseNodeLookupResponse response = (BrowseNodeLookupResponse) um
	// .unmarshal(sr);
	// System.out.println("Response: " + response);
	// }

	private void parseBrowseNodeLookupResponse(Document doc)
			throws JAXBException {
		JAXBContext jc = JAXBContext
				.newInstance(BrowseNodeLookupResponse.class);

		Unmarshaller um = jc.createUnmarshaller();
		// StringReader sr = new StringReader(url);

		BrowseNodeLookupResponse response = (BrowseNodeLookupResponse) um
				.unmarshal(doc);
		System.out.println("Response: " + response);
	}
}