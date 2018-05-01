/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

/**
 *
 * @author Stephan
 */
public class Order {
    
    /* order */
    private int id;
    private String stringId;
    
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
    private String status ="Behandles";
    
    public String getPrice(){
        
        int p = 500;
        
        
        if(hasShed()){
            p += shedWidth*shedLength*400;
        }
        
        if(isFlat()){
            
            p+= width*length*300;
        }else{
            
            p+= width*length*666;
        }
        
        p /= 10000;
        
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMAN);
        
        
        return nf.format(p) + " kr.";
        
    }
    
    
    /** 
     * Checks if carport has shed
     * @return true if it does and false if it doesnt
     */
    public boolean hasShed(){
        return (shedWidth > 0 || shedLength > 0);
    }
    
    /**
     * Checks if roof is flat
     * @return returns true if it is and false if it isnt
     */
    public boolean isFlat(){
        return (angle == 0);
    }
    
    /**
     * Find the color associated with the current status
     * @return a color as a string
     */
    public String getStatusColor(){
        
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
    
    private String generateStringId(int amount){
        amount--;
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String randomString = "x";
        Random rand = new Random();
        char[] text = new char[amount];
        
        for (int i = 0; i < amount; i++) {
            text[i] = chars.charAt(rand.nextInt(chars.length()));
        }
        for (int i = 0; i < text.length-1; i++) {
            randomString += text[i];
        }
        return randomString;
    }
    
    public String getStringId(){
        if(stringId == null){
            stringId = generateStringId(20);
        }
        return stringId;
    }
    
    public int getId() {
        return id;
    }

    public Order setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Order setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Order setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getZipCode() {
        return zipCode;
    }

    public Order setZipCode(int zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Order setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Order setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Order setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Order setNote(String note) {
        this.note = note;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public Order setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getLength() {
        return length;
    }

    public Order setLength(int length) {
        this.length = length;
        return this;
    }

    public int getRoof() {
        return roof;
    }

    public Order setRoof(int roof) {
        this.roof = roof;
        return this;
    }

    public int getAngle() {
        return angle;
    }

    public Order setAngle(int angle) {
        this.angle = angle;
        return this;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public Order setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
        return this;
    }

    public int getShedLength() {
        return shedLength;
    }

    public Order setShedLength(int shedLength) {
        this.shedLength = shedLength;
        return this;
    }

    public String getPlaced() {
        return placed;
    }

    public Order setPlaced(String placed) {
        this.placed = placed;
        return this;
    }
    
    public String getStatus() {
        return status;
    }

    public Order setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString()
    {
        return "Order{" + "id=" + id + ", name=" + name + ", address=" + address + ", zipCode=" + zipCode + ", city=" + city + ", phone=" + phone + ", email=" + email + ", note=" + note + ", width=" + width + ", length=" + length + ", roof=" + roof + ", angle=" + angle + ", shedWidth=" + shedWidth + ", shedLength=" + shedLength + ", placed=" + placed + ", status=" + status + '}';
    }
    
    
}
