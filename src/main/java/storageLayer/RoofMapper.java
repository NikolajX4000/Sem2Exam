package storageLayer;

import logicLayer.CustomException;
import logicLayer.Roof;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logicLayer.Log;

/**
 *
 * @author super
 */
public class RoofMapper {

    /**
     * Get all roofs.
     * This method calls the database with a prepared statement to 
     * request an arraylist of all elements from the roofs table.
     * 
     * @return An arraylist of all roofs objects.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
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
            Log.severe(ex);
            throw new CustomException( "Kunne ikke hente information" );
        } finally {
            closeStatement(ps);
        }
        return roofs;
    }

    /**
     * Get roof by id.
     * This method calls the database with a prepared statement to
     * request an element from the roofs table by it's 'roof_id'.
     * 
     * @param id The id attached to the roof. Should not be out of index bounds.
     * @return A roof object with requested id.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
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
                
                return roof;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Log.severe(ex);
            throw new CustomException( "Kunne ikke hente information" );
        } finally {
            closeStatement(ps);
        }
        throw new CustomException( "Kunne ikke hente information" );
    }

    /**
     * Update roof.
     * This method calls the database with a prepared statement to
     * request an update on a specific roof. Replacing the current name 
     * with the modified at the id, using transaction in case of conflicts.
     * 
     * @param id The id og the roof. Should not be null.
     * @param name The modified name. Should not be null.
     * @param oldname The current name. Should not be null.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
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
                    Log.severe(ex);
                    throw new CustomException( "Dette navn eksistere allerede" );
                }
            }
            Log.severe(e);
            throw new CustomException( "Dette navn eksistere allerede" );
        } finally {
            closeStatement(updateRoof);
            closeStatement(updateTagsten);
            
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Log.severe(ex);
                throw new CustomException("Der gik noget galt ved opdateringen, prøv igen senere.");
            }
            
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
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                Log.severe(ex);
                throw new CustomException( "Kunne ikke hente information" );
            }
        }
    }
}
