package functionLayer;

import java.util.*;
import storageLayer.StorageFacade;

public class TallCarPortList
{

    double size = 1;
    double length;
    double width;
    double height;
    double angle;
    double shedLength;
    double shedWidth;
    double spaerLength;
    boolean hasShed;
    String roof;

    public TallCarPortList(Order order) throws CustomException
    {
        this.length = order.getLength();
        this.width = order.getWidth();
        this.hasShed = order.hasShed();
        this.shedLength = order.getShedLength();
        this.shedWidth = order.getShedWidth();
        this.angle = order.getAngle();
        double angleB = 180 - 90 - angle;
        double side = width / 2;
        this.height = Math.ceil(Math.sin(Math.toRadians(angle)) * side / Math.sin(Math.toRadians(angleB)));
        this.roof = order.getRoof().getNAME();
        this.spaerLength = Math.ceil(Math.sqrt(Math.pow(width / 2, 2) + Math.pow(height, 2)) * 2);
    }

    public ArrayList<PartLine> getParts() throws CustomException
    {
        ArrayList<PartLine> parts = new ArrayList<>();
        parts.add(vindskede());                                                 //0
        parts.add(SternCarport());                                              //1
        int totalStern = parts.get(1).getAmount();
        parts.add(spear());                                                     //2
        int amountSpear = parts.get(2).getAmount();                             //3
        parts.addAll(stolperOgBolt());                                          //4
        parts.add(remCarport());                                                //5
        parts.add(vandbraet());                                                 //6
        parts.add(beklaedningGavle());                                          //7
        int amountBeklaedning = parts.get(7).getAmount();
        parts.add(ovenPaaTagfodslaegte());                                      //8
        parts.add(tagLaegte());                                                 //9
        parts.add(topLaegte());                                                 //10
        parts.add(tagsten());                                                   //11
        int amountTagsten = parts.get(11).getAmount();
        parts.add(rygsten());                                                   //12
        int amountRygsten = parts.get(12).getAmount();
        parts.add(toplaegteHolder(amountSpear));                                //13
        int amountToplaegteHoldere = parts.get(13).getAmount();
        parts.add(rygstensbeslag(amountRygsten));                               //14
        parts.add(tagstensBindereOgNakkekroge(amountTagsten));                  //15
        parts.add(universalH(amountSpear));                                     //16
        parts.add(universalV(amountSpear));                                     //17
        int amountBeslag = parts.get(16).getAmount() + parts.get(17).getAmount();
        if (hasShed)
        {
            parts.add(remSkur());                                               //18
            totalStern += parts.get(18).getAmount();
            parts.add(SternSkur());                                             //19
            parts.add(loesholterSider());                                       //20
            parts.add(loesholterGavle());                                       //21
            int amountLoesholter = parts.get(20).getAmount()+ parts.get(21).getAmount();
            parts.add(beklaedningSkur());                                       //22
            amountBeklaedning += parts.get(22).getAmount();
            parts.add(zTilDoer());                                              //23
            parts.add(Stalddoersgreb());                                        //24
            parts.add(tHaengsel());                                             //25
            parts.add(vinkelbeslag(amountLoesholter));                          //26
        }
        parts.add(skruerSternVindskedeVandbraet(totalStern));                   //27
        parts.add(skruerUniverslbeslagToplaegte(amountBeslag, amountToplaegteHoldere)); //28
        parts.add(skruertaglaegter(amountSpear));                               //29
        parts.add(skruerInderstBeklaedning(amountBeklaedning));                 //30
        parts.add(skruerYderstBeklaedning(amountBeklaedning));                  //31
        return parts;
    }

    PartLine vindskede() throws CustomException
    {
        Material material = findBestMat(spaerLength, StorageFacade.getMaterials("vindskede"));
        int amount = (int) Math.ceil(spaerLength / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    PartLine SternCarport() throws CustomException
    {
        List<Material> materials = StorageFacade.getMaterials("overstern");
        double carportLength = length - shedLength;
        Material material = findBestMat(carportLength, materials);
        int amount = (int) (Math.ceil(carportLength / material.getSize() * 2));
        return new PartLine(material, amount);
    }

    PartLine SternSkur() throws CustomException
    {
        List<Material> materials = StorageFacade.getMaterials("overstern");
        Material material = findBestMat(shedLength, materials);
        int amount = (int) (Math.ceil(shedLength / material.getSize() * 2));
        return new PartLine(material, amount);
    }

    PartLine spear() throws CustomException
    {
        List<Material> materials = StorageFacade.getMaterials("spær");
        Material material = findBestMat(spaerLength, materials);
        double amount = Math.ceil((length - shedLength) / 89);
        if (hasShed)
        {
            amount += Math.ceil(shedLength / 110) - 1;
        }
        return new PartLine(material, (int) amount);
    }

    ArrayList<PartLine> stolperOgBolt() throws CustomException
    {
        List<Material> materials = StorageFacade.getMaterials("stolpe");
        Material material = findBestMat(300, materials);
        ArrayList<PartLine> parts = new ArrayList<>();
        int amount;
        if (!hasShed)
        {
            amount = 4;
            if (length - 200 >= 400)
            {
                amount += 2;
            }
            parts.add(braeddebolt(amount));
            parts.add(firkantskiver(amount));
        } else
        {
            amount = 4;
            if (length - shedLength >= 300)
            {
                amount += 2;
                if (length - shedLength >= 600)
                {
                    amount += 2;
                }
            }
            if (shedWidth == width - 40)
            {
                amount += 2;
            } else if (shedWidth >= 300)
            {
                amount += 6;
            } else
            {
                amount += 3;
            }
            parts.add(braeddebolt(amount));
            parts.add(firkantskiver(amount));
            amount++;//for the door
        }
        parts.add(new PartLine(material, amount));
        return parts;
    }

    PartLine remCarport() throws CustomException
    {
        List<Material> materials = StorageFacade.getMaterials("rem");
        double carportLength = length - shedLength - 30;
        Material material = findBestMat(carportLength, materials);
        int amount = (int) Math.ceil(carportLength / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    PartLine remSkur() throws CustomException
    {
        List<Material> materials = StorageFacade.getMaterials("rem");
        Material material = findBestMat(shedLength, materials);
        int amount = (int) Math.ceil(shedLength / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    PartLine loesholterSider() throws CustomException
    {
        List<Material> materials = StorageFacade.getMaterials("løsholte");
        Material material = findBestMat(shedLength, materials);
        int amount = (int) Math.ceil(shedLength / material.getSize()) * 4;//2 i højden i begge sider
        return new PartLine(material, amount);
    }

    PartLine loesholterGavle() throws CustomException
    {
        List<Material> materials = StorageFacade.getMaterials("løsholte");
        Material material = findBestMat(shedWidth, materials);
        int amount = (int) Math.ceil(shedWidth / material.getSize()) * 6;//3 i højden i begge sider
        return new PartLine(material, amount);
    }

    PartLine vandbraet() throws CustomException
    {
        Material material = findBestMat(spaerLength, StorageFacade.getMaterials("vandbræt"));
        int amount = (int) Math.ceil(spaerLength / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    PartLine beklaedningGavle() throws CustomException
    {
        List<Material> materials = StorageFacade.getMaterials("beklædning");
        Material material = findBestMat(height, materials);
        //6cm mellemrum
        //10cm bred
        int amount = 0;
        //inderst 
        amount += (int) Math.ceil(width / 16);
        //yderst
        amount += (int) Math.ceil((width - 8) / 16);

        return new PartLine(material, amount);
    }

    PartLine beklaedningSkur() throws CustomException
    {
        List<Material> materials = StorageFacade.getMaterials("beklædning");
        Material material = findBestMat(210, materials);
        //6cm mellemrum
        //10cm bred
        int amount = 0;
        //inderst sider
        amount += (int) Math.ceil(shedLength / 16) * 2;
        //inderst ender
        amount += (int) Math.ceil(shedWidth / 16) * 2;
        //yderst sider
        amount += (int) Math.ceil((shedLength - 8) / 16) * 2;
        //yderst ender
        amount += (int) Math.ceil((shedWidth - 8) / 16) * 2;

        return new PartLine(material, amount);
    }

    PartLine ovenPaaTagfodslaegte() throws CustomException
    {
        Material material = findBestMat(length, StorageFacade.getMaterials("oven på tagfodslægte"));
        int amount = (int) Math.ceil(length / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    PartLine zTilDoer() throws CustomException
    {
        List<Material> material = StorageFacade.getMaterials("z til dør");
        return new PartLine(material.get(0), 1);
    }

    PartLine tagLaegte() throws CustomException
    {
        Material material = findBestMat(length, StorageFacade.getMaterials("taglægte"));
        int amount = (int) Math.ceil(spaerLength / 30);
        amount += Math.ceil(length / material.getSize());
        return new PartLine(material, amount);
    }

    PartLine topLaegte() throws CustomException
    {
        Material material = findBestMat(length, StorageFacade.getMaterials("taglægte"));
        int amount = (int) Math.ceil(length / material.getSize());
        return new PartLine(material, amount);
    }

    PartLine tagsten() throws CustomException
    {
        Material material = findMaterial(roof, StorageFacade.getMaterials("tagsten"));
        int amount = (int) Math.ceil((spaerLength / 32) * (length / 30));
        amount += 50 - amount % 50;
        return new PartLine(material, amount);
    }

    PartLine rygsten() throws CustomException
    {
        Material material = findMaterial(roof, StorageFacade.getMaterials("rygsten"));
        int amount = (int) Math.ceil(length / 35);
        return new PartLine(material, amount);
    }

    PartLine toplaegteHolder(int amount) throws CustomException
    {
        Material material = StorageFacade.getTool(14);
        return new PartLine(material, amount);
    }

    PartLine rygstensbeslag(int amount) throws CustomException
    {
        Material material = StorageFacade.getTool(15);
        return new PartLine(material, amount);
    }

    PartLine tagstensBindereOgNakkekroge(int amount) throws CustomException
    {
        Material material = StorageFacade.getTool(16);
        amount *= 0.45;
        return new PartLine(material, amount);
    }

    PartLine universalH(int amount) throws CustomException
    {
        Material material = StorageFacade.getTool(3);
        return new PartLine(material, amount);
    }

    PartLine universalV(int amount) throws CustomException
    {
        Material material = StorageFacade.getTool(4);
        return new PartLine(material, amount);
    }

    PartLine Stalddoersgreb() throws CustomException
    {
        Material material = StorageFacade.getTool(11);
        return new PartLine(material, 1);
    }

    PartLine tHaengsel() throws CustomException
    {
        Material material = StorageFacade.getTool(12);
        return new PartLine(material, 2);
    }

    PartLine vinkelbeslag(int amount) throws CustomException
    {
        Material material = StorageFacade.getTool(13);
        return new PartLine(material, amount * 2);
    }

    PartLine skruerSternVindskedeVandbraet(int totalSternSize) throws CustomException
    {
        Material material = StorageFacade.getTool(5);
        int amount = totalSternSize / 10;
        return new PartLine(material, amount);
    }

    PartLine skruerUniverslbeslagToplaegte(int amountBeslag, int amountToplaegteHoldere) throws CustomException
    {
        Material material = StorageFacade.getTool(17);
        int amount = (amountBeslag * 11) + (amountToplaegteHoldere * 4);
        return new PartLine(material, amount);
    }

    PartLine skruertaglaegter(int amountSpaer) throws CustomException
    {
        Material material = StorageFacade.getTool(18);
        int amountTaglaegter = (int) Math.ceil(spaerLength / 30);
        int amount = amountSpaer * amountTaglaegter;
        return new PartLine(material, amount);
    }

    PartLine braeddebolt(int amount) throws CustomException
    {
        Material material = StorageFacade.getTool(7);
        amount *= 2;
        if (hasShed)
        {
            amount += 4;
        }
        return new PartLine(material, amount);
    }

    PartLine firkantskiver(int amount) throws CustomException
    {
        Material material = StorageFacade.getTool(8);
        amount *= 2;
        if (hasShed)
        {
            amount += 4;
        }
        return new PartLine(material, amount);
    }

    PartLine skruerYderstBeklaedning(int amount) throws CustomException
    {
        Material material = StorageFacade.getTool(9);
        return new PartLine(material, amount * 5);
    }

    PartLine skruerInderstBeklaedning(int amount) throws CustomException
    {
        Material material = StorageFacade.getTool(10);
        return new PartLine(material, amount * 3);
    }

    private Material findBestMat(double length, List<Material> list) throws CustomException
    {
        double best = 0;
        double wasted;
        Material mat = null;
        if(length<=0)
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

    private Material findMaterial(String name, List<Material> materials) throws CustomException
    {
        for (Material material : materials)
        {
            if (material.getDescription().equals(name))
            {
                return material;
            }
        }
        throw new CustomException("ingen matriel passer");
    }

}
