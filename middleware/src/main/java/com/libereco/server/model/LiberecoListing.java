/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author rrached
 *
 */
@Entity
@Table(name = "Libereco_Listing")
//@IdClass(LiberecoListingPK.class)
@SuppressWarnings("serial")
public class LiberecoListing implements Listing {

  /** 
   *  no need to repeat the listingId attribute on child classes
   */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long listingId;
	
	@OneToOne(cascade = { CascadeType.PERSIST})
	private GenericListing listingAttribute;
	
	/**
	 * amazingly enough the Hibernate JPA provider does NOT properly support the EAGER
	 * fetching strategy! Must rely on the default fetching strategy 
	 */
	@OneToMany(cascade = { CascadeType.PERSIST })
	private List<Marketplace> marketplaces = new ArrayList<Marketplace>();
	
	public LiberecoListing() {
		super();
	}


	/**
	 * @return the listingId
	 */
	public Long getListingId() {
		return listingId;
	}

	/**
	 * @param listingId
	 *            the listingId to set
	 */
	public void setListingId(Long listingId) {
		this.listingId = listingId;
	}
	
	/**
	 * @return the listingAttribute
	 */
	public GenericListing getListingAttribute() {
		return listingAttribute;
	}

	/**
	 * @param listingAttribute the listingAttribute to set
	 */
	public void setListingAttribute(GenericListing listingAttribute) {
		this.listingAttribute = listingAttribute;
	}

	@Override
	public List<Marketplace> getMarketplaces() {
		return marketplaces;
	}
	
	@Override
	public void addMarketplace(Marketplace marketplace) {
		getMarketplaces().add(marketplace);
	}	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LiberecoListing == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		LiberecoListing rhs = (LiberecoListing) obj;
		return new EqualsBuilder()
				.append(listingId, rhs.listingId)
				.append(listingAttribute, rhs.listingAttribute)
				.append(marketplaces, rhs.marketplaces)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(listingId)
			.append(listingAttribute)
			.append(marketplaces)
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}