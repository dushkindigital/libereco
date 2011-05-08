/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.etsy;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
public class EtsyCrawler implements MarketplaceCrawler {

	private Logger logger = LoggerFactory.getLogger(EtsyCrawler.class);

	private String categoriesUrl;

	private String apiKey;

	private int maxRequestPerSecond = -1;

	public EtsyCrawler(String categoriesUrl, String apiKey) {
		this(categoriesUrl, apiKey, -1);
	}

	public EtsyCrawler(String categoriesUrl, String apiKey,
			int maxRequestPerSecond) {
		super();
		this.categoriesUrl = categoriesUrl;
		this.apiKey = apiKey;
		this.maxRequestPerSecond = maxRequestPerSecond;
	}

	public List<EtsyCategory> getCategories(CrawlingContext crawlingContext)
			throws IOException {
		List<EtsyCategory> etsyCategories = null;

		EtsyCrawlingHelper etsyCrawlingHelper = new EtsyCrawlingHelper();
		ClientResource cr = new ClientResource(categoriesUrl + "?" + "api_key="
				+ apiKey);

		// Get a JSON representation of the contact
		String jsonResponse = getJsonResponse(cr, etsyCrawlingHelper);
		GsonBuilder gsonBuilder = new GsonBuilder();

		// gsonBuilder.registerTypeAdapter(EtsyCategory.class,
		// new EtsyCategoryDeserializer());
		//
		// gsonBuilder.registerTypeAdapter(EtsyCategoryResponse.class,
		// new EtsyCategoryResponseDeserializer());

		// gson = new GsonBuilder().setPrettyPrinting().create();

		Gson gson = gsonBuilder.create();
		EtsyCategoryResponse etsyCategoryResponse = gson.fromJson(jsonResponse,
				EtsyCategoryResponse.class);

		logger.debug("Gson customized converted etsy category response: "
				+ etsyCategoryResponse.toString());

		List<EtsyCategory> topLevelCategories = etsyCategoryResponse
				.getResults();

		updateEtsyCategories(topLevelCategories, crawlingContext);
		etsyCrawlingHelper.addTopLevelCategories(topLevelCategories,
				crawlingContext);

		getChildCategories(topLevelCategories, gson, crawlingContext,
				etsyCrawlingHelper);

		etsyCategories = etsyCrawlingHelper.getCategories();

		return etsyCategories;
	}

	public List<EtsyCategory> getCategories() throws IOException {
		return getCategories(new CrawlingContext());
	}

	private void getChildCategories(List<EtsyCategory> parentCategories,
			Gson gson, CrawlingContext crawlingContext,
			EtsyCrawlingHelper etsyCrawlingHelper) throws IOException {

		if (parentCategories != null) {
			for (EtsyCategory parentCategory : parentCategories) {
				updateEtsyCategory(parentCategory, crawlingContext);

				ClientResource cr = new ClientResource(categoriesUrl + "/"
						+ parentCategory.getCategoryName() + "?api_key="
						+ apiKey);

				String jsonResponse = getJsonResponse(cr, etsyCrawlingHelper);
				EtsyCategoryResponse childrenCategoryResponse = gson.fromJson(
						jsonResponse, EtsyCategoryResponse.class);

				logger.debug("Gson etsy category response: "
						+ childrenCategoryResponse.toString());

				List<EtsyCategory> childrenCategories = childrenCategoryResponse
						.getResults();

				if (childrenCategories != null) {
					for (EtsyCategory childCategory : childrenCategories) {
						logger.debug("Adding category: " + childCategory);

						updateEtsyCategory(childCategory, crawlingContext);

						etsyCrawlingHelper.addCategory(childCategory,
								parentCategory, crawlingContext);

						if (childCategory.getNumChildren() > 0) {

							logger.debug("Getting children for category: "
									+ childCategory);
							getChildCategories(
									Arrays.asList(new EtsyCategory[] { childCategory }),
									gson, crawlingContext, etsyCrawlingHelper);
						}
					}
				}
			}
		}
	}

	private String getJsonResponse(ClientResource cr,
			EtsyCrawlingHelper etsyCrawlingHelper) throws IOException {

		logger.debug("\nJSON representation");
		StringWriter sw = new StringWriter();
		throttleCheck(etsyCrawlingHelper);
		etsyCrawlingHelper.incrementCounter();
		cr.get(MediaType.APPLICATION_JSON).write(sw);
		// cr.get(MediaType.APPLICATION_JSON).write(System.out);

		String jsonResponse = sw.toString();
		logger.debug(jsonResponse);
		return jsonResponse;
	}

	private void throttleCheck(EtsyCrawlingHelper crawlingContext) {
		if (maxRequestPerSecond > 0) {
			if (crawlingContext.getQueryCounter() % maxRequestPerSecond == 0) {
				logger.debug("Throttling requests - going to sleep");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					logger.warn("Sleep interrupted", e);
				}
			}
		}
	}

	public void crawl(CrawlingContext crawlingContext) throws CrawlingException {
		try {
			List<EtsyCategory> etsyCategories = getCategories(crawlingContext);
			logger.debug("Finished retrieving categories");
			generateOwl(etsyCategories, crawlingContext);
		} catch (Exception e) {
			logger.warn("Crawling failed", e);
			throw new CrawlingException(e);
		}
	}

	public void generateOwl(List<EtsyCategory> etsyCategories,
			CrawlingContext crawlingContext)
			throws OWLOntologyCreationException, OWLOntologyStorageException {

		OwlCategoryWriter owlCategoryWriter = new OwlCategoryWriter();

		OwlCategoryConfig marketplaceCategoryConfig = new OwlCategoryConfig();
		marketplaceCategoryConfig.setClassId("#EtsyCategory");
		marketplaceCategoryConfig.setInstanceId("#etsyCategory");
		marketplaceCategoryConfig.setMarketplaceName("etsy");
		marketplaceCategoryConfig.setOutputFileFolder(crawlingContext
				.getOwlOutputFolder());
		marketplaceCategoryConfig.setOutputFileName("etsyCategories");
		marketplaceCategoryConfig.setOutputFileDatePattern("yyyyMMdd");

		logger.debug("Generate OWL for categories");

		owlCategoryWriter
				.generateOwl(etsyCategories, marketplaceCategoryConfig);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.libereco.server.crawler.MarketplaceCrawler#getMarketplace()
	 */
	public Marketplace getMarketplace() {
		return MarketplaceImpl.ETSY;
	}

	private void updateEtsyCategories(List<EtsyCategory> etsyCategories,
			CrawlingContext crawlingContext) {
		if (etsyCategories != null) {
			for (EtsyCategory ec : etsyCategories) {
				updateEtsyCategory(ec, crawlingContext);
			}
		}
	}

	private void updateEtsyCategory(EtsyCategory etsyCategory,
			CrawlingContext crawlingContext) {

		String categoryName = etsyCategory.getCategoryName();
		String etsyCategoryId = categoryName;

		if (logger.isDebugEnabled()) {
			logger.debug("Category id [" + etsyCategoryId
					+ "], category name [" + categoryName + "]");
		}

		Long liberecoId = etsyCategory.getId();

		if (liberecoId == null) {
			liberecoId = crawlingContext.getNextLibrecoId();
			etsyCategory.setId(liberecoId);

			if (logger.isDebugEnabled()) {
				logger.debug("Setting libereco id [" + liberecoId
						+ "], category id [" + etsyCategoryId
						+ "], category name [" + categoryName + "]");
			}
		}

		etsyCategory.setMarketplaceCategoryId(etsyCategoryId);
		etsyCategory.setName(categoryName);

		crawlingContext
				.addCategoryIdLiberecoMapping(etsyCategoryId, liberecoId);
	}

	class EtsyCrawlingHelper {

		int queryCounter;

		LinkedHashMap<String, EtsyCategory> categories;

		public EtsyCrawlingHelper() {
			categories = new LinkedHashMap<String, EtsyCategory>();
		}

		public List<EtsyCategory> getCategories() {
			return new ArrayList<EtsyCategory>(categories.values());
		}

		public void addCategory(EtsyCategory category,
				EtsyCategory parentCategory, CrawlingContext crawlingContext) {

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
									+ "], parent category [" + parentCategory + "]");
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

		public void addTopLevelCategories(List<EtsyCategory> cats,
				CrawlingContext crawlingContext) {
			if (cats != null) {
				for (EtsyCategory ec : cats) {
					addCategory(ec, null, crawlingContext);
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
