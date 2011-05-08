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
public class OwlCategoryConfig {

	private String classId;
	
	private String instanceId;
	
	private String marketplaceName;
	
	private String outputFileFolder;
	
	private String outputFileName;
	
	private String outputFileDatePattern;

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getMarketplaceName() {
		return marketplaceName;
	}

	public void setMarketplaceName(String marketplaceName) {
		this.marketplaceName = marketplaceName;
	}

	public String getOutputFileFolder() {
		return outputFileFolder;
	}

	public void setOutputFileFolder(String outputFileFolder) {
		this.outputFileFolder = outputFileFolder;
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public String getOutputFileDatePattern() {
		return outputFileDatePattern;
	}

	public void setOutputFileDatePattern(String outputFileDatePattern) {
		this.outputFileDatePattern = outputFileDatePattern;
	}
}
