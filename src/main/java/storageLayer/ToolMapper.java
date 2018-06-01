/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storageLayer;

import logicLayer.CustomException;
import logicLayer.Material;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logicLayer.Log;

/**
 *
 * @author Stephan
 */
public class ToolMapper {

    /**
     * Get all tools.
     * This method calls the database with a prepared statement to 
     * request an arraylist of all elements from the tools table.
     * 
     * @return An arraylist of all tools objects.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
    public static List<Material> getAllTool() throws CustomException {
        PreparedStatement ps = null;
        Material material;
        List<Material> materials = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM tools";

            ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                material = new Material().
                        setId(rs.getInt("tool_id")).
                        setName(rs.getString("name")).
                        setPrice(rs.getInt("price")).
                        setUnitSize(rs.getInt("unit_size"));
                materials.add(material);
            }

        } catch(SQLException | ClassNotFoundException ex) {
            Log.severe(ex);
            throw new CustomException( "Kunne ikke hente infomation" );
        } finally {
            closeStatement(ps);
        }
        return materials;
    }

    /**
     * Get tool by id.
     * This method calls the database with a prepared statement to
     * request an element from the tools table by it's 'tool_id'.
     * 
     * @param tool_id The id attached to the tool. Should not be out of index bounds.
     * @return A Material object with requested id.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
    public static Material getTool(int tool_id) throws CustomException {
        PreparedStatement ps = null;
        
        try {
            Material material = new Material();
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM tools WHERE tool_id = ?";

            ps = con.prepareStatement(SQL);
            ps.setInt(1, tool_id);
            ResultSet rs = ps.executeQuery();

            if(rs.first()) {
                material.
                        setId(rs.getInt("tool_id")).
                        setName(rs.getString("name")).
                        setPrice(rs.getInt("price")).
                        setUnitSize(rs.getInt("unit_size"));
                
                return material;
            }
            
        } catch(SQLException | ClassNotFoundException ex) {
            Log.severe(ex);
            throw new CustomException( "Kunne ikke hente information" );
        } finally {
            closeStatement(ps);
        }
        throw new CustomException( "Kunne ikke hente information" );
    }

    /**
     * Update tool with Material object.
     * This method calls the database with a prepared statement to
     * request an update on a specific tool. By using an material object as 
     * parameter, this method will use multiple get methods to modify the 
     * attributes for the giving id.
     * 
     * @param material The modified material object. Should not be null.
     * @return  An updated material object.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
    public static Material updateTool(Material material) throws CustomException {
        PreparedStatement ps = null;
        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE tools SET "
                    + "name = ?, price = ?, unit_size = ? "
                    + "WHERE tool_id = ?";

            ps = con.prepareStatement(SQL);

            ps.setString(1, material.getName());
            ps.setInt(2, material.getPrice());
            ps.setInt(3, material.getSize());
            ps.setInt(4, material.getId());
            ps.executeUpdate();

        } catch(SQLException | ClassNotFoundException | NullPointerException ex) {
            Log.severe(ex);
            throw new CustomException( "Kunne ikke hente information" );
        } finally {
            closeStatement(ps);
        }
        return material;
    }

    /**
     * Update tool by id.
     * This method calls the database with a prepared statement to
     * request an update on a specific tool. This method will use the unitsize and price parameters to modify the 
     * attributes for the giving id.
     * 
     * @param id The id attached to the tool. Should not be out of index bounds.
     * @param unitSize The modified unitsize. Should not be negative.
     * @param price The modified price. Should not be negative.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
    static void updateTool(int id, int unitSize, int price) throws CustomException {
        if ( unitSize < 0 || price < 0 ) throw new CustomException( "Kunne ikke hente information" );
        PreparedStatement ps = null;

        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE tools SET "
                    + "price = ?, unit_size = ? "
                    + "WHERE tool_id = ?";

            ps = con.prepareStatement(SQL);

            ps.setInt(1, price);
            ps.setInt(2, unitSize);
            ps.setInt(3, id);

            ps.executeUpdate();

        } catch(ClassNotFoundException | SQLException ex) {
            Log.severe(ex);
            throw new CustomException( "Kunne ikke hente information" );
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
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
    private static void closeStatement(PreparedStatement ps) throws CustomException {
        if(ps != null) {
            try {
                ps.close();
            } catch(SQLException ex) {
                Log.severe(ex);
                throw new CustomException( "Kunne ikke hente information" );
            }
        }
    }
}
