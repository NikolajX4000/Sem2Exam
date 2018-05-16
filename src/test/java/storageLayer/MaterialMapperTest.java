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
        int expResult = 65;
        
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
