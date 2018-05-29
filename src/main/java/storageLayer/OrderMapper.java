/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storageLayer;

import logicLayer.Order;
import logicLayer.CustomException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    /**
     * Add order to database.
     * This method calls the database with a prepared statement to 
     * request an insert to ad an order into the orders table.
     *
     * @param order The new order object. Should not be null.
     * @return The new order object with the generated id key and status.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
    public static Order addOrder(Order order) throws CustomException {
        PreparedStatement ps = null;

        try {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO orders ("
                    /* customer */ + "name, address, zip_code, city, phone, email, note, "
                    /* carport */ + "width, length, "
                    /* roof */ + "roof_id, angle, "
                    /* shed */ + "shed_width, shed_length, "
                    /* price */ + "material_price, price) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            /* customer */
            ps.setString(1, order.getName());
            ps.setString(2, order.getAddress());
            ps.setInt(3, order.getZipCode());
            ps.setString(4, order.getCity());
            ps.setString(5, order.getPhone());
            ps.setString(6, order.getEmail());
            ps.setString(7, order.getNote());

            /* carport */
            ps.setInt(8, order.getWidth());
            ps.setInt(9, order.getLength());

            /* roof */
            ps.setInt(10, order.getRoof().getID());
            ps.setInt(11, order.getAngle());

            /* shed */
            ps.setInt(12, order.getShedWidth());
            ps.setInt(13, order.getShedLength());

            /* price */
            ps.setInt(14, order.getMaterialPrice());
            ps.setInt(15, order.getPriceInt());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if(rs.first()) {
                order.setId(rs.getInt(1));
            }

        } catch(SQLException | ClassNotFoundException ex) {
            throw new CustomException(ex.getMessage());
        } finally {
            closeStatement(ps);
        }
        return order;
    }

    /**
     * Get Order by id.
     * This method calls the database with a prepared statement to 
     * request an element from the orders table by it's 'order_id'.
     *
     * @param id The id of the order, should not be out of index bounds.
     * @return An order object with requested id.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
    public static Order getOrder(int id) throws CustomException {
        PreparedStatement ps = null;
        Order order = new Order();

        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM orders "
                    + "WHERE order_id = ?";

            ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.first()) {
                order.
                        /* order id */
                        setId(id).
                        /* customer */
                        setName(rs.getString("name")).
                        setAddress(rs.getString("address")).
                        setZipCode(rs.getInt("zip_code")).
                        setCity(rs.getString("city")).
                        setPhone(rs.getString("phone")).
                        setEmail(rs.getString("email")).
                        setNote(rs.getString("note")).
                        /* carport */
                        setWidth(rs.getInt("width")).
                        setLength(rs.getInt("length")).
                        /* roof */
                        setRoof(rs.getInt("roof_id")).
                        setAngle(rs.getInt("angle")).
                        /* shed */
                        setShedWidth(rs.getInt("shed_width")).
                        setShedLength(rs.getInt("shed_length")).
                        /* dates */
                        setPlaced(rs.getString("placed").substring(0, 10)).
                        /* status */
                        setStatus(rs.getString("status")).
                        /* price */
                        setMaterialPrice(rs.getInt("material_price")).
                        setPrice(rs.getInt("price"));
            } else {
                throw new CustomException( "Dette ID er ikke tilgængeligt" );
            }

        } catch(SQLException | ClassNotFoundException ex) {
            throw new CustomException(ex.getMessage());
        } finally {
            closeStatement(ps);
        }        
        return order;
    }

    /**
     * Get orders by email.
     * This method calls the database with a prepared statement to
     * request an ArrayList of elements from the orders table by their email.
     *
     * @param email The email attached to the orders. Should not be null.
     * @return An arraylist of order objects with requested email.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
    public static List<Order> getOrders(String email) throws CustomException {
        PreparedStatement ps = null;
        Order order;
        List<Order> orders = new ArrayList<>();

        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM orders "
                    + "WHERE email = ?"
                    + "ORDER BY order_id DESC";

            ps = con.prepareStatement(SQL);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                order = new Order().
                        /* order id */
                        setId(rs.getInt("order_id")).
                        /* customer */
                        setName(rs.getString("name")).
                        setAddress(rs.getString("address")).
                        setZipCode(rs.getInt("zip_code")).
                        setCity(rs.getString("city")).
                        setPhone(rs.getString("phone")).
                        setEmail(rs.getString("email")).
                        setNote(rs.getString("note")).
                        /* carport */
                        setWidth(rs.getInt("width")).
                        setLength(rs.getInt("length")).
                        /* roof */
                        setRoof(rs.getInt("roof_id")).
                        setAngle(rs.getInt("angle")).
                        /* shed */
                        setShedWidth(rs.getInt("shed_width")).
                        setShedLength(rs.getInt("shed_length")).
                        /* dates */
                        setPlaced(rs.getString("placed").substring(0, 10)).
                        /* status */
                        setStatus(rs.getString("status")).
                        /* price */
                        setMaterialPrice(rs.getInt("material_price")).
                        setPrice(rs.getInt("price"));

                orders.add(order);
            }

        } catch(SQLException | ClassNotFoundException ex) {
            throw new CustomException(ex.getMessage());
        } finally {
            closeStatement(ps);
        }
        return orders;
    }

    /**
     * Get all Orders.
     * This method calls the database with a prepared statement to 
     * request an arraylist of all elements from the orders table.
     *
     * @return An arraylist of all order objects.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
    public static List<Order> getAllOrders() throws CustomException {
        PreparedStatement ps = null;
        Order order;
        List<Order> orders = new ArrayList<>();

        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM orders ORDER BY order_id DESC";

            ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                order = new Order().
                        /* order id */
                        setId(rs.getInt("order_id")).
                        /* customer */
                        setName(rs.getString("name")).
                        setAddress(rs.getString("address")).
                        setZipCode(rs.getInt("zip_code")).
                        setCity(rs.getString("city")).
                        setPhone(rs.getString("phone")).
                        setEmail(rs.getString("email")).
                        setNote(rs.getString("note")).
                        /* carport */
                        setWidth(rs.getInt("width")).
                        setLength(rs.getInt("length")).
                        /* roof */
                        setRoof(rs.getInt("roof_id")).
                        setAngle(rs.getInt("angle")).
                        /* shed */
                        setShedWidth(rs.getInt("shed_width")).
                        setShedLength(rs.getInt("shed_length")).
                        /* dates */
                        setPlaced(rs.getString("placed").substring(0, 10)).
                        /* status */
                        setStatus(rs.getString("status")).
                        /* price */
                        setMaterialPrice(rs.getInt("material_price")).
                        setPrice(rs.getInt("price"));

                orders.add(order);
            }

        } catch(SQLException | ClassNotFoundException ex) {
            throw new CustomException( "Kunne ikke hente infomation" );
        } finally {
            closeStatement(ps);
        }
        return orders;
    }

    /**
     * Update Order by Order object.
     * This method calls the database with a prepared statement to
     * request an update on a specific order. By using an order object as 
     * parameter, this method can update multiple attributes for the giving id.
     *
     * @param order The modified order object. Should not be null.
     * @return An updated order object.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
    public static Order updateOrder(Order order) throws CustomException {
        PreparedStatement ps = null;

        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE orders SET "
                    /* carport */ + "width = ?, length = ?, note = ?, "
                    /* roof */ + "roof_id = ?, angle = ?, "
                    /* shed */ + "shed_width = ?, shed_length = ?, "
                    /* status */ + "status = ? "
                    + "WHERE order_id = ?";

            ps = con.prepareStatement(SQL);

            /* carport */
            ps.setInt(1, order.getWidth());
            ps.setInt(2, order.getLength());
            ps.setString(3, order.getNote());

            /* roof */
            ps.setInt(4, order.getRoof().getID());
            ps.setInt(5, order.getAngle());

            /* shed */
            ps.setInt(6, order.getShedWidth());
            ps.setInt(7, order.getShedLength());

            /* status */
            ps.setString(8, order.getStatus());

            /* order id */
            ps.setInt(9, order.getId());

            ps.executeUpdate();

        } catch(SQLException | ClassNotFoundException ex) {
            throw new CustomException(ex.getMessage());
        } finally {
            closeStatement(ps);
        }
        return order;
    }

    /**
     * Update Order status by Order object.
     * This method calls the database with a prepared statement to
     * request an update on a specific order status. By using an order object as 
     * parameter, this method will use the objects getId() and getStatus() 
     * to modify the attributes for the giving id.
     *
     * @param order The modified order object. Should not be null.
     * @return An updated order object.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
    public static Order updateStatus(Order order) throws CustomException {
        PreparedStatement ps = null;

        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE orders "
                    + "SET status = ? "
                    + "WHERE order_id = ?";

            ps = con.prepareStatement(SQL);

            ps.setString(1, order.getStatus());
            ps.setInt(2, order.getId());

            ps.executeUpdate();

        } catch(SQLException | ClassNotFoundException ex) {
            throw new CustomException(ex.getMessage());
        } finally {
            closeStatement(ps);
        }
        return order;
    }

    /**
     * Update Order status by id.
     * This method calls the database with a prepared statement to
     * request an update on a specific order. By using id and status as parameters,
     * this method can update the status attributes for the giving id.
     *
     * @param id The id of the order. Should not be out of index bounds.
     * @param status The modified status. Should not be null.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
    public static void updateStatus(int id, String status) throws CustomException {
        PreparedStatement ps = null;

        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE orders "
                    + "SET status = ? "
                    + "WHERE order_id = ?";

            ps = con.prepareStatement(SQL);

            ps.setString(1, status);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch(SQLException | ClassNotFoundException ex) {
            throw new CustomException( "Kunne ikke hente information" );
        } finally {
            closeStatement(ps);
        }
    }

    /**
     * Update Order price by id.
     * This method calls the database with a prepared statement to
     * request an update on a specific order. By using id and price as parameters,
     * this method can update the status attributes for the giving id.
     *
     * @param id The id of the order. Should not be out of index bounds.
     * @param price The modified price. Should not be negative.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
    public static void updatePrice(int id, int price) throws CustomException {
        if ( price < 0 ) throw new CustomException( "Kunne ikke hente information" );
        PreparedStatement ps = null;

        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE orders "
                    + "SET price = ? "
                    + "WHERE order_id = ?";

            ps = con.prepareStatement(SQL);

            ps.setInt(1, price);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch(SQLException | ClassNotFoundException ex) {
            throw new CustomException( "Kunne ikke hente infomation" );
        } finally {
            closeStatement(ps);
        }
    }

    /**
     * Close Prepared Statement connection.
     * This method will close the prepared statement connection 
     * if it's connected. The reason is to reduce
     * in- and outgoing trafic from the server.
     *
     * @param ps PreparedStatement object. Should not be null.
     * @throws CustomException if it can't close the connection.
     */
    private static void closeStatement(PreparedStatement ps) throws CustomException {
        if(ps != null) {
            try {
                ps.close();
            } catch(SQLException ex) {
                throw new CustomException("Kunne ikke få kontakt til databasen");
            }
        }
    }
}
