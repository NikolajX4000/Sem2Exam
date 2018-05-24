package logicLayer;

import storageLayer.StorageFacade;
import java.util.*;

/**
 *
 * @author super
 */
public class FlatCarPortList
{

    List<Material> allTools;
    List<Material> allMaterials;
    double length;
    double width;
    double shedLength;
    double shedWidth;
    boolean hasShed;

    List<PartLine> list = new ArrayList<>();

    /**
     *
     * @param order
     * @throws logicLayer.CustomException
     */
    public FlatCarPortList(Order order) throws CustomException
    {
        this.length = order.getLength();
        this.width = order.getWidth();
        this.shedLength = order.getShedLength();
        this.shedWidth = order.getShedWidth();
        this.hasShed = order.hasShed();
        this.allMaterials = StorageFacade.getAllMaterials();
        this.allTools = StorageFacade.getAllTools();
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
//        Material material = StorageFacade.getTool(5);
        Material material = allTools.get(4);
        return new PartLine(material, amount * 10);
    }

    PartLine oversternEnder() throws CustomException
    {
//        Material material = findBestMat(width, StorageFacade.getMaterials("overstern"));
        Material material = findBestMat(width, findMaterials("overstern"));
        int amount = (int) Math.ceil(width / material.getSize());
        if (!hasShed)
        {
            amount *= 2;
        }
        return new PartLine(material, amount);
    }

    PartLine understernEnder() throws CustomException
    {
//        Material material = findBestMat(width, StorageFacade.getMaterials("understern"));
        Material material = findBestMat(width, findMaterials("understern"));
        int amount = (int) Math.ceil(width / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    PartLine oversternSider() throws CustomException
    {
//        Material material = findBestMat(length, StorageFacade.getMaterials("overstern"));
        Material material = findBestMat(length, findMaterials("overstern"));
        int amount = (int) (Math.ceil(length / material.getSize())) * 2;
        return new PartLine(material, amount);

    }

    PartLine understernSider() throws CustomException
    {
//        Material material = findBestMat(length, StorageFacade.getMaterials("understern"));
        Material material = findBestMat(length, findMaterials("understern"));
        int amount = (int) Math.ceil(length / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    PartLine vandbraetEnde() throws CustomException
    {
//        Material material = findBestMat(width, StorageFacade.getMaterials("vandbræt"));
        Material material = findBestMat(width, findMaterials("vandbræt"));
        int amount = (int) Math.ceil(width / material.getSize());
        if (!hasShed)
        {
            amount *= 2;
        }
        return new PartLine(material, amount);
    }

    PartLine vandbraetSide() throws CustomException
    {
//        Material material = findBestMat(length, StorageFacade.getMaterials("vandbræt"));
        Material material = findBestMat(length, findMaterials("vandbræt"));
        int amount = (int) Math.ceil(length / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    PartLine remCarport() throws CustomException
    {
        double carportLength = length - shedLength;
//        Material material = findBestMat(carportLength, StorageFacade.getMaterials("rem"));
        Material material = findBestMat(carportLength, findMaterials("rem"));
        int amount = (int) Math.ceil(carportLength / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    ArrayList<PartLine> stolperOgBolt() throws CustomException
    {
//        Material material = findBestMat(300, StorageFacade.getMaterials("stolpe"));
        Material material = findBestMat(300, findMaterials("stolpe"));
        ArrayList<PartLine> parts = new ArrayList<>();
        int amount;
        if (!hasShed)
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
            if (length - shedLength >= 300)
            {
                amount += 2;
                if (length - shedLength>= 600)
                {
                    amount += 2;
                }
            }
            parts.addAll(braeddeboltOgFirkantskive(amount));
            if (shedWidth == width - 70)
            {
                amount += 4;
            } else if (shedWidth >= 300)
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
//        Material material = StorageFacade.getTool(7);
        Material material = allTools.get(6);
        ArrayList<PartLine> parts = new ArrayList<>();
        amount *= 2;
        if (hasShed)
        {
            amount += 4;
        }
        parts.add(new PartLine(material, amount));
//        material = StorageFacade.getTool(8);
        material = allTools.get(7);
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
//        Material material = StorageFacade.getTool(6);
        Material material = allTools.get(5);
        int amount = (amountBeslag * 15) + (amountHulbaand * 30);
        return new PartLine(material, amount);
    }

    PartLine spaer() throws CustomException
    {
//        Material material = findBestMat(width, StorageFacade.getMaterials("spær"));
        Material material = findBestMat(width, findMaterials("spær"));
        int amount = 0;
        amount = (int) length / 55 + 1;
        return new PartLine(material, amount);
    }

    PartLine universalBeslagH(int amount) throws CustomException
    {
//        Material material = StorageFacade.getTool(3);
        Material material = allTools.get(2);
        return new PartLine(material, amount);
    }

    PartLine universalBeslagV(int amount) throws CustomException
    {
//        Material material = StorageFacade.getTool(4);
        Material material = allTools.get(3);
        return new PartLine(material, amount);
    }

    PartLine hulbaand() throws CustomException
    {
//        Material material = StorageFacade.getTool(2);
        Material material = allTools.get(1);
        return new PartLine(material, 2);
    }

    ArrayList<PartLine> tagplade() throws CustomException
    {
//        Material material = findBestMat(length, StorageFacade.getMaterials("tagplade"));
        Material material = findBestMat(length, findMaterials("tagplade"));
        ArrayList<PartLine> parts = new ArrayList<>();
        int amount = (int) Math.ceil(width / 89);// 20cm overlap
        amount *= (int) Math.ceil(length / material.getSize());
        parts.add(new PartLine(material, amount));
        parts.add(plastmoBundskruer());
        return parts;
    }

    PartLine plastmoBundskruer() throws CustomException
    {
//        Material material = StorageFacade.getTool(1);
        Material material = allTools.get(0);
        double size = (length / 100) * (width / 100);
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
//        Material material = findBestMat(shedWidth/2, StorageFacade.getMaterials("løsholte"));
        Material material = findBestMat(shedWidth/2, findMaterials("løsholte"));
        int amount = (int) Math.ceil(shedWidth / material.getSize()) * 6;//3 i højden i begge sider
        return new PartLine(material, amount);
    }

    PartLine loesholterSider() throws CustomException
    {
//        Material material = findBestMat(shedLength/2, StorageFacade.getMaterials("løsholte"));
        Material material = findBestMat(shedLength/2, findMaterials("løsholte"));
        int amount = (int) Math.ceil(shedLength / material.getSize()) * 4;//2 i højden i begge sider
        return new PartLine(material, amount);
    }

    PartLine vinkelbeslag(int amount) throws CustomException
    {
//        Material material = StorageFacade.getTool(13);
        Material material = allTools.get(12);
        return new PartLine(material, amount * 2);
    }

    PartLine remSkur() throws CustomException
    {
//        Material material = findBestMat(shedLength, StorageFacade.getMaterials("rem"));
        Material material = findBestMat(shedLength, findMaterials("rem"));
        int amount = (int) Math.ceil(shedLength / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    ArrayList<PartLine> beklaedning() throws CustomException
    {
//        Material material = findBestMat(210, StorageFacade.getMaterials("beklædning"));
        Material material = findBestMat(210, findMaterials("beklædning"));
        ArrayList<PartLine> parts = new ArrayList<>();
        //6cm mellemrum
        //10cm bred
        double amount = 0;
        //inderst sider
        amount += Math.ceil(shedLength / 16) * 2;
        //inderst ender
        amount += Math.ceil(shedWidth / 16) * 2;
        //yderst sider
        amount += Math.ceil((shedLength - 8) / 16) * 2;
        //yderst ender
        amount += Math.ceil((shedWidth - 8) / 16) * 2;

        parts.add(new PartLine(material, (int)amount));
        parts.addAll(beklaedningSkruer((int)amount));

        return parts;
    }

    ArrayList<PartLine> beklaedningSkruer(int amount) throws CustomException
    {
        ArrayList<PartLine> parts = new ArrayList<>();
//        Material material = StorageFacade.getTool(9);
        Material material = allTools.get(8);
        parts.add(new PartLine(material, amount * 3));
        material = allTools.get(9);
        parts.add(new PartLine(material, amount / 2 * 3));
        return parts;
    }

    private PartLine stalddørsgreb() throws CustomException
    {
//        Material material = StorageFacade.getTool(11);
        Material material = allTools.get(10);
        return new PartLine(material, 1);
    }

    private PartLine tHængsel() throws CustomException
    {
//        Material material = StorageFacade.getTool(12);
        Material material = StorageFacade.getTool(11);
        return new PartLine(material, 2);
    }

    private PartLine zTilDoer() throws CustomException
    {
//        Material material = findBestMat(420, StorageFacade.getMaterials("z til dør"));
        Material material = findBestMat(420, findMaterials("z til dør"));
        return new PartLine(material, 1);
    }

    private void addParts() throws CustomException
    {
        list.addAll(sternOgVandbreat());
        list.addAll(spaerOgBeslag());
        list.addAll(tagplade());
        list.addAll(stolperOgBolt());
        list.add(remCarport());

        if (hasShed)
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
    
     private List<Material> findMaterials(String name) throws CustomException
    {
        List<Material> materials = new ArrayList<>();
        for (Material material : allMaterials)
        {
            if (material.getName().equals(name))
            {
                materials.add(material);
            }
        }
        if(materials.size() > 0)
        {
            return materials;
        }
        throw new CustomException("ingen matriel passer");
    }
}
