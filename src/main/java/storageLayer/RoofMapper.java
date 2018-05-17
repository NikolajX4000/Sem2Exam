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
