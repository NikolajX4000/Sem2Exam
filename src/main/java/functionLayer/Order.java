/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer;

import storageLayer.StorageFacade;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import presentationLayer.DrawCarportAngleSide;
import presentationLayer.DrawCarportAngleTop;
import presentationLayer.DrawCarportFlatSide;
import presentationLayer.DrawCarportFlatTop;

/**
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

    public int calculatePrice() throws CustomException {
        if (isFlat()) {
            partsList = new FlatCarPortList(this).getParts();
        } else {
            partsList = new TallCarPortList(this).getParts();
        }
        price = 0;
        for (PartLine p : partsList) {
            price += p.calculatePrice();
        }
        return price;
    }

    /**
     *
     * @return
     */
    public int getPriceInt() throws CustomException {

        if (price == 0) {
            calculatePrice();
        }

        return price;

    }

    /**
     *
     * @return
     */
    public String getPrice() throws CustomException {

        if (price == 0) {
            calculatePrice();
        }

        NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);
        return nf.format(price) + " kr.";

    }

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
     * @return
     */
    public String getStringId() {
        if (stringId == null) {
            stringId = generateStringId(20);
        }
        return stringId;
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
    public Order setId(int id) {
        this.id = id;
        return this;
    }

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
    public Order setName(String name) {
        this.name = name;
        return this;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * @return
     */
    public Order setAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     *
     * @return
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     *
     * @param zipCode
     * @return
     */
    public Order setZipCode(int zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * @return
     */
    public Order setCity(String city) {
        this.city = city;
        return this;
    }

    /**
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * @return
     */
    public Order setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * @return
     */
    public Order setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     *
     * @return
     */
    public String getNote() {
        return note;
    }

    /**
     *
     * @param note
     * @return
     */
    public Order setNote(String note) {
        this.note = note;
        return this;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @param width
     * @return
     */
    public Order setWidth(int width) {
        this.width = width;
        return this;
    }

    /**
     *
     * @return
     */
    public int getLength() {
        return length;
    }

    /**
     *
     * @param length
     * @return
     */
    public Order setLength(int length) {
        this.length = length;
        return this;
    }

    /**
     *
     * @return @throws functionLayer.CustomException
     */
    public Roof getRoof() throws CustomException {
        return StorageFacade.getRoofById(roof);
    }

    /**
     *
     * @param roof
     * @return
     */
    public Order setRoof(int roof) {
        this.roof = roof;
        return this;
    }

    /**
     *
     * @return
     */
    public int getAngle() {
        return angle;
    }

    /**
     *
     * @param angle
     * @return
     */
    public Order setAngle(int angle) {
        this.angle = angle;
        return this;
    }

    /**
     *
     * @return
     */
    public int getShedWidth() {
        return shedWidth;
    }

    /**
     *
     * @param shedWidth
     * @return
     */
    public Order setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
        return this;
    }

    /**
     *
     * @return
     */
    public int getShedLength() {
        return shedLength;
    }

    /**
     *
     * @param shedLength
     * @return
     */
    public Order setShedLength(int shedLength) {
        this.shedLength = shedLength;
        return this;
    }

    /**
     *
     * @return
     */
    public String getPlaced() {
        return placed;
    }

    /**
     *
     * @param placed
     * @return
     */
    public Order setPlaced(String placed) {
        this.placed = placed;
        return this;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * @return
     */
    public Order setStatus(String status) {
        this.status = status;
        return this;
    }

    public int getMaterialPrice() {
        return materialPrice;
    }

    public Order setMaterialPrice(int materialPrice) {
        this.materialPrice = materialPrice;
        return this;
    }
    
    public String getDrawingSide(){
        if(isFlat())
            return new DrawCarportFlatSide(this).getDrawing();       
        else
            return new DrawCarportAngleSide(this).getDrawing();
    }
    
    public String getDrawingTop(){
        if(isFlat())
            return new DrawCarportFlatTop(this).getDrawing();       
        else
            return new DrawCarportAngleTop(this).getDrawing();
    }
    
    public List<PartLine> getPartlist() throws CustomException{
        
        if(isFlat())
            return new FlatCarPortList(this).getParts();       
        else
            return new TallCarPortList(this).getParts();
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", name=" + name + ", address=" + address + ", zipCode=" + zipCode + ", city=" + city + ", phone=" + phone + ", email=" + email + ", note=" + note + ", width=" + width + ", length=" + length + ", roof=" + roof + ", angle=" + angle + ", shedWidth=" + shedWidth + ", shedLength=" + shedLength + ", placed=" + placed + ", status=" + status + '}';
    }

}
