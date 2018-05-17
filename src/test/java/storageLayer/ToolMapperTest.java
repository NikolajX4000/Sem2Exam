/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storageLayer;

import com.mysql.jdbc.Statement;
import functionLayer.CustomException;
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
    private static final String USER = "fogTest";
    private static final String USERPW = "password123";
    private static final String DBNAME = "sem2examTest";
    private static final String HOST = "159.89.9.144";

    /**
     * Changing the connection from the actual database to the test database.
     * This method drops and creates a new table called 'tools'.
     * After creating the table it inserts a duplicates of the data 
     * from the 'toolsTest' table.
     */
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
     * Testing for table 'tools' cotaining a static amount of '18'
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAllTool() throws Exception {
        System.out.println("getAllTool");
        List<Material> tools = ToolMapper.getAllTool();
        int result = tools.size();
        int expResult = 18;
        assertEquals(expResult, result);
    }

    /**
     * Test of getTool method, of class ToolMapper.
     * Testing for table 'tools' cotains item with id '1'
     * with name of '4,5x70 mm. skruer'
     * @throws java.lang.Exception
     */
    @Test
    public void testGetTool() throws Exception {
        System.out.println( "getTool" );
        int tool_id = 9;
        Material meterial = ToolMapper.getTool( tool_id );
        String expResult = "4,5x70 mm. skruer";
        String result = meterial.getName();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getTool method, of class ToolMapper - with negative out of bounds.
     * Testing for getting an Tool object from the 'tools' table, 
     * with the 'tool_id' as 'Integer.MIN_VALUE'.
     * This should return an null object because this 'tool_id' doesn't exits.
     * @throws Exception 
     */
    @Test
    public void testGetTool_negOutOfBoundsID() throws Exception {
        System.out.println( "getTool: called by neg. OOB value" );
        int tool_id = Integer.MIN_VALUE;
        Material meterial = ToolMapper.getTool( tool_id );
        String result = meterial.getName();
        assertNull( result );
    }
    
    /**
     * Test of getTool method, of class ToolMapper - with positive out of bounds.
     * Testing for getting an Tool object from the 'tools' table, 
     * with the 'tool_id' as 'Integer.MAX_VALUE'.
     * This should return an null object because this 'tool_id' doesn't exits.
     * @throws Exception 
     */
    @Test
    public void testGetTool_posOutOfBoundsID() throws Exception {
        System.out.println( "getTool: called by pos. OOB value" );
        int tool_id = Integer.MAX_VALUE;
        Material meterial = ToolMapper.getTool( tool_id );
        String result = meterial.getName();
        assertNull( result );
    }

    /**
     * Test of updateTool method, of class ToolMapper.
     * Testing an update method on the 'tools' table. 
     * Getting a Material object from the database, modifying it, 
     * updateting it, get it back again and checks if the modification 
     * was successful. 
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateTool() throws Exception {
        System.out.println("updateTool");
        Material tool = ToolMapper.getTool( 1 );
        tool.setName( "Testing" );
        
        ToolMapper.updateTool( tool );
        String expResult = "Testing";
        String result = ToolMapper.getTool( 1 ).getName();
        assertEquals( result, expResult );
    }
    
    /**
     * Test of updateTool method, of class ToolMapper - with 'null' object.
     * Testing an update method on the 'tools' table. 
     * Getting a Material object from the database, modifying it with a null object, 
     * updateting it, and recieve a CustomException
     * @throws java.lang.Exception
     */
    @Test( expected = CustomException.class )
    public void testUpdateTool_null() throws Exception {
        System.out.println("updateTool null");
        Material tool = null;
        ToolMapper.updateTool( tool );
    }

}
