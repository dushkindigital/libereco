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
public class Children {

	private List<BrowseNode> children;

	@XmlElement(name = "BrowseNode")
	public List<BrowseNode> getChildren() {
		return children;
	}

	public void setChildren(List<BrowseNode> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Children [children=" + children + "]";
	}
}