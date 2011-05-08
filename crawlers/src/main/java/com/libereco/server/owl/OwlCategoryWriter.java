/**
 * Copyright (C) 2011 Dushkin Digital Media, LLC
 * 900 Chapel Street, Ste. 210
 * New Haven, CT 06510-2802
 */
package com.libereco.server.owl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.libereco.server.crawler.MarketplaceCategory;

/**
 * @author Aleksandar
 * 
 */
public class OwlCategoryWriter {

	private Logger logger = LoggerFactory.getLogger(OwlCategoryWriter.class);

	private OwlCategoryWriterConfig categoryConfig;

	private static final String DEFAULT_OUTPUT_DATE_PATTERN = "yyyyMMdd";

	public OwlCategoryWriter() {
		this(new OwlCategoryWriterConfig());
	}

	public OwlCategoryWriter(OwlCategoryWriterConfig categoryConfig) {
		super();
		this.categoryConfig = categoryConfig;
	}

	public <T extends MarketplaceCategory> void generateOwl(
			List<T> marketplaceCategories,
			OwlCategoryConfig marketplaceCategoryConfig)
			throws OWLOntologyCreationException, OWLOntologyStorageException {

		long startTime = java.util.Calendar.getInstance().getTimeInMillis();

		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

		IRI ontologyIRI = IRI.create(categoryConfig.getOntologyIri());

		// Create the ontology.
		OWLOntology ont = manager.createOntology(ontologyIRI);

		// We can use the manager to get a reference to an OWLDataFactory.
		// The data factory provides a point for creating OWL API objects
		// such as classes, properties and individuals.
		OWLDataFactory factory = manager.getOWLDataFactory();

		OWLClass baseCategory = factory.getOWLClass(IRI.create(ontologyIRI
				+ categoryConfig.getBaseCategoryId()));

		OWLClass marketplaceClass = factory.getOWLClass(IRI.create(ontologyIRI
				+ marketplaceCategoryConfig.getClassId()));

		manager.addAxiom(ont,
				factory.getOWLSubClassOfAxiom(marketplaceClass, baseCategory));

		for (MarketplaceCategory ebc : marketplaceCategories) {
			if (logger.isDebugEnabled()) {
				logger.debug("Generating instance for category [" + ebc + "]");
			}

			// We first need to create some references to individuals. All
			// of our individual must have URIs. A common convention is to
			// take the URI of an ontology, append a # and then a local
			// name.
			OWLIndividual marketplaceInstance = factory
					.getOWLNamedIndividual(IRI.create(ontologyIRI
							+ marketplaceCategoryConfig.getInstanceId()
							+ ebc.getId()));

			// Now create a ClassAssertion to specify that :etsyCategoryN
			// is an instance of :EtsyCategory
			OWLClassAssertionAxiom classAssertion = factory
					.getOWLClassAssertionAxiom(marketplaceClass,
							marketplaceInstance);

			// Add the class assertion
			manager.addAxiom(ont, classAssertion);

			OWLDataProperty categoryId = factory.getOWLDataProperty(IRI
					.create(ontologyIRI + "#id"));
			OWLDataProperty parentId = factory.getOWLDataProperty(IRI
					.create(ontologyIRI + "#parentId"));
			OWLDataProperty marketplaceName = factory.getOWLDataProperty(IRI
					.create(ontologyIRI + "#marketplaceName"));
			OWLDataProperty marketplaceCategoryId = factory
					.getOWLDataProperty(IRI.create(ontologyIRI
							+ "#marketplaceCategoryId"));
			OWLDataProperty name = factory.getOWLDataProperty(IRI
					.create(ontologyIRI + "#name"));

			OWLDatatype integerDatatype = factory.getIntegerOWLDatatype();
			OWLDatatype stringDatatype = factory.getRDFPlainLiteral();

			// Now we add the domain and range axioms that specify the
			// domains and ranges of the various properties that we are
			// interested in.
			Set<OWLAxiom> domainsAndRanges = new HashSet<OWLAxiom>();

			domainsAndRanges.add(factory.getOWLDataPropertyRangeAxiom(
					categoryId, integerDatatype));
			domainsAndRanges.add(factory.getOWLDataPropertyRangeAxiom(parentId,
					integerDatatype));
			domainsAndRanges.add(factory.getOWLDataPropertyRangeAxiom(
					marketplaceName, stringDatatype));
			domainsAndRanges.add(factory.getOWLDataPropertyRangeAxiom(
					marketplaceCategoryId, stringDatatype));
			domainsAndRanges.add(factory.getOWLDataPropertyRangeAxiom(name,
					stringDatatype));

			OWLAxiom marketplaceAxiom = factory
					.getOWLDataPropertyAssertionAxiom(categoryId,
							marketplaceInstance, ebc.getId().intValue());
			manager.applyChange(new AddAxiom(ont, marketplaceAxiom));

			marketplaceAxiom = factory
					.getOWLDataPropertyAssertionAxiom(parentId,
							marketplaceInstance, ebc.getParentId().intValue());
			manager.applyChange(new AddAxiom(ont, marketplaceAxiom));

			marketplaceAxiom = factory.getOWLDataPropertyAssertionAxiom(
					marketplaceName, marketplaceInstance,
					marketplaceCategoryConfig.getMarketplaceName());
			manager.applyChange(new AddAxiom(ont, marketplaceAxiom));

			marketplaceAxiom = factory.getOWLDataPropertyAssertionAxiom(
					marketplaceCategoryId, marketplaceInstance,
					ebc.getMarketplaceCategoryId());
			manager.applyChange(new AddAxiom(ont, marketplaceAxiom));

			marketplaceAxiom = factory.getOWLDataPropertyAssertionAxiom(name,
					marketplaceInstance, ebc.getName());
			manager.applyChange(new AddAxiom(ont, marketplaceAxiom));

			// Now add all of our domain and range axioms
			manager.addAxioms(ont, domainsAndRanges);
		}

		// ////////////////////////////////////////////////////////////////////////////////////////////
		//
		// Ontology Management
		//
		// ////////////////////////////////////////////////////////////////////////////////////////////

		// Save ontology
		saveOntology(ont, manager, marketplaceCategoryConfig);
		
		long endTime = java.util.Calendar.getInstance().getTimeInMillis();

		logger.debug("OWL generation [" + (endTime - startTime) + "]");
	}

	private void saveOntology(OWLOntology ont, OWLOntologyManager manager,
			OwlCategoryConfig marketplaceCategoryConfig) throws OWLOntologyStorageException {
		
		String owlOutputFolder = marketplaceCategoryConfig
				.getOutputFileFolder();
		
		if (owlOutputFolder == null || owlOutputFolder.trim().length() == 0) {
			// Save to a file in the temporary file
			owlOutputFolder = System.getProperty("java.io.tmpdir");
		}

		logger.debug("Output file folder [" + owlOutputFolder + "]");
		
		String outputFileDatePattern = marketplaceCategoryConfig
				.getOutputFileDatePattern();
		
		logger.debug("Configured output file date pattern [" + outputFileDatePattern + "]");
		
		SimpleDateFormat sdf = new SimpleDateFormat(
				outputFileDatePattern != null ? outputFileDatePattern
						: DEFAULT_OUTPUT_DATE_PATTERN);

		String outputFileName = marketplaceCategoryConfig.getOutputFileName();
		
		if (outputFileName == null || outputFileName.trim().length() == 0) {
			outputFileName = marketplaceCategoryConfig.getMarketplaceName();
		}

		File file = new File(owlOutputFolder + File.separatorChar
				+ (outputFileName != null ? outputFileName : "marketplaceOwl")
				+ "." + sdf.format(new Date(System.currentTimeMillis()))
				+ ".owl");

		logger.debug("Saving OWL to [" + file.getAbsolutePath() + "]");

		manager.saveOntology(ont, IRI.create(file.toURI()));

		// Other formats for saving ontology
		// RDF/XML 
		// manager.saveOntology(ont, new StreamDocumentTarget(System.out));
		
		// OWL/XML
		// manager.saveOntology(ont, new OWLXMLOntologyFormat(), new StreamDocumentTarget(System.out));

		// Manchester Syntax
		// manager.saveOntology(ont, new ManchesterOWLSyntaxOntologyFormat(), new StreamDocumentTarget(System.out));
		
		// Turtle		
		// manager.saveOntology(ont, new TurtleOntologyFormat(), new StreamDocumentTarget(System.out));
	}
}
