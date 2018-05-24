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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stephan
 */
public class OrderMapper {

    /**
     * This method adds a Order object to the database and returns the object
     * with the auto-generated id key and status.
     *
     * @param order
     * @return the new order object with the generated id key and status
     * @throws CustomException
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
     * This method fetch a Order object from the database with a specific
     * order_id.
     *
     * @param id
     * @return order with specific order_id from the param 'id'
     * @throws CustomException
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
            }

        } catch(SQLException | ClassNotFoundException ex) {
            throw new CustomException(ex.getMessage());
        } finally {
            closeStatement(ps);
        }
        
        if ( order.getId() == 0 ) {
            throw new CustomException( "Dette ID er ikke tilgængeligt" );
        }
        
        return order;
    }

    /**
     * This method fetch an ArrayList containing all the Orders a specific email
     * has placed.
     *
     * @param email
     * @return list of orders from customer with specific email from the param
     * 'email'
     * @throws CustomException
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
     * This method fetch an ArrayList containing all placed Orders.
     *
     * @return all orders as a ArrayList
     * @throws CustomException
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
            throw new CustomException(ex.getMessage());
        } finally {
            closeStatement(ps);
        }
        return orders;
    }

    /**
     * This Method updates one or more details for an Order object.
     *
     * @param order
     * @return an order object where one or more variables has been updated
     * @throws CustomException
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
     * This method updates an orders status.
     *
     * @param order
     * @return an order object where status has been updated
     * @throws CustomException
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
     * This method updates an orders status.
     *
     * @param id
     * @param status
     * @throws CustomException
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
            throw new CustomException(ex.getMessage());
        } finally {
            closeStatement(ps);
        }
    }

    public static void updatePrice(int id, int price) throws CustomException {
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
            throw new CustomException(ex.getMessage());
        } finally {
            closeStatement(ps);
        }
    }

    /**
     * This method will close the prepared statement connection if it's
     * connection, the reason is to reduce as musch in- and outgoing trafic from
     * the server.
     *
     * @param ps PreparedStatement object, the SQL controller.
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
