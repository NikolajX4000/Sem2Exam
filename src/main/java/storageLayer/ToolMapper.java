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
            throw new CustomException(ex.getMessage());
        } finally {
            closeStatement(ps);
        }
        return materials;
    }

    /**
     *
     * @param tool_id
     * @return
     * @throws CustomException
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
            throw new CustomException( "Kunne ikke hente infomation" );
        } finally {
            closeStatement(ps);
        }
        throw new CustomException( "Kunne ikke hente infomation" );
    }

    /**
     *
     * @param material
     * @return
     * @throws CustomException
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
            throw new CustomException(ex.getMessage());
        } finally {
            closeStatement(ps);
        }
        return material;
    }

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
            throw new CustomException( "Kunne ikke hente infomation" );
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
                Log.severe(ex);
                throw new CustomException( "Kunne ikke hente infomation" );
            }
        }
    }
}
