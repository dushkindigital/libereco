/**
 * 
 */
package com.libereco.server.dao;

import com.libereco.common.ListingState;
import com.libereco.common.ReturnPolicy;

/**
 * @author rrached
 *
 */
public interface EbayListingDao extends MarketplaceDao {
	public String getListingId();
	public void setListingId(String listingId);
	
	public ListingState getListingState();
	public void setListingState(ListingState listingState);
	
	public ReturnPolicy getReturnPolicy();
	public void setReturnPolicy(ReturnPolicy returnPolicy);
	
	public Integer getDispatchTimeMax();
	public void setDispatchTimeMax(Integer dispatchTimeMax);
	
	public EbayShipppingTemplateDao getShippingTemplate();
	public void getShippingTemplate(EbayShipppingTemplateDao shippingTemplate);

}

class EbayListingDaoC {
	public String listingId = "";
	public ListingState listingState = ListingState.NEW;	
	public ReturnPolicy returnPolicy = ReturnPolicy.THIRTY_DAY_RETURN;
	public Integer dispatchTimeMax = 0;
}
