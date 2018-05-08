/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storageLayer;

import functionLayer.Order;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stephan
 */
public class OrderMapperTest {
    
    public OrderMapperTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addOrder method, of class OrderMapper.
     */
    /*@Test
    public void testAddOrder() throws Exception {
        System.out.println("addOrder");
        
        List before = OrderMapper.getAllOrders();
        int expResult = before.size() + 1;
        
        OrderMapper.addOrder( testOrderObject() );
        
        List after = OrderMapper.getAllOrders();
        int result = after.size();
        
        assertEquals(expResult, result);
    }*/

    /**
     * Test of getOrder method, of class OrderMapper.
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
            } else {
                result = false;
                break;
            }
       }
        assertTrue(result);
    }

    /**
     * Test of getAllOrders method, of class OrderMapper.
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
     */
    /*@Test
    public void testUpdateStatus() throws Exception {
        System.out.println("updateStatus");
        Order order = null;
        Order expResult = null;
        Order result = OrderMapper.updateStatus(order);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
    private Order testOrderObject(){
        Order order = new Order().

            /* customer */
            setName( "test" ).
            setAddress( "addressTest" ).
            setZipCode( 1234 ).
            setCity( "cityTest" ).
            setPhone( "12345678" ).
            setEmail( "emailTest" ).
            setNote( "noteTest" ).

            /* carport */
            setWidth( 240 ).
            setLength( 240 ).

            /* roof */
            setRoof( 1 ).
            setAngle( 0 ).

            /* shed */
            setShedWidth( 0 ).
            setShedLength( 0 );
        
        return order;    
    }
}
