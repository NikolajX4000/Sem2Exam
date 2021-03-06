package storageLayer;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import logicLayer.CustomException;
import logicLayer.Material;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author super
 */
public class MaterialMapperTest {

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
            if(testConnection == null) {
                String url = String.format("jdbc:mysql://%s:3306/%s", HOST, DBNAME);
                Class.forName("com.mysql.jdbc.Driver");

                testConnection = DriverManager.getConnection(url, USER, USERPW);
                // Make mappers use test 
                Connector.setConnection(testConnection);
            }
            // reset test database
            try(Statement stmt = testConnection.createStatement()) {
                stmt.execute("drop table if exists materials");
                stmt.execute("create table materials like materialsTest");
                stmt.execute("insert into materials select * from materialsTest");
            }

        } catch(ClassNotFoundException | SQLException ex) {
            testConnection = null;
            System.out.println("Could not open connection to database: " + ex.getMessage());
        }
    }

    /**
     * Test of getMaterials method, of class MaterialMapper. Testing for table
     * 'materials' cotaining a static amount of '124'
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMaterials() throws Exception {
        List<Material> matarials = MaterialMapper.getAllMaterials();
        int result = matarials.size();
        int expResult = 124;

        assertEquals(expResult, result);
    }

    /**
     * Test of getMaterial method, of class MaterialMapper. Testing for table
     * 'materials' cotains item named 'overstern' with descibtion of '25x125mm.
     * trykimp. Bræt'
     *
     * @throws Exception
     */
    @Test
    public void testGetMaterial() throws Exception {
        String materialName = "overstern";
        List<Material> materials = MaterialMapper.getMaterials(materialName);
        String expResult = "25x125mm. trykimp. Bræt";
        String result = materials.get(1).getDescription();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMaterial method, of class MaterialMapper. Testing for table
     * 'materials' cotains item named 'overstern' with descibtion of '25x125mm.
     * trykimp. Bræt'
     *
     * @throws Exception
     */
    @Test(expected = CustomException.class)
    public void testGetMaterial_posOutOfBounds() throws Exception {
        int id = Integer.MAX_VALUE;
        MaterialMapper.getMaterial( id );
    }

    /**
     * Test of getMaterial method, of class MaterialMapper - with invalid name.
     * Testing for getting an invalid name from the 'materials' table. This
     * should return an empty List because this name doesn't exits.
     *
     * @throws Exception
     */
    @Test
    public void testGetMaterial_invalidName() throws Exception {
        String materialName = "invalidName";
        List<Material> result = MaterialMapper.getMaterials(materialName);
        assertTrue(result.isEmpty());
    }

    /**
     * Test of getMaterial method, of class MaterialMapper - with 'null' name.
     * Testing for getting an 'null' name from the 'materials' table. This
     * should return an empty List because this name doesn't exits.
     *
     * @throws Exception
     */
    @Test
    public void testGetMaterial_nullName() throws Exception {
        String materialName = null;
        List<Material> result = MaterialMapper.getMaterials(materialName);
        assertTrue(result.isEmpty());
    }

    /**
     * Test of updateMaterial method, of class MaterialMapper. Testing an update
     * method on the 'materials' table. Getting a Material object from the
     * database, modifying it, updateting it, get it back again and checks if
     * the modification was successful.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateMaterial() throws Exception {
        Material material = MaterialMapper.getMaterials("overstern").get(1);
        material.setDescription("Testing");

        MaterialMapper.updateMaterial(material);
        String expResult = "Testing";
        String result = MaterialMapper.getMaterials("overstern").get(1).getDescription();
        assertEquals(result, expResult);
    }

    /**
     * Test of updateMaterial method, of class MaterialMapper - with 'null'
     * object. Testing an update method on the 'materials' table. Getting a
     * Material object from the database, modifying it with a null object,
     * updateting it, and recieve a CustomException
     *
     * @throws java.lang.Exception
     */
    @Test(expected = CustomException.class)
    public void testUpdateMaterial_null() throws Exception {
        Material material = null;
        MaterialMapper.updateMaterial(material);
    }

    /**
     * Test of updateMaterial method, of class MaterialMapper - method w/
     * parameters. Testing an update method on the 'materials' table. Calling
     * updateMaterial with predifined arguments, getting it and check is it was
     * successful.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateMaterial_wParameters() throws Exception {
        int id = 3;
        int size = 5;
        int price = 7;
        MaterialMapper.updateMaterial(id, size, price);
        Material material = MaterialMapper.getMaterial(id);

        assertEquals(material.getSize(), size);
        assertEquals(material.getPrice(), price);
    }

    /**
     * Test of updateMaterial method, of class MaterialMapper - method w/
     * parameters. In this case the overloaded method with 'describtion' is
     * used. Testing an update method on the 'materials' table. Calling
     * updateMaterial with predifined arguments, getting it and check is it was
     * successful.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateMaterial_wParameters_overloaded_wDesc() throws Exception {
        int id = 3;
        int size = 5;
        int price = 7;
        String describtion = "eleven";

        MaterialMapper.updateMaterial(id, size, price, describtion);
        Material material = MaterialMapper.getMaterial(id);

        assertEquals(material.getSize(), size);
        assertEquals(material.getPrice(), price);
        assertEquals(material.getDescription(), describtion);
    }

    /**
     * Test of updateMaterial method, of class MaterialMapper - method w/
     * parameters. Testing an update method on the 'materials' table. Calling
     * updateMaterial with predifined arguments, and with the 'plank_id' as
     * 'Integer.MIN_VALUE'. getting it and check is it was successful.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = CustomException.class)
    public void testUpdateMaterial_wParameters_negOutOfBoundsID() throws Exception {
        int id = Integer.MIN_VALUE;
        int size = 5;
        int price = 7;

        MaterialMapper.updateMaterial(id, size, price);
    }

    /**
     * Test of updateMaterial method, of class MaterialMapper - method w/
     * parameters. In this case the overloaded method with 'describtion' is
     * used. Testing an update method on the 'materials' table. Calling
     * updateMaterial with predifined arguments, and with the 'plank_id' as
     * 'Integer.MIN_VALUE'. getting it and check is it was successful.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = CustomException.class)
    public void testUpdateMaterial_wParameters_negOutOfBoundsID_overloaded_wDesc() throws Exception {
        int id = Integer.MIN_VALUE;
        int size = 5;
        int price = 7;
        String describtion = "eleven";

        MaterialMapper.updateMaterial(id, size, price, describtion);
    }

    /**
     * Test of updateMaterial method, of class MaterialMapper - method w/
     * parameters. Testing an update method on the 'materials' table. Calling
     * updateMaterial with predifined arguments, and with the 'plank_id' as
     * 'Integer.MAX_VALUE'. getting it and check is it was successful.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = CustomException.class)
    public void testUpdateMaterial_wParameters_posOutOfBoundsID() throws Exception {
        int id = Integer.MAX_VALUE;
        int size = 5;
        int price = 7;

        MaterialMapper.updateMaterial(id, size, price);
    }

    /**
     * Test of updateMaterial method, of class MaterialMapper - method w/
     * parameters. In this case the overloaded method with 'describtion' is
     * used. Testing an update method on the 'materials' table. Calling
     * updateMaterial with predifined arguments, and with the 'plank_id' as
     * 'Integer.MAX_VALUE'. getting it and check is it was successful.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = CustomException.class)
    public void testUpdateMaterial_wParameters_posOutOfBoundsID_overloaded_wDesc() throws Exception {
        int id = Integer.MAX_VALUE;
        int size = 5;
        int price = 7;
        String describtion = "eleven";

        MaterialMapper.updateMaterial(id, size, price, describtion);
    }

    /**
     * Test of updateMaterial method, of class MaterialMapper - method w/
     * parameters. Testing an update method on the 'materials' table. Calling
     * updateMaterial with predifined arguments, and with the 'price' as
     * 'Integer.MIN_VALUE'. This would throw an exception, do to the
     * 'setPrice()' method in the Material class.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = CustomException.class)
    public void testUpdateMaterial_wParameters_negPrice() throws Exception {
        int id = 1;
        int size = 5;
        int price = -1;

        MaterialMapper.updateMaterial(id, size, price);
    }

    /**
     * Test of updateMaterial method, of class MaterialMapper - method w/
     * parameters. In this case the overloaded method with 'describtion' is
     * used. Testing an update method on the 'materials' table. Calling
     * updateMaterial with predifined arguments, and with the 'price' as
     * 'Integer.MIN_VALUE'. This would throw an exception, do to the
     * 'setPrice()' method in the Material class.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = CustomException.class)
    public void testUpdateMaterial_wParameters_negPrice_overloaded_wDesc() throws Exception {
        int id = 1;
        int size = 5;
        int price = -1;
        String describtion = "eleven";

        MaterialMapper.updateMaterial(id, size, price, describtion);
    }

    /**
     * Test of updateMaterial method, of class MaterialMapper - method w/
     * parameters. Testing an update method on the 'materials' table. Calling
     * updateMaterial with predifined arguments, and with the 'size' as
     * 'Integer.MIN_VALUE'. This would throw an exception, do to the 'setSize()'
     * method in the Material class.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = CustomException.class)
    public void testUpdateMaterial_wParameters_negSize() throws Exception {
        int id = 1;
        int size = -1;
        int price = 7;

        MaterialMapper.updateMaterial(id, size, price);
    }

    /**
     * Test of updateMaterial method, of class MaterialMapper - method w/
     * parameters. In this case the overloaded method with 'describtion' is
     * used. Testing an update method on the 'materials' table. Calling
     * updateMaterial with predifined arguments, and with the 'size' as
     * 'Integer.MIN_VALUE'. This would throw an exception, do to the 'setSize()'
     * method in the Material class.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = CustomException.class)
    public void testUpdateMaterial_wParameters_negSize_overloaded_wDesc() throws Exception {
        int id = 1;
        int size = -1;
        int price = 7;
        String describtion = "eleven";

        MaterialMapper.updateMaterial(id, size, price, describtion);
    }

    /**
     * Test of updateMaterial method, of class MaterialMapper - method w/
     * parameters. Testing an update method on the 'materials' table. Calling
     * updateMaterial with predifined arguments, and with the 'describtion' as
     * 'null'. This would throw an exception, do to the SQL exception catched in
     * the 'updateMaterial()'s try-catch.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = CustomException.class)
    public void testUpdateMaterial_wParameters_nullDescribtion() throws Exception {
        int id = 3;
        int size = 5;
        int price = 7;
        String describtion = null;

        MaterialMapper.updateMaterial(id, size, price, describtion);
    }

}
