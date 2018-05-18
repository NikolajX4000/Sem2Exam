package functionLayer;

import storageLayer.StorageFacade;
import java.util.*;

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

    /**
     *
     * @param order
     * @throws functionLayer.CustomException
     */
    public FlatCarPortList(Order order)
    {
        this.order = order;
        length = order.getLength();
        width = order.getWidth();
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
        Material material = StorageFacade.getTool(5);
        return new PartLine(material, amount * 10);
    }

    PartLine oversternEnder() throws CustomException
    {
        Material material = findBestMat(width, StorageFacade.getMaterials("overstern"));
        int amount = (int) Math.ceil(width / material.getSize());
        if (!order.hasShed())
        {
            amount *= 2;
        }
        return new PartLine(material, amount);
    }

    PartLine understernEnder() throws CustomException
    {
        Material material = findBestMat(width, StorageFacade.getMaterials("understern"));
        int amount = (int) Math.ceil(width / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    PartLine oversternSider() throws CustomException
    {
        Material material = findBestMat(length, StorageFacade.getMaterials("overstern"));
        int amount = (int) (Math.ceil(length / material.getSize())) * 2;
        return new PartLine(material, amount);

    }

    PartLine understernSider() throws CustomException
    {
        Material material = findBestMat(length, StorageFacade.getMaterials("understern"));
        int amount = (int) Math.ceil(length / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    PartLine vandbraetEnde() throws CustomException
    {
        Material material = findBestMat(width, StorageFacade.getMaterials("vandbræt"));
        int amount = (int) Math.ceil(width / material.getSize());
        if (!order.hasShed())
        {
            amount *= 2;
        }
        return new PartLine(material, amount);
    }

    PartLine vandbraetSide() throws CustomException
    {
        Material material = findBestMat(length, StorageFacade.getMaterials("vandbræt"));
        int amount = (int) Math.ceil(length / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    PartLine remCarport() throws CustomException
    {
        int carportLength = (int) length - order.getShedLength();
        Material material = findBestMat(carportLength, StorageFacade.getMaterials("rem"));
        int amount = (int) Math.ceil(carportLength / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    ArrayList<PartLine> stolperOgBolt() throws CustomException
    {
        Material material = findBestMat(300, StorageFacade.getMaterials("stolpe"));
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
        parts.add(new PartLine(material, amount));
        return parts;
    }

    ArrayList<PartLine> braeddeboltOgFirkantskive(int amount) throws CustomException
    {
        Material material = StorageFacade.getTool(7);
        ArrayList<PartLine> parts = new ArrayList<>();
        amount *= 2;
        if (order.hasShed())
        {
            amount += 4;
        }
        parts.add(new PartLine(material, amount));
        material = StorageFacade.getTool(8);
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
        Material material = StorageFacade.getTool(6);
        int amount = (amountBeslag * 15) + (amountHulbaand * 30);
        return new PartLine(material, amount);
    }

    PartLine spaer() throws CustomException
    {
        Material material = findBestMat(width, StorageFacade.getMaterials("spær"));
        int amount = 0;
        amount = (int) length / 55 + 1;
        return new PartLine(material, amount);
    }

    PartLine universalBeslagH(int amount) throws CustomException
    {
        Material material = StorageFacade.getTool(3);
        return new PartLine(material, amount);
    }

    PartLine universalBeslagV(int amount) throws CustomException
    {
        Material material = StorageFacade.getTool(4);
        return new PartLine(material, amount);
    }

    PartLine hulbaand() throws CustomException
    {
        Material material = StorageFacade.getTool(2);
        return new PartLine(material, 2);
    }

    ArrayList<PartLine> tagplade() throws CustomException
    {
        Material material = findBestMat(length, StorageFacade.getMaterials("tagplade"));
        ArrayList<PartLine> parts = new ArrayList<>();
        int amount = (int) Math.ceil(width / 89);// 20cm overlap
        amount *= (int) Math.ceil(length / size);
        parts.add(new PartLine(material, amount));
        parts.add(plastmoBundskruer());
        return parts;
    }

    PartLine plastmoBundskruer() throws CustomException
    {
        Material material = StorageFacade.getTool(1);
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
        Material material = findBestMat(order.getShedWidth(), StorageFacade.getMaterials("løsholte"));
        int amount = (int) Math.ceil(order.getShedWidth() / size) * 6;//3 i højden i begge sider
        return new PartLine(material, amount);
    }

    PartLine loesholterSider() throws CustomException
    {
        Material material = findBestMat(order.getShedLength(), StorageFacade.getMaterials("løsholte"));
        int amount = (int) Math.ceil(order.getShedLength() / size) * 4;//2 i højden i begge sider
        return new PartLine(material, amount);
    }

    PartLine vinkelbeslag(int amount) throws CustomException
    {
        Material material = StorageFacade.getTool(13);
        return new PartLine(material, amount * 2);
    }

    PartLine remSkur() throws CustomException
    {
        Material material = findBestMat(order.getShedLength(), StorageFacade.getMaterials("rem"));
        int amount = (int) Math.ceil(order.getShedLength() / size) * 2;
        return new PartLine(material, amount);
    }

    ArrayList<PartLine> beklaedning() throws CustomException
    {
        Material material = findBestMat(210, StorageFacade.getMaterials("beklædning"));
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

        parts.add(new PartLine(material, amount));
        parts.addAll(beklaedningSkruer(amount));

        return parts;
    }

    ArrayList<PartLine> beklaedningSkruer(int amount) throws CustomException
    {
        ArrayList<PartLine> parts = new ArrayList<>();
        Material material = StorageFacade.getTool(9);
        parts.add(new PartLine(material, amount * 3));
        material = StorageFacade.getTool(10);
        parts.add(new PartLine(material, amount / 2 * 3));
        return parts;
    }

    private PartLine stalddørsgreb() throws CustomException
    {
        Material material = StorageFacade.getTool(11);
        return new PartLine(material, 1);
    }

    private PartLine tHængsel() throws CustomException
    {
        Material material = StorageFacade.getTool(12);
        return new PartLine(material, 2);
    }

    private PartLine zTilDoer() throws CustomException
    {
        Material material = findBestMat(420, StorageFacade.getMaterials("z til dør"));
        return new PartLine(material, 1);
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

    private Material findBestMat(double length, List<Material> list) throws CustomException
    {
        double best = 0;
        double wasted;
        Material mat = null;
        if (length <= 0)
        {
            throw new CustomException("mærklige mål");
        }

        for (int i = list.size() - 1; i >= 0; i--)
        {
            wasted = (length / (double) list.get(i).getSize()) % 1;
            if (wasted == 0)
            {
                return list.get(i);
            } else if (wasted > best)
            {
                mat = list.get(i);
                best = wasted;
            }
        }
        return mat;
    }

    /**
     *
     * @return
     */
    public List<PartLine> getParts() throws CustomException
    {
        addParts();
        return list;
    }
}
