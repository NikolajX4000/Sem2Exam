/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storageLayer;

import functionLayer.Material;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Stephan
 */
public class ToolMapperTest {
    /**
     * Test of getAllTool method, of class ToolMapper.
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
     * @throws java.lang.Exception
     */
    @Test
    public void testUpdateTool() throws Exception {
        System.out.println("updateTool");
    }
    
}
