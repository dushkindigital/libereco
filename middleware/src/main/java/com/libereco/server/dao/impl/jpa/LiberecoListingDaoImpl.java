/**
 * 
 */
package com.libereco.server.dao.impl.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.libereco.server.dao.LiberecoListingDao;
import com.libereco.server.model.LiberecoListing;

/**
 * @author rrached
 *
 */
public class LiberecoListingDaoImpl extends AbstractJpaDaoSupport<Long, LiberecoListing> implements
		LiberecoListingDao {

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#saveOrUpdate(java.lang.Object)
	 */
	@Override
	public LiberecoListing saveOrUpdate(LiberecoListing obj) {
		super.persist(obj);
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(LiberecoListing obj) {
		super.remove(obj);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#find(java.lang.Object)
	 */
	@Override
	public LiberecoListing find(LiberecoListing obj) {
		return entityManager.find(LiberecoListing.class, obj.getListingId());
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#findById(java.lang.Object)
	 */
	@Override
	public LiberecoListing findById(Long id) {
		return super.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#deleteAll()
	 */
	@Override
	public Integer deleteAll() {
		return super.removeAll();
	}
	
	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#findById(java.lang.Long)
	 */
	@Override
	public LiberecoListing findByListingId(Long listingId) {
		Query q = entityManager.createQuery("from LiberecoListing where listingId = :listingId");
		q.setParameter("listingId", listingId);
		List<?> ls = null;
		return (LiberecoListing) ((ls = q.getResultList()) != null && !ls.isEmpty() ? ls.get(0) : null);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#hasLiberecoListingName(java.lang.String)
	 */
	@Override
	public boolean hasLiberecoListingName(String name) {
		boolean found = false;	
		Query q = entityManager.createQuery("from LiberecoListing where listingAttribute.name = :_name");
		q.setParameter("_name", name);
		List<?> ls = null;
		found = (ls = q.getResultList()) != null && !ls.isEmpty() ? true : false;
		
		return found;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getLiberecoListing(java.lang.String)
	 */
	@Override
	public LiberecoListing getLiberecoListing(String name) {
		Query q = entityManager.createQuery("from LiberecoListing where listingAttribute.name = :_name");
		q.setParameter("_name", name);
		List<?> ls = null;
		return (LiberecoListing) ((ls = q.getResultList()) != null && !ls.isEmpty() ? ls.get(0) : null);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getLiberecoListingIds()
	 */
	@Override
	public List<Long> getLiberecoListingIds() {
		List<Long> liberecoListingIds = new ArrayList<Long>();
		@SuppressWarnings("unchecked")
		List<Long> ids = entityManager.createQuery("select id from LiberecoListing").getResultList();
		if (ids != null && !ids.isEmpty()) {
			liberecoListingIds.addAll(ids);		
		}
		
		return liberecoListingIds;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoListingDao#getLiberecoListingNames()
	 */
	@Override
	public List<String> getLiberecoListingNames() {
		List<String> liberecoListingNames = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<String> names = entityManager.createQuery("select name from GenericListing").getResultList();
		if (names != null && !names.isEmpty()) {
			liberecoListingNames.addAll(names);		
		}
		return liberecoListingNames;
	}

}
