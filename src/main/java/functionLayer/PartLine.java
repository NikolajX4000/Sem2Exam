package functionLayer;

import java.util.*;

/**
 *
 * @author super
 */
public class PartLine
{

    Material material;
    int size;
    int amount;
    int unit;
    String description;

    /**
     *
     * @param amount
     * @param unit
     */
    public PartLine(Material material, int amount) {
        this.material = material;
        this.amount = (int) Math.ceil(amount / unit);
    }

    public int calculatePrice() {
        return (int) Math.ceil( material.getPrice() * (size / 100d) * amount );
    }
    
    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     *
     * @param size
     * @return
     */
    public PartLine setSize(int size) {
        this.size = size;
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
        return size;
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
    public String getDescription()
    {
        return description;
    }

}
