package com.libereco.server.model;

import com.libereco.common.ListingCondition;
import java.util.List;
import com.libereco.common.LiberecoCategory;
import java.util.Set;
import com.libereco.common.LiberecoPaymentType;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.libereco.common.ListingState;

@Entity
@Table(name = "Libereco_Listing")
@IdClass(LiberecoListingPK.class)
@SuppressWarnings("serial")
public class LiberecoListing extends Marketplace {

  /** 
   *  no need to repeat the listingId attribute on child classes
   */
	@Column(name = "listing_id")
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

	@Transient
	private Set<Marketplace> listedMarketPlaces;

	private String description;

	private Date listingDuration;

	private byte[] picture;
	
	public LiberecoListing() {
		super();
	}

	///** 
//*  link to shipping template
//*/
//private Long shippingId;
//
//private LiberecoPaymentType paymentType;
//
// /**
//* 
//* @element-type LiberecoPaymentTemplate
//*/
//private List<LiberecoPaymentTemplate>  payments;
// /**
//* 
//* @element-type LiberecoPaymentMethod
//*/
//private List<LiberecoPaymentMethod>  paymentMethods;
// /**
//* 
//* @element-type LiberecoShippingTemplate
//*/
//private List<LiberecoShippingTemplate>  shipments;
// /**
//* 
//* @element-type LiberecoShippingMethod
//*/
//private List<LiberecoShippingMethod>  shippingMethods;
	
	

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
	 * @return the listedMarketPlaces
	 */
	public Set<Marketplace> getListedMarketPlaces() {
		return listedMarketPlaces;
	}

	/**
	 * @param listedMarketPlaces
	 *            the listedMarketPlaces to set
	 */
	public void setListedMarketPlaces(Set<Marketplace> listedMarketPlaces) {
		this.listedMarketPlaces = listedMarketPlaces;
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
				.append(listedMarketPlaces, rhs.listedMarketPlaces)
				.append(listingDuration, rhs.listingDuration)
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
			.append(listedMarketPlaces)
			.append(listingDuration)
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}