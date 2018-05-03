package functionLayer;

import java.util.*;

/**
 *
 * @author super
 */
public class PartLine
{

    String name;
    int size;
    int amount;
    int unit;
    String description;

    /**
     *
     * @param name
     * @param amount
     * @param unit
     */
    public PartLine(String name, int amount, int unit)
    {
        this.name = name;
        this.unit = unit;
        this.amount = (int) Math.ceil(amount / unit);
    }

    /**
     *
     * @param size
     * @return
     */
    public PartLine setSize(int size)
    {
        this.size = size;
        return this;
    }

    /**
     *
     * @param description
     * @return
     */
    public PartLine setDescription(String description)
    {
        this.description = description;
        return this;
    }

    /**
     *
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @return
     */
    public int getSize()
    {
        return size;
    }

    /**
     *
     * @return
     */
    public int getAmount()
    {
        return amount;
    }

    /**
     *
     * @return
     */
    public int getUnit()
    {
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
