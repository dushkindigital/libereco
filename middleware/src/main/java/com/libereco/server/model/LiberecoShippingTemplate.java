/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author rrached
 * 
 */
@Entity
@Table(name = "Libereco_Shipping_Template")
@SuppressWarnings("serial")
public class LiberecoShippingTemplate implements Serializable, Template {

	/**
	 * no need to repeat the paymentId attributes on children classes
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shippingId;
	
//	@OneToMany(cascade = { CascadeType.PERSIST })
//	private List<Address> addresses = new ArrayList<Address>();
	@OneToOne(cascade = { CascadeType.PERSIST })
	private Address address = new Address();

	/**
	 * @return the shippingId
	 */
	public Long getShippingId() {
		return shippingId;
	}

	/**
	 * @param shippingId
	 *            the shippingId to set
	 */
	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}
	
//	public List<Address> getAddresses() {
//		return addresses;
//	}
//	
//	public void setAddresses(List<Address> addresses) {
//		this.addresses = addresses;
//	}
//	
//	public void addAddress(Address address) {
//		getAddresses() .add(address);
//	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LiberecoShippingTemplate == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		LiberecoShippingTemplate rhs = (LiberecoShippingTemplate) obj;
		return new EqualsBuilder()
				.append(address, rhs.address)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(address)
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	

}