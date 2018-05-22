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
    private int size = 0;
    private String description;
    private int unitSize = 1;

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
    public Material setPrice(int price) throws CustomException {
        if ( price >= 0 ) {
            this.price = price;
        } else throw new CustomException( "Pris kan ikke være negativ" );
        
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
    public Material setSize(int size) throws CustomException {
        if ( size >= 0 ) {
            this.size = size;
        } else throw new CustomException( "Støresle kan ikke være negativ" );
        
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

    /**
     *
     * @return
     */
    public int getUnitSize() {
        return unitSize;
    }

    /**
     *
     * @param unitSize
     * @return
     */
    public Material setUnitSize(int unitSize) throws CustomException {
        if ( unitSize >= 0 ) {
            this.unitSize = unitSize;
        } else throw new CustomException( "Enhedsstørelse kan ikke være negativ" );
        return this;
    }
}
