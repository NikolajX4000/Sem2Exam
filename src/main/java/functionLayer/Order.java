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
public class Order {
    
    /* order */
    private int id;
    
    /* customer */
    private String name;
    private String address;
    private int zipCode;
    private String city;
    private String phone;
    private String email;
    private String note;
    
    /* carport */
    private int width;
    private int length;
    
    /* roof */
    private int roof;
    private int angle;
    
    /* shed */
    private int shedWidth;
    private int shedLength;
    
    /* dates */
    private String placed;
    
    /* status */
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getRoof() {
        return roof;
    }

    public void setRoof(int roof) {
        this.roof = roof;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
    }

    public int getShedLength() {
        return shedLength;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }

    public String getPlaced() {
        return placed;
    }

    public void setPlaced(String placed) {
        this.placed = placed;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
