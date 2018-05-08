/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer;

/**
 *
 * @author Stephan
 */
public class Material {
    private int id;
    private String name;
    private int price;
    private int size;
    private String description;
    private int unitSize;

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * @return
     */
    public Material setName(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * @return
     */
    public Material setId(int id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @return
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * @return
     */
    public Material setPrice(int price) {
        this.price = price;
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
     * @param size
     * @return
     */
    public Material setSize(int size) {
        this.size = size;
        return this;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * @return
     */
    public Material setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getUnitSize()
    {
        return unitSize;
    }

    public void setUnitSize(int unitSize)
    {
        this.unitSize = unitSize;
    }
}
