package functionLayer;

import java.util.*;
import storageLayer.MaterialMapper;
import storageLayer.ToolMapper;

/**
 *
 * @author super
 */
public class FlatCarPortList
{

    Order order;
    double size = 1;
    double length;
    double width;

    List<PartLine> list = new ArrayList<>();
//    private HashMap<String, Material> materials;

    /**
     *
     * @param order
     * @throws functionLayer.CustomException
     */
    public FlatCarPortList(Order order) throws CustomException
    {
        this.order = order;
        length = order.getLength();
        width = order.getWidth();
        addParts();
    }

    private ArrayList<PartLine> sternOgVandbreat() throws CustomException
    {
        ArrayList<PartLine> parts = new ArrayList<>();
        int amount = 0;
        parts.add(oversternEnder());
        parts.add(oversternSider());
        parts.add(understernEnder());
        parts.add(understernSider());
        parts.add(vandbraetEnde());
        parts.add(vandbraetSide());
        for (int i = 0; i < parts.size(); i++)
        {
            amount += parts.get(i).getAmount();
        }
        parts.add(skruerSternOgVandbreat(amount));
        return parts;
    }

    PartLine skruerSternOgVandbreat(int amount) throws CustomException
    {
        Material material = ToolMapper.getTool(5);
        return new PartLine(material, amount * 10);
    }

    PartLine oversternEnder() throws CustomException
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

    PartLine understernEnder() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("understern");
        List<Integer> sizes = collectSizes(material);
        size = findSize(width, sizes);
        int amount = (int) Math.ceil(width / size) * 2;
        return new PartLine(material.get(1), amount).setSize(size);
    }

    PartLine oversternSider() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("overstern");
        List<Integer> sizes = collectSizes(material);
        size = findSize(length, sizes);
        int amount = (int) (Math.ceil(length / size)) * 2;
        return new PartLine(material.get(1), amount).setSize(size);

    }

    PartLine understernSider() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("understern");
        List<Integer> sizes = collectSizes(material);
        size = findSize(length, sizes);
        int amount = (int) Math.ceil(length / size) * 2;
        return new PartLine(material.get(1), amount).setSize(size);
    }

    PartLine vandbraetEnde() throws CustomException
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

    PartLine vandbraetSide() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("vandbræt");
        List<Integer> sizes = collectSizes(material);
        size = findSize(length, sizes);
        int amount = (int) Math.ceil(length / size) * 2;
        return new PartLine(material.get(1), amount).setSize(size);
    }

    PartLine remCarport() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("rem");
        List<Integer> sizes = collectSizes(material);
        int carportLength = (int) length - order.getShedLength();
        size = findSize(carportLength, sizes);
        int amount = (int) Math.ceil(carportLength / size) * 2;
        return new PartLine(material.get(1), amount).setSize(size);
    }

    ArrayList<PartLine> stolperOgBolt() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("stolpe");
        ArrayList<PartLine> parts = new ArrayList<>();
        int amount;
        if (!order.hasShed())
        {
            amount = 4;
            if (length - 200 >= 400)
            {
                amount += 2;
            }
            parts.addAll(braeddeboltOgFirkantskive(amount));
        } else
        {
            amount = 4;
            if (length - order.getShedLength() >= 300)
            {
                amount += 2;
                if (length - order.getShedLength() >= 600)
                {
                    amount += 2;
                }
            }
            parts.addAll(braeddeboltOgFirkantskive(amount));
            if (order.getShedWidth() == width - 70)
            {
                amount += 4;
            } else if (order.getShedWidth() >= 300)
            {
                amount += 6;
            } else
            {
                amount += 3;
            }
            amount++;//for the door
        }
        parts.add(new PartLine(material.get(1), amount).setSize(210));
        return parts;
    }

    ArrayList<PartLine> braeddeboltOgFirkantskive(int amount) throws CustomException
    {
        Material material = ToolMapper.getTool(7);
        ArrayList<PartLine> parts = new ArrayList<>();
        amount *= 2;
        if (order.hasShed())
        {
            amount += 2;
        }
        parts.add(new PartLine(material, amount));
        material = ToolMapper.getTool(8);
        parts.add(new PartLine(material, amount));
        return parts;
    }

    private ArrayList<PartLine> spaerOgBeslag() throws CustomException
    {
        ArrayList<PartLine> parts = new ArrayList<>();
        parts.add(spaer());
        parts.add(universalBeslagH(parts.get(0).getAmount()));
        parts.add(universalBeslagV(parts.get(0).getAmount()));
        parts.add(hulbaand());
        int amountBeslag = parts.get(1).getAmount() + parts.get(2).getAmount();
        int amountHulbaand = parts.get(3).getAmount();
        parts.add(beslagSkruer(amountBeslag, amountHulbaand));
        return parts;
    }

    PartLine beslagSkruer(int amountBeslag, int amountHulbaand) throws CustomException
    {
        Material material = ToolMapper.getTool(6);
        int amount = (amountBeslag * 15) + (amountHulbaand * 30);
        return new PartLine(material, amount);
    }

    PartLine spaer() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("spær");
        List<Integer> sizes = collectSizes(material);
        int amount = 0;
        if (!order.isFlat())
        {
            throw new UnsupportedOperationException("not implemented yet");
        } else
        {
            size = findSize(width, sizes);
            amount = (int) length / 55 + 1;
        }
        return new PartLine(material.get(1), amount).setSize(size);
    }

    PartLine universalBeslagH(int amount) throws CustomException
    {
        Material material = ToolMapper.getTool(3);
        return new PartLine(material, amount);
    }

    PartLine universalBeslagV(int amount) throws CustomException
    {
        Material material = ToolMapper.getTool(4);
        return new PartLine(material, amount);
    }

    PartLine hulbaand() throws CustomException
    {
        Material material = ToolMapper.getTool(2);
        return new PartLine(material, 2);
    }

    ArrayList<PartLine> tagplade() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("tagplade");
        List<Integer> sizes = collectSizes(material);
        ArrayList<PartLine> parts = new ArrayList<>();
        size = findSize(length, sizes);
        int amount = (int) Math.ceil(width / 89);// 20cm overlap
        amount *= (int) Math.ceil(length / size);
        parts.add(new PartLine(material.get(1), amount).setSize(size));
        parts.add(plastmoBundskruer());
        return parts;
    }

    PartLine plastmoBundskruer() throws CustomException
    {
        Material material = ToolMapper.getTool(1);
        size = (length / 100) * (width / 100);
        int amount = (int) size * 12;
        return new PartLine(material, amount);
    }

    ArrayList<PartLine> loesholter() throws CustomException
    {
        ArrayList<PartLine> parts = new ArrayList<>();
        parts.add(loesholterGavl());
        parts.add(loesholterSider());
        parts.add(vinkelbeslag(parts.get(0).getAmount() + parts.get(1).getAmount()));

        return parts;
    }

    PartLine loesholterGavl() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("løsholte");
        List<Integer> sizes = collectSizes(material);
        size = findSize(order.getShedWidth() / 2, sizes);
        int amount = (int) Math.ceil(order.getShedWidth() / size) * 6;//3 i højden i begge sider
        return new PartLine(material.get(1), amount).setSize(size);
    }

    PartLine loesholterSider() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("løsholte");
        List<Integer> sizes = collectSizes(material);
        size = findSize(order.getShedLength() / 2, sizes);
        int amount = (int) Math.ceil(order.getShedLength() / size) * 4;//2 i højden i begge sider
        return new PartLine(material.get(1), amount).setSize(size);
    }

    PartLine vinkelbeslag(int amount) throws CustomException
    {
        Material material = ToolMapper.getTool(13);
        return new PartLine(material, amount * 2);
    }

    PartLine remSkur() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("rem");
        List<Integer> sizes = collectSizes(material);
        size = findSize(order.getShedLength(), sizes);
        int amount = (int) Math.ceil(order.getShedLength() / size) * 2;
        return new PartLine(material.get(1), amount).setSize(size);
    }

    ArrayList<PartLine> beklaedning() throws CustomException
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

    ArrayList<PartLine> beklaedningSkruer(int amount) throws CustomException
    {
        ArrayList<PartLine> parts = new ArrayList<>();
        Material material = ToolMapper.getTool(9);
        parts.add(new PartLine(material, amount * 3));
        material = ToolMapper.getTool(10);
        parts.add(new PartLine(material, amount / 2 * 3));
        return parts;
    }

    private PartLine stalddørsgreb() throws CustomException
    {
        Material material = ToolMapper.getTool(11);
        return new PartLine(material, 1);
    }

    private PartLine tHængsel() throws CustomException
    {
        Material material = ToolMapper.getTool(12);
        return new PartLine(material, 2);
    }
    
    private PartLine zTilDoer() throws CustomException
    {
        List<Material> material = MaterialMapper.getMaterials("z til dør");
        return new PartLine(material.get(0), 1).setSize(material.get(0).getSize());
    }

    private int findSize(double length, List<Integer> sizes)
    {
        double best = 0;
        double wasted;
        int size = 0;

        for (int i = sizes.size() - 1; i >= 0; i--)
        {
            wasted = (length / (double) sizes.get(i)) % 1;
            if (wasted == 0)
            {
                return sizes.get(i);
            } else if (wasted > best)
            {
                size = sizes.get(i);
                best = wasted;
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
        list.addAll(sternOgVandbreat());
        list.addAll(spaerOgBeslag());
        list.addAll(tagplade());
        list.addAll(stolperOgBolt());
        list.add(remCarport());

        if (order.hasShed())
        {
            list.addAll(loesholter());
            list.add(remSkur());
            list.addAll(beklaedning());
            list.add(stalddørsgreb());
            list.add(tHængsel());
            list.add(zTilDoer());
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
