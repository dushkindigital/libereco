package com.libereco.server.model;

import com.libereco.common.ShippingLevelType;
import java.util.Vector;

public class LiberecoShippingTemplate {

  public String shippingId;

  public ShippingLevelType shippingMethod;

  public String postcode;

  public String country;

    public LiberecoListing shipping;
    public Vector  shippingByMarketPlace;

}