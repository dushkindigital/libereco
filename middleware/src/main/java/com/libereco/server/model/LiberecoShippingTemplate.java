package com.libereco.server.model;

import com.libereco.common.ShippingLevelType;
import java.util.Vector;

public class LiberecoShippingTemplate {

  private String shippingId;

  private ShippingLevelType shippingMethod;

  private String postcode;

  private String country;

    private LiberecoListing shipping;
    private Vector  shippingByMarketPlace;

}