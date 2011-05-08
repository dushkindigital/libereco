/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.crawler.amazon;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Aleksandar
 *
 */
public class Ancestors {
	private List<BrowseNode> ancestors;

	@XmlElement(name = "BrowseNode")
	public List<BrowseNode> getAncestors() {
		return ancestors;
	}

	public void setAncestors(List<BrowseNode> ancestors) {
		this.ancestors = ancestors;
	}

	@Override
	public String toString() {
		return "Ancestors [ancestors=" + ancestors + "]";
	}
}
