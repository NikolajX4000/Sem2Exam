package functionLayer;

import java.util.*;
import storageLayer.MaterialMapper;

/**
 *
 * @author super
 */
public class CarPortList
{

    Order order;
    int size = 1;
    int tmp;
    int best = Integer.MAX_VALUE;
    int length;
    int width;

    List<PartLine> list = new ArrayList<>();
    private HashMap<String, Material> materials;

    /**
     *
     * @param order
     * @throws functionLayer.CustomException
     */
    public CarPortList(Order order) throws CustomException
    {
        this.order = order;
        length = order.getLength();
        width = order.getWidth();
        addParts();
    }

    private PartLine oversternEnder() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("overstern");
        List<Integer> sizes = collectSizes(material);
        size = findSize(width, sizes);
        int amount = (int) Math.ceil(width / size);
        if (!order.hasShed())
        {
            amount *= 2;
        }
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private PartLine understernEnder() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("understern");
        List<Integer> sizes = collectSizes(material);
        size = findSize(width, sizes);
        int amount = (int) Math.ceil(width / size) * 2;
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private PartLine oversternSider() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("overstern");
        List<Integer> sizes = collectSizes(material);
        size = findSize(width, sizes);
        int amount = (int) Math.ceil(length / size) * 2;
        return new PartLine(material.get(1), amount).setSize(size);

    }

    private PartLine understernSider() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("understern");
        List<Integer> sizes = collectSizes(material);
        size = findSize(length, sizes);
        int amount = (int) Math.ceil(length / size) * 2;
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private PartLine remCarport() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("rem");
        List<Integer> sizes = collectSizes(material);
        int carportLength = length - order.getShedLength();
        size = findSize(carportLength, sizes);
        int amount = (int) Math.ceil(carportLength / size) * 2;
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private ArrayList<PartLine> spaer() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("spær");
        ArrayList<PartLine> parts = new ArrayList<>();
        List<Integer> sizes = collectSizes(material);
        int amount = 0;
        if (!order.isFlat())
        {
            throw new UnsupportedOperationException("not implemented yet");
        } else
        {
            size = findSize(width, sizes);
            amount = (int) Math.ceil(length / 55) + 1;
        }
        parts.add(new PartLine(material.get(1), amount).setSize(size));
        parts.add(universalBeslagH(amount));
        parts.add(universalBeslagV(amount));
        return parts;
    }

    private PartLine universalBeslagH(int amount) throws CustomException
    {
        Material material = MaterialMapper.getTool(3);
        return new PartLine(material.getName(), amount, material.getUnitSize());
    }

    private PartLine universalBeslagV(int amount) throws CustomException
    {
        Material material = MaterialMapper.getTool(4);
        return new PartLine(material.getName(), amount, material.getUnitSize());
    }

    private PartLine stolper() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("stolpe");
        int amount;
        if (!order.hasShed())
        {
            amount = 4;
            if (length - 200 >= 400)
            {
                amount += 2;
            }
        } else
        {
            amount = 5;
            if (length - order.getShedLength() >= 300)
            {
                amount += 2;
                if (length - order.getShedLength() >= 500)
                {
                    amount += 2;
                }
            }
            if (order.getShedLength() == length - 30)
            {
                amount += 4;
            } else if (order.getShedLength() >= 300)
            {
                amount += 6;
            } else
            {
                amount += 3;
            }
        }
        return new PartLine(material.get(1), amount).setSize(210);
    }

    private PartLine vandbraetEnde() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("vandbræt");
        List<Integer> sizes = collectSizes(material);
        size = findSize(width, sizes);
        int amount = (int) Math.ceil(width / size);
        if (!order.hasShed())
        {
            amount *= 2;
        }
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private PartLine vandbraetSide() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("vandbræt");
        List<Integer> sizes = collectSizes(material);
        size = findSize(length, sizes);
        int amount = (int) Math.ceil(length / size) * 2;
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private List<PartLine> tagplade() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("tagplade");
        List<Integer> sizes = collectSizes(material);
        List<PartLine> parts = new ArrayList<>();
        size = findSize(length, sizes);
        int amount = (int) Math.ceil(width / 89);// 20cm overlap
        amount *= (int) Math.ceil(length / size);
        parts.add(plastmoBundskruer());
        parts.add(new PartLine(material.get(1), amount).setSize(size));
        return parts;
    }

    private PartLine plastmoBundskruer() throws CustomException
    {
        Material material = MaterialMapper.getTool(1);
        size = (length / 100) * (width / 100);
        int amount = size * 24;
        return new PartLine(material.getName(), amount, material.getUnitSize());
    }

    private ArrayList<PartLine> loesholter() throws CustomException
    {
        ArrayList<PartLine> parts = new ArrayList<>();
        parts.add(loesholterGavl());
        parts.add(loesholterSider());
        parts.add(vinkelbeslag(parts.get(1).amount + parts.get(2).amount));

        return parts;
    }

    private PartLine loesholterGavl() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("løsholte");
        List<Integer> sizes = collectSizes(material);
        size = findSize(order.getShedWidth() / 2, sizes);
        int amount = (int) Math.ceil(order.getShedWidth() / size) * 6;//3 i højden i begge sider
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private PartLine loesholterSider() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("løsholte");
        List<Integer> sizes = collectSizes(material);
        size = findSize(order.getShedLength() / 2, sizes);
        int amount = (int) Math.ceil(order.getShedLength() / size) * 4;//2 i højden i begge sider
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private PartLine vinkelbeslag(int amount) throws CustomException
    {
        Material material = MaterialMapper.getTool(13);
        return new PartLine(material.getName(), amount * 2, material.getUnitSize());
    }

    private PartLine remSkur() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("rem");
        List<Integer> sizes = collectSizes(material);
        size = findSize(order.getShedLength(), sizes);
        int amount = (int) Math.ceil(order.getShedLength() / size) * 2;
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private ArrayList<PartLine> beklaedning() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("beklædning");
        ArrayList<PartLine> parts = new ArrayList<>();
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

        parts.add(new PartLine(material.get(1), amount).setSize(210));
        parts.addAll(beklaedningSkruer(amount));

        return parts;
    }

    private ArrayList<PartLine> beklaedningSkruer(int amount) throws CustomException
    {
        ArrayList<PartLine> parts = new ArrayList<>();
        Material material = MaterialMapper.getTool(9);
        parts.add(new PartLine(material.getName(), amount * 3, material.getUnitSize()));
        material = MaterialMapper.getTool(10);
        parts.add(new PartLine(material.getName(), amount / 2 * 3, material.getUnitSize()));
        return parts;
    }

    private PartLine hulbånd() throws CustomException
    {
        Material material = MaterialMapper.getTool(2);
        return new PartLine(material.getName(), 2, material.getUnitSize());
    }

    private ArrayList<PartLine> skruer()
    {
        ArrayList<PartLine> parts = new ArrayList<>();
        parts.add(new PartLine("4,5 x 60 mm. skruer", 200, 200));//not final
        parts.add(new PartLine("4,0 x 50 mm. skruer", 750, 250));//not final

        return parts;
    }

    private PartLine stalddørsgreb() throws CustomException
    {
        Material material = MaterialMapper.getTool(11);
        return new PartLine(material.getName(), 1, material.getUnitSize());
    }

    private PartLine tHængsel() throws CustomException
    {
        Material material = MaterialMapper.getTool(12);
        return new PartLine(material.getName(), 2, material.getUnitSize());
    }

    private int findSize(int length, List<Integer> sizes)
    {
        best = Integer.MAX_VALUE;
        size = 0;

        for (int i = sizes.size(); i > 0; i--)
        {
            tmp = length % sizes.get(i);
            if (tmp < best)
            {
                size = sizes.get(i);
                best = tmp;
            }
        }
        return size;
    }

    private List<Integer> collectSizes(List<Material> list)
    {
        List<Integer> sizes = new ArrayList<>();
        for (Material material : list)
        {
            sizes.add(material.getSize());
        }
        return sizes;
    }

    private void addParts() throws CustomException
    {
        list.add(oversternEnder());
        list.add(understernEnder());
        list.add(oversternSider());
        list.add(understernSider());
        list.add(remCarport());
        list.addAll(spaer());
        list.add(stolper());
        list.add(vandbraetEnde());
        list.add(vandbraetSide());
        list.addAll(tagplade());
        list.add(hulbånd());

        if (order.hasShed())
        {
            list.addAll(loesholter());
            list.add(remSkur());
            list.addAll(beklaedning());
            list.add(stalddørsgreb());
            list.add(tHængsel());
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
