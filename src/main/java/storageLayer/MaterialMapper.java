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

/**
 *
 * @author Stephan
 */
public class MaterialMapper {

    /**
     * Get All Materials.
     * This method calls the database with a preparedStatement to 
     * request all the elements from the materials table.
     * 
     * @return An ArrayList of Materials Objects.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
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
            throw new CustomException( "Kunne ikke hente infomation" );
        } finally {
            closeStatement(ps);
        }
        return materials;
    }

    /**
     * Get Material by id.
     * This method calls the database with a preparedStatement to
     * request an element from the materials table by it's 'plank_id'.
     * 
     * @param id The id of the material, should not be out of index bounds.
     * @return A material object with requested id.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
    public static Material getMaterial(int id) throws CustomException {
        PreparedStatement ps = null;
        Material material = null;
        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM materials WHERE plank_id=?";
            ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.first()) {                
                material = new Material().
                    setId(rs.getInt("plank_id")).
                    setName(rs.getString("name")).
                    setPrice(rs.getInt("price")).
                    setDescription(rs.getString("description")).
                    setSize(rs.getInt("size"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            throw new CustomException( "Kunne ikke hente infomation" );
        } finally {
            closeStatement(ps);
        }
        return material;
    }
    
    /**
     * Get Material by material name.
     * This method calls the database with a preparedStatement to
     * request a ArrayList of elements from the materials table by they names.
     * 
     * @param material The name of the material, should not be null.
     * @return An ArrayList of material objects with requested name.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
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
            throw new CustomException( "Kunne ikke hente infomation" );
        } finally {
            closeStatement(ps);
        }
        return materials;
    }

    /**
     * Update Material by Material Object.
     * This method calls the database with a preparedStatement to
     * request an update on a specific material. By using an Material object as 
     * parameter, this method can update multiple attributes for the giving id.
     * 
     * @param material The modified material object, should not be null.
     * @return An updated material object.
     * @throws CustomException 'Kunne ikke hente infomation'.
     */
    public static Material updateMaterial(Material material) throws CustomException {
        PreparedStatement ps = null;

        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE materials SET "
                    + "name = ?, price = ?, size = ?, description = ? "
                    + "WHERE plank_id = ?";

            ps = con.prepareStatement(SQL);

            ps.setString(1, material.getName());
            ps.setInt(2, material.getPrice());
            ps.setInt(3, material.getSize());
            ps.setString(4, material.getDescription());
            ps.setInt(5, material.getId());

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException | NullPointerException ex) {
            throw new CustomException( "Kunne ikke hente infomation" );
        } finally {
            closeStatement(ps);
        }
        return material;
    }

    /**
     * Update Material by id, size, price and desciption.
     * This method calls the database with a preparedStatement to
     * request an update on a specific material. By using multiple parameters,
     * this method can update multiple attributes for the giving id.
     * 
     * @param id The id of the material, should not be out of index bounds.
     * @param size The modified material size, should not be negative.
     * @param price The modified material price, should not be negative.
     * @param desc The modified material description, should not be null.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or if the closeStatement() method can't close the connection.
     */
    public static void updateMaterial(int id, int size, int price, String desc) throws CustomException {
        PreparedStatement ps = null;

        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE materials SET "
                    + "price = ?, size = ?, description = ? "
                    + "WHERE plank_id = ?";

            ps = con.prepareStatement(SQL);

            ps.setInt(1, price);
            ps.setInt(2, size);
            ps.setString(3, desc);
            ps.setInt(4, id);

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException | NullPointerException ex) {
            throw new CustomException( "Kunne ikke hente infomation" );
        } finally {
            closeStatement(ps);
        }
    }
    
    /**
     * Update Material by id, size, price and desciption.
     * This method calls the database with a preparedStatement to
     * request an update on a specific material. By using multiple parameters,
     * this method can update multiple attributes for the giving id.
     * 
     * @param id The id of the material, should not be out of index bounds.
     * @param size The modified material size, should not be negative.
     * @param price The modified material price, should not be negative.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or if the closeStatement() method can't close the connection.
     */
    public static void updateMaterial(int id, int size, int price) throws CustomException {
        PreparedStatement ps = null;

        try {
            Connection con = Connector.connection();
            String SQL = "UPDATE materials SET "
                    + "price = ?, size = ? "
                    + "WHERE plank_id = ?";

            ps = con.prepareStatement(SQL);

            ps.setInt(1, price);
            ps.setInt(2, size);
            ps.setInt(3, id);

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new CustomException( "Kunne ikke hente infomation" );
        } finally {
            closeStatement(ps);
        }
    }

    /**
     * Close Prepared Statement connection.
     * This method will close the prepared statement connection 
     * if it's connected. The reason is to reduce as 
     * musch in- and outgoing trafic from the server.
     *
     * @param ps PreparedStatement object, should not be null.
     * @throws CustomException if itcan't close the connection.
     */
    private static void closeStatement(PreparedStatement ps) throws CustomException {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                throw new CustomException( "Kunne ikke få kontakt til databasen" );
            }
        }
    }
}
