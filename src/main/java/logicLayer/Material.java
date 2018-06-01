/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLayer;

/**
 * A material can be different things, such as a plank, a tool, a screw etc. it
 * contains the needed information of that specific item.
 *
 * @author Stephan
 */
public class Material {

    private int id;
    private String name;
    private int price;
    private int size = 0;
    private String description;
    private int unitSize = 1;

    /**
     *
     * @return name of the material
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name materials new name
     * @return set the name of material
     */
    public Material setName(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @return id of the material
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id materials new id
     * @return this
     */
    public Material setId(int id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @return the price as an integer
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @param price set price of material
     * @return this
     * @throws logicLayer.CustomException if price is negative
     */
    public Material setPrice(int price) throws CustomException {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new CustomException("Pris kan ikke være negativ");
        }

        return this;
    }

    /**
     *
     * @return the price
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @param size set a new size
     * @return this
     * @throws logicLayer.CustomException if size is negative
     */
    public Material setSize(int size) throws CustomException {
        if (size >= 0) {
            this.size = size;
        } else {
            throw new CustomException("Støresle kan ikke være negativ");
        }

        return this;
    }

    /**
     *
     * @return material description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description set new mateiral description
     * @return this
     */
    public Material setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     *
     * @return the unitSize
     */
    public int getUnitSize() {
        return unitSize;
    }

    /**
     *
     * @param unitSize sets new unitSize
     * @return this
     * @throws logicLayer.CustomException if unitSize is negative
     */
    public Material setUnitSize(int unitSize) throws CustomException {
        if (unitSize >= 0) {
            this.unitSize = unitSize;
        } else {
            throw new CustomException("Enhedsstørelse kan ikke være negativ");
        }
        return this;
    }
}
