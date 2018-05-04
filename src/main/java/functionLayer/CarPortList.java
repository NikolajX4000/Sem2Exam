package functionLayer;

import java.util.*;

/**
 *
 * @author super
 */
public class CarPortList
{

    Order order;
    int[] sizes;
    int size = 1;
    int tmp;
    int best = Integer.MAX_VALUE;
    int length;
    int width;

    List<PartLine> list = new ArrayList<>();

    /**
     *
     * @param order
     * @param sizes
     */
    public CarPortList(Order order, int[] sizes)
    {
        this.order = order;
        this.sizes = sizes;
        length = order.getLength();
        width = order.getWidth();
        addParts();
    }

    private PartLine oversternEnder()
    {
        size = findSize(width);
        int amount = (int) Math.ceil(width / size);
        if (!order.hasShed())
        {
            amount *= 2;
        }
        return new PartLine("25x125mm. trykimp. Bræt", amount, 1).setSize(size);
    }

    private PartLine understernEnder()
    {
        size = findSize(width);
        int amount = (int) Math.ceil(width / size) * 2;
        return new PartLine("25x200mm. trykimp. Bræt", amount, 1).setSize(size);
    }

    private PartLine oversternSider()
    {
        size = findSize(length);
        int amount = (int) Math.ceil(length / size) * 2;
        return new PartLine("25x125mm. trykimp. Bræt", amount, 1).setSize(size);

    }

    private PartLine understernSider()
    {
        size = findSize(length);
        int amount = (int) Math.ceil(length / size) * 2;
        return new PartLine("25x200mm. trykimp. Bræt", amount, 1).setSize(size);
    }

    private PartLine remCarport()
    {
        int carportLength = length - order.getShedLength();
        size = findSize(carportLength);
        int amount = (int) Math.ceil(carportLength / size) * 2;
        return new PartLine("45x195 mm. spærtræ ubh.", amount, 1).setSize(size);
    }

    private PartLine spaer()
    {
        int amount = 0;
        if (!order.isFlat())
        {
            throw new UnsupportedOperationException("not implemented yet");
        } else
        {
            size = findSize(width);
            amount = (int) Math.ceil(length / 55) + 1;
        }
        return new PartLine("45x195 mm. spærtræ ubh.", amount, 1).setSize(size);
    }

    private PartLine stolper()
    {
        int amount = 6;
        if (order.hasShed())
        {
            amount += 5;
        }
        return new PartLine("97x97 mm. trykimp. Stolpe", amount, 1).setSize(210);
    }

    private PartLine vandbraetEnde()
    {
        size = findSize(width);
        int amount = (int) Math.ceil(width / size);
        if (!order.hasShed())
        {
            amount *= 2;
        }
        return new PartLine("19x100 mm. trykimp. Bræt", amount, 1).setSize(size);
    }

    private PartLine vandbraetSide()
    {
        size = findSize(width);
        int amount = (int) Math.ceil(width / size) * 2;
        return new PartLine("19x100 mm. trykimp. Bræt", amount, 1).setSize(size);
    }

    private PartLine tagplade()
    {
        size = findSize(length);
        int amount = (int) Math.ceil(width / 89);// 20cm overlap
        amount *= (int) Math.ceil(length / size);
            return new PartLine("Plastmo Ecolite blåtonet", amount, 1).setSize(size);
    }

    private PartLine loesholterGavl()
    {
        size = findSize(order.getShedWidth() / 2);
        int amount = (int) Math.ceil(order.getShedWidth() / size) * 6;//3 i højden i begge sider
        return new PartLine("45x95 mm. Reglar ub.", amount, 1).setSize(size);
    }

    private PartLine loesholterSider()
    {
        size = findSize(order.getShedLength() / 2);
        int amount = (int) Math.ceil(order.getShedLength() / size) * 4;//2 i højden i begge sider
        return new PartLine("45x95 mm. Reglar ub.", amount, 1).setSize(size);
    }

    private PartLine remSkur()
    {
        size = findSize(order.getShedLength());
        int amount = (int) Math.ceil(order.getShedLength() / size) * 2;
        return new PartLine("45x195 mm. spærtræ ubh.", amount, 1).setSize(size);
    }

    private PartLine beklaedning()
    {
        //6cm mellemrum
        //10cm bred
        int amount = 0;
        //inderst sider
        amount += (int) Math.ceil(order.getShedLength() / 16) * 2;
        //inderst ender
        amount += (int) Math.ceil(order.getShedWidth() / 16) * 2;
        //yderst sider
        amount += (int) Math.ceil((order.getShedLength() - 8) / 16) * 2;
        //yderst ender
        amount += (int) Math.ceil((order.getShedWidth() - 8) / 16) * 2;

        return new PartLine("19x100 mm. trykimp. Bræt", amount, 1).setSize(210);
    }

    private PartLine plastmoBundskruer()
    {
        throw new UnsupportedOperationException("not yet supported");
    }

    private PartLine hulbånd()
    {
        return new PartLine("hulbånd 1x20mm 10mtr", 2, 1);
    }

    private PartLine universalBeslagH()
    {
        int amount = 0;
        if (!order.isFlat())
        {
            throw new UnsupportedOperationException("not implemented yet");
        } else
        {
            amount = (int) Math.ceil(length / 55) + 1;
        }
        return new PartLine("universal 190mm højre", amount, 1);
    }

    private PartLine universalBeslagV()
    {
        int amount = 0;
        if (!order.isFlat())
        {
            throw new UnsupportedOperationException("not implemented yet");
        } else
        {
            amount = (int) Math.ceil(length / 55) + 1;
        }
        return new PartLine("universal 190mm venstre", amount, 1);
    }

    private ArrayList<PartLine> skruer()
    {
        ArrayList<PartLine> parts = new ArrayList<>();
        int amount = 0;
        //inderst sider
        amount += (int) Math.ceil(order.getShedLength() / 16) * 2;
        //inderst ender
        amount += (int) Math.ceil(order.getShedWidth() / 16) * 2;
        //yderst sider
        amount += (int) Math.ceil((order.getShedLength() - 8) / 16) * 2;
        //yderst ender
        amount += (int) Math.ceil((order.getShedWidth() - 8) / 16) * 2;
        parts.add(new PartLine("4,5 x 70mm. Skruer", amount * 3, 400));
        parts.add(new PartLine("4,5 x 50mm. Skruer", amount / 2 * 3, 300));
        
//        4,5 x 60 mm. skruer 200 stk.          mangler
//        4,0 x 50 mm. beslagskruer 250 stk.    mangler
        
        return parts;
    }

    private PartLine stalddørsgreb()
    {
        return new PartLine("stalddørsgreb 50 x 75", 1, 1);
    }

    private PartLine tHængsel()
    {
        return new PartLine("t hængsel 190mm", 1, 1);
    }

    private PartLine vinkelbeslag()
    {
        int amount = (int) Math.ceil(order.getShedLength() / size) * 4;//2 i højden i begge sider
        amount += (int) Math.ceil(order.getShedWidth() / size) * 6;//3 i højden i begge sider

        return new PartLine("vinkelbeslag 35", amount, 1);
    }

    private int findSize(int length)
    {
        best = Integer.MAX_VALUE;
        size = 0;

        for (int i = sizes.length; i > 0; i--)
        {
            tmp = length % sizes[i];
            if (tmp < best)
            {
                size = sizes[i];
                best = tmp;
            }
        }
        return size;
    }

    private void addParts()
    {
        list.add(oversternEnder());
        list.add(understernEnder());
        list.add(oversternSider());
        list.add(understernSider());
        list.add(remCarport());
        list.add(spaer());
        list.add(stolper());
        list.add(vandbraetEnde());
        list.add(vandbraetSide());
        list.add(tagplade());

        if (order.hasShed())
        {
            list.add(loesholterGavl());
            list.add(loesholterSider());
            list.add(remSkur());
            list.add(beklaedning());
        }
    }

    /**
     *
     * @return
     */
    public List<PartLine> getParts()
    {
        return list;
    }
}
