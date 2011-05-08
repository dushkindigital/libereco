/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.amazon;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.libereco.server.crawler.CrawlingContext;
import com.libereco.server.crawler.CrawlingException;
import com.libereco.server.crawler.MarketplaceCrawler;
import com.libereco.server.marketplace.Marketplace;
import com.libereco.server.marketplace.MarketplaceImpl;
import com.libereco.server.owl.OwlCategoryConfig;
import com.libereco.server.owl.OwlCategoryWriter;

/**
 * @author Aleksandar
 * 
 */
public class AmazonCrawler implements MarketplaceCrawler {

	private Logger logger = LoggerFactory.getLogger(AmazonCrawler.class);

	private static final List<Long> ROOT_NODE_IDS_US = Arrays
			.asList(new Long[] { 1036682L, 15690151L, 1036682L, 11055981L,
					1000L, 301668L, 195208011L, 130L, 493964L, 3580501L,
					16310101L, 3760931L, 285080L, 228239L, 3880591L, 1063498L,
					599872L, 493964L, 10304191L, 195211011L, 301668L,
					11965861L, 1084128L, 1063498L, 493964L, 1063498L, 493964L,
					409488L, 1079730L, 468240L, 493964L, 404272L, 130L,
					493964L, 1079730L, 508494L, 13900851L });

	private String awsAccessKeyId;

	private String awsSecretKey;

	private String endpoint;

	private String apiVersionNumber;

	// TODO: If we need to support additional countries we'll have to inject
	// root category ids for each country

	private List<Long> rootNodeIds = ROOT_NODE_IDS_US;

	private int maxRequestPerSecond = -1;

	public AmazonCrawler(String awsAccessKeyId, String awsSecretKey,
			String endpoint) {
		this(awsAccessKeyId, awsSecretKey, endpoint, ROOT_NODE_IDS_US, -1);
	}

	public AmazonCrawler(String awsAccessKeyId, String awsSecretKey,
			String endpoint, int maxRequestsPerSecond) {
		this(awsAccessKeyId, awsSecretKey, endpoint, ROOT_NODE_IDS_US,
				maxRequestsPerSecond, null);
	}

	public AmazonCrawler(String awsAccessKeyId, String awsSecretKey,
			String endpoint, List<Long> rootCategoryIds,
			int maxRequestsPerSecond) {
		this(awsAccessKeyId, awsSecretKey, endpoint, rootCategoryIds,
				maxRequestsPerSecond, null);
	}

	public AmazonCrawler(String awsAccessKeyId, String awsSecretKey,
			String endpoint, List<Long> rootNodeIds, int maxRequestsPerSecond,
			String apiVersionNumber) {
		this.awsAccessKeyId = awsAccessKeyId;
		this.awsSecretKey = awsSecretKey;
		this.endpoint = endpoint;
		this.rootNodeIds = rootNodeIds;
		this.maxRequestPerSecond = maxRequestsPerSecond;
		this.apiVersionNumber = apiVersionNumber;
	}

	public int getMaxRequestPerSecond() {
		return maxRequestPerSecond;
	}

	public void setMaxRequestPerSecond(int maxRequestPerSecond) {
		this.maxRequestPerSecond = maxRequestPerSecond;
	}

	@Override
	public void crawl(CrawlingContext crawlingContext) throws CrawlingException {
		try {
			List<AmazonCategory> amazonCategories = getCategories(crawlingContext);
			logger.debug("Finished retrieving categories");
			generateOwl(amazonCategories, crawlingContext);
		} catch (Exception e) {
			logger.warn("Crawling failed", e);
			throw new CrawlingException(e);
		}
	}

	public List<AmazonCategory> getCategories(CrawlingContext crawlingContext)
			throws IOException, InvalidKeyException, IllegalArgumentException,
			NoSuchAlgorithmException, JAXBException,
			ParserConfigurationException, SAXException {
		List<AmazonCategory> amazonCategories = new ArrayList<AmazonCategory>();

		AmazonCrawlingHelper amazonCrawlingHelper = new AmazonCrawlingHelper();

		getAmazonCategories(rootNodeIds, crawlingContext, amazonCrawlingHelper,
				amazonCategories);

		return amazonCategories;
	}

	private void getAmazonCategories(List<Long> nodeIds,
			CrawlingContext crawlingContext,
			AmazonCrawlingHelper amazonCrawlingHelper,
			List<AmazonCategory> amazonCategories) throws InvalidKeyException,
			IllegalArgumentException, NoSuchAlgorithmException, JAXBException,
			ParserConfigurationException, SAXException, IOException {

		if (nodeIds != null) {
			List<AmazonCategoryHelper> categoryHelpers = getCategoryHelpers(
					nodeIds, crawlingContext, amazonCrawlingHelper);

			if (categoryHelpers != null) {
				for (AmazonCategoryHelper ch : categoryHelpers) {
					AmazonCategory category = ch.getCategory();
					amazonCategories.add(category);

					List<AmazonCategory> children = ch.getChildren();
					List<Long> childrenNodeIds = getNodeIds(children);
					if (childrenNodeIds != null && childrenNodeIds.size() > 0) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							logger.warn("Sleep interrupted", e);
						}
						getAmazonCategories(childrenNodeIds, crawlingContext,
								amazonCrawlingHelper, amazonCategories);
					}
				}
			}
		}
	}

	private void throttleCheck(AmazonCrawlingHelper crawlingHelper) {
		if (maxRequestPerSecond > 0) {
			if (crawlingHelper.getQueryCounter() % maxRequestPerSecond == 0) {
				logger.debug("Throttling requests - going to sleep");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					logger.warn("Sleep interrupted", e);
				}
			}
		}
	}

	public List<AmazonCategory> getCategories() throws IOException,
			InvalidKeyException, IllegalArgumentException,
			NoSuchAlgorithmException, JAXBException,
			ParserConfigurationException, SAXException {
		return getCategories(new CrawlingContext());
	}

	public void generateOwl(List<AmazonCategory> amazonCategories,
			CrawlingContext crawlingContext)
			throws OWLOntologyCreationException, OWLOntologyStorageException {

		OwlCategoryWriter owlCategoryWriter = new OwlCategoryWriter();

		OwlCategoryConfig marketplaceCategoryConfig = new OwlCategoryConfig();
		marketplaceCategoryConfig.setClassId("#AmazonCategory");
		marketplaceCategoryConfig.setInstanceId("#amazonCategory");
		marketplaceCategoryConfig.setMarketplaceName("amazon");
		marketplaceCategoryConfig.setOutputFileFolder(crawlingContext
				.getOwlOutputFolder());
		marketplaceCategoryConfig.setOutputFileName("amazonCategories");
		marketplaceCategoryConfig.setOutputFileDatePattern("yyyyMMdd");

		logger.debug("Generate OWL for categories");

		owlCategoryWriter.generateOwl(amazonCategories,
				marketplaceCategoryConfig);
	}

	@Override
	public Marketplace getMarketplace() {
		return MarketplaceImpl.AMAZON;
	}

	private List<Long> getNodeIds(List<AmazonCategory> categories) {
		List<Long> nodeIds = new ArrayList<Long>();

		if (categories != null) {
			for (AmazonCategory ac : categories) {
				Long id = Long.parseLong(ac.getMarketplaceCategoryId());
				nodeIds.add(id);
			}
		}

		return nodeIds;
	}

	List<AmazonCategory> getTopLevelCategories(CrawlingContext crawlingContext)
			throws InvalidKeyException, IllegalArgumentException,
			NoSuchAlgorithmException, JAXBException,
			ParserConfigurationException, SAXException, IOException {

		List<AmazonCategory> topCategories = new ArrayList<AmazonCategory>();

		SignedRequestsHelper helper = SignedRequestsHelper.getInstance(
				endpoint, awsAccessKeyId, awsSecretKey);

		for (Long rid : rootNodeIds) {

			BrowseNodeLookupResponse rootNodeResponse = getBrowseNode(rid,
					helper);
			AmazonCategory ac = buildCategory(rootNodeResponse, crawlingContext);

			// TODO: Add processing for parentId and id from context.
			// TODO: Update breadcrumbs if we decide to use them
			topCategories.add(ac);
		}

		return topCategories;
	}

	private List<AmazonCategoryHelper> getCategoryHelpers(List<Long> nodeIds,
			CrawlingContext crawlingContext,
			AmazonCrawlingHelper amazonCrawlingHelper) throws JAXBException,
			ParserConfigurationException, SAXException, IOException,
			InvalidKeyException, IllegalArgumentException,
			NoSuchAlgorithmException {

		List<AmazonCategoryHelper> categoryHelpers = new ArrayList<AmazonCategoryHelper>();

		if (nodeIds != null) {
			for (Long nid : nodeIds) {
				AmazonCategoryHelper ch = getCategoryHelper(nid,
						crawlingContext, amazonCrawlingHelper);
				categoryHelpers.add(ch);
			}
		}
		return categoryHelpers;
	}

	private AmazonCategoryHelper getCategoryHelper(Long nodeId,
			CrawlingContext crawlingContext,
			AmazonCrawlingHelper amazonCrawlingHelper) throws JAXBException,
			ParserConfigurationException, SAXException, IOException,
			InvalidKeyException, IllegalArgumentException,
			NoSuchAlgorithmException {

		SignedRequestsHelper signatureHelper = SignedRequestsHelper
				.getInstance(endpoint, awsAccessKeyId, awsSecretKey);

		return getCategoryHelper(nodeId, crawlingContext, amazonCrawlingHelper,
				signatureHelper);
	}

	private AmazonCategoryHelper getCategoryHelper(Long nodeId,
			CrawlingContext crawlingContext,
			AmazonCrawlingHelper amazonCrawlingHelper,
			SignedRequestsHelper signatureHelper) throws JAXBException,
			ParserConfigurationException, SAXException, IOException {

		AmazonCategoryHelper categoryHelper = null;

		throttleCheck(amazonCrawlingHelper);
		BrowseNodeLookupResponse browseNodeLookupResponse = getBrowseNode(
				nodeId, signatureHelper);
		amazonCrawlingHelper.incrementCounter();

		categoryHelper = processBrowseNodeLookupResponse(
				browseNodeLookupResponse, crawlingContext);

		return categoryHelper;
	}

	private BrowseNodeLookupResponse getBrowseNode(Long nodeId,
			SignedRequestsHelper helper) throws JAXBException,
			ParserConfigurationException, SAXException, IOException {

		BrowseNodeLookupResponse response = null;

		StringBuffer sb = new StringBuffer("Service=AWSECommerceService");
		if (apiVersionNumber != null) {
			sb.append("&Version=" + apiVersionNumber);
		}

		sb.append("&Operation=BrowseNodeLookup&BrowseNodeId="
				+ nodeId.toString());
		String queryString = sb.toString();

		String requestUrl = helper.sign(queryString);
		logger.debug("Request is \"" + requestUrl + "\"");

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(requestUrl);

		response = getBrowseNodeLookupResponse(doc);

		return response;
	}

	private BrowseNodeLookupResponse getBrowseNodeLookupResponse(Document doc)
			throws JAXBException {
		JAXBContext jc = JAXBContext
				.newInstance(BrowseNodeLookupResponse.class);

		Unmarshaller um = jc.createUnmarshaller();
		BrowseNodeLookupResponse response = (BrowseNodeLookupResponse) um
				.unmarshal(doc);
		return response;
	}

	private AmazonCategory buildCategory(
			BrowseNodeLookupResponse browseNodeLookupResponse,
			CrawlingContext crawlingContext) {
		AmazonCategory ac = null;

		if (browseNodeLookupResponse != null) {
			BrowseNode browseNode = browseNodeLookupResponse.getBrowseNodes()
					.getBrowseNode();
			ac = buildCategory(browseNode, crawlingContext);
		}

		return ac;
	}

	private AmazonCategoryHelper buildCategoryHelper(BrowseNode browseNode,
			CrawlingContext crawlingContext, boolean includeChildren) {

		AmazonCategoryHelper categoryHelper = new AmazonCategoryHelper();

		if (browseNode != null) {
			AmazonCategory ac = buildCategory(browseNode, crawlingContext);
			categoryHelper.setCategory(ac);

			if (includeChildren && (browseNode.getChildren() != null)) {
				Children nodeChildren = browseNode.getChildren();
				if (nodeChildren != null) {
					List<AmazonCategory> categoryChildren = new ArrayList<AmazonCategory>();
					List<BrowseNode> childrenBrowseNodes = nodeChildren
							.getChildren();
					if (childrenBrowseNodes != null) {
						for (BrowseNode bn : childrenBrowseNodes) {
							AmazonCategory childCategory = buildCategory(bn,
									crawlingContext);
							childCategory.setParentId(ac.getId());
							categoryChildren.add(childCategory);
						}
					}
					categoryHelper.setChildren(categoryChildren);
				}
			}
		}

		return categoryHelper;
	}

	private AmazonCategory buildCategory(BrowseNode browseNode,
			CrawlingContext crawlingContext) {
		AmazonCategory ac = null;

		if (browseNode != null) {
			String name = browseNode.getName();
			Long id = browseNode.getBrowseNodeId();
			ac = new AmazonCategory();
			ac.setMarketplaceCategoryId(id.toString());
			ac.setName(name);
			ac.setMarketplace(getMarketplace());

			// TODO: Add processing for parentId.
			// TODO: Decide whether we want to use breadcrumbs

			Long liberecoId = crawlingContext.getNextLibrecoId();
			ac.setId(liberecoId);

			if (logger.isDebugEnabled()) {
				logger.debug("Setting libereco id [" + liberecoId
						+ "], category id [" + id + "], category name [" + name
						+ "]");
			}
		}

		return ac;
	}

	private AmazonCategoryHelper processBrowseNodeLookupResponse(
			BrowseNodeLookupResponse browseNodeLookupResponse,
			CrawlingContext crawlingContext) {

		AmazonCategoryHelper categoryHelper = new AmazonCategoryHelper();
		BrowseNode browseNode = browseNodeLookupResponse.getBrowseNodes()
				.getBrowseNode();
		if (browseNodeLookupResponse != null) {
			categoryHelper = buildCategoryHelper(browseNode, crawlingContext,
					true);
		}

		return categoryHelper;
	}

	class AmazonCategoryHelper {

		AmazonCategory category;

		List<AmazonCategory> children;

		public AmazonCategory getCategory() {
			return category;
		}

		public void setCategory(AmazonCategory category) {
			this.category = category;
		}

		public List<AmazonCategory> getChildren() {
			return children;
		}

		public void setChildren(List<AmazonCategory> children) {
			this.children = children;
		}
	}

	class AmazonCrawlingHelper {

		int queryCounter;

		LinkedHashMap<String, AmazonCategory> categories;

		public AmazonCrawlingHelper() {
			categories = new LinkedHashMap<String, AmazonCategory>();
		}

		public List<AmazonCategory> getCategories() {
			return new ArrayList<AmazonCategory>(categories.values());
		}

		public void addCategory(AmazonCategory category,
				AmazonCategory parentCategory, CrawlingContext crawlingContext) {

			if (category != null) {
				if (logger.isDebugEnabled()) {
					logger.debug("Helper adding category [" + category
							+ "], parent category [" + parentCategory + "]");
				}

				if (parentCategory != null) {
					Long parentId = crawlingContext
							.getLiberecoId(parentCategory.getCategoryName());

					if (parentId != null) {
						if (logger.isDebugEnabled()) {
							logger.debug("Set parent id, category [" + category
									+ "], parent category [" + parentCategory
									+ "]");
						}
						category.setParentId(parentId);
					} else {
						logger.warn("Failed to lookup parent id, parent category ["
								+ parentCategory
								+ "], category ["
								+ category
								+ "]");
					}
				} else {
					// TODO: Find out whether this is the way we want to do it
					// for top-level categories (eBay does it this way) or
					// prefer to leave this one empty
					category.setParentId(category.getId());
				}

				String categoryName = category.getCategoryName();
				categories.put(categoryName, category);
			}
		}

		public void addTopLevelCategories(List<AmazonCategory> cats,
				CrawlingContext crawlingContext) {
			if (cats != null) {
				for (AmazonCategory ac : cats) {
					addCategory(ac, null, crawlingContext);
				}
			}
		}

		public int getQueryCounter() {
			return queryCounter;
		}

		public void setQueryCounter(int queryCounter) {
			this.queryCounter = queryCounter;
		}

		public void incrementCounter() {
			queryCounter += 1;
		}
	}
}
