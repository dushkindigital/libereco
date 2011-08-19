package com.libereco.server.model;

import java.util.Currency;

public class LiberecoPaymentTemplate {

  /** 
   *  no need to repeat the paymentId attributes on children classes
   */
  public String paymentId;

  public String paymentMethodId;

  public Currency currency;

    public LiberecoListing listing;
    public LiberecoPaymentMethod paymentByMarketplace;

}