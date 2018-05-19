package storageLayer;

import functionLayer.CustomException;
import functionLayer.Roof;
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
 * @author super
 */
public class RoofMapper {

    public static List<Roof> getAllRoofs() throws CustomException {
        PreparedStatement ps = null;
        List<Roof> roofs = new ArrayList<>();

        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM roofs";

            ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("roof_id");
                String name = rs.getString("name");
                int type = rs.getInt("type");
                roofs.add(new Roof(id, name, type));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CustomException(ex.getMessage());
        } finally {
            closeConnection(ps);
        }
        return roofs;
    }

    public static Roof getRoofById(int id) throws CustomException {
        PreparedStatement ps = null;
        Roof roof = null;

        try {
            Connection con = Connector.connection();
            String SQL = "SELECT * FROM roofs "
                    + "WHERE roof_id = ?";

            ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.first()) {
                String name = rs.getString("name");
                int type = rs.getInt("type");
                roof = new Roof(id, name, type);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new CustomException(ex.getMessage());
        } finally {
            closeConnection(ps);
        }
        return roof;
    }

    public static void updateRoof(int id, String name, String oldname) throws CustomException {
        Connection con = null;
        PreparedStatement updateRoof = null;
        PreparedStatement updateTagsten = null;
        
        String updateRoofString = "UPDATE roofs SET name = ? WHERE roof_id = ?";
        String updateTagstenString = "UPDATE materials SET description = ? WHERE description = ? AND (name = 'tagsten' OR name = 'rygsten')";
        
        try {
            con = Connector.connection();
            con.setAutoCommit(false);
            
            updateRoof = con.prepareStatement(updateRoofString);
            updateRoof.setString(1, name);
            updateRoof.setInt(2, id);
            updateRoof.executeUpdate();
            
            
            updateTagsten = con.prepareStatement(updateTagstenString);
            updateTagsten.setString(1, name);
            updateTagsten.setString(2, oldname);
            updateTagsten.executeUpdate();
            
            con.commit();
        } catch (ClassNotFoundException | SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    throw new CustomException(ex.getMessage());
                }
            }
            throw new CustomException(e.getMessage());
        } finally {
            closeConnection(updateRoof);
            closeConnection(updateTagsten);
            
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new CustomException("Der gik noget galt ved opdateringen, prøv igen senere.");
            }
            
        }
    }

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
