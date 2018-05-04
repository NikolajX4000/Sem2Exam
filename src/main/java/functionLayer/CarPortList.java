package functionLayer;

import java.sql.SQLException;
import java.util.*;
import storageLayer.MaterialMapper;

/**
 *
 * @author super
 */
public class CarPortList {

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
    public CarPortList(Order order) throws CustomException {
        this.order = order;
        length = order.getLength();
        width = order.getWidth();
        addParts();
    }

    private PartLine oversternEnder() throws CustomException {
        List<Material> material = MaterialMapper.getMaterials("overstern");
        List<Integer> sizes = collectSizes(material);
        size = findSize(width, sizes);
        int amount = (int) Math.ceil(width / size);
        if (!order.hasShed()) {
            amount *= 2;
        }
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private PartLine understernEnder() throws CustomException {
        List<Material> material = MaterialMapper.getMaterials("understern");
        List<Integer> sizes = collectSizes(material);
        size = findSize(width, sizes);
        int amount = (int) Math.ceil(width / size) * 2;
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private PartLine oversternSider() throws CustomException {
        List<Material> material = MaterialMapper.getMaterials("overstern");
        List<Integer> sizes = collectSizes(material);
        size = findSize(width, sizes);
        int amount = (int) Math.ceil(length / size) * 2;
        return new PartLine(material.get(1), amount).setSize(size);

    }

    private PartLine understernSider() throws CustomException {
        List<Material> material = MaterialMapper.getMaterials("understern");
        List<Integer> sizes = collectSizes(material);
        size = findSize(length, sizes);
        int amount = (int) Math.ceil(length / size) * 2;
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private PartLine remCarport() throws CustomException {
        List<Material> material = MaterialMapper.getMaterials("rem");
        List<Integer> sizes = collectSizes(material);
        int carportLength = length - order.getShedLength();
        size = findSize(carportLength, sizes);
        int amount = (int) Math.ceil(carportLength / size) * 2;
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private PartLine spaer() throws CustomException {
        List<Material> material = MaterialMapper.getMaterials("spear");
        List<Integer> sizes = collectSizes(material);
        int amount = 0;
        if (!order.isFlat()) {
            throw new UnsupportedOperationException("not implemented yet");
        } else {
            size = findSize(width, sizes);
            amount = (int) Math.ceil(length / 55) + 1;
        }
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private PartLine stolper() throws CustomException {
        List<Material> material = MaterialMapper.getMaterials("stolpe");
        int amount = 6;
        if (order.hasShed()) {
            amount += 5;
        }
        return new PartLine(material.get(1), amount).setSize(210);
    }

    private PartLine vandbraetEnde() throws CustomException {
        List<Material> material = MaterialMapper.getMaterials("vandbreat");
        List<Integer> sizes = collectSizes(material);
        size = findSize(width, sizes);
        int amount = (int) Math.ceil(width / size);
        if (!order.hasShed()) {
            amount *= 2;
        }
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private PartLine vandbraetSide() throws CustomException {
        List<Material> material = MaterialMapper.getMaterials("vandbreat");
        List<Integer> sizes = collectSizes(material);
        size = findSize(width, sizes);
        int amount = (int) Math.ceil(width / size) * 2;
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private PartLine tagplade() throws CustomException {
        List<Material> material = MaterialMapper.getMaterials("tagplade");
        List<Integer> sizes = collectSizes(material);
        size = findSize(length, sizes);
        int amount = (int) Math.ceil(width / 89);// 20cm overlap
        amount *= (int) Math.ceil(length / size);
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private PartLine loesholterGavl() throws CustomException {
        List<Material> material = MaterialMapper.getMaterials("løsholte");
        List<Integer> sizes = collectSizes(material);
        size = findSize(order.getShedWidth() / 2, sizes);
        int amount = (int) Math.ceil(order.getShedWidth() / size) * 6;//3 i højden i begge sider
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private PartLine loesholterSider() throws CustomException {
        List<Material> material = MaterialMapper.getMaterials("løsholte");
        List<Integer> sizes = collectSizes(material);
        size = findSize(order.getShedLength() / 2, sizes);
        int amount = (int) Math.ceil(order.getShedLength() / size) * 4;//2 i højden i begge sider
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private PartLine remSkur() throws CustomException {
        List<Material> material = MaterialMapper.getMaterials("rem");
        List<Integer> sizes = collectSizes(material);
        size = findSize(order.getShedLength(), sizes);
        int amount = (int) Math.ceil(order.getShedLength() / size) * 2;
        return new PartLine(material.get(1), amount).setSize(size);
    }

    private PartLine beklaedning() throws CustomException {
        List<Material> material = MaterialMapper.getMaterials("beklaedning");
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

        return new PartLine(material.get(1), amount).setSize(210);
    }

    private PartLine plastmoBundskruer() {
        size = length * width;
        int amount = (size / 100) * 24;
        return new PartLine("plastmo bundskruer", amount, 200);
    }

    private PartLine hulbånd() {
        return new PartLine("hulbånd 1x20mm 10mtr", 2, 1);
    }

    private PartLine universalBeslagH() {
        int amount = 0;
        if (!order.isFlat()) {
            throw new UnsupportedOperationException("not implemented yet");
        } else {
            amount = (int) Math.ceil(length / 55) + 1;
        }
        return new PartLine("universal 190mm højre", amount, 1);
    }

    private PartLine universalBeslagV() {
        int amount = 0;
        if (!order.isFlat()) {
            throw new UnsupportedOperationException("not implemented yet");
        } else {
            amount = (int) Math.ceil(length / 55) + 1;
        }
        return new PartLine("universal 190mm venstre", amount, 1);
    }

    private ArrayList<PartLine> skruer() {
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

        parts.add(new PartLine("4,5 x 60 mm. skruer", 200, 200));
        parts.add(new PartLine("4,0 x 50 mm. skruer", 750, 250));

        return parts;
    }

    private PartLine stalddørsgreb() {
        return new PartLine("stalddørsgreb 50 x 75", 1, 2);
    }

    private PartLine tHængsel() {
        return new PartLine("t hængsel 190mm", 1, 1);
    }

    private PartLine vinkelbeslag() {
        int amount = (int) Math.ceil(order.getShedLength() / size) * 4;//2 i højden i begge sider
        amount += (int) Math.ceil(order.getShedWidth() / size) * 6;//3 i højden i begge sider

        return new PartLine("vinkelbeslag 35", amount, 1);
    }

    private int findSize(int length, List<Integer> sizes) {
        best = Integer.MAX_VALUE;
        size = 0;

        for (int i = sizes.size(); i > 0; i--) {
            tmp = length % sizes.get(i);
            if (tmp < best) {
                size = sizes.get(i);
                best = tmp;
            }
        }
        return size;
    }

    private List<Integer> collectSizes(List<Material> list) {
        List<Integer> sizes = new ArrayList();
        for (Material material : list) {
            sizes.add(material.getSize());
        }
        return sizes;
    }

    private void addParts() throws CustomException {
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

        if (order.hasShed()) {
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
    public List<PartLine> getParts() {
        return list;
    }
}
