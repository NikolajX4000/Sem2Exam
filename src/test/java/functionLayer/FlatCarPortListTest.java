///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package functionLayer;
//
//import java.util.ArrayList;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Jacob Borg
// */
//public class FlatCarPortListTest
//{
//
//    FlatCarPortList carport = null;
//
//    public FlatCarPortListTest() throws CustomException
//    {
//        Order order = new Order();
//        order.
//                setWidth(600).
//                setLength(780).
//                setShedWidth(530).
//                setShedLength(210);
//        carport = new FlatCarPortList(order);
//    }
//
//    /**
//     * Test of skruerSternOgVandbreat method, of class FlatCarPortList.
//     */
//    @Test
//    public void testSkruerSternOgVandbreat() throws Exception
//    {
//        int expRes = 1;
//        int res = carport.skruerSternOgVandbreat(17).getAmount();
//        assertEquals(expRes, res);
//    }
//
//    /**
//     * Test of oversternEnder method, of class FlatCarPortList.
//     */
//    @Test
//    public void testOversternEnder() throws Exception
//    {
//        PartLine part = carport.oversternEnder();
//        int expAmount = 1;
//        int expSize = 600;
//        int amount = part.getAmount();
//        int size = part.getSize();
//        assertEquals(expAmount, amount);
//        assertEquals(expSize, size);
//    }
//
//    /**
//     * Test of understernEnder method, of class FlatCarPortList.
//     */
//    @Test
//    public void testUndersternEnder() throws Exception
//    {
//        PartLine part = carport.understernEnder();
//        int expAmount = 2;
//        int expSize = 600;
//        int amount = part.getAmount();
//        int size = part.getSize();
//        assertEquals(expAmount, amount);
//        assertEquals(expSize, size);
//    }
//
//    /**
//     * Test of oversternSider method, of class FlatCarPortList.
//     */
//    @Test
//    public void testOversternSider() throws Exception
//    {
//        PartLine part = carport.oversternSider();
//        int expAmount = 4;
//        int expSize = 420;
//        int amount = part.getAmount();
//        int size = part.getSize();
//        assertEquals(expAmount, amount);
//        assertEquals(expSize, size);
//    }
//
//    /**
//     * Test of understernSider method, of class FlatCarPortList.
//     */
//    @Test
//    public void testUndersternSider() throws Exception
//    {
//        PartLine part = carport.understernSider();
//        int expAmount = 4;
//        int expSize = 420;
//        int amount = part.getAmount();
//        int size = part.getSize();
//        assertEquals(expAmount, amount);
//        assertEquals(expSize, size);
//    }
//
//    /**
//     * Test of vandbraetEnde method, of class FlatCarPortList.
//     */
//    @Test
//    public void testVandbraetEnde() throws Exception
//    {
//        PartLine part = carport.vandbraetEnde();
//        int expAmount = 2;
//        int expSize = 300;
//        int amount = part.getAmount();
//        int size = part.getSize();
//        assertEquals(expAmount, amount);
//        assertEquals(expSize, size);
//    }
//
//    /**
//     * Test of vandbraetSide method, of class FlatCarPortList.
//     */
//    @Test
//    public void testVandbraetSide() throws Exception
//    {
//        PartLine part = carport.vandbraetSide();
//        int expAmount = 4;
//        int expSize = 420;
//        int amount = part.getAmount();
//        int size = part.getSize();
//        assertEquals(expAmount, amount);
//        assertEquals(expSize, size);
//    }
//
//    /**
//     * Test of remCarport method, of class FlatCarPortList.
//     */
//    @Test
//    public void testRemCarport() throws Exception
//    {
//        PartLine part = carport.remCarport();
//        int expAmount = 2;
//        int expSize = 600;
//        int amount = part.getAmount();
//        int size = part.getSize();
//        assertEquals(expAmount, amount);
//        assertEquals(expSize, size);
//    }
//
//    /**
//     * Test of stolperOgBolt method, of class FlatCarPortList.
//     */
//    @Test
//    public void testStolperOgBolt() throws Exception
//    {
//        ArrayList<PartLine> parts = carport.stolperOgBolt();
//        PartLine part = findPart("stolpe", parts);
//        int expAmount = 11;
//        int expSize = 210;
//        int amount = part.getAmount();
//        int size = part.getSize();
//        assertEquals(expAmount, amount);
//        assertEquals(expSize, size);
//    }
//
//    /**
//     * Test of braeddeboltOgFirkantskive method, of class FlatCarPortList.
//     */
//    @Test
//    public void testBraeddeboltOgFirkantskive() throws Exception
//    {
//        ArrayList<PartLine> parts = carport.braeddeboltOgFirkantskive(12);
//        PartLine braeddebolt = findPart("Bræddebolte 10x120 mm.", parts);
//        PartLine firkantSkive = findPart("Firkantskiver 40x40x11 mm.", parts);
//        int expAmountB = 26;
//        int amountB = braeddebolt.getAmount();
//        int expAmountF = 26;
//        int amountF = firkantSkive.getAmount();
//        assertEquals(expAmountB, amountB);
//        assertEquals(expAmountF, amountF);
//    }
//
//    /**
//     * Test of beslagSkruer method, of class FlatCarPortList.
//     */
//    @Test
//    public void testBeslagSkruer() throws Exception
//    {
//        PartLine part = carport.beslagSkruer(30, 2);
//        int expAmount = 3;
//        int amount = part.getAmount();
//        assertEquals(expAmount, amount);
//    }
//
//    /**
//     * Test of spaer method, of class FlatCarPortList.
//     */
//    @Test
//    public void testSpaer() throws Exception
//    {
//        PartLine part = carport.spaer();
//        int expAmount = 15;
//        int expSize = 600;
//        int amount = part.getAmount();
//        int size = part.getSize();
//        assertEquals(expAmount, amount);
//        assertEquals(expSize, size);
//    }
//
//    /**
//     * Test of universalBeslagH method, of class FlatCarPortList.
//     */
//    @Test
//    public void testUniversalBeslagH() throws Exception
//    {
//        PartLine part = carport.universalBeslagH(15);
//        int expAmount = 15;
//        int amount = part.getAmount();
//        assertEquals(expAmount, amount);
//    }
//
//    /**
//     * Test of universalBeslagV method, of class FlatCarPortList.
//     */
//    @Test
//    public void testUniversalBeslagV() throws Exception
//    {
//        PartLine part = carport.universalBeslagV(15);
//        int expAmount = 15;
//        int amount = part.getAmount();
//        assertEquals(expAmount, amount);
//    }
//
//    /**
//     * Test of hulbaand method, of class FlatCarPortList.
//     */
//    @Test
//    public void testHulbaand() throws Exception
//    {
//        PartLine part = carport.hulbaand();
//        int expAmount = 2;
//        int amount = part.getAmount();
//        assertEquals(expAmount, amount);
//    }
//
//    /**
//     * Test of tagplade method, of class FlatCarPortList.
//     */
//    @Test
//    public void testTagplade() throws Exception
//    {
//        ArrayList<PartLine> parts = carport.tagplade();
//        PartLine tagplade = findPart("tagplade", parts);
//        int expAmount = 14;
//        int expSize = 420;
//        int amount = tagplade.getAmount();
//        int size = tagplade.getSize();
//        assertEquals(expAmount, amount);
//        assertEquals(expSize, size);
//    }
//
//    /**
//     * Test of plastmoBundskruer method, of class FlatCarPortList.
//     */
//    @Test
//    public void testPlastmoBundskruer() throws Exception
//    {
//        PartLine part = carport.plastmoBundskruer();
//        int expAmount = 3;
//        int amount = part.getAmount();
//        assertEquals(expAmount, amount);
//    }
//
//    /**
//     * Test of loesholter method, of class FlatCarPortList.
//     */
//    @Test
//    public void testLoesholter() throws Exception
//    {
//        PartLine gavl = carport.loesholterGavl();
//        PartLine side = carport.loesholterSider();
//        int expAmountG = 12;
//        int expSizeG = 270;
//        int amountG = gavl.getAmount();
//        int sizeG = gavl.getSize();
//        int expAmountS = 4;
//        int expSizeS = 240;
//        int amountS = side.getAmount();
//        int sizeS = side.getSize();
//        assertEquals(expAmountG, amountG);
//        assertEquals(expSizeG, sizeG);
//        assertEquals(expAmountS, amountS);
//        assertEquals(expSizeS, sizeS);
//    }
//
//    /**
//     * Test of vinkelbeslag method, of class FlatCarPortList.
//     */
//    @Test
//    public void testVinkelbeslag() throws Exception
//    {
//        PartLine part = carport.vinkelbeslag(16);
//        int expAmount = 32;
//        int amount = part.getAmount();
//        assertEquals(expAmount, amount);
//    }
//
//    /**
//     * Test of remSkur method, of class FlatCarPortList.
//     */
//    @Test
//    public void testRemSkur() throws Exception
//    {
//        PartLine part = carport.remSkur();
//        int expAmount = 2;
//        int expSize = 300;
//        int amount = part.getAmount();
//        int size = part.getSize();
//        assertEquals(expAmount, amount);
//        assertEquals(expSize, size);
//    }
//
//    /**
//     * Test of beklaedning method, of class FlatCarPortList.
//     */
//    @Test
//    public void testBeklaedning() throws Exception
//    {
//        ArrayList<PartLine> parts = carport.beklaedning();
//        PartLine part = findPart("beklædning", parts);
//        int expAmount = 180;
//        int expSize = 210;
//        int amount = part.getAmount();
//        int size = part.getSize();
//        assertEquals(expAmount, amount);
//        assertEquals(expSize, size);
//    }
//
//    /**
//     * Test of beklaedningSkruer method, of class FlatCarPortList.
//     */
//    @Test
//    public void testBeklaedningSkruer() throws Exception
//    {
//        ArrayList<PartLine> parts = carport.beklaedningSkruer(210);
//        PartLine small = findPart("4,5x50 mm. skruer", parts);
//        PartLine big = findPart("4,5x70 mm. skruer", parts);
//        int expAmountS = 2;
//        int amountS = small.getAmount();
//        int expAmountB = 2;
//        int amountB = big.getAmount();
//        assertEquals(expAmountS, amountS);
//        assertEquals(expAmountB, amountB);
//    }
//
//    private static PartLine findPart(String name, ArrayList<PartLine> parts)
//    {
//        for (PartLine part : parts)
//        {
//            if (part.getMaterial().getName().equals(name))
//            {
//                return part;
//            }
//        }
//        return null;
//    }
//
//}
