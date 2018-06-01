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
     * @param material the material of the partline
     * @param amount the amount of the given material
     */
    public PartLine(Material material, int amount) {
        this.material = material;
        this.unit = material.getUnitSize();
        this.amount = (int) Math.ceil((double) amount / unit);
        this.size = material.getSize();
        this.description = material.getDescription();
    }

    /**
     *
     * @return the price per unit times the amount
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
     * @return the material of the partline
     */
    public Material getMaterial() {
        return material;
    }

    /**
     *
     * @param material the material of the partline
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     *
     * @param size the length of the planks
     * @return this
     * @throws logicLayer.CustomException if size is negative
     */
    public PartLine setSize(double size) throws CustomException {
        this.size = (int) size;
        material.setSize((int) size);
        return this;
    }

    /**
     *
     * @param description the description of the material
     * @return this
     */
    public PartLine setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     *
     * @return the length of the planks
     */
    public int getSize() {
        return material.getSize();
    }

    /**
     *
     * @return the amount of planks
     */
    public int getAmount() {
        return amount;
    }

    /**
     *
     * @return the unit of the material
     */
    public int getUnit() {
        return unit;
    }

    /**
     *
     * @return the description of the material
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return material.getDescription() + " : " + size + " : " + amount + " : " + unit + ": " + material.getName();
    }

}
