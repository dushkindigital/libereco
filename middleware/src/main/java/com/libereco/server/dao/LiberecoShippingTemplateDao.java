/**
 * 
 */
package com.libereco.server.dao;


/**
 * @author rrached
 *
 */
public interface LiberecoShippingTemplateDao {
	public String getShippingId();
	public void setShippingId(String shippingId);
	
	public LiberecoShippingMethodDao getShippingMethod();
	public void setShippingMethod(LiberecoShippingMethodDao shippingMethod);
	
	public String getPostcode();
	public void setPostcode(String postcode);
	
	public String getCountry();
	public void setCountry(String country);
}
