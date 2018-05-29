package logicLayer;

/**
 *
 * @author super
 */
public class PartLine {

    private Material material;
    private int size;
    private int amount;
    private int unit;
    private String description;

    /**
     *
     * @param material
     * @param amount
     */
    public PartLine(Material material, int amount)  {
        this.material = material;
        this.unit = material.getUnitSize();
        this.amount = (int) Math.ceil((double) amount / unit);
        this.size = material.getSize();
        this.description = material.getDescription();
    }

    /**
     *
     * @param name
     * @param amount
     * @param unit
     */
//    public PartLine(String name, int amount, int unit) {
//        // Comming soon
//    }
    /**
     *
     * @return
     */
    public int calculatePrice() {
        int price = 0;
        if (material.getSize() != 0) {
            price += material.getPrice() * (material.getSize() / 100) * amount;
        } else {
            price += material.getPrice() * amount;
        }
        return price;
    }

    /**
     *
     * @return
     */
    public Material getMaterial() {
        return material;
    }

    /**
     *
     * @param material
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     *
     * @param size
     * @return
     * @throws logicLayer.CustomException
     */
    public PartLine setSize(double size) throws CustomException {
        this.size = (int) size;
        material.setSize((int) size);
        return this;
    }

    /**
     *
     * @param description
     * @return
     */
    public PartLine setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     *
     * @return
     */
    public int getSize() {
        return material.getSize();
    }

    /**
     *
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     *
     * @return
     */
    public int getUnit() {
        return unit;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return material.getDescription() + " : " + size + " : " + amount + " : " + unit + ": " + material.getName();
    }

}
