/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storageLayer;

import functionLayer.Order;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stephan
 */
public class OrderMapperTest {
    
    public OrderMapperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
        
        OrderMapper.addOrder( new Order() );
        
        List after = OrderMapper.getAllOrders();
        int result = after.size();
        
        assertEquals(expResult, result);
    }*/

    /**
     * Test of getOrder method, of class OrderMapper.
     */
    /*@Test
    public void testGetOrder() throws Exception {
        System.out.println("getOrder");
        int id = 0;
        String expResult = null;
        String result = OrderMapper.getOrder(id).getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of getOrders method, of class OrderMapper.
     */
    @Test
    public void testGetOrders() throws Exception {
        System.out.println("getOrders");
        String email = "test@test";
        String expResult = "test";
        List<Order> orders = OrderMapper.getOrders(email);
        Boolean result = true;
        
        for (Order o : orders) {
            if (!o.getName().equals(expResult)); {
                result = false;
            }
        }
        assertTrue(result);
    }

    /**
     * Test of getAllOrders method, of class OrderMapper.
     */
    /*@Test
    public void testGetAllOrders() throws Exception {
        System.out.println("getAllOrders");
        List<Order> expResult = null;
        List<Order> result = OrderMapper.getAllOrders();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of updateOrder method, of class OrderMapper.
     */
    @Test
    public void testUpdateOrder() throws Exception {
        System.out.println("updateOrder");
        Order order = testOrderObject();
        Order expResult = order;
        Order result = OrderMapper.updateOrder(order);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
            setWidth( 9 ).
            setLength( 9 ).

            /* roof */
            setRoof( 1 ).
            setAngle( 0 ).

            /* shed */
            setShedWidth( 0 ).
            setShedLength( 0 );
        
        return order;    
    }
}
