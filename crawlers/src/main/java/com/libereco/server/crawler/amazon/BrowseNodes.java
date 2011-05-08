/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.amazon;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Aleksandar
 * 
 */
public class BrowseNodes {

	private BrowseNode browseNode;

	@XmlElement(name = "BrowseNode")
	public BrowseNode getBrowseNode() {
		return browseNode;
	}

	public void setBrowseNode(BrowseNode browseNode) {
		this.browseNode = browseNode;
	}

	@Override
	public String toString() {
		return "BrowseNodes [browseNode=" + browseNode + "]";
	}
}