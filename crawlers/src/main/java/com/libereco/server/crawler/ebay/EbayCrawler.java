/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.ebay;

import java.util.ArrayList;
import java.util.List;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiLogging;
import com.ebay.sdk.call.GetCategoriesCall;
import com.ebay.soap.eBLBaseComponents.CategoryType;
import com.ebay.soap.eBLBaseComponents.DetailLevelCodeType;
import com.ebay.soap.eBLBaseComponents.SiteCodeType;
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
public class EbayCrawler implements MarketplaceCrawler {

	private Logger logger = LoggerFactory.getLogger(EbayCrawler.class);

	private static final int DEFAULT_MAX_LEVEL = 7;

	private ApiContext apiContext;

	private int maxLevel;

	public EbayCrawler(ApiContext apiContext) {
		this(apiContext, DEFAULT_MAX_LEVEL);
	}

	public EbayCrawler(ApiContext apiContext, int maxLevel) {
		this.apiContext = apiContext;
		this.maxLevel = maxLevel;
		ApiLogging apiLogging = initApiLogging();
		this.apiContext.setApiLogging(apiLogging);
	}

	private ApiLogging initApiLogging() {
		ApiLogging apiLogging = new ApiLogging();
		apiLogging.setLogSOAPMessages(false);
		apiLogging.setLogHTTPHeaders(true);

		return apiLogging;
	}

	/**
	 * Gets eBay categories for US site using the eBay's recommended approach
	 * (best practice). It gets list of 1st level categories and for each
	 * category it makes another call to get a list of all sub-categories.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<EbayCategory> getCategories(CrawlingContext crawlingContext)
			throws Exception {

		List<EbayCategory> ebayCategories = new ArrayList<EbayCategory>();

		long startTime = java.util.Calendar.getInstance().getTimeInMillis();

		boolean enableCompression = true;
		GetCategoriesCall api = new GetCategoriesCall(apiContext);
		api.setEnableCompression(enableCompression);
		api.addDetailLevel(DetailLevelCodeType.RETURN_ALL);

		int levelLimit = 1;
		api.setLevelLimit(levelLimit);
		api.setViewAllNodes(true);

		SiteCodeType site = SiteCodeType.US;
		api.setCategorySiteID(site);

		CategoryType[] cats = api.getCategories();
		if (cats != null) {

			logger.debug("1st Level [" + levelLimit + "], categories ["
					+ cats.length + "]");

			List<String> firstLevelCategoryIds = new ArrayList<String>();

			for (CategoryType ct : cats) {
				firstLevelCategoryIds.add(ct.getCategoryID());
				String categoryId = ct.getCategoryID();
				crawlingContext.addCategoryIdNameMapping(categoryId,
						ct.getCategoryName());
			}

			getFirstLevelChildren(firstLevelCategoryIds, ebayCategories,
					crawlingContext, api);
		}

		long endTime = java.util.Calendar.getInstance().getTimeInMillis();
		if (logger.isDebugEnabled()) {
			logger.debug("Get categories duration ["
					+ (endTime - startTime) + "]");
		}

		return ebayCategories;
	}

	public List<EbayCategory> getCategories() throws Exception {
		return getCategories(new CrawlingContext());
	}

	public List<EbayCategory> getCategoriesSingleCall(
			CrawlingContext crawlingContext) throws Exception {

		List<EbayCategory> ebayCategories = new ArrayList<EbayCategory>();
		boolean enableCompression = true;

		long startTime = java.util.Calendar.getInstance().getTimeInMillis();

		GetCategoriesCall api = new GetCategoriesCall(apiContext);
		api.setEnableCompression(enableCompression);

		api.addDetailLevel(DetailLevelCodeType.RETURN_ALL);
		int levelLimit = maxLevel;
		api.setLevelLimit(levelLimit);
		api.setViewAllNodes(true);

		SiteCodeType site = SiteCodeType.US;
		api.setCategorySiteID(site);

		CategoryType[] cats = api.getCategories();
		if (cats != null) {

			if (logger.isDebugEnabled()) {
				logger.debug("Level [" + levelLimit + "], categories ["
						+ cats.length + "]");
			}

			for (CategoryType ct : cats) {
				crawlingContext.addCategoryIdNameMapping(ct.getCategoryID(),
						ct.getCategoryName());
				EbayCategory ebayCategory = buildEbayCategory(ct,
						crawlingContext);
				ebayCategories.add(ebayCategory);
			}
		}

		long endTime = java.util.Calendar.getInstance().getTimeInMillis();

		if (logger.isDebugEnabled()) {
			logger.debug("Single call duration [" + (endTime - startTime) + "]");
		}

		return ebayCategories;

	}

	/**
	 * Gets eBay categories for US site using a single eBay API call. This is
	 * not a recommended practice because a timeout can occur.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<EbayCategory> getCategoriesSingleCall() throws Exception {
		return getCategoriesSingleCall(new CrawlingContext());
	}

	private void getFirstLevelChildren(List<String> firstLevelCategoryIds,
			List<EbayCategory> ebayCategories, CrawlingContext crawlingContext,
			GetCategoriesCall api) throws Exception {

		for (String flCategory : firstLevelCategoryIds) {
			api.setLevelLimit(maxLevel);
			api.setViewAllNodes(true);
			String[] parentCategories = new String[1];
			parentCategories[0] = flCategory;
			api.setParentCategory(parentCategories);

			if (logger.isDebugEnabled()) {
				logger.debug("--------------------------------------------------");
			}

			// TODO: This can be done in individual threads if we need to
			// speed things up
			CategoryType[] cats = api.getCategories();
			if (cats != null) {
				if (logger.isDebugEnabled()) {
					logger.debug("1st level category children, categoryId ["
							+ flCategory + "], category name ["
							+ crawlingContext.getCategoryName(flCategory)
							+ "], children count [" + cats.length + "]");
				}

				for (CategoryType ct : cats) {
					String categoryId = ct.getCategoryID();
					crawlingContext.addCategoryIdNameMapping(categoryId,
							ct.getCategoryName());
					EbayCategory ebayCategory = buildEbayCategory(ct,
							crawlingContext);
					ebayCategories.add(ebayCategory);
				}
			}
		}
	}

	public String getCategoriesVersion() throws Exception {
		GetCategoriesCall api = new GetCategoriesCall(apiContext);
		api.getCategories();
		String version = api.getReturnedCategoryVersion();
		return version;
	}

	public void crawl(CrawlingContext crawlingContext) throws CrawlingException {
		try {
			List<EbayCategory> ebayCategories = getCategories(crawlingContext);
			logger.debug("Finished retrieving categories");
			generateOwl(ebayCategories, crawlingContext);
		} catch (Exception e) {
			logger.warn("Crawling failed", e);
			throw new CrawlingException(e);
		}
	}

	public void generateOwl(List<EbayCategory> ebayCategories,
			CrawlingContext crawlingContext)
			throws OWLOntologyCreationException, OWLOntologyStorageException {

		OwlCategoryWriter owlCategoryWriter = new OwlCategoryWriter();

		OwlCategoryConfig marketplaceCategoryConfig = new OwlCategoryConfig();
		marketplaceCategoryConfig.setClassId("#EbayCategory");
		marketplaceCategoryConfig.setInstanceId("#ebayCategory");
		marketplaceCategoryConfig.setMarketplaceName("ebay");
		marketplaceCategoryConfig.setOutputFileFolder(crawlingContext
				.getOwlOutputFolder());
		marketplaceCategoryConfig.setOutputFileName("ebayCategories");
		marketplaceCategoryConfig.setOutputFileDatePattern("yyyyMMdd");

		logger.debug("Generate OWL for categories");
		
		owlCategoryWriter
				.generateOwl(ebayCategories, marketplaceCategoryConfig);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.libereco.server.crawler.MarketplaceCrawler#getMarketplace()
	 */
	public Marketplace getMarketplace() {
		return MarketplaceImpl.EBAY;
	}

	private EbayCategory buildEbayCategory(CategoryType ct,
			CrawlingContext crawlingContext) {

		String ebayCategoryId = ct.getCategoryID();
		String ebayCategoryParentId = ct.getCategoryParentID(0);
		String categoryName = ct.getCategoryName();

		if (logger.isDebugEnabled()) {
			logger.debug("Category id [" + ct.getCategoryID()
					+ "], category name [" + categoryName
					+ "], category level [" + ct.getCategoryLevel()
					+ "], parent id [" + ct.getCategoryParentID(0) + "]");
		}

		EbayCategory ebayCategory = new EbayCategory();
		Long liberecoId = crawlingContext.getNextLibrecoId();
		ebayCategory.setId(liberecoId);
		ebayCategory.setMarketplaceCategoryId(ebayCategoryId);
		ebayCategory.setName(categoryName);
		crawlingContext
				.addCategoryIdLiberecoMapping(ebayCategoryId, liberecoId);
		Long parentId = crawlingContext.getLiberecoId(ebayCategoryParentId);

		ebayCategory.setParentId(parentId);

		return ebayCategory;
	}
}
