/**
 * 
 */
package com.libereco.server.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.libereco.common.LiberecoCategory;
import com.libereco.common.ListingCondition;
import com.libereco.common.ListingState;

/**
 * @author rrached
 *
 */
public interface LiberecoListingDao extends MarketplaceDao {
	public String getListingId();
	public void setListingId(String listingId);
	
	public String getUserId();
	public void setUserId(String userId);
	
	public String getName();
	public void setName(String name);
	
	public Double getPrice();
	public void setPrice(Double price);
	
	public Integer getQuantity();
	public void setQuantity(Integer quantity);
	
	public String getDescription();
	public void setDescription(String description);
	
	public LiberecoCategory getCategory();
	public void setCategory(LiberecoCategory category);
	
	public ListingCondition getCondition();
	public void setCondition(ListingCondition condition);
	
	public ListingState getListingState();
	public void setListingState(ListingState listingState);
	
	public Date getListingDuration();
	public void setListingDuration(Date listingDuration);
	
	public Set<Long> getListedMarketplaces();
	public void setListedMarketplaces(Set<Long> listedMarketplaces);
	
	public byte[] getPicture();
	public void setPicture(byte[] picture);
	
	public LiberecoShippingTemplateDao getShippingTemplate();
	public void setShippingTemplate(LiberecoShippingTemplateDao shippingTemplate);
}

class LiberecoListingDaoC  {
	public String listingId = "";
	public String userId = "ID";	
	public String name	= "";
	public Double price	= 0.0;
	public Integer quantity	= 0;	
	public String description = "";
	public LiberecoCategory category = LiberecoCategory.CAT_BOOKS;	
	public ListingCondition condition = ListingCondition.FAIR;
	public ListingState listingState = ListingState.NEW;
	public Date listingDuration	= new Date();	
	public Set<Long> listedMarketplaces = new HashSet<Long>();
	public byte[] picture = new byte[1024];
}

