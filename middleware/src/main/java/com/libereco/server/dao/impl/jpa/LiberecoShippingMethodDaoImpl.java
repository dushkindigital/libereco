/**
 * 
 */
package com.libereco.server.dao.impl.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.libereco.common.ShippingLevel;
import com.libereco.server.dao.LiberecoShippingMethodDao;

/**
 * @author rrached
 *
 */
@Entity
@Table(name="LiberecoShippingMethodTable")
public class LiberecoShippingMethodDaoImpl implements LiberecoShippingMethodDao {
	private String shippingMethodId;
	private ShippingLevel shippingLevel;
	private String name;

	/**
	 * 
	 */
	public LiberecoShippingMethodDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public LiberecoShippingMethodDaoImpl(String shippingMethodId,
			ShippingLevel shippingLevel, String name) {
		this.shippingMethodId = shippingMethodId;
		this.shippingLevel = shippingLevel;
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoShippingMethodDao#getShippingMethodId()
	 */
	@Override
	@Id
	public String getShippingMethodId() {
		return shippingMethodId;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoShippingMethodDao#setShippingMethodId(java.lang.String)
	 */
	@Override
	public void setShippingMethodId(String id) {
		this.shippingMethodId = id;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoShippingMethodDao#getShippingLevel()
	 */
	@Override
	public ShippingLevel getShippingLevel() {
		return shippingLevel;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoShippingMethodDao#setShippingLevel(com.libereco.common.ShippingLevel)
	 */
	@Override
	public void setShippingLevel(ShippingLevel level) {
		this.shippingLevel = level;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoShippingMethodDao#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoShippingMethodDao#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LiberecoShippingMethodDaoImpl == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		LiberecoShippingMethodDaoImpl rhs = (LiberecoShippingMethodDaoImpl) obj;
		return new EqualsBuilder()
				.append(shippingMethodId, rhs.shippingMethodId)
				.append(shippingLevel, rhs.shippingLevel)
				.append(name, rhs.name)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(shippingMethodId)
			.append(shippingLevel)
			.append(name)
			.toHashCode();
	}

}
