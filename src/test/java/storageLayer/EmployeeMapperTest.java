package storageLayer;

import functionLayer.CustomException;
import functionLayer.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author super
 */
public class EmployeeMapperTest {

    private static Connection testConnection;
    private static final String USER = "fogTest";
    private static final String USERPW = "password123";
    private static final String DBNAME = "sem2examTest";
    private static final String HOST = "159.89.9.144";

    /**
     * Changing the connection from the actual database to the test database.
     * This method drops and creates a new table called 'materials'. After
     * creating the table it inserts a duplicates of the data from the
     * 'materialsTest' table.
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
            try (Statement stmt = testConnection.createStatement()) {
                stmt.execute("drop table if exists employees");
                stmt.execute("create table employees like employeesTest");
                stmt.execute("insert into employees select * from employeesTest");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    /**
     * Test of addEmployee method, of class EmployeeMapper.
     */
    @Test
    public void testAddEmployee() throws Exception {
        int id = 2;
        String name = "Test";
        String password = "1234";
        String hashedPW = "$2a$14$AiniAWZaiBJG9XmOiEFJr.0Qa.Nvw4v.ffhNmhXRq1eZwY7kKSmC6";

        EmployeeMapper.addEmployee(name, hashedPW);
        Employee emp = EmployeeMapper.login(name, password);

        assertEquals(id, emp.getId());
        assertEquals(name, emp.getName());
        assertEquals(hashedPW, emp.getPassword());
    }

    /**
     * Test of login method, of class EmployeeMapper.
     */
    @Test(expected = CustomException.class)
    public void testLoginWrongPassword() throws Exception {
        String name = "Test";
        String wrongPassword = "12345";
        String hashedPW = "$2a$14$AiniAWZaiBJG9XmOiEFJr.0Qa.Nvw4v.ffhNmhXRq1eZwY7kKSmC6";

        EmployeeMapper.addEmployee(name, hashedPW);
        Employee emp = EmployeeMapper.login(name, wrongPassword);
    }
}
