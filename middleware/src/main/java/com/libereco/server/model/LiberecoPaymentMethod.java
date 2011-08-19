package com.libereco.server.model;

import com.libereco.common.LiberecoPaymentType;

/** 
 *  meta-data class encapsulating the LiberecoPaymentType and other details,
 *  and maintains a relationship with the LiberecoListing used by each payment template class
 */
public abstract class LiberecoPaymentMethod {

	public LiberecoPaymentType paymentMethodId;

	public String marketplaceId;

	public String name;

	public LiberecoListing paymentLiberecoListing;

}