package storageLayer;

import functionLayer.Material;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author super
 */
public class MaterialMapperTest {
    
    public MaterialMapperTest() {
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
        int expResult = 100;
        
        assertEquals(expResult, result);
    }

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
     * Test of updateMaterial method, of class MaterialMapper.
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateMaterial() throws Exception {
    }

}
