/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storageLayer;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import logicLayer.CustomException;
import logicLayer.Order;
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
    @Test
    public void testAddOrder() throws Exception {
        String expResult = "OrderTest.addOrder";
        Order order = OrderMapper.getOrder( 1 );
        order.setName( expResult );
        order.setPrice( order.calculatePrice() );
        OrderMapper.addOrder( order );
        List<Order> orders = OrderMapper.getAllOrders();
        String result = orders.get( 0 ).getName();

        assertEquals( expResult, result );
    }
    /**
     * Test of getOrder method, of class OrderMapper.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOrder() throws Exception {
        int id = 1;
        String expResult = "Dummy";
        String result = OrderMapper._getOrder(id).getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrders method, of class OrderMapper.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetOrders() throws Exception {
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
        Order order = OrderMapper.getOrder( 1 );
        String expResult = "Testing";
        order.setNote(expResult );
        OrderMapper.updateOrder( order );
        String result = OrderMapper.getOrder( 1 ).getNote();

        assertEquals( expResult, result );
    }

    /**
     * Test of updateStatus method, of class OrderMapper.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateStatus() throws Exception {
        Order order = OrderMapper.getOrder( 1 );
        String expResult = "Annulleret";
        order.setStatus( expResult );
        OrderMapper.updateStatus( order );
        String result = OrderMapper.getOrder( 1 ).getStatus();

        assertEquals( expResult, result );
    }
    
    /**
     * Test of updateStatus method, of class OrderMapper.
     *
     * @throws java.lang.Exception
     */
    
    @Test
    public void testUpdateStatus_wParameters_status() throws Exception {
        int id = 1;
        String expResult = "Annulleret";
        
        OrderMapper.updateStatus( id, expResult );
        String result = OrderMapper.getOrder( 1 ).getStatus();

        assertEquals( expResult, result );
    }
    
    /**
     * Test of updateStatus method, of class OrderMapper.
     * In this case the overloaded method with 'id' and 'status' is
     * used. Testing an update method on the 'orders' table. Calling
     * updateStatus with predifined arguments, with the 'id' as
     * 'Integer.MIN_VALUE'. This would create a new Order Object 
     * with its predefined atributes.
     * 
     * @throws java.lang.Exception
     */
    
    @Test(expected = CustomException.class)
    public void testUpdateStatus_wParameters_negOutOfBounds() throws Exception {
        int id = Integer.MIN_VALUE;
        String status = "Anulleret";
        
        OrderMapper.updateStatus( id, status );
        OrderMapper.getOrder( id ).getName();
    }
    
    /**
     * Test of updateStatus method, of class OrderMapper.
     * In this case the overloaded method with 'id' and 'status' is
     * used. Testing an update method on the 'orders' table. Calling
     * updateStatus with predifined arguments, with the 'id' as
     * 'Integer.MAX_VALUE'. This would create a new Order Object 
     * with its predefined atributes.
     * 
     * @throws java.lang.Exception
     */
    
    @Test(expected = CustomException.class)
    public void testUpdateStatus_wParameters_posOutOfBounds() throws Exception {
        int id = Integer.MAX_VALUE;
        String status = "Anulleret";
        
        OrderMapper.updateStatus( id, status );
        OrderMapper.getOrder( id ).getName();
    }
    
    /**
     * Test of updateStatus method, of class OrderMapper.
     *
     * @throws java.lang.Exception
     */
    
    @Test
    public void testUpdatePrice() throws Exception {
        int id = 1;
        int price = 9999;
        String expResult = "9.999 kr.";
        OrderMapper.updatePrice( id, price );
        String result = OrderMapper.getOrder( 1 ).getPrice();

        assertEquals( expResult, result );
    }

}
