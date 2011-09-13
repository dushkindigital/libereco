/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @author Aleksandar
 *
 */
@Entity
@Table(name = "pendingMarketplaceAuthorizations")
@SuppressWarnings("serial")
public class PendingMarketplaceAuthorizations extends AbstractUserMarketplace {

	// TODO: Replace individual token-related members with AuthRequestToken data member
	private String requestToken;
	private String requestTokenSecret;
	
	public PendingMarketplaceAuthorizations() {
		super();
	}

	public PendingMarketplaceAuthorizations(User user, Marketplace marketplace, String requestToken) {
		this(user, marketplace, requestToken, null);
	}

	public PendingMarketplaceAuthorizations(User user, Marketplace marketplace, String requestToken, String requestTokenSecret) {
		super(user, marketplace);
		this.requestToken = requestToken;
		this.requestTokenSecret = requestTokenSecret;
	}

	@Column(name = "requestToken", length = 128)
	public String getRequestToken() {
		return requestToken;
	}

	public void setRequestToken(String requestToken) {
		this.requestToken = requestToken;
	}

	@Column(name = "requestTokenSecret", length = 128)
	public String getRequestTokenSecret() {
		return requestTokenSecret;
	}

	public void setRequestTokenSecret(String requestTokenSecret) {
		this.requestTokenSecret = requestTokenSecret;
	}
	
	
}
