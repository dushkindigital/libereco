/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.owl;


/**
 * @author Aleksandar
 *
 */
public class OwlCategoryWriterConfig {

	private static final String DEFAULT_ONTOLOGY_IRI = "http://libereco.dushkindigital.com/ontologies/categories";
	
	private String ontologyIri = DEFAULT_ONTOLOGY_IRI;
	
	private String baseCategoryId = "#Category";

	public String getOntologyIri() {
		return ontologyIri;
	}

	public void setOntologyIri(String ontologyIri) {
		this.ontologyIri = ontologyIri;
	}

	public String getBaseCategoryId() {
		return baseCategoryId;
	}

	public void setBaseCategoryId(String baseCategoryId) {
		this.baseCategoryId = baseCategoryId;
	}
}
