/**
 * 
 */
package com.libereco.server.dao.impl.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.libereco.server.dao.MarketplaceDao;
import com.libereco.server.model.Marketplace;

/**
 * @author rrached
 *
 */
public class MarketplaceDaoImpl extends AbstractJpaDaoSupport<Long, Marketplace> implements
		MarketplaceDao {

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#saveOrUpdate(java.lang.Object)
	 * 
	 * There is no exact equivalent method in 
	 * com.libereco.server.dao.impl.jpa.AbstractJpaDaoSupport<Long, Marketplace>
	 */
	@Override
	public Marketplace saveOrUpdate(Marketplace obj) {
		super.persist(obj);
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#delete(java.lang.Object)
	 * 
	 * The equivalent method in 
	 * com.libereco.server.dao.impl.jpa.AbstractJpaDaoSupport<Long, Marketplace>
	 * is remove()
	 */
	@Override
	public void delete(Marketplace obj) {
		super.remove(obj);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#find(java.lang.Object)
	 */
	@Override
	public Marketplace find(Marketplace obj) {
		return getJpaTemplate().find(Marketplace.class, obj.getId());
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#findById(java.lang.Object)
	 */
	@Override
	public Marketplace findById(Long id) {
		return super.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#findAll()
	 */
	@Override
	public List<Marketplace> findAll() {
		return super.findAll();
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.AbstractDao#deleteAll()
	 */
	@Override
	public Integer deleteAll() {
		return super.removeAll();
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.MarketplaceDao#hasMarketplaceName(java.lang.String)
	 */
	@Override
	public boolean hasMarketplaceName(String marketplaceName) {
		boolean found = false;		
		final String qryString = "select m from Marketplace m where m.marketplaceName = ?1";
		List<?> results = getJpaTemplate().find(qryString, marketplaceName);
		found = results != null && !results.isEmpty() ? true : false;
		return found;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.MarketplaceDao#getMarketplace(java.lang.String)
	 */
	@Override
	public Marketplace getMarketplace(String marketplaceName) {
		final String qryString = "select m from Marketplace m where m.marketplaceName = ?1";
		@SuppressWarnings("unchecked")
		List<Marketplace> results = getJpaTemplate().find(qryString, marketplaceName);
		return (results != null && !results.isEmpty() ? results.get(0) : null);
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.MarketplaceDao#getMarketplaceIds()
	 */
	@Override
	public List<Long> getMarketplaceIds() {
		List<Long> marketplaceIds = new ArrayList<Long>();
		@SuppressWarnings("unchecked")
		List<Long> ids = getJpaTemplate().find("select id from Marketplace");
		if (ids != null && !ids.isEmpty()) {
			marketplaceIds.addAll(ids);		
		}
		return marketplaceIds;
	}

	/* (non-Javadoc)
	 * @see com.libereco.server.dao.MarketplaceDao#getMarketplaceNames()
	 */
	@Override
	public List<String> getMarketplaceNames() {
		List<String> marketplaceNames = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<String> names = getJpaTemplate().find("select marketplaceName from Marketplace");
		if (names != null && !names.isEmpty()) {
			marketplaceNames.addAll(names);		
		}
		return marketplaceNames;
	}

}
