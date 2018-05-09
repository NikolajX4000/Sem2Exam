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
     * @param material
     * @param amount
     */
    public PartLine(Material material, int amount) {
        this.material = material;
        this.unit = material.getUnitSize();
        this.amount = (int) Math.ceil((double)amount / unit);
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
        return (int) Math.ceil( material.getPrice() * (size / 100d) * amount );
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
     */
    public PartLine setSize(double size) {
        this.size = (int)size;
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

    @Override
    public String toString()
    {
        return material.getDescription()+ " : " + size + " : " + amount + " : " + unit + ": " + material.getName();
    }
    
    

}
