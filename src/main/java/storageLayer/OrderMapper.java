/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storageLayer;

import functionLayer.Order;
import functionLayer.CustomException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Stephan
 */
public class OrderMapper {
    /**
     * This method adds a Order object to the database 
     * and returns the object with the auto-generated id key and status.  
     * @param order
     * @return the new order object with the generated id key and status
     * @throws CustomException 
     */
    public static Order addOrder( Order order ) throws CustomException {
        try {
            Connection con = Connector.connection();
            String SQL  = "INSERT INTO orders ("
            /* customer */  + "name, address, zipcode, city, phone, email, note, "
            /* carport */   + "width, length, "
            /* roof */      + "roof, angle, "
            /* shed */      + "shed_width, shed_length, "
            /* dates */     + "placed) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement ps = con.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
            
            try {
                /* customer */
                ps.setString( 1, order.getName() );
                ps.setString( 2, order.getAddress() );
                ps.setInt( 3, order.getZipcode() );
                ps.setString( 4, order.getCity() );
                ps.setString( 5, order.getPhone() );
                ps.setString( 6, order.getEmail() );
                ps.setString( 7, order.getNote() );

                /* carport */
                ps.setInt( 8, order.getWidth() );
                ps.setInt( 9, order.getLength() );

                /* roof */
                ps.setInt( 10, order.getRoof() );
                ps.setInt( 11, order.getAngle() );

                /* shed */
                ps.setInt( 12, order.getShedWidth() );
                ps.setInt( 13, order.getShedLength() );

                /* dates */
                ps.setString( 14, order.getPlaced() );
                
            } catch ( SQLException ex ) {
                throw new CustomException( "Formateringsfejl" );
            }

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.first()) {
                order.setId( rs.getInt( 1 ) );
                order.setStatus( rs.getString( "status" ));
            }
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new CustomException( ex.getMessage() );
        }
        return order;
    } 
    
    /**
     * This method fetch a Order object from the database 
     * with a specific order_id.
     * @param id
     * @return order with specific order_id from the param 'id'
     * @throws CustomException 
     */
    public static Order getOrder( int id ) throws CustomException {
        Order order = new Order();
        try {
            Connection con = Connector.connection();
            String SQL  = "SLELECT * FROM orders "
                        + "WHERE order_id = ?";
            
            PreparedStatement ps = con.prepareStatement( SQL );
            ps.setInt( 1, id );
            ResultSet rs = ps.executeQuery();
            
            if (rs.first()) {
                order.setId( id );
                
                /* customer */
                order.setName( rs.getString( "name" ) );
                order.setAddress( rs.getString( "address" ) );
                order.setZipcode( rs.getInt( "zipcode" ) );
                order.setCity( rs.getString( "city" ) );
                order.setPhone( rs.getString( "phone" ) );
                order.setEmail( rs.getString( "email" ) );
                order.setNote( rs.getString( "note" ) );
                
                /* carport */
                order.setWidth( rs.getInt( "width" ) );
                order.setLength( rs.getInt( "length" ) );
                
                /* roof */
                order.setRoof( rs.getInt( "roof" ) );
                order.setAngle( rs.getInt( "angle" ) );
                
                /* shed */
                order.setShedWidth( rs.getInt( "shed_width" ) );
                order.setShedLength( rs.getInt( "shed_length" ) );
                
                /* dates */
                order.setPlaced( rs.getString( "placed" ) );
                
                /* status */
                order.setPlaced( rs.getString( "status" ) );
            }
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new CustomException( ex.getMessage() );
        }
        return order;
    } 
    
    /**
     * This method fetch an ArrayList containing all 
     * the Orders a specific email has placed.
     * @param email
     * @return list of orders from customer with specific email from the param 'email'
     * @throws CustomException 
     */
    public static ArrayList<Order> getOrders( String email ) throws CustomException {
        Order order = new Order();
        ArrayList<Order> orders = new ArrayList();
        try {
            Connection con = Connector.connection();
            String SQL  = "SLELECT * FROM orders "
                        + "WHERE email = ?";
            
            PreparedStatement ps = con.prepareStatement( SQL );
            ps.setString( 1, email );
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                order.setId( rs.getInt( "order_id" ) );
                
                /* customer */
                order.setName( rs.getString( "name" ) );
                order.setAddress( rs.getString( "address" ) );
                order.setZipcode( rs.getInt( "zipcode" ) );
                order.setCity( rs.getString( "city" ) );
                order.setPhone( rs.getString( "phone" ) );
                order.setEmail( rs.getString( "email" ) );
                order.setNote( rs.getString( "note" ) );
                
                /* carport */
                order.setWidth( rs.getInt( "width" ) );
                order.setLength( rs.getInt( "length" ) );
                
                /* roof */
                order.setRoof( rs.getInt( "roof" ) );
                order.setAngle( rs.getInt( "angle" ) );
                
                /* shed */
                order.setShedWidth( rs.getInt( "shed_width" ) );
                order.setShedLength( rs.getInt( "shed_length" ) );
                
                /* dates */
                order.setPlaced( rs.getString( "placed" ) );
                
                /* status */
                order.setPlaced( rs.getString( "status" ) );
                
                orders.add( order );
            }
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new CustomException( ex.getMessage() );
        }
        return orders;
    } 
    
    /**
     * This method fetch an ArrayList containing all placed Orders.
     * @return all orders as a ArrayList
     * @throws CustomException 
     */
    public static ArrayList<Order> getAllOrders() throws CustomException {
        Order order = new Order();
        ArrayList<Order> orders = new ArrayList();
        try {
            Connection con = Connector.connection();
            String SQL  = "SLELECT * FROM orders";
            
            PreparedStatement ps = con.prepareStatement( SQL );
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                order.setId( rs.getInt( "order_id" ) );
                
                /* customer */
                order.setName( rs.getString( "name" ) );
                order.setAddress( rs.getString( "address" ) );
                order.setZipcode( rs.getInt( "zipcode" ) );
                order.setCity( rs.getString( "city" ) );
                order.setPhone( rs.getString( "phone" ) );
                order.setEmail( rs.getString( "email" ) );
                order.setNote( rs.getString( "note" ) );
                
                /* carport */
                order.setWidth( rs.getInt( "width" ) );
                order.setLength( rs.getInt( "length" ) );
                
                /* roof */
                order.setRoof( rs.getInt( "roof" ) );
                order.setAngle( rs.getInt( "angle" ) );
                
                /* shed */
                order.setShedWidth( rs.getInt( "shed_width" ) );
                order.setShedLength( rs.getInt( "shed_length" ) );
                
                /* dates */
                order.setPlaced( rs.getString( "placed" ) );
                
                /* status */
                order.setPlaced( rs.getString( "status" ) );
                
                orders.add( order );
            }
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new CustomException( ex.getMessage() );
        }
        return orders;
    } 
    
    /**
     * This Method updates one or more details for an Order object.
     * @param order
     * @return an order object where one or more variables has been updated
     * @throws CustomException 
     */
    public static Order updateOrder( Order order ) throws CustomException {
        try {
            Connection con = Connector.connection();
            String SQL  = "UPDATE orders SET "
            /* carport */   + "width = ?, length = ?, "
            /* roof */      + "roof = ?, angle = ?, "
            /* shed */      + "shed_width = ?, shed_length = ?, "
            /* status */    + "status = ? "
                        + "WHERE order_id = ?";
            
            PreparedStatement ps = con.prepareStatement( SQL );
            
            try {
                /* carport */
                ps.setInt( 1, order.getWidth() );
                ps.setInt( 2, order.getLength() );

                /* roof */
                ps.setInt( 3, order.getRoof() );
                ps.setInt( 4, order.getAngle() );

                /* shed */
                ps.setInt( 5, order.getShedWidth() );
                ps.setInt( 6, order.getShedLength() );

                /* status */
                ps.setString( 7, order.getStatus() );
                
                /* order id */
                ps.setInt( 7, order.getId() );
                
            } catch ( SQLException ex ) {
                throw new CustomException( "Formateringsfejl" );
            }
            ps.executeUpdate();
            
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new CustomException( ex.getMessage() );
        }
        return order;
    } 
    
    /**
     * This method updates an orders status.
     * @param order
     * @return an order object where status has been updated
     * @throws CustomException 
     */
    public static Order updateStatus( Order order ) throws CustomException {
        try {
            Connection con = Connector.connection();
            String SQL  = "UPDATE orders "
                        + "SET status = ? "
                        + "WHERE order_id = ?";
            
            PreparedStatement ps = con.prepareStatement( SQL );
            
            try {
                ps.setString( 1, order.getStatus() );
                ps.setInt( 2, order.getId() );
                
            } catch ( SQLException ex ) {
                throw new CustomException( "Formateringsfejl" );
            }
            ps.executeUpdate();
            
        } catch ( SQLException | ClassNotFoundException ex ) {
            throw new CustomException( ex.getMessage() );
        }
        return order;
    } 
}
