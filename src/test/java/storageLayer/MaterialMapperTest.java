package storageLayer;

import com.mysql.jdbc.Connection;
import functionLayer.Material;
import java.sql.DriverManager;
import com.mysql.jdbc.*;
import functionLayer.CustomException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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
    
    @Before
    public void setUp() {
    try {        
        // awoid making a new connection for each test
        if ( testConnection == null ) {
            String url = String.format( "jdbc:mysql://%s:3306/%s", HOST, DBNAME );
            Class.forName( "com.mysql.jdbc.Driver" );
            
            testConnection = ( Connection )DriverManager.getConnection( url, USER, USERPW );
            // Make mappers use test 
            Connector.setConnection( testConnection );
        }
        // reset test database
        try ( Statement stmt = ( Statement )testConnection.createStatement() ) {
            stmt.execute( "drop table if exists materials" );
            stmt.execute( "create table materials like materialsTest" );
            stmt.execute( "insert into materials select * from materialsTest" );
        }

    } catch ( ClassNotFoundException | SQLException ex ) {
        testConnection = null;
        System.out.println( "Could not open connection to database: " + ex.getMessage() );
    }
}       

    /**
     * Test of getMaterials method, of class MaterialMapper.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetMaterials() throws Exception{
        System.out.println("getMaterials");
        List<Material> matarials = MaterialMapper.getAllMaterials();
        int result = matarials.size();
        int expResult = 124;
        
        assertEquals(expResult, result);
    }

    /**
     * 
     * @throws Exception 
     */
    @Test
    public void testGetMaterial() throws Exception {
        System.out.println( "getMaterial" );
        String materialName = "overstern";
        List<Material> materials = MaterialMapper.getMaterials( materialName );
        String expResult = "25x125mm. trykimp. Bræt";
        String result = materials.get(1).getDescription();
        
        assertEquals(expResult, result);
    }
    
    /**
     * 
     * @throws Exception 
     */
    @Test
    public void testGetMaterial_invalidName() throws Exception {
        System.out.println( "getMaterial: called by invalid name" );
        String materialName = "invalidName";
        List<Material> result = MaterialMapper.getMaterials( materialName );
        
        assertTrue( result.isEmpty() );
    }
    
    /**
     * 
     * @throws Exception 
     */
    @Test
    public void testGetMaterial_nullName() throws Exception {
        System.out.println( "getMaterial: called by null name" );
        String materialName = null;
        List<Material> result = MaterialMapper.getMaterials( materialName );
        
        assertTrue( result.isEmpty() );
    }
    
    
    /**
     * Test of updateMaterial method, of class MaterialMapper.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateMaterial() throws Exception {
        System.out.println( "updateMaterial" );
        Material material = MaterialMapper.getMaterials( "overstern" ).get(1);
        material.setDescription( "Testing" );
        MaterialMapper.updateMaterial( material );
        String expResult = "Testing";
        String result = MaterialMapper.getMaterials( "overstern" ).get(1).getDescription();
        assertEquals( result, expResult );
    }
    
    /**
     * Test of updateMaterial method, of class MaterialMapper.
     * @throws java.lang.Exception
     */
    @Test( expected = CustomException.class )
    public void testUpdateMaterial_null() throws Exception {
        System.out.println( "updateMaterial null" );
        Material material = null;
        MaterialMapper.updateMaterial( material );
    }

}
