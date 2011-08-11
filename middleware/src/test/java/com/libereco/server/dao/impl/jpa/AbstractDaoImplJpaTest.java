/**
 * 
 */
package com.libereco.server.dao.impl.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

/**
 * @author rrached
 *
 */
public abstract class AbstractDaoImplJpaTest {
	private EntityManagerFactory emf;
	private EntityManager entityManager;
	
	@Before
	protected void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("myjpacontext");
		entityManager = emf.createEntityManager();
	}
	
	@After
	protected void tearDown() throws Exception {
		entityManager.close();
	}
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
