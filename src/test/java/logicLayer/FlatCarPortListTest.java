/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLayer;

import logicLayer.Order;
import logicLayer.PartLine;
import logicLayer.CustomException;
import logicLayer.FlatCarPortList;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jacob Borg
 */
public class FlatCarPortListTest
{

    FlatCarPortList carportShed = null;
    FlatCarPortList carport = null;

    public FlatCarPortListTest() throws CustomException
    {
        Order order = new Order();
        order.
                setWidth(600).
                setLength(780).
                setShedWidth(530).
                setShedLength(210);
        carportShed = new FlatCarPortList(order);
        order = new Order();
        order.
                setWidth(600).
                setLength(780);
        carport = new FlatCarPortList(order);
    }

    @Test
    public void hasShedTest() throws Exception
    {
        assertTrue(carportShed.hasShed);
        assertFalse(carport.hasShed);
    }

    /**
     * Test of skruerSternOgVandbreat method, of class FlatCarPortList.
     */
    @Test
    public void testSkruerSternOgVandbreatShed() throws Exception
    {
        int expRes = 1;
        int res = carportShed.skruerSternOgVandbreat(17).getAmount();
        assertEquals(expRes, res);
    }

    /**
     * Test of oversternEnder method, of class FlatCarPortList.
     */
    @Test
    public void testOversternEnderShed() throws Exception
    {
        PartLine part = carportShed.oversternEnder();
        int expAmount = 1;
        int expSize = 600;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of understernEnder method, of class FlatCarPortList.
     */
    @Test
    public void testUndersternEnderShed() throws Exception
    {
        PartLine part = carportShed.understernEnder();
        int expAmount = 2;
        int expSize = 600;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of oversternSider method, of class FlatCarPortList.
     */
    @Test
    public void testOversternSiderShed() throws Exception
    {
        PartLine part = carportShed.oversternSider();
        int expAmount = 4;
        int expSize = 420;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of understernSider method, of class FlatCarPortList.
     */
    @Test
    public void testUndersternSiderShed() throws Exception
    {
        PartLine part = carportShed.understernSider();
        int expAmount = 4;
        int expSize = 420;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of vandbraetEnde method, of class FlatCarPortList.
     */
    @Test
    public void testVandbraetEndeShed() throws Exception
    {
        PartLine part = carportShed.vandbraetEnde();
        int expAmount = 2;
        int expSize = 300;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of vandbraetSide method, of class FlatCarPortList.
     */
    @Test
    public void testVandbraetSideShed() throws Exception
    {
        PartLine part = carportShed.vandbraetSide();
        int expAmount = 4;
        int expSize = 420;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of remCarport method, of class FlatCarPortList.
     */
    @Test
    public void testRemCarportShed() throws Exception
    {
        PartLine part = carportShed.remCarport();
        int expAmount = 2;
        int expSize = 600;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of stolperOgBolt method, of class FlatCarPortList.
     */
    @Test
    public void testStolperOgBoltShed() throws Exception
    {
        ArrayList<PartLine> parts = carportShed.stolperOgBolt();
        PartLine part = findPart("stolpe", parts);
        int expAmount = 12;
        int expSize = 300;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of braeddeboltOgFirkantskive method, of class FlatCarPortList.
     */
    @Test
    public void testBraeddeboltOgFirkantskiveShed() throws Exception
    {
        ArrayList<PartLine> parts = carportShed.braeddeboltOgFirkantskive(8);
        PartLine braeddebolt = findPart("Bræddebolte 10x120 mm.", parts);
        PartLine firkantSkive = findPart("Firkantskiver 40x40x11 mm.", parts);
        int expAmountB = 16;
        int amountB = braeddebolt.getAmount();
        int expAmountF = 16;
        int amountF = firkantSkive.getAmount();
        assertEquals(expAmountB, amountB);
        assertEquals(expAmountF, amountF);
    }

    /**
     * Test of beslagSkruer method, of class FlatCarPortList.
     */
    @Test
    public void testBeslagSkruerShed() throws Exception
    {
        PartLine part = carportShed.beslagSkruer(30, 2);
        int expAmount = 3;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of spaer method, of class FlatCarPortList.
     */
    @Test
    public void testSpaerShed() throws Exception
    {
        PartLine part = carportShed.spaer();
        int expAmount = 15;
        int expSize = 600;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of universalBeslagH method, of class FlatCarPortList.
     */
    @Test
    public void testUniversalBeslagHShed() throws Exception
    {
        PartLine part = carportShed.universalBeslagH(15);
        int expAmount = 15;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of universalBeslagV method, of class FlatCarPortList.
     */
    @Test
    public void testUniversalBeslagVShed() throws Exception
    {
        PartLine part = carportShed.universalBeslagV(15);
        int expAmount = 15;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of hulbaand method, of class FlatCarPortList.
     */
    @Test
    public void testHulbaandShed() throws Exception
    {
        PartLine part = carportShed.hulbaand();
        int expAmount = 2;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of tagplade method, of class FlatCarPortList.
     */
    @Test
    public void testTagpladeShed() throws Exception
    {
        ArrayList<PartLine> parts = carportShed.tagplade();
        PartLine tagplade = findPart("tagplade", parts);
        int expAmount = 14;
        int expSize = 420;
        int amount = tagplade.getAmount();
        int size = tagplade.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of plastmoBundskruer method, of class FlatCarPortList.
     */
    @Test
    public void testPlastmoBundskruerShed() throws Exception
    {
        PartLine part = carportShed.plastmoBundskruer();
        int expAmount = 3;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of loesholter method, of class FlatCarPortList.
     */
    @Test
    public void testLoesholterShed() throws Exception
    {
        PartLine gavl = carportShed.loesholterGavl();
        PartLine side = carportShed.loesholterSider();
        int expAmountG = 12;
        int expSizeG = 270;
        int amountG = gavl.getAmount();
        int sizeG = gavl.getSize();
        int expAmountS = 4;
        int expSizeS = 240;
        int amountS = side.getAmount();
        int sizeS = side.getSize();
        assertEquals(expAmountG, amountG);
        assertEquals(expSizeG, sizeG);
        assertEquals(expAmountS, amountS);
        assertEquals(expSizeS, sizeS);
    }

    /**
     * Test of vinkelbeslag method, of class FlatCarPortList.
     */
    @Test
    public void testVinkelbeslagShed() throws Exception
    {
        PartLine part = carportShed.vinkelbeslag(16);
        int expAmount = 32;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of remSkur method, of class FlatCarPortList.
     */
    @Test
    public void testRemSkurShed() throws Exception
    {
        PartLine part = carportShed.remSkur();
        int expAmount = 2;
        int expSize = 300;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of beklaedning method, of class FlatCarPortList.
     */
    @Test
    public void testBeklaedningShed() throws Exception
    {
        ArrayList<PartLine> parts = carportShed.beklaedning();
        PartLine part = findPart("beklædning", parts);
        int expAmount = 188;
        int expSize = 210;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of beklaedningSkruer method, of class FlatCarPortList.
     */
    @Test
    public void testBeklaedningSkruerShed() throws Exception
    {
        ArrayList<PartLine> parts = carportShed.beklaedningSkruer(210);
        PartLine small = findPart("4,5x50 mm. skruer", parts);
        PartLine big = findPart("4,5x70 mm. skruer", parts);
        int expAmountS = 2;
        int amountS = small.getAmount();
        int expAmountB = 2;
        int amountB = big.getAmount();
        assertEquals(expAmountS, amountS);
        assertEquals(expAmountB, amountB);
    }

    /**
     * Test of skruerSternOgVandbreat method, of class FlatCarPortList.
     */
    @Test
    public void testSkruerSternOgVandbreat() throws Exception
    {
        int expRes = 1;
        int res = carport.skruerSternOgVandbreat(17).getAmount();
        assertEquals(expRes, res);
    }

    /**
     * Test of oversternEnder method, of class FlatCarPortList.
     */
    @Test
    public void testOversternEnder() throws Exception
    {
        PartLine part = carport.oversternEnder();
        int expAmount = 2;
        int expSize = 600;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of understernEnder method, of class FlatCarPortList.
     */
    @Test
    public void testUndersternEnder() throws Exception
    {
        PartLine part = carport.understernEnder();
        int expAmount = 2;
        int expSize = 600;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of oversternSider method, of class FlatCarPortList.
     */
    @Test
    public void testOversternSider() throws Exception
    {
        PartLine part = carport.oversternSider();
        int expAmount = 4;
        int expSize = 420;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of understernSider method, of class FlatCarPortList.
     */
    @Test
    public void testUndersternSider() throws Exception
    {
        PartLine part = carport.understernSider();
        int expAmount = 4;
        int expSize = 420;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of vandbraetEnde method, of class FlatCarPortList.
     */
    @Test
    public void testVandbraetEnde() throws Exception
    {
        PartLine part = carport.vandbraetEnde();
        int expAmount = 4;
        int expSize = 300;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of vandbraetSide method, of class FlatCarPortList.
     */
    @Test
    public void testVandbraetSide() throws Exception
    {
        PartLine part = carport.vandbraetSide();
        int expAmount = 4;
        int expSize = 420;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of remCarport method, of class FlatCarPortList.
     */
    @Test
    public void testRemCarport() throws Exception
    {
        PartLine part = carport.remCarport();
        int expAmount = 4;
        int expSize = 420;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of stolperOgBolt method, of class FlatCarPortList.
     */
    @Test
    public void testStolperOgBolt() throws Exception
    {
        ArrayList<PartLine> parts = carport.stolperOgBolt();
        PartLine part = findPart("stolpe", parts);
        int expAmount = 6;
        int expSize = 300;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of braeddeboltOgFirkantskive method, of class FlatCarPortList.
     */
    @Test
    public void testBraeddeboltOgFirkantskive() throws Exception
    {
        ArrayList<PartLine> parts = carport.braeddeboltOgFirkantskive(6);
        PartLine braeddebolt = findPart("Bræddebolte 10x120 mm.", parts);
        PartLine firkantSkive = findPart("Firkantskiver 40x40x11 mm.", parts);
        int expAmountB = 12;
        int amountB = braeddebolt.getAmount();
        int expAmountF = 12;
        int amountF = firkantSkive.getAmount();
        assertEquals(expAmountB, amountB);
        assertEquals(expAmountF, amountF);
    }

    /**
     * Test of beslagSkruer method, of class FlatCarPortList.
     */
    @Test
    public void testBeslagSkruer() throws Exception
    {
        PartLine part = carport.beslagSkruer(30, 2);
        int expAmount = 3;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of spaer method, of class FlatCarPortList.
     */
    @Test
    public void testSpaer() throws Exception
    {
        PartLine part = carport.spaer();
        int expAmount = 15;
        int expSize = 600;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of universalBeslagH method, of class FlatCarPortList.
     */
    @Test
    public void testUniversalBeslagH() throws Exception
    {
        PartLine part = carport.universalBeslagH(15);
        int expAmount = 15;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of universalBeslagV method, of class FlatCarPortList.
     */
    @Test
    public void testUniversalBeslagV() throws Exception
    {
        PartLine part = carport.universalBeslagV(15);
        int expAmount = 15;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of hulbaand method, of class FlatCarPortList.
     */
    @Test
    public void testHulbaand() throws Exception
    {
        PartLine part = carport.hulbaand();
        int expAmount = 2;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of tagplade method, of class FlatCarPortList.
     */
    @Test
    public void testTagplade() throws Exception
    {
        ArrayList<PartLine> parts = carport.tagplade();
        PartLine tagplade = findPart("tagplade", parts);
        int expAmount = 14;
        int expSize = 420;
        int amount = tagplade.getAmount();
        int size = tagplade.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of plastmoBundskruer method, of class FlatCarPortList.
     */
    @Test
    public void testPlastmoBundskruer() throws Exception
    {
        PartLine part = carport.plastmoBundskruer();
        int expAmount = 3;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of vinkelbeslag method, of class FlatCarPortList.
     */
    @Test
    public void testVinkelbeslag() throws Exception
    {
        PartLine part = carport.vinkelbeslag(16);
        int expAmount = 32;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of beklaedning method, of class FlatCarPortList.
     */
    @Test
    public void testBeklaedning() throws Exception
    {
        ArrayList<PartLine> parts = carport.beklaedning();
        PartLine part = findPart("beklædning", parts);
        int expAmount = 0;
        int expSize = 210;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of beklaedningSkruer method, of class FlatCarPortList.
     */
    @Test
    public void testBeklaedningSkruer() throws Exception
    {
        ArrayList<PartLine> parts = carport.beklaedningSkruer(210);
        PartLine small = findPart("4,5x50 mm. skruer", parts);
        PartLine big = findPart("4,5x70 mm. skruer", parts);
        int expAmountS = 2;
        int amountS = small.getAmount();
        int expAmountB = 2;
        int amountB = big.getAmount();
        assertEquals(expAmountS, amountS);
        assertEquals(expAmountB, amountB);
    }

    private static PartLine findPart(String name, ArrayList<PartLine> parts)
    {
        for (PartLine part : parts)
        {
            if (part.getMaterial().getName().equals(name))
            {
                return part;
            }
        }
        return null;
    }

}
