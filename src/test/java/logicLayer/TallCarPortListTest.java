/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLayer;

import logicLayer.Order;
import logicLayer.PartLine;
import logicLayer.CustomException;
import logicLayer.TallCarPortList;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jacob Borg
 */
public class TallCarPortListTest
{

    TallCarPortList carport;

    public TallCarPortListTest() throws CustomException
    {
        Order order = new Order();
        order.
                setWidth(360).
                setLength(730).
                setAngle(20).
                setShedWidth(320).
                setShedLength(225).
                setRoof(7);
        carport = new TallCarPortList(order);
    }

    /**
     * Test of vindskede method, of class TallCarPortList.
     */
    @Test
    public void testVindskede() throws CustomException
    {
        PartLine part = carport.vindskede();
        int expAmount = 2;
        int expSize = 420;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of SternCarport method, of class TallCarPortList.
     */
    @Test
    public void testSternSiderCarport() throws CustomException
    {
        PartLine part = carport.SternCarport();
        int expAmount = 2;
        int expSize = 540;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of SternSkur method, of class TallCarPortList.
     */
    @Test
    public void testSternSiderSkur() throws CustomException
    {
        PartLine part = carport.SternSkur();
        int expAmount = 2;
        int expSize = 300;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of spear method, of class TallCarPortList.
     */
    @Test
    public void testSpear() throws CustomException
    {
        PartLine part = carport.spear();
        int expAmount = 8;
        int expSize = 420;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of remCarport method, of class TallCarPortList.
     */
    @Test
    public void testRemCarport() throws CustomException
    {
        PartLine part = carport.remCarport();
        int expAmount = 2;
        int expSize = 480;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of remSkur method, of class TallCarPortList.
     */
    @Test
    public void testRemSkur() throws CustomException
    {
        PartLine part = carport.remSkur();
        int expAmount = 2;
        int expSize = 300;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of loesholterSider method, of class TallCarPortList.
     */
    @Test
    public void testLoesholterSider() throws CustomException
    {
        PartLine part = carport.loesholterSider();
        int expAmount = 4;
        int expSize = 240;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of loesholterGavle method, of class TallCarPortList.
     */
    @Test
    public void testLoesholterGavle() throws CustomException
    {
        PartLine part = carport.loesholterGavle();
        int expAmount = 6;
        int expSize = 330;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of vandbraet method, of class TallCarPortList.
     */
    @Test
    public void testVandbaet() throws CustomException
    {
        PartLine part = carport.vandbraet();
        int expAmount = 2;
        int expSize = 420;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of beklaedningGavle method, of class TallCarPortList.
     */
    @Test
    public void testBeklaedningGavle() throws CustomException
    {
        PartLine part = carport.beklaedningGavle();
        int expAmount = 45;
        int expSize = 210;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of beklaedningSkur method, of class TallCarPortList.
     */
    @Test
    public void testBeklaedningSkur() throws CustomException
    {
        PartLine part = carport.beklaedningSkur();
        int expAmount = 138;
        int expSize = 210;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of ovenPaaTagfodslaegte method, of class TallCarPortList.
     */
    @Test
    public void testOvenPaaTagfodslaegte() throws CustomException
    {
        PartLine part = carport.ovenPaaTagfodslaegte();
        int expAmount = 4;
        int expSize = 390;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of zTilDoer method, of class TallCarPortList.
     */
    @Test
    public void testZTilDoer() throws CustomException
    {
        PartLine part = carport.zTilDoer();
        int expAmount = 1;
        int expSize = 540;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of tagLaegte method, of class TallCarPortList.
     */
    @Test
    public void testTagLaegte() throws CustomException
    {
        PartLine part = carport.tagLaegte();
        int expAmount = 15;
        int expSize = 420;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of topLaegte method, of class TallCarPortList.
     */
    @Test
    public void testTopLaegte() throws CustomException
    {
        PartLine part = carport.topLaegte();
        int expAmount = 2;
        int expSize = 420;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }

    /**
     * Test of tagsten method, of class TallCarPortList.
     */
    @Test
    public void testTagsten() throws CustomException
    {
        PartLine part = carport.tagsten();
        int expAmount = 300;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of rygsten method, of class TallCarPortList.
     */
    @Test
    public void testRygsten() throws CustomException
    {
        PartLine part = carport.rygsten();
        int expAmount = 21;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of toplaegteHolder method, of class TallCarPortList.
     */
    @Test
    public void testToplaegteHolder() throws CustomException
    {
        PartLine part = carport.toplaegteHolder(8);
        int expAmount = 8;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of rygstensbeslag method, of class TallCarPortList.
     */
    @Test
    public void testRygstensbeslag() throws CustomException
    {
        PartLine part = carport.rygstensbeslag(21);
        int expAmount = 21;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of tagstensBindereOgNakkekroge method, of class TallCarPortList.
     */
    @Test
    public void testTagstensBindereOgNakkekroge() throws CustomException
    {
        PartLine part = carport.tagstensBindereOgNakkekroge(300);
        int expAmount = 2;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of universalH method, of class TallCarPortList.
     */
    @Test
    public void testUniversalH() throws CustomException
    {
        PartLine part = carport.universalH(8);
        int expAmount = 8;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of universalV method, of class TallCarPortList.
     */
    @Test
    public void testUniversalV() throws CustomException
    {
        PartLine part = carport.universalV(8);
        int expAmount = 8;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of Stalddoersgreb method, of class TallCarPortList.
     */
    @Test
    public void testStalddoersgreb() throws CustomException
    {
        PartLine part = carport.Stalddoersgreb();
        int expAmount = 1;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of tHaengsel method, of class TallCarPortList.
     */
    @Test
    public void testTHaengsel() throws CustomException
    {
        PartLine part = carport.tHaengsel();
        int expAmount = 2;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of vinkelbeslag method, of class TallCarPortList.
     */
    @Test
    public void testVinkelbeslag() throws CustomException
    {
        PartLine part = carport.vinkelbeslag(10);
        int expAmount = 20;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of skruerSternVindskedeVandbraet method, of class TallCarPortList.
     */
    @Test
    public void testSkruerSternVindskedeVandbraet() throws CustomException
    {
        PartLine part = carport.skruerSternVindskedeVandbraet(7);
        int expAmount = 1;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of skruerUniverslbeslagToplaegte method, of class TallCarPortList.
     */
    @Test
    public void testSkruerUniverslbeslagToplaegte() throws CustomException
    {
        PartLine part = carport.skruerUniverslbeslagToplaegte(16, 8);
        int expAmount = 1;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of skruertaglaegter method, of class TallCarPortList.
     */
    @Test
    public void testSkruertaglaegter() throws CustomException
    {
        PartLine part = carport.skruertaglaegter(21);
        int expAmount = 3;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of braeddebolt method, of class TallCarPortList.
     */
    @Test
    public void testBraeddebolt() throws CustomException
    {
        PartLine part = carport.braeddebolt(8);
        int expAmount = 20;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of firkantskiver method, of class TallCarPortList.
     */
    @Test
    public void testFirkantskiver() throws CustomException
    {
        PartLine part = carport.firkantskiver(8);
        int expAmount = 20;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of skruerYderstBeklaedning method, of class TallCarPortList.
     */
    @Test
    public void testSkruerYderstBeklaedning() throws CustomException
    {
        PartLine part = carport.skruerYderstBeklaedning(183);
        int expAmount = 3;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of skruerInderstBeklaedning method, of class TallCarPortList.
     */
    @Test
    public void testSkruerInderstBeklaedning() throws CustomException
    {
        PartLine part = carport.skruerInderstBeklaedning(183);
        int expAmount = 2;
        int amount = part.getAmount();
        assertEquals(expAmount, amount);
    }

    /**
     * Test of stolperOgBolt method, of class TallCarPortList.
     */
    @Test
    public void testStolper() throws Exception
    {
        ArrayList<PartLine> parts = carport.stolperOgBolt();
        PartLine part = findPart("stolpe", parts);
        int expAmount = 12;
        int expSize = 300;
        int amount = part.getAmount();
        int size = part.getSize();
        assertEquals(expAmount, amount);
        assertEquals(expSize, size);
    }
    
    private static PartLine findPart(String name, ArrayList<PartLine> parts) {
        for(PartLine part : parts) {
            if(part.getMaterial().getName().equals(name)) {
                return part;
            }
        }
        return null;
    }

}
