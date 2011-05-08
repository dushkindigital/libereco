/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.etsy;

import java.util.List;

/**
 * @author Aleksandar
 * 
 */
public class EtsyCategoryResponse {
	
	private int count;

	private List<EtsyCategory> results;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<EtsyCategory> getResults() {
		return results;
	}

	public void setResults(List<EtsyCategory> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "EtsyCategoryResponse [count=" + count + ", results=" + results
				+ "]";
	}
}
