/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.amazon;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Aleksandar
 * 
 */
@XmlRootElement(name = "BrowseNodeLookupResponse")
public class BrowseNodeLookupResponse {

	private BrowseNodes browseNodes;

	@XmlElement(name = "BrowseNodes")
	public BrowseNodes getBrowseNodes() {
		return browseNodes;
	}

	public void setBrowseNodes(BrowseNodes browseNodes) {
		this.browseNodes = browseNodes;
	}

	@Override
	public String toString() {
		return "BrowseNodeLookupResponse [browseNodes=" + browseNodes + "]";
	}
}