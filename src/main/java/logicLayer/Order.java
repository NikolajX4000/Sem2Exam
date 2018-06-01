/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLayer;

import storageLayer.StorageFacade;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * An order includes everything about a specific Order It has the partlist,
 * contact information, the drawings, and the general order information like
 * status, price, date of order etc.
 *
 * @author Stephan
 */
public class Order {

    /* order */
    private int id;
    private String stringId;
    private List<PartLine> partsList;
    private int price = 0;
    private int materialPrice = 0;

    /* customer */
    private String name;
    private String address;
    private int zipCode;
    private String city;
    private String phone;
    private String email;
    private String note = "";

    /* carport */
    private int width;
    private int length;

    /* roof */
    private int roof;
    private int angle = 0;

    /* shed */
    private int shedWidth = 0;
    private int shedLength = 0;

    /* dates */
    private String placed;

    /* status */
    private String status = "Behandles";

    /**
     *
     * @return calculated price from partlist
     * @throws CustomException may throw CustomException thrown from
     * MaterialMapper.java
     */
    public int calculatePrice() throws CustomException {
        materialPrice = 0;
        for (PartLine p : getPartlist()) {
            materialPrice += p.calculatePrice();
        }
        return materialPrice;
    }

    /**
     *
     * @return the price unformatted
     * @throws logicLayer.CustomException may throw CustomException thrown from
     * MaterialMapper.java
     */
    public int getPriceInt() throws CustomException {

        if (price == 0) {
            price = calculatePrice();
        }

        return price;

    }

    /**
     *
     * @return the price formatted
     * @throws logicLayer.CustomException may throw CustomException thrown from
     * MaterialMapper.java
     */
    public String getPrice() throws CustomException {

        if (price == 0) {
            calculatePrice();
        }

        NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);
        return nf.format(price) + " kr.";

    }

    /**
     *
     * @param price sets the price
     * @return this
     */
    public Order setPrice(int price) {
        this.price = price;
        return this;
    }

    /**
     * Checks if carport has shed
     *
     * @return true if it does and false if it doesn't
     */
    public boolean hasShed() {
        return (shedWidth > 0 && shedLength > 0);
    }

    /**
     * Checks if roof is flat
     *
     * @return returns true if it is and false if it isn't
     */
    public boolean isFlat() {
        return (angle == 0);
    }

    /**
     * Find the color associated with the current status
     *
     * @return a color as a string
     */
    public String getStatusColor() {

        String color = "orange";
        switch (status) {
            case "Modtaget":
                color = "green";
                break;
            case "Sendt":
                color = "blue";
                break;
            case "Annulleret":
                color = "red";
                break;
            case "Behandles":
                color = "orange";
                break;
        }
        return color;
    }

    private String generateStringId(int amount) {
        amount--;
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String randomString = "x";
        Random rand = new Random();
        char[] text = new char[amount];

        for (int i = 0; i < amount; i++) {
            text[i] = chars.charAt(rand.nextInt(chars.length()));
        }
        for (int i = 0; i < text.length - 1; i++) {
            randomString += text[i];
        }
        return randomString;
    }

    /**
     *
     * @return randomly generalted unique string for this Order
     */
    public String getStringId() {
        if (stringId == null) {
            stringId = generateStringId(20);
        }
        return stringId;
    }

    /**
     *
     * @return this Orders id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id sets this Orders id
     * @return this
     */
    public Order setId(int id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @return this Orders name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name set this Orders name
     * @return this
     */
    public Order setName(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @return this Orders address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address sets this Orders address
     * @return this
     */
    public Order setAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     *
     * @return this Orders zipCode
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     *
     * @param zipCode sets this Orders zipCode
     * @return this
     */
    public Order setZipCode(int zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    /**
     *
     * @return this Orders city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city sets this Orders city
     * @return this
     */
    public Order setCity(String city) {
        this.city = city;
        return this;
    }

    /**
     *
     * @return this Orders phonenumber
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone sets this Orders phonenumber
     * @return this
     */
    public Order setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     *
     * @return this Orders email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email sets this Orders email
     * @return this
     */
    public Order setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     *
     * @return this Orders note
     */
    public String getNote() {
        return note;
    }

    /**
     *
     * @param note sets this Orders note
     * @return this
     */
    public Order setNote(String note) {
        this.note = note;
        return this;
    }

    /**
     *
     * @return the carports width
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @param width sets the carports width
     * @return this
     */
    public Order setWidth(int width) {
        this.width = width;
        return this;
    }

    /**
     *
     * @return the carports length
     */
    public int getLength() {
        return length;
    }

    /**
     *
     * @param length sets the carports length
     * @return this
     */
    public Order setLength(int length) {
        this.length = length;
        return this;
    }

    /**
     *
     * @return the roof corosponding to this
     * @throws logicLayer.CustomException may throw CustomException thrown from
     * RoofMapper.java
     */
    public Roof getRoof() throws CustomException {
        return StorageFacade.getRoofById(roof);
    }

    /**
     *
     * @param roof sets the carports roof to its ID
     * @return this
     */
    public Order setRoof(int roof) {
        this.roof = roof;
        return this;
    }

    /**
     *
     * @return the carportts roof angle
     */
    public int getAngle() {
        return angle;
    }

    /**
     *
     * @param angle sets the carports roof angle
     * @return this
     */
    public Order setAngle(int angle) {
        this.angle = angle;
        return this;
    }

    /**
     *
     * @return the shed width
     */
    public int getShedWidth() {
        return shedWidth;
    }

    /**
     *
     * @param shedWidth sets the shed width
     * @return this
     */
    public Order setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
        return this;
    }

    /**
     *
     * @return the shed length
     */
    public int getShedLength() {
        return shedLength;
    }

    /**
     *
     * @param shedLength sets the shed length
     * @return this
     */
    public Order setShedLength(int shedLength) {
        this.shedLength = shedLength;
        return this;
    }

    /**
     *
     * @return the date order was placed
     */
    public String getPlaced() {
        return placed;
    }

    /**
     *
     * @param placed sets the date Order was placed
     * @return this
     */
    public Order setPlaced(String placed) {
        this.placed = placed;
        return this;
    }

    /**
     *
     * @return status of the Order
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status sets Orders status
     * @return this
     */
    public Order setStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     *
     * @return gets the material price
     */
    public int getMaterialPrice() {
        return materialPrice;
    }

    /**
     *
     * @param materialPrice sets material price
     * @return this
     * @throws CustomException may throw CustomException thrown from
     * MaterialMapper.java
     */
    public Order setMaterialPrice(int materialPrice) throws CustomException {
        calculatePrice();
        return this;
    }

    /**
     *
     * @return svg drawing of carport from the side
     */
    public String getDrawingSide() {
        if (isFlat()) {
            return new DrawCarportFlatSide(this).getDrawing();
        } else {
            return new DrawCarportAngleSide(this).getDrawing();
        }
    }

    /**
     *
     * @return svg drawing of carport seem from the top
     */
    public String getDrawingTop() {
        if (isFlat()) {
            return new DrawCarportFlatTop(this).getDrawing();
        } else {
            return new DrawCarportAngleTop(this).getDrawing();
        }
    }

    /**
     *
     * @return the partlist
     * @throws CustomException may throw CustomException thrown from
     * MaterialMapper.java
     */
    public List<PartLine> getPartlist() throws CustomException {
        if (partsList == null) {

            if (isFlat()) {
                partsList = new FlatCarPortList(this).getParts();
            } else {
                partsList = new TallCarPortList(this).getParts();
            }
        }
        return partsList;
    }

    /**
     *
     * @return Order as concatenated string
     */
    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", name=" + name + ", address=" + address + ", zipCode=" + zipCode + ", city=" + city + ", phone=" + phone + ", email=" + email + ", note=" + note + ", width=" + width + ", length=" + length + ", roof=" + roof + ", angle=" + angle + ", shedWidth=" + shedWidth + ", shedLength=" + shedLength + ", placed=" + placed + ", status=" + status + '}';
    }

}
