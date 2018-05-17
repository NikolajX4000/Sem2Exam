package storageLayer;

import com.mysql.jdbc.Statement;
import functionLayer.Roof;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author super
 */
public class RoofMapperTest {

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
                stmt.execute("drop table if exists roofs");
                stmt.execute("create table roofs like roofsTest");
                stmt.execute("insert into roofs select * from roofsTest");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    public RoofMapperTest() {
    }

    /**
     * Test of getAllRoofs method, of class RoofMapper.
     */
    @Test
    public void testGetAllRoofs() throws Exception {
        List<Roof> list = RoofMapper.getAllRoofs();
        assertEquals(16, list.size());
    }

    /**
     * Test of getRoofsByType method, of class RoofMapper.
     */
    @Test
    public void testGetRoofsById() throws Exception {
        Roof roof = RoofMapper.getRoofById(1);
        int expId = 1;
        String expName = "Plasttrapezplader";
        int expType = 0;
        assertEquals(expId, roof.getID());
        assertEquals(expName, roof.getNAME());
        assertEquals(expName, roof.toString());
        assertEquals(expType, roof.getTYPE());
    }

}
