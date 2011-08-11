/**
 * 
 */
package com.libereco.server.dao.impl.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.libereco.common.LiberecoCategory;
import com.libereco.common.ListingCondition;
import com.libereco.common.ListingState;
import com.libereco.server.dao.LiberecoListingDao;
import com.libereco.server.dao.LiberecoShippingTemplateDao;
import com.libereco.server.model.Marketplace;

/**
 * @author rrached
 *
 */
@Entity
@Table(name="LiberecoListingTbl")
public class LiberecoListingDaoImpl implements LiberecoListingDao {
	private String listingId = "";
	private String userId = "ID";	
	private String name	= "";
	private Double price	= 0.0;
	private Integer quantity	= 0;	
	private String description = "";
	private LiberecoCategory category = LiberecoCategory.CAT_BOOKS;	
	private ListingCondition condition = ListingCondition.FAIR;
	private ListingState listingState = ListingState.NEW;
	private Date listingDuration	= new Date();	
	private Set<Long> listedMarketplaces = new HashSet<Long>();
	private byte[] picture = new byte[1024];
	private LiberecoShippingTemplateDao shippingTemplate;
	
	private List<Long> marketplaceIds = new ArrayList<Long>();
	private List<String> marketplaceNames = new ArrayList<String>();


	/* (non-Javadoc)
	 * @see com.libereco.server.dao.MarketplaceDao#hasMarketplaceName(java.lang.String)
	 */
	@Override
	public boolean hasMarketplaceName(String marketplaceName) {
		return listedMarketplaces.contains(marketplaceName);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.MarketplaceDao#getMarketplace(java.lang.String)
	 */
	@Override
	public Marketplace getMarketplace(String marketplaceName) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.MarketplaceDao#getMarketplaceIds()
	 */
	@Override
	@ElementCollection(fetch = FetchType.LAZY)
	public List<Long> getMarketplaceIds() {
		marketplaceIds = new ArrayList<Long>();
		List<Marketplace> marketplaces = findAll();
		if (marketplaces != null) {
			for (Marketplace mp : marketplaces) {
				marketplaceIds.add(mp.getId());
			}
		}

		return marketplaceIds;
	}
	
	/**
	 * @param marketplaceIds
	 * 
	 * for JPA
	 */
	public void setMarketplaceIds(List<Long> marketplaceIds) {
		this.marketplaceIds = marketplaceIds;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.MarketplaceDao#getMarketplaceNames()
	 */
	@Override
	@ElementCollection(fetch = FetchType.LAZY)
	public List<String> getMarketplaceNames() {
		marketplaceNames.clear();
		List<Marketplace> marketplaces = findAll();
		if (marketplaces != null) {
			for (Marketplace mp : marketplaces) {
				marketplaceNames.add(mp.getMarketplaceName());
			}
		}

		return marketplaceNames;
	}
	
	/**
	 * @param marketplaceNames
	 * 
	 * for JPA
	 */
	public void setMarketplaceNames(List<String> marketplaceNames) {
		this.marketplaceNames = marketplaceNames;
	}
	
	

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getListingId()
	 */
	@Override
	@Id
	public String getListingId() {
		return listingId;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#setListingId(java.lang.String)
	 */
	@Override
	public void setListingId(String listingId) {
		this.listingId = listingId;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getUserId()
	 */
	@Override
	public String getUserId() {
		return userId;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#setUserId(java.lang.String)
	 */
	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getPrice()
	 */
	@Override
	public Double getPrice() {
		return price;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#setPrice(java.lang.Double)
	 */
	@Override
	public void setPrice(Double price) {
		this.price = price;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getQuantity()
	 */
	@Override
	public Integer getQuantity() {
		return quantity;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#setQuantity(java.lang.Integer)
	 */
	@Override
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getCategory()
	 */
	@Override
	public LiberecoCategory getCategory() {
		return category;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#setCategory(com.libereco.common.LiberecoCategory)
	 */
	@Override
	public void setCategory(LiberecoCategory category) {
		this.category = category;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getCondition()
	 */
	@Override
	public ListingCondition getCondition() {
		return condition;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#setCondition(com.libereco.common.ListingCondition)
	 */
	@Override
	public void setCondition(ListingCondition condition) {
		this.condition = condition;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getListingState()
	 */
	@Override
	public ListingState getListingState() {
		return listingState;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#setListingState(com.libereco.common.ListingState)
	 */
	@Override
	public void setListingState(ListingState listingState) {
		this.listingState = listingState;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getListingDuration()
	 */
	@Override
	public Date getListingDuration() {
		return listingDuration;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#setListingDuration(java.util.Date)
	 */
	@Override
	public void setListingDuration(Date listingDuration) {
		this.listingDuration = listingDuration;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getListedMarketplaces()
	 */
	@Override
	// @OneToMany(cascade = { CascadeType.PERSIST }) ??? is it really?
	@ElementCollection(fetch = FetchType.EAGER)
	public Set<Long> getListedMarketplaces() {
		return listedMarketplaces;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#setListedMarketplaces(java.util.Set)
	 */
	@Override
	public void setListedMarketplaces(Set<Long> listedMarketplaces) {
		this.listedMarketplaces = listedMarketplaces;
	}
	
	public void addMarketplace(Long listedMarketplace) {
		this.listedMarketplaces.add(listedMarketplace);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getPicture()
	 */
	@Override
	public byte[] getPicture() {
		return picture;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#setPicture(byte[])
	 */
	@Override
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getShippingTemplate()
	 */
	@Override
	@OneToOne(cascade = {CascadeType.PERSIST})
	public LiberecoShippingTemplateDaoImpl getShippingTemplate() {
		return (LiberecoShippingTemplateDaoImpl) shippingTemplate;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#setShippingTemplate(com.libereco.server.dao.LiberecoShippingTemplateDao)
	 */
	@Override
	public void setShippingTemplate(LiberecoShippingTemplateDao shippingTemplate) {
		this.shippingTemplate = shippingTemplate;
	}

	@Override
	public Marketplace saveOrUpdate(Marketplace obj) {
		return null;
	}

	@Override
	public void delete(Marketplace obj) {
	}

	@Override
	public Marketplace find(Marketplace obj) {
		return null;
	}

	@Override
	public Marketplace findById(Long id) {
		return null;
	}

	@Override
	public List<Marketplace> findAll() {
		return null;
	}

	@Override
	public Integer deleteAll() {
		return null;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
