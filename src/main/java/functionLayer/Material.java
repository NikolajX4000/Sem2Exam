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

    public String getName() {
        return name;
    }

    public Material setName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return id;
    }

    public Material setId(int id) {
        this.id = id;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public Material setPrice(int price) {
        this.price = price;
        return this;
    }

    public int getSize() {
        return size;
    }

    public Material setSize(int size) {
        this.size = size;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Material setDescription(String description) {
        this.description = description;
        return this;
    }
}
