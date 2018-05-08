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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stephan
 */
public class MaterialMapper
{

    /**
     *
     * @param material
     * @return
     * @throws CustomException
     */
    public static List<Material> getMaterials(String material) throws CustomException
    {
        PreparedStatement ps = null;
        List<Material> materials = new ArrayList<>();
        try
        {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM materials WHERE name=?";
            
            ps = con.prepareStatement(SQL);
            ps.setString(1, material);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {

                Material materialTmp = new Material().
                        setId(rs.getInt("plank_id")).
                        setName(rs.getString("name")).
                        setPrice(rs.getInt("price")).
                        setDescription(rs.getString("description")).
                        setSize(rs.getInt("size"));
                materials.add(materialTmp);
            }

        } catch (SQLException | ClassNotFoundException ex)
        {
            throw new CustomException(ex.getMessage());
        } finally
        {
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
    public static Material addMaterial(Material material) throws CustomException
    {
        PreparedStatement ps = null;

        try
        {
            Connection con = Connector.connection();
            String SQL = "INSERT INTO material ( "
                    + "name, price, size, description ) "
                    + "VALUES (?, ?, ?, ?)";

            ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

            try
            {
                ps.setString(1, material.getName());
                ps.setInt(2, material.getPrice());
                ps.setInt(3, material.getSize());
                ps.setString(4, material.getDescription());

            } catch (SQLException ex)
            {
                throw new CustomException("Formateringsfejl");
            }

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.first())
            {
                material.setId(rs.getInt(1));
            }

        } catch (SQLException | ClassNotFoundException ex)
        {
            throw new CustomException(ex.getMessage());
        } finally
        {
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
    public static Material updateMaterial(Material material) throws CustomException
    {
        PreparedStatement ps = null;

        try
        {
            Connection con = Connector.connection();
            String SQL = "UPDATE orders SET "
                    + "name = ?, price = ?, size = ?, description = ? "
                    + "WHERE order_id = ?";

            ps = con.prepareStatement(SQL);

            try
            {
                ps.setString(1, material.getName());
                ps.setInt(2, material.getPrice());
                ps.setInt(3, material.getSize());
                ps.setString(4, material.getDescription());
                ps.setInt(5, material.getId());

            } catch (SQLException ex)
            {
                throw new CustomException("Formateringsfejl");
            }
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex)
        {
            throw new CustomException(ex.getMessage());
        } finally
        {
            closeConnection(ps);
        }
        return material;
    }

    /**
     *
     * @param id
     * @return
     * @throws CustomException
     */
    public static boolean deleteMaterial(int id) throws CustomException
    {
        throw new UnsupportedOperationException("comming soon");
    }

    public static Material getTool(int tool) throws CustomException
    {
        PreparedStatement ps = null;
        Material material = new Material();
        try
        {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM tools WHERE tool_id = ?";

            ps = con.prepareStatement(SQL);
            ps.setInt(0, tool);
            ResultSet rs = ps.executeQuery();

            if (rs.first())
            {
                material.
                        setId(rs.getInt("tool_id")).
                        setName(rs.getString("name")).
                        setPrice(rs.getInt("price")).
                        setUnitSize(rs.getInt("unit_size"));
            }

        } catch (SQLException | ClassNotFoundException ex)
        {
            throw new CustomException(ex.getMessage());
        } finally
        {
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
    private static void closeConnection(PreparedStatement ps)
    {
        if (ps != null)
        {
            try
            {
                ps.close();
            } catch (SQLException ex)
            {
                Logger.getLogger(OrderMapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
