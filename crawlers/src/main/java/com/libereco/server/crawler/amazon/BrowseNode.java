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
public class BrowseNode {

	private Long browseNodeId;

	private String name;

	private Integer isCategoryRoot;

	private Children children;

	private Ancestors ancestors;
	
	@XmlElement(name = "BrowseNodeId")
	public Long getBrowseNodeId() {
		return browseNodeId;
	}

	public void setBrowseNodeId(Long browseNodeId) {
		this.browseNodeId = browseNodeId;
	}

	@XmlElement(name = "Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "IsCategoryRoot")
	public Integer getIsCategoryRoot() {
		return isCategoryRoot;
	}

	public void setIsCategoryRoot(Integer isCategoryRoot) {
		this.isCategoryRoot = isCategoryRoot;
	}

	@XmlElement(name = "Children")
	public Children getChildren() {
		return children;
	}

	public void setChildren(Children children) {
		this.children = children;
	}

	@XmlElement(name = "Ancestors")
	public Ancestors getAncestors() {
		return ancestors;
	}

	public void setAncestors(Ancestors ancestors) {
		this.ancestors = ancestors;
	}

	@Override
	public String toString() {
		return "BrowseNode [browseNodeId=" + browseNodeId + ", name=" + name
				+ ", isCategoryRoot=" + isCategoryRoot + ", children="
				+ children + ", ancestors="
				+ ancestors + "]";
	}

}