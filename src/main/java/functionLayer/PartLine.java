package functionLayer;

import java.util.*;

public class PartLine {
    
    String name;
    int size;
    int amount;
    int unit;
    String description;

    public PartLine(String name, int amount, int unit)
    {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public PartLine setSize(int size)
    {
        this.size = size;
        return this;
    }

    public PartLine setDescription(String description)
    {
        this.description = description;
        return this;
    }

    public String getName()
    {
        return name;
    }

    public int getSize()
    {
        return size;
    }

    public int getAmount()
    {
        return amount;
    }

    public int getUnit()
    {
        return unit;
    }

    public String getDescription()
    {
        return description;
    }

    
    

}
