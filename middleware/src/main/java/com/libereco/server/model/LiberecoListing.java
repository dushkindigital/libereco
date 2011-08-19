package com.libereco.server.model;

import com.libereco.common.ListingCondition;
import java.util.List;
import com.libereco.common.LiberecoCategory;
import java.util.Set;
import com.libereco.common.LiberecoPaymentType;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.libereco.common.ListingState;

@Entity
@Table(name = "LiberecoListing")
@SuppressWarnings("serial")
public class LiberecoListing extends Marketplace {

  /** 
   *  no need to repeat the listingId attribute on child classes
   */
  public Long listingId;

  public String userId;

  public String name;

  public Double price;

  public Integer quantity;

  public LiberecoCategory category;

  public ListingCondition condition;

  public ListingState listingState;

  public Set<Marketplace> listedMarketPlaces;

  public String description;

  public Date listingDuration;

  public byte[] picture;

  /** 
   *  link to shipping template
   */
  public Long shippingId;

  public LiberecoPaymentType paymentType;

    /**
   * 
   * @element-type LiberecoPaymentTemplate
   */
  public List<LiberecoPaymentTemplate>  payments;
    /**
   * 
   * @element-type LiberecoPaymentMethod
   */
  public List<LiberecoPaymentMethod>  paymentMethods;
    /**
   * 
   * @element-type LiberecoShippingTemplate
   */
  public List<LiberecoShippingTemplate>  shipments;
    /**
   * 
   * @element-type LiberecoShippingMethod
   */
  public List<LiberecoShippingMethod>  shippingMethods;
	/**
	 * @return the listingId
	 */
	public Long getListingId() {
		return listingId;
	}
	/**
	 * @param listingId the listingId to set
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
	 * @param userId the userId to set
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
	 * @param name the name to set
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
	 * @param price the price to set
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
	 * @param quantity the quantity to set
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
	 * @param category the category to set
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
	 * @param condition the condition to set
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
	 * @param listingState the listingState to set
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
	 * @param listedMarketPlaces the listedMarketPlaces to set
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
	 * @param description the description to set
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
	 * @param listingDuration the listingDuration to set
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
	 * @param picture the picture to set
	 */
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	/**
	 * @return the shippingId
	 */
	public Long getShippingId() {
		return shippingId;
	}
	/**
	 * @param shippingId the shippingId to set
	 */
	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}
	/**
	 * @return the paymentType
	 */
	public LiberecoPaymentType getPaymentType() {
		return paymentType;
	}
	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(LiberecoPaymentType paymentType) {
		this.paymentType = paymentType;
	}
	/**
	 * @return the payments
	 */
	public List<LiberecoPaymentTemplate> getPayments() {
		return payments;
	}
	/**
	 * @param payments the payments to set
	 */
	public void setPayments(List<LiberecoPaymentTemplate> payments) {
		this.payments = payments;
	}
	/**
	 * @return the paymentMethods
	 */
	public List<LiberecoPaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}
	/**
	 * @param paymentMethods the paymentMethods to set
	 */
	public void setPaymentMethods(List<LiberecoPaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}
	/**
	 * @return the shipments
	 */
	public List<LiberecoShippingTemplate> getShipments() {
		return shipments;
	}
	/**
	 * @param shipments the shipments to set
	 */
	public void setShipments(List<LiberecoShippingTemplate> shipments) {
		this.shipments = shipments;
	}
	/**
	 * @return the shippingMethods
	 */
	public List<LiberecoShippingMethod> getShippingMethods() {
		return shippingMethods;
	}
	/**
	 * @param shippingMethods the shippingMethods to set
	 */
	public void setShippingMethods(List<LiberecoShippingMethod> shippingMethods) {
		this.shippingMethods = shippingMethods;
	}
  
  
  
  

}