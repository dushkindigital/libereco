/**
 * 
 */
package com.libereco.server.dao.impl.jpa;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
public class LiberecoShippingTemplateDaoImplTest extends AbstractDaoImplJpaTest {
	private LiberecoShippingTemplateDaoImpl shippingTemplate;
	private LiberecoShippingTemplateDaoImpl shippingTemplate2;
	
	private LiberecoShippingMethodDaoImpl shipping;
    private LiberecoShippingMethodDaoImpl shipping2;
	
	private static final String SHIPPING_METHOD_ID = "001";
	private static final String SHIPPING_TEMPLATE_ID = "T001";
	private static final ShippingLevel SHIPPING_LEVEL = ShippingLevel.STANDARD;
	private static final String NAME = "shipping item 001";
	private static final String POSTCODE = "H4N Y6R";
	private static final String COUNTRY = "USA";

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		EntityTransaction tx = getEntityManager().getTransaction();
	    tx.begin();		//************** BEGIN **************
		shipping = 
			new LiberecoShippingMethodDaoImpl(SHIPPING_METHOD_ID, SHIPPING_LEVEL, NAME);
		shippingTemplate =
			new LiberecoShippingTemplateDaoImpl(SHIPPING_TEMPLATE_ID, shipping, POSTCODE, COUNTRY);
		getEntityManager().persist(shippingTemplate);
	    tx.commit();	//************** END **************
	    
	    shipping2 = 
			new LiberecoShippingMethodDaoImpl("002", ShippingLevel.EXPEDITED, "shipping item 002");
		shippingTemplate2 =
			new LiberecoShippingTemplateDaoImpl("T002", shipping2, POSTCODE, COUNTRY);		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		try {
			EntityTransaction tx = getEntityManager().getTransaction();
		    tx.begin();		//************** BEGIN **************
		    getEntityManager().remove(shippingTemplate);
		    if (getEntityManager().contains(shippingTemplate2)) {
		    	getEntityManager().remove(shippingTemplate2);
		    }
		    tx.commit();	//************** END **************		
		} finally {
			getEntityManager().close();
		}
	}

	@Test
	public final void testPersistLiberecoShippingTemplateCompleteObject() throws Exception {
		LiberecoShippingTemplateDaoImpl expected = 
			new LiberecoShippingTemplateDaoImpl(SHIPPING_TEMPLATE_ID, shipping, POSTCODE, COUNTRY);
	    Query readShippingTemplateResultsQuery = 
	    	getEntityManager().createQuery("from LiberecoShippingTemplateDaoImpl");	// query refers to table name
	    @SuppressWarnings("unchecked")
		List<LiberecoShippingTemplateDaoImpl> resultReadShippingTemplateResultsQuery = 
			readShippingTemplateResultsQuery.getResultList();
	    assertNotNull(resultReadShippingTemplateResultsQuery);
	    assertTrue(resultReadShippingTemplateResultsQuery.size() == 1);
	    assertEquals(expected, resultReadShippingTemplateResultsQuery.get(0));
	    System.out.println("All shipping templates");
	    for (LiberecoShippingTemplateDaoImpl shippingTemplate : resultReadShippingTemplateResultsQuery) {
			System.out.println(shippingTemplate);
		}
	}
	
	@Test
	public final void testPersistLiberecoShippingMethodMultipleObjects() throws Exception {
		EntityTransaction tx = getEntityManager().getTransaction();
	    tx.begin();		//************** BEGIN **************
	    getEntityManager().persist(shippingTemplate2);
	    tx.commit();	//************** END **************

	    Query readShippingResultsQuery = 
	    	getEntityManager().createQuery("from LiberecoShippingTemplateDaoImpl");	// query refers to table name
	    @SuppressWarnings("unchecked")
		List<LiberecoShippingTemplateDaoImpl> resultReadShippingTemplateResultsQuery = 
			readShippingResultsQuery.getResultList();
	    assertNotNull(resultReadShippingTemplateResultsQuery);
	    assertTrue(resultReadShippingTemplateResultsQuery.size() == 2);
	    System.out.println("All shipping templates");
	    for (LiberecoShippingTemplateDaoImpl shippingTemplate : resultReadShippingTemplateResultsQuery) {
			System.out.println(shippingTemplate);
		}
	}
	
}
