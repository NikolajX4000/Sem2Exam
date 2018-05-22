/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storageLayer;

import functionLayer.CustomException;
import functionLayer.Material;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stephan
 */
public class ToolMapper {

    public static List<Material> getAllTool() throws CustomException {
        PreparedStatement ps = null;
        Material material = null;
        List<Material> materials = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM tools";

            ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                material = new Material().
                    setId(rs.getInt("tool_id")).
                    setName(rs.getString("name")).
                    setPrice(rs.getInt("price")).
                    setUnitSize(rs.getInt("unit_size"));
                materials.add(material);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            throw new CustomException(ex.getMessage());
        } finally {
            closeConnection(ps);
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
        Material material = new Material();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM tools WHERE tool_id = ?";

            ps = con.prepareStatement(SQL);
            ps.setInt(1, tool_id);
            ResultSet rs = ps.executeQuery();

            if (rs.first()) {
                material.
                        setId(rs.getInt("tool_id")).
                        setName(rs.getString("name")).
                        setPrice(rs.getInt("price")).
                        setUnitSize(rs.getInt("unit_size"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            throw new CustomException(ex.getMessage());
        } finally {
            closeConnection(ps);
        }
        return material;
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

            try {
                ps.setString(1, material.getName());
                ps.setInt(2, material.getPrice());
                ps.setInt(3, material.getSize());
                ps.setInt(4, material.getId());

            } catch (SQLException ex) {
                throw new CustomException("Formateringsfejl");
            }
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException | NullPointerException ex) {
            throw new CustomException(ex.getMessage());
        } finally {
            closeConnection(ps);
        }
        return material;
    }

    /**
     * This method will close the prepared statement connection if it's
     * connection, the reason is to reduce as musch in- and outgoing trafic from
     * the server.
     *
     * @param ps PreparedStatement object, the SQL controller.
     */
    private static void closeConnection(PreparedStatement ps) throws CustomException {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                throw new CustomException( "Kunne ikke få kontakt til databasen" );
            }
        }
    }

    static void updateTool(int id, int unitSize, int price) throws CustomException
    {
        PreparedStatement ps = null;

        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE tools SET "
                    + "price = ?, unit_size = ? "
                    + "WHERE tool_id = ?";

            ps = con.prepareStatement(SQL);

            try {
                ps.setInt(1, price);
                ps.setInt(2, unitSize);
                ps.setInt(3, id);

            } catch (SQLException ex) {
                throw new CustomException("Formateringsfejl");
            }
            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            throw new CustomException(ex.getMessage());
        } finally {
            closeConnection(ps);
        }
    }

}
