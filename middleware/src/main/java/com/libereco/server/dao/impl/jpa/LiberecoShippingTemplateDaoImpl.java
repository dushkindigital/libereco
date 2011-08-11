/**
 * 
 */
package com.libereco.server.dao.impl.jpa;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.libereco.server.dao.LiberecoShippingMethodDao;
import com.libereco.server.dao.LiberecoShippingTemplateDao;

/**
 * @author rrached
 *
 */
@Entity
@Table(name="LiberecoShippingTemplateTable")
public class LiberecoShippingTemplateDaoImpl implements LiberecoShippingTemplateDao {
	private String shippingId;
	private LiberecoShippingMethodDao shippingMethod;
	private String postcode;
	private String country;
	
	public LiberecoShippingTemplateDaoImpl() {}

	public LiberecoShippingTemplateDaoImpl(String shippingId,
			LiberecoShippingMethodDao shippingMethod, String postcode,
			String country) {
		this.shippingId = shippingId;
		this.shippingMethod = shippingMethod;
		this.postcode = postcode;
		this.country = country;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoShippingTemplateDao#getShippingId()
	 */
	@Override
	@Id
	public String getShippingId() {
		return shippingId;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoShippingTemplateDao#setShippingId(java.lang.String)
	 */
	@Override
	public void setShippingId(String shippingId) {
		this.shippingId = shippingId;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoShippingTemplateDao#getShippingMethod()
	 */
	@Override
	@OneToOne(cascade = { CascadeType.PERSIST }, orphanRemoval = true)
	public LiberecoShippingMethodDaoImpl getShippingMethod() {
		return (LiberecoShippingMethodDaoImpl) shippingMethod;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoShippingTemplateDao#setShippingMethod(com.libereco.server.dao.LiberecoShippingMethodDao)
	 */
	@Override
	public void setShippingMethod(LiberecoShippingMethodDao shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoShippingTemplateDao#getPostcode()
	 */
	@Override
	public String getPostcode() {
		return postcode;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoShippingTemplateDao#setPostcode(java.lang.String)
	 */
	@Override
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoShippingTemplateDao#getCountry()
	 */
	@Override
	public String getCountry() {
		return country;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoShippingTemplateDao#setCountry(java.lang.String)
	 */
	@Override
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LiberecoShippingTemplateDaoImpl == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		LiberecoShippingTemplateDaoImpl rhs = (LiberecoShippingTemplateDaoImpl) obj;
		return new EqualsBuilder()
				.append(shippingId, rhs.shippingId)
				.append(shippingMethod, rhs.shippingMethod)
				.append(postcode, rhs.postcode)
				.append(country, rhs.country)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 41)
			.append(shippingId)
			.append(shippingMethod)
			.append(postcode)
			.append(country)
			.toHashCode();
	}

}
