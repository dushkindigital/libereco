package com.libereco.server.model;

import com.libereco.common.ShippingLevelType;

/** 
 *  meta-data class encapsulating the ShippingLevelType and other details, and maintains a relationship with the LiberecoListing used by each payment template class
 */
public abstract class LiberecoShippingMethod {

  public ShippingLevelType shippingMethodId;

  public String marketplaceId;

  /** 
   *  name the marketplace uses to refer to this kind of shipping
   */
  public String name;

    public LiberecoListing shippingLibererecoListing;
  
}