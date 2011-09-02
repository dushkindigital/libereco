/** * Copyright (C) 2011 Dushkin Digital Media, LLC. */
package com.libereco.server.dao.impl.jpa;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import com.libereco.common.LiberecoPaymentType;
import com.libereco.server.dao.LiberecoPaymentMethodDao;
import com.libereco.server.model.LiberecoPaymentMethod;
import com.libereco.server.model.Marketplace;

/**
 * @author rrached
 * 
 * Make sure that the contract for each implementation method returning an Object
 * (especially {@link Collection} type of objects) end up returning null or an instance
 * of the Object.
 */
public class LiberecoPaymentMethodDaoImpl extends AbstractJpaDaoSupport<Long, LiberecoPaymentMethod>
		implements LiberecoPaymentMethodDao {

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#saveOrUpdate(java.lang.Object)
	 */
	@Override
	public LiberecoPaymentMethod saveOrUpdate(LiberecoPaymentMethod obj) {
		super.persist(obj);
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(LiberecoPaymentMethod obj) {
		super.remove(obj);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#find(java.lang.Object)
	 */
	@Override
	public LiberecoPaymentMethod find(LiberecoPaymentMethod obj) {
		return entityManager.find(LiberecoPaymentMethod.class, obj.getId());
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#findById(java.lang.Object)
	 */
	@Override
	public LiberecoPaymentMethod findById(Long id) {
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
	 * @see com.libereco.server.dao.LiberecoPaymentMethodDao#hasLiberecoPaymentType(com.libereco.common.LiberecoPaymentType)
	 */
	@Override
	public boolean hasLiberecoPaymentType(LiberecoPaymentType type) {
		boolean found = false;	
		Query q = entityManager.createQuery("from " + entityClass.getName() + " where paymentMethodType = :type");
		q.setParameter("type", type);
		List<?> ls = null;
		found = (ls = q.getResultList()) != null && !ls.isEmpty() ? true : false;
		
		return found;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoPaymentMethodDao#getLiberecoPaymentMethods(com.libereco.common.LiberecoPaymentType)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LiberecoPaymentMethod> getLiberecoPaymentMethods(LiberecoPaymentType type) {
		Query q = entityManager.createQuery("from " + entityClass.getName() + " where paymentMethodType = :type");
		q.setParameter("type", type);
		List<?> ls = null;
		return (List<LiberecoPaymentMethod>) ((ls = q.getResultList()) != null && !ls.isEmpty() ? ls : null);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoPaymentMethodDao#getLiberecoPaymentMethodIds()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getLiberecoPaymentMethodIds() {
		Query q = entityManager.createQuery("select id from " + entityClass.getName());
		List<Long> ids = null;
		return (List<Long>) ((ids = q.getResultList()) != null && !ids.isEmpty() ? ids : null);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoPaymentMethodDao#getLiberecoPaymentMethodTypes()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LiberecoPaymentType> getLiberecoPaymentMethodTypes() {
		Query q = entityManager.createQuery("select type from " + entityClass.getName());
		List<LiberecoPaymentType> liberecoPaymentMethodTypes = null;
		return (List<LiberecoPaymentType>) ((liberecoPaymentMethodTypes = q.getResultList()) != null && !liberecoPaymentMethodTypes.isEmpty() ? liberecoPaymentMethodTypes : null);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.LiberecoPaymentMethodDao#findLiberecoPaymentMethodsByMarketplace(com.libereco.server.model.Marketplace)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LiberecoPaymentMethod> findLiberecoPaymentMethodsByMarketplace(Marketplace marketplace) {
		List<LiberecoPaymentMethod> liberecoPaymentMethods = null;
		Query q = entityManager.createQuery("select m FROM "
				+ entityClass.getName() + " m WHERE marketplace = :entity");
		q.setParameter("entity", marketplace);
		return (List<LiberecoPaymentMethod>) ((liberecoPaymentMethods = q.getResultList()) != null && !liberecoPaymentMethods.isEmpty() ? liberecoPaymentMethods : null);
	}

}
