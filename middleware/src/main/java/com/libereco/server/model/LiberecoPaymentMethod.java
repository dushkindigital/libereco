package com.libereco.server.model;

import com.libereco.common.LiberecoPaymentType;

/** 
 *  meta-data class encapsulating the LiberecoPaymentType and other details,
 *  and maintains a relationship with the LiberecoListing used by each payment template class
 */
public abstract class LiberecoPaymentMethod {

	private LiberecoPaymentType paymentMethodId;

	private String marketplaceId;

	private String name;

	private LiberecoListing paymentLiberecoListing;

}