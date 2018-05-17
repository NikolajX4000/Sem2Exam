/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storageLayer;

import com.mysql.jdbc.Statement;
import functionLayer.Material;
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
public class ToolMapperTest {

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
                stmt.execute("drop table if exists tools");
                stmt.execute("create table tools like toolsTest");
                stmt.execute("insert into tools select * from toolsTest");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    /**
     * Test of getAllTool method, of class ToolMapper.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllTool() throws Exception {
        System.out.println("getAllTool");
        List<Material> tools = ToolMapper.getAllTool();
        int result = tools.size();
        int expResult = 16;
        assertEquals(expResult, result);
    }

    /**
     * Test of getTool method, of class ToolMapper.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTool() throws Exception {
        System.out.println("getTool");
        int tool_id = 9;
        Material meterial = ToolMapper.getTool(tool_id);
        String expResult = "4,5x70 mm. skruer";
        String result = meterial.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of updateTool method, of class ToolMapper.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateTool() throws Exception {
        System.out.println("updateTool");
    }

}
