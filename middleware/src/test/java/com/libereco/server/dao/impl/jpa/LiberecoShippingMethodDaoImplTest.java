/**
 * 
 */
package com.libereco.server.dao.impl.jpa;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.libereco.common.ShippingLevel;

/**
 * @author rrached
 *
 */
public class LiberecoShippingMethodDaoImplTest extends AbstractDaoImplJpaTest {
//	private LiberecoShippingMethodDaoImpl shipping;
//    private LiberecoShippingMethodDaoImpl shipping2;
//
//	
//	private static final String SHIPPING_METHOD_ID = "001";
//	private static final ShippingLevel SHIPPING_LEVEL = ShippingLevel.STANDARD;
//	private static final String NAME = "shipping item 001";
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@Before
//	public void setUp() throws Exception {
//		super.setUp();	
//		EntityTransaction tx = getEntityManager().getTransaction();
//	    tx.begin();		//************** BEGIN **************
//		shipping = 
//			new LiberecoShippingMethodDaoImpl(SHIPPING_METHOD_ID, SHIPPING_LEVEL, NAME);
//	    getEntityManager().persist(shipping);
//	    tx.commit();	//************** END **************
//	    
//	    shipping2 = 
//			new LiberecoShippingMethodDaoImpl("002", ShippingLevel.EXPEDITED, "shipping item 002");
//	}
//
//	/**
//	 * @throws java.lang.Exception
//	 */
//	@After
//	public void tearDown() throws Exception {
//		try {
//			EntityTransaction tx = getEntityManager().getTransaction();
//		    tx.begin();		//************** BEGIN **************
//		    getEntityManager().remove(shipping);
//		    if (getEntityManager().contains(shipping2)) {
//		    	getEntityManager().remove(shipping2);
//		    }
//		    tx.commit();	//************** END **************		
//		} finally {
//			getEntityManager().close();
//		}
//	}
//	
//	@Test
//	public final void testPersistLiberecoShippingMethodCompleteObject() throws Exception {
//		LiberecoShippingMethodDaoImpl expected = 
//			new LiberecoShippingMethodDaoImpl(SHIPPING_METHOD_ID, SHIPPING_LEVEL, NAME);
//	    Query readShippingResultsQuery = 
//	    	getEntityManager().createQuery("from LiberecoShippingMethodDaoImpl");	// query refers to table name
//	    @SuppressWarnings("unchecked")
//		List<LiberecoShippingMethodDaoImpl> resultReadShippingResultsQuery = 
//			readShippingResultsQuery.getResultList();
//	    assertNotNull(resultReadShippingResultsQuery);
//	    assertTrue(resultReadShippingResultsQuery.size() == 1);
//	    assertEquals(expected, resultReadShippingResultsQuery.get(0));
//	    System.out.println("All shipping methods");
//	    for (LiberecoShippingMethodDaoImpl shippingMethod : resultReadShippingResultsQuery) {
//			System.out.println(shippingMethod);
//		}
//	}
//	
//	@Test
//	public final void testPersistLiberecoShippingMethodMultipleObjects() throws Exception {
//		EntityTransaction tx = getEntityManager().getTransaction();
//	    tx.begin();		//************** BEGIN **************
//	    getEntityManager().persist(shipping2);
//	    tx.commit();	//************** END **************
//
//	    Query readShippingResultsQuery = 
//	    	getEntityManager().createQuery("from LiberecoShippingMethodDaoImpl");	// query refers to table name
//	    @SuppressWarnings("unchecked")
//		List<LiberecoShippingMethodDaoImpl> resultReadShippingResultsQuery = 
//			readShippingResultsQuery.getResultList();
//	    assertNotNull(resultReadShippingResultsQuery);
//	    assertTrue(resultReadShippingResultsQuery.size() == 2);
//	    System.out.println("All shipping methods");
//	    for (LiberecoShippingMethodDaoImpl shippingMethod : resultReadShippingResultsQuery) {
//			System.out.println(shippingMethod);
//		}
//	}
	
}
