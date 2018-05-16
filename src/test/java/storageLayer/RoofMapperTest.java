package storageLayer;

import functionLayer.Roof;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author super
 */
public class RoofMapperTest {
    
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
        assertEquals(expType, roof.getTYPE());
    }
    
}
