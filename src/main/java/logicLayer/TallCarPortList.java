package logicLayer;

import java.util.List;
import java.util.ArrayList;
import storageLayer.StorageFacade;

/**
 * This class calculates the parts list for a carport with a raised roof.
 */
public class TallCarPortList
{

    List<Material> allTools;
    List<Material> allMaterials;
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

    /**
     * The constructor takes an Order. The order needs to hold at least a
     * length, a width, a roof type and an angle.
     *
     * @param order
     * @throws CustomException
     */
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
        this.allMaterials = StorageFacade.getAllMaterials();
        this.allTools = StorageFacade.getAllTools();
    }

    /**
     *
     * @return @throws CustomException
     */
    public List<PartLine> getParts() throws CustomException
    {
        List<PartLine> parts = new ArrayList<>();
        parts.add(vindskede());
        parts.add(SternCarport());
        parts.add(spear());
        int amountSpear = findMaterialAmount("spær", parts);
        parts.addAll(stolperOgBolt());
        parts.add(remCarport());
        parts.add(vandbraet());
        parts.add(beklaedningGavle());
        parts.add(ovenPaaTagfodslaegte());
        parts.add(tagLaegte());
        parts.add(topLaegte());
        parts.add(tagsten());
        int amountTagsten = findMaterialAmount("tagsten", parts);
        parts.add(rygsten());
        int amountRygsten = findMaterialAmount("rygsten", parts);
        parts.add(toplaegteHolder(amountSpear));
        int amountToplaegteHoldere = findMaterialAmount("B & C Toplægteholder", parts);
        parts.add(rygstensbeslag(amountRygsten));
        parts.add(tagstensBindereOgNakkekroge(amountTagsten));
        parts.add(universalH(amountSpear));
        parts.add(universalV(amountSpear));
        int amountBeslag = findMaterialAmount("Universal 190mm højre", parts) + findMaterialAmount("Universal 190mm venstre", parts);
        int amountBeklaedning = findMaterialAmount("beklædning", parts);
        int totalSternVindskedeVandbraet = findMaterialAmount("overstern", parts);
        totalSternVindskedeVandbraet += findMaterialAmount("vindskede", parts);
        totalSternVindskedeVandbraet += findMaterialAmount("vandbræt", parts);
        parts.add(skruerSternVindskedeVandbraet(totalSternVindskedeVandbraet));
        parts.add(skruerUniverslbeslagToplaegte(amountBeslag, amountToplaegteHoldere));
        parts.add(skruertaglaegter(amountSpear));
        parts.add(skruerInderstBeklaedning(amountBeklaedning));
        parts.add(skruerYderstBeklaedning(amountBeklaedning));
        if (hasShed)
        {
            parts.add(remSkur());
            parts.add(SternSkur());
            parts.add(loesholterSider());
            parts.add(loesholterGavle());
            int amountLoesholter = findMaterialAmount("løsholte", parts);
            parts.add(beklaedningSkur());
            parts.add(zTilDoer());
            parts.add(Stalddoersgreb());
            parts.add(tHaengsel());
            parts.add(vinkelbeslag(amountLoesholter));
        }
        return parts;
    }

    PartLine vindskede() throws CustomException
    {
//        Material material = findBestMat(spaerLength, StorageFacade.getMaterials("vindskede"));
        Material material = findBestMat(spaerLength, findMaterials("vindskede"));
        int amount = (int) Math.ceil(spaerLength / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    PartLine SternCarport() throws CustomException
    {
        double carportLength = length - shedLength;
//        List<Material> materials = StorageFacade.getMaterials("overstern");
        Material material = findBestMat(carportLength, findMaterials("overstern"));
        int amount = (int) (Math.ceil(carportLength / material.getSize() * 2));
        return new PartLine(material, amount);
    }

    PartLine SternSkur() throws CustomException
    {
//        List<Material> materials = StorageFacade.getMaterials("overstern");
        Material material = findBestMat(shedLength, findMaterials("overstern"));
        int amount = (int) (Math.ceil(shedLength / material.getSize() * 2));
        return new PartLine(material, amount);
    }

    PartLine spear() throws CustomException
    {
//        List<Material> materials = StorageFacade.getMaterials("spær");
        Material material = findBestMat(spaerLength, findMaterials("spær"));
        double amount = 1 + Math.ceil((length - shedLength - 60) / 89);
        if (hasShed)
        {
            amount += Math.ceil(shedLength / 110);
        }
        amount *= 2;
        return new PartLine(material, (int) amount);
    }

    List<PartLine> stolperOgBolt() throws CustomException
    {
//        List<Material> materials = StorageFacade.getMaterials("stolpe");
        Material material = findBestMat(300, findMaterials("stolpe"));
        List<PartLine> parts = new ArrayList<>();
        int amount;

        int total;

        if (hasShed)
        {

            //shed x
            int shedX = 2 + (int) (shedLength / 400);
            //shed y
            int shedY = 2 + (int) (shedWidth / 400);

            // fjern stolper der er beregnet til at være midt i skuret
            int removed = (shedX - 2) * (shedY - 2);

            // kig om der skal bruges stolper under carport
            if (width - 30 > shedWidth)
            {
                shedY++;
            }
            // kig om der skal bruges stolper til venstre fra carport
            if (length - 60 > shedLength)
            {
                shedX++;
            }

            //skur stolper og dem til venstre og under
            int shedBeams = shedY * shedX;

            int distanceX = (int) (length - shedLength) - 60;
            int distanceY = (int) (width - shedWidth) - 30;
            // 2*hvor mange gange distance går op i 400

            int bonusX = 2 * (distanceX / 400);
            int bonusY = 2 * (distanceY / 400);

            total = shedBeams + bonusX + bonusY - removed;
            //til døren
            total++;
            amount = 2 * shedX + bonusX + 2;

            parts.add(braeddebolt(amount));
            parts.add(firkantskiver(amount));

        } else
        {

            total = 4 + (2 * ((int) ((length - 60) / 400) + (int) ((width - 30) / 400)));
            amount = 4 + (2 * (((int) width - 60) / 400));
            parts.add(braeddebolt(amount));
            parts.add(braeddebolt(amount));
        }

        parts.add(new PartLine(material, total));
        return parts;
    }

    PartLine remCarport() throws CustomException
    {
        double carportLength = length - shedLength - 30;
//        List<Material> materials = StorageFacade.getMaterials("rem");
        Material material = findBestMat(carportLength, findMaterials("rem"));
        int amount = (int) Math.ceil(carportLength / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    PartLine remSkur() throws CustomException
    {
//        List<Material> materials = StorageFacade.getMaterials("rem");
        Material material = findBestMat(shedLength, findMaterials("rem"));
        int amount = (int) Math.ceil(shedLength / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    PartLine loesholterSider() throws CustomException
    {
//        List<Material> materials = StorageFacade.getMaterials("løsholte");
        Material material = findBestMat(shedLength, findMaterials("løsholte"));
        int amount = (int) Math.ceil(shedLength / material.getSize()) * 4;//2 i højden i begge sider
        return new PartLine(material, amount);
    }

    PartLine loesholterGavle() throws CustomException
    {
//        List<Material> materials = StorageFacade.getMaterials("løsholte");
        Material material = findBestMat(shedWidth, findMaterials("løsholte"));
        int amount = (int) Math.ceil(shedWidth / material.getSize()) * 6;//3 i højden i begge sider
        return new PartLine(material, amount);
    }

    PartLine vandbraet() throws CustomException
    {
//        Material material = findBestMat(spaerLength, StorageFacade.getMaterials("vandbræt"));
        Material material = findBestMat(spaerLength, findMaterials("vandbræt"));
        int amount = (int) Math.ceil(spaerLength / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    PartLine beklaedningGavle() throws CustomException
    {
//        List<Material> materials = StorageFacade.getMaterials("beklædning");
        Material material = findBestMat(height, findMaterials("beklædning"));
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
//        List<Material> materials = StorageFacade.getMaterials("beklædning");
        Material material = findBestMat(210, findMaterials("beklædning"));
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
//        Material material = findBestMat(length, StorageFacade.getMaterials("oven på tagfodslægte"));
        Material material = findBestMat(length, findMaterials("oven på tagfodslægte"));
        int amount = (int) Math.ceil(length / material.getSize()) * 2;
        return new PartLine(material, amount);
    }

    PartLine zTilDoer() throws CustomException
    {
//        List<Material> material = StorageFacade.getMaterials("z til dør");
        Material material = findBestMat(540, findMaterials("z til dør"));
        return new PartLine(material, 1);
    }

    PartLine tagLaegte() throws CustomException
    {
//        Material material = findBestMat(length, StorageFacade.getMaterials("taglægte"));
        Material material = findBestMat(length, findMaterials("taglægte"));
        int amount = (int) Math.ceil(spaerLength / 30);
        amount += Math.ceil(length / material.getSize());
        return new PartLine(material, amount);
    }

    PartLine topLaegte() throws CustomException
    {
//        Material material = findBestMat(length, StorageFacade.getMaterials("taglægte"));
        Material material = findBestMat(length, findMaterials("taglægte"));
        int amount = (int) Math.ceil(length / material.getSize());
        return new PartLine(material, amount);
    }

    PartLine tagsten() throws CustomException
    {
//        Material material = findMaterial(roof, StorageFacade.getMaterials("tagsten"));
        Material material = findMaterial(roof, findMaterials("tagsten"));
        int amount = (int) Math.ceil((spaerLength / 32) * (length / 30));
        amount += 50 - amount % 50;
        return new PartLine(material, amount);
    }

    PartLine rygsten() throws CustomException
    {
//        Material material = findMaterial(roof, StorageFacade.getMaterials("rygsten"));
        Material material = findMaterial(roof, findMaterials("rygsten"));
        int amount = (int) Math.ceil(length / 35);
        return new PartLine(material, amount);
    }

    PartLine toplaegteHolder(int amount) throws CustomException
    {
//        Material material = StorageFacade.getTool(14);
        Material material = allTools.get(13);
        return new PartLine(material, amount);
    }

    PartLine rygstensbeslag(int amount) throws CustomException
    {
//        Material material = StorageFacade.getTool(15);
        Material material = allTools.get(14);
        return new PartLine(material, amount);
    }

    PartLine tagstensBindereOgNakkekroge(int amount) throws CustomException
    {
//        Material material = StorageFacade.getTool(16);
        Material material = allTools.get(15);
        amount *= 0.45;
        return new PartLine(material, amount);
    }

    PartLine universalH(int amount) throws CustomException
    {
//        Material material = StorageFacade.getTool(3);
        Material material = allTools.get(2);
        return new PartLine(material, amount);
    }

    PartLine universalV(int amount) throws CustomException
    {
//        Material material = StorageFacade.getTool(4);
        Material material = allTools.get(3);
        return new PartLine(material, amount);
    }

    PartLine Stalddoersgreb() throws CustomException
    {
//        Material material = StorageFacade.getTool(11);
        Material material = allTools.get(10);
        return new PartLine(material, 1);
    }

    PartLine tHaengsel() throws CustomException
    {
//        Material material = StorageFacade.getTool(12);
        Material material = allTools.get(11);
        return new PartLine(material, 2);
    }

    PartLine vinkelbeslag(int amount) throws CustomException
    {
//        Material material = StorageFacade.getTool(13);
        Material material = allTools.get(12);
        return new PartLine(material, amount * 2);
    }

    PartLine skruerSternVindskedeVandbraet(int amount) throws CustomException
    {
//        Material material = StorageFacade.getTool(5);
        Material material = allTools.get(4);
        return new PartLine(material, amount * 10);
    }

    PartLine skruerUniverslbeslagToplaegte(int amountBeslag, int amountToplaegteHoldere) throws CustomException
    {
//        Material material = StorageFacade.getTool(17);
        Material material = allTools.get(16);
        int amount = (amountBeslag * 11) + (amountToplaegteHoldere * 4);
        return new PartLine(material, amount);
    }

    PartLine skruertaglaegter(int amountSpaer) throws CustomException
    {
//        Material material = StorageFacade.getTool(18);
        Material material = allTools.get(17);
        int amountTaglaegter = (int) Math.ceil(spaerLength / 30);
        int amount = amountSpaer * amountTaglaegter;
        return new PartLine(material, amount);
    }

    PartLine braeddebolt(int amount) throws CustomException
    {
//        Material material = StorageFacade.getTool(7);
        Material material = allTools.get(6);
        amount *= 2;
        if (hasShed)
        {
            amount += 4;
        }
        return new PartLine(material, amount);
    }

    PartLine firkantskiver(int amount) throws CustomException
    {
//        Material material = StorageFacade.getTool(8);
        Material material = allTools.get(7);
        amount *= 2;
        if (hasShed)
        {
            amount += 4;
        }
        return new PartLine(material, amount);
    }

    PartLine skruerYderstBeklaedning(int amount) throws CustomException
    {
//        Material material = StorageFacade.getTool(9);
        Material material = allTools.get(8);
        return new PartLine(material, amount * 5);
    }

    PartLine skruerInderstBeklaedning(int amount) throws CustomException
    {
//        Material material = StorageFacade.getTool(10);
        Material material = allTools.get(9);
        return new PartLine(material, amount * 3);
    }

    private Material findBestMat(double length, List<Material> list) throws CustomException
    {
        double best = Double.MAX_VALUE;
        double wasted;
        Material mat = null;
        if (length <= 0)
        {
            throw new CustomException("mærklige mål");
        }

        for (int i = list.size() - 1; i >= 0; i--)
        {
            wasted = 1 - ((length / list.get(i).getSize()) % 1);
            if (wasted == 0)
            {
                return list.get(i);
            } else if (wasted < best)
            {
                mat = list.get(i);
                best = wasted;
            }
        }
        return mat;
    }

    private int findMaterialAmount(String name, List<PartLine> parts) throws CustomException
    {
        int amount = 0;
        for (PartLine part : parts)
        {
            if (part.getMaterial().getName().equals(name))
            {
                amount += part.getAmount();
            }
        }
        if (amount == 0)
        {
            throw new CustomException("Material ikke fundet");
        }
        return amount;
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
        if (materials.size() == 0)
        {
            throw new CustomException("ingen matriel passer");
        }
        return materials;
    }

}
