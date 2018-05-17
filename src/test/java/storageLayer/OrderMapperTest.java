/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storageLayer;

import com.mysql.jdbc.Statement;
import functionLayer.Order;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Stephan
 */
public class OrderMapperTest {

    private static Connection testConnection;
    private static String USER = "fogTest";
    private static String USERPW = "password123";
    private static String DBNAME = "sem2examTest";
    private static String HOST = "159.89.9.144";

    @Before
    public void setUp() {
        try {
            // awoid making a new connection for each test
            if (testConnection == null) {
                String url = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
                Class.forName("com.mysql.jdbc.Driver");

                testConnection = DriverManager.getConnection(url, USER, USERPW);
                // Make mappers use test 
                Connector.setConnection(testConnection);
            }
            // reset test database
            try (Statement stmt = (Statement) testConnection.createStatement()) {
                stmt.execute("drop table if exists orders");
                stmt.execute("create table orders like ordersTest");
                stmt.execute("insert into orders select * from ordersTest");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    public OrderMapperTest() {
    }

    /**
     * Test of addOrder method, of class OrderMapper.
     *
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
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOrder() throws Exception {
        System.out.println("getOrder");
        int id = 1;
        String expResult = "Dummy";
        String result = OrderMapper.getOrder(id).getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrders method, of class OrderMapper.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOrders() throws Exception {
        System.out.println("getOrders");
        String email = "dummy@dummy.dummy";
        String expResult = "Dummy";
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
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllOrders() throws Exception {
        System.out.println("getAllOrders");
        String expResult = "dummy@dummy.dummy";
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
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateOrder() throws Exception {
        System.out.println("updateOrder");
        Order expResult = OrderMapper.getOrder(1);
        expResult.setNote("Testing");
        OrderMapper.updateOrder(expResult);
        boolean result = OrderMapper.getOrder(1).getNote().equals("Testing");

        assertTrue(result);
    }

    /**
     * Test of updateStatus method, of class OrderMapper.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateStatus() throws Exception {
        System.out.println("updateStatus");
        Order expResult = OrderMapper.getOrder(1);
        expResult.setStatus("Annulleret");
        OrderMapper.updateOrder(expResult);
        boolean result = OrderMapper.getOrder(1).getStatus().equals("Annulleret");

        assertTrue(result);
    }

}
