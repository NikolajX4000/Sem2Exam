package functionLayer;

import java.util.*;

public class CarPortList
{

    Order order;
    int[] sizes;
    int size = 1;
    int tmp;
    int best = Integer.MAX_VALUE;
    int length;
    int width;
    
    List list = new ArrayList();

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
        return new PartLine("25x125mm. trykimp. Brædt", amount, 1).setSize(size);
    }

    private PartLine understernEnder()
    {
        size = findSize(width);
        int amount = (int) Math.ceil(width / size) * 2;
        return new PartLine("25x200mm. trykimp. Brædt", amount, 1).setSize(size);
    }

    private PartLine oversternSider()
    {
        size = findSize(length);
        int amount = (int) Math.ceil(length / size) * 2;
        return new PartLine("25x125mm. trykimp. Brædt", amount, 1).setSize(size);

    }

    private PartLine understernSider()
    {
        size = findSize(length);
        int amount = (int) Math.ceil(length / size) * 2;
        return new PartLine("25x200mm. trykimp. Brædt", amount, 1).setSize(size);
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
        return new PartLine("19x100 mm. trykimp. Brædt", amount, 1).setSize(size);
    }

    private PartLine vandbraetSide()
    {
        size = findSize(width);
        int amount = (int) Math.ceil(width / size) * 2;
        return new PartLine("19x100 mm. trykimp. Brædt", amount, 1).setSize(size);
    }

    private PartLine tagplade()
    {
        size = findSize(length);
        int amount = (int) Math.ceil(width / 100);// 9cm overlap
        amount *= (int) Math.ceil(length / size);
        return new PartLine("Plastmo Ecolite blåtonet", amount, 1).setSize(size);
    }

    private PartLine løsholterGavl()
    {
        size = findSize(order.getShedWidth() / 2);
        int amount = (int) Math.ceil(order.getShedWidth() / size) * 6;//3 i højden i begge sider
        return new PartLine("45x95 mm. Reglar ub.", amount, 1).setSize(size);
    }

    private PartLine løsholterSider()
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
        throw new UnsupportedOperationException("not implemented yet");
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
    
    private void addParts() {
        list.add( oversternEnder() );
        list.add( understernEnder() );
        list.add( oversternSider() );
        list.add( understernSider() );
        list.add( remCarport() );
        list.add( spaer() );
        list.add( stolper() );
        list.add( vandbraetEnde() );
        list.add( vandbraetSide() );
        list.add( tagplade() );

        if ( order.hasShed() ) {
            list.add( løsholterGavl() );
            list.add( løsholterSider() );
            list.add( remSkur() );
            list.add( beklaedning() );
        }
    }
    
    public List getParts() {
        return list;
    }
}
