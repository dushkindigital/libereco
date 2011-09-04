/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.libereco.common.LiberecoCategory;
import com.libereco.common.ListingCondition;
import com.libereco.common.ListingState;

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

	private String userId;

	private String name;

	private Double price;

	private Integer quantity;

	@Enumerated(EnumType.STRING)
	private LiberecoCategory category;

	@Enumerated(EnumType.STRING)
	private ListingCondition condition;

	@Enumerated(EnumType.STRING)
	private ListingState listingState;

	private String description;

	private Date listingDuration;

	private byte[] picture;
	
	/**
	 * amazingly enough the Hibernate JPA provider does NOT properly support the EAGER
	 * fetching strategy! Must rely on the default fetching strategy 
	 */
	@OneToMany(cascade = { CascadeType.PERSIST })
	private List<Marketplace> marketplaces = new ArrayList<Marketplace>();
	
	/**
	 * 
	 * @element-type LiberecoPaymentTemplate
	 */
	@OneToOne(cascade = CascadeType.PERSIST)
	private LiberecoPaymentTemplate listingPayment;

	/**
	 * 
	 * @element-type LiberecoShippingTemplate
	 */
	@OneToOne(cascade = CascadeType.PERSIST)
	private LiberecoShippingTemplate listingShipping;
	
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
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the category
	 */
	public LiberecoCategory getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(LiberecoCategory category) {
		this.category = category;
	}

	/**
	 * @return the condition
	 */
	public ListingCondition getCondition() {
		return condition;
	}

	/**
	 * @param condition
	 *            the condition to set
	 */
	public void setCondition(ListingCondition condition) {
		this.condition = condition;
	}

	/**
	 * @return the listingState
	 */
	public ListingState getListingState() {
		return listingState;
	}

	/**
	 * @param listingState
	 *            the listingState to set
	 */
	public void setListingState(ListingState listingState) {
		this.listingState = listingState;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the listingDuration
	 */
	public Date getListingDuration() {
		return listingDuration;
	}

	/**
	 * @param listingDuration
	 *            the listingDuration to set
	 */
	public void setListingDuration(Date listingDuration) {
		this.listingDuration = listingDuration;
	}

	/**
	 * @return the picture
	 */
	public byte[] getPicture() {
		return picture;
	}

	/**
	 * @param picture
	 *            the picture to set
	 */
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
	public LiberecoPaymentTemplate getListingPayment() {
		return listingPayment;
	}
	
	public void setListingPayment(LiberecoPaymentTemplate listingPayment) {
		this.listingPayment = listingPayment;
	}
	
	public LiberecoShippingTemplate getListingShipping() {
		return listingShipping;
	}
	
	public void setListingShipping(LiberecoShippingTemplate listingShipping) {
		this.listingShipping = listingShipping;
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
				.append(userId, rhs.userId)
				.append(name, rhs.name)
				.append(price, rhs.price)
				.append(quantity, rhs.quantity)
				.append(category, rhs.category)
				.append(condition, rhs.condition)
				.append(listingState, rhs.listingState)
				.append(listingDuration, rhs.listingDuration)
				.append(listingPayment, rhs.listingPayment)
				.append(listingShipping, rhs.listingShipping)
				.append(marketplaces, rhs.marketplaces)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(listingId)
			.append(userId)
			.append(name)
			.append(price)
			.append(quantity)
			.append(category)
			.append(condition)
			.append(listingState)
			.append(listingDuration)
			.append(listingPayment)
			.append(listingShipping)
			.append(marketplaces)
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}