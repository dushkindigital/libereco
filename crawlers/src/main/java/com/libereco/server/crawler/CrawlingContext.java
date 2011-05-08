/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aleksandar
 * 
 */
public class CrawlingContext {

	private CrawlerOutputConfig outputConfig;

	/**
	 * Keeps track of assigned libereco ids for each category until we decide
	 * where we want to keep categories and how to assign ids
	 */
	private Long librecoId = 0L;

	/**
	 * Maps marketplace category id to libereco id
	 */
	private Map<String, Long> librecoIdMap = new HashMap<String, Long>();

	/**
	 * Maps marketplace category id to marketplace category name
	 */
	private Map<String, String> categoryIdNameMap = new HashMap<String, String>();

	public CrawlingContext() {
	}

	public CrawlingContext(CrawlerOutputConfig outputConfig) {
		this.outputConfig = outputConfig;
	}

	public CrawlerOutputConfig getOutputConfig() {
		return outputConfig;
	}

	public void setOutputConfig(CrawlerOutputConfig outputConfig) {
		this.outputConfig = outputConfig;
	}

	public Long getLibrecoId() {
		return librecoId;
	}

	public void setLibrecoId(Long librecoId) {
		this.librecoId = librecoId;
	}

	public Map<String, Long> getLibrecoIdMap() {
		return librecoIdMap;
	}

	public void setLibrecoIdMap(Map<String, Long> librecoIdMap) {
		this.librecoIdMap = librecoIdMap;
	}

	public Map<String, String> getCategoryIdNameMap() {
		return categoryIdNameMap;
	}

	public void setCategoryIdNameMap(Map<String, String> categoryIdNameMap) {
		this.categoryIdNameMap = categoryIdNameMap;
	}

	public void addCategoryIdNameMapping(String categoryId, String categoryName) {
		categoryIdNameMap.put(categoryId, categoryName);
	}

	public String getCategoryName(String categoryId) {
		return categoryIdNameMap.get(categoryId);
	}

	public void addCategoryIdLiberecoMapping(String categoryId, Long liberecoId) {
		librecoIdMap.put(categoryId, liberecoId);
	}

	public Long getLiberecoId(String categoryId) {
		return librecoIdMap.get(categoryId);
	}

	public synchronized Long getNextLibrecoId() {
		return ++librecoId;
	}

	public String getOwlOutputFolder() {
		String owlOutputFolder = null;
		
		if (outputConfig != null) {
			owlOutputFolder = outputConfig.getOwlOutputFolder();
		}
		
		return owlOutputFolder;
	}
}
