/**
  *  Copyright (C) 2011 Dushkin Digital Media, LLC
  *  500 E 77th Street, Ste. 806
  *  New York, NY 10162
  *
  *  All rights reserved.
  **/
package com.libereco.server.dao.impl.jpa;

import java.util.List;

import javax.persistence.Query;

import com.libereco.common.ShippingLevelType;
import com.libereco.server.dao.LiberecoShippingMethodDao;
import com.libereco.server.model.LiberecoShippingMethod;
import com.libereco.server.model.Marketplace;

/**
 * @author rrached
 *
 */
public class LiberecoShippingMethodDaoImpl extends AbstractJpaDaoSupport<Long, LiberecoShippingMethod>
		implements LiberecoShippingMethodDao {

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#saveOrUpdate(java.lang.Object)
	 */
	@Override
	public LiberecoShippingMethod saveOrUpdate(LiberecoShippingMethod obj) {
		super.persist(obj);
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(LiberecoShippingMethod obj) {
		super.remove(obj);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#find(java.lang.Object)
	 */
	@Override
	public LiberecoShippingMethod find(LiberecoShippingMethod obj) {
		return entityManager.find(LiberecoShippingMethod.class, obj.getShippingMethodId());
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#findById(java.lang.Object)
	 */
	@Override
	public LiberecoShippingMethod findById(Long id) {
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
	 * @see com.libereco.server.dao.LiberecoShippingMethodDao#hasLiberecoShippingType(com.libereco.common.ShippingLevelType)
	 */
	@Override
	public boolean hasLiberecoShippingType(ShippingLevelType type) {
		boolean found = false;	
		Query q = entityManager.createQuery("from " + entityClass.getName() + " where shippingLevelType = :type");
		q.setParameter("type", type);
		List<?> ls = null;
		found = (ls = q.getResultList()) != null && !ls.isEmpty() ? true : false;
		
		return found;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoShippingMethodDao#getLiberecoShippingMethods(com.libereco.common.ShippingLevelType)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LiberecoShippingMethod> getLiberecoShippingMethods(ShippingLevelType type) {
		Query q = entityManager.createQuery("from " + entityClass.getName() + " where shippingLevelType = :type");
		q.setParameter("type", type);
		List<?> ls = null;
		return (List<LiberecoShippingMethod>) ((ls = q.getResultList()) != null && !ls.isEmpty() ? ls : null);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoShippingMethodDao#getLiberecoShippingMethodIds()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getLiberecoShippingMethodIds() {
		Query q = entityManager.createQuery("select id from " + entityClass.getName());
		List<Long> ids = null;
		return (List<Long>) ((ids = q.getResultList()) != null && !ids.isEmpty() ? ids : null);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoShippingMethodDao#getLiberecoShippingMethodTypes()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ShippingLevelType> getLiberecoShippingMethodTypes() {
		Query q = entityManager.createQuery("select type from " + entityClass.getName());
		List<ShippingLevelType> liberecoShippingMethodTypes = null;
		return (List<ShippingLevelType>) ((liberecoShippingMethodTypes = q.getResultList()) != null && !liberecoShippingMethodTypes.isEmpty() ? liberecoShippingMethodTypes : null);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoShippingMethodDao#findLiberecoShippingMethodsByMarketplace(com.libereco.server.model.Marketplace)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LiberecoShippingMethod> findLiberecoShippingMethodsByMarketplace(Marketplace m) {
		List<LiberecoShippingMethod> liberecoShippingMethods = null;
		Query q = entityManager.createQuery("select shippingMethods FROM Marketplace");
		return (List<LiberecoShippingMethod>) ((liberecoShippingMethods = q.getResultList()) != null && !liberecoShippingMethods.isEmpty() ? liberecoShippingMethods : null);
	}

}
