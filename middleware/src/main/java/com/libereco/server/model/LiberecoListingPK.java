/**
 * 
 */
package com.libereco.server.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author rrached
 *
 * Composite Key class for {@link LiberecoListing}
 */
@SuppressWarnings("serial")
public class LiberecoListingPK implements Serializable {
	private Long id;
	public Long listingId;
	
	public LiberecoListingPK() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the listingId
	 */
	public Long getListingId() {
		return listingId;
	}

	/**
	 * @param listingId the listingId to set
	 */
	public void setListingId(Long listingId) {
		this.listingId = listingId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LiberecoListingPK == false) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		LiberecoListingPK rhs = (LiberecoListingPK) obj;
		return new EqualsBuilder()
				.append(id, rhs.id)
				.append(listingId, rhs.listingId)
				.isEquals();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(id)
			.append(listingId)
			.toHashCode();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}	

}
