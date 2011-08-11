/**
 * 
 */
package com.libereco.server.dao;

/**
 * @author rrached
 *
 */
public interface EbayShipppingTemplateDao {
	public String getShippingId();
	public void setShippingId(String shippingId);
	
	public String getShippingType();
	public void setShippingType(String shippingType);
	
	public String getShippingService();
	public void setShippingService(String shippingService);
	
	public Integer getShippingPriority();
	public void setShippingPriority(Integer shippingPriority);

	public Double getShippingCost();
	public void setShippingCost(Double shippingCost);
	
	public EbayShippingMethodDao getShippingMethod();
	public void setShippingMethod(EbayShippingMethodDao shippingMethod);
}
