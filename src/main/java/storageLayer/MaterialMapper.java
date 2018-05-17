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
public class MaterialMapper {

    /**
     *
     * @return @throws CustomException
     */
    public static List<Material> getAllMaterials() throws CustomException {
        PreparedStatement ps = null;
        List<Material> materials = new ArrayList<>();
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM materials";

            ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Material materialTmp = new Material().
                        setId(rs.getInt("plank_id")).
                        setName(rs.getString("name")).
                        setPrice(rs.getInt("price")).
                        setDescription(rs.getString("description")).
                        setSize(rs.getInt("size"));
                materials.add(materialTmp);
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
     * @param material
     * @return
     * @throws CustomException
     */
    public static List<Material> getMaterials(String material) throws CustomException {
        PreparedStatement ps = null;
        List<Material> materials = new ArrayList<>();
        
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM materials WHERE name=?";
            ps = con.prepareStatement(SQL);
            ps.setString(1, material);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Material materialTmp = new Material().
                        setId(rs.getInt("plank_id")).
                        setName(rs.getString("name")).
                        setPrice(rs.getInt("price")).
                        setDescription(rs.getString("description")).
                        setSize(rs.getInt("size"));
                materials.add(materialTmp);
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
     * @param material
     * @return
     * @throws CustomException
     */
    public static Material updateMaterial(Material material) throws CustomException {
        PreparedStatement ps = null;

        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE materials SET "
                    + "name = ?, price = ?, size = ?, description = ? "
                    + "WHERE plank_id = ?";

            ps = con.prepareStatement(SQL);

            try {
                ps.setString(1, material.getName());
                ps.setInt(2, material.getPrice());
                ps.setInt(3, material.getSize());
                ps.setString(4, material.getDescription());
                ps.setInt(5, material.getId());

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
     *
     * @param id
     * @param size
     * @param price
     * @param desc
     * @return
     * @throws CustomException
     */
    public static void updateMaterial(int id, int size, int price, String desc) throws CustomException {
        PreparedStatement ps = null;

        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE materials SET "
                    + "price = ?, size = ?, description = ? "
                    + "WHERE plank_id = ?";

            ps = con.prepareStatement(SQL);

            try {
                ps.setInt(1, price);
                ps.setInt(2, size);
                ps.setString(3, desc);
                ps.setInt(4, id);

            } catch (SQLException ex) {
                throw new CustomException("Formateringsfejl");
            }
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new CustomException(ex.getMessage());
        } finally {
            closeConnection(ps);
        }
    }

    /**
     * This method will close the prepared statement connection if it's
     * connection, the reason is to reduce as musch in- and outgoing trafic from
     * the server.
     *
     * @param ps PreparedStatement object, the SQL controller.
     */
    private static void closeConnection(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrderMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
