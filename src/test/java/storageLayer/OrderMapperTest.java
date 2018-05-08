/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storageLayer;

import functionLayer.Order;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stephan
 */
public class OrderMapperTest {
    
    public OrderMapperTest() {
    }

    /**
     * Test of addOrder method, of class OrderMapper.
     * @throws java.lang.Exception
     */
    /*@Test
    public void testAddOrder() throws Exception {
        System.out.println("addOrder");
        String expResult = "OrderTest.addOrder";
        OrderMapper.addOrder( OrderMapper.getOrder( 4 ).setName( "OrderTest.addOrder" ));
        List<Order> orders = OrderMapper.getAllOrders();
        Boolean result = false;
        
        if (orders.get(0).getName().equals(expResult)) {
            result = true;
        }

        assertTrue(result);
    }*/

    /**
     * Test of getOrder method, of class OrderMapper.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOrder() throws Exception {
        System.out.println("getOrder");
        int id = 4;
        String expResult = "orderTest";
        String result = OrderMapper.getOrder(id).getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrders method, of class OrderMapper.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOrders() throws Exception {
        System.out.println("getOrders");
        String email = "test@mail";
        String expResult = "orderTest";
        List<Order> orders = OrderMapper.getOrders(email);
        Boolean result = false;
        
        for (Order o : orders) {
            if (o.getName().equals(expResult)) {
                result = true;
                break;
            }
       }
        assertTrue(result);
    }

    /**
     * Test of getAllOrders method, of class OrderMapper.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllOrders() throws Exception {
        System.out.println("getAllOrders");
        String expResult = "test@mail";
        List<Order> orders = OrderMapper.getAllOrders();
        boolean result = false;
        
        for (Order o : orders) {
            if (o.getEmail().equals(expResult)) {
                result = true;
                break;
            }
       }
       assertTrue(result); 
    }

    /**
     * Test of updateOrder method, of class OrderMapper.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateOrder() throws Exception {
        System.out.println("updateOrder");
        Order expResult = OrderMapper.getOrder( 4 );
        expResult.setNote( "Testing" );
        OrderMapper.updateOrder( expResult );
        boolean result = OrderMapper.getOrder( 4 ).getNote().equals( "Testing" );
        
        assertTrue( result );
    }

    /**
     * Test of updateStatus method, of class OrderMapper.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateStatus() throws Exception {
        System.out.println("updateStatus");
        Order expResult = OrderMapper.getOrder( 4 );
        expResult.setStatus( "Annulleret" );
        OrderMapper.updateOrder( expResult );
        boolean result = OrderMapper.getOrder( 4 ).getStatus().equals( "Annulleret" );
        
        assertTrue( result );
    }
    
}
