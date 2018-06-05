/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storageLayer.NewMappers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import logicLayer.CustomException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logicLayer.Log;
import logicLayer.NewMaterial;
import storageLayer.Connector;

/**
 *
 * @author Stephan
 */
public class NewMaterialMapper {

    /**
     * Get All Materials.
     * This method calls the database with a prepared statement to 
     * request an arraylist of all elements from the materials table.
     * 
     * @return An arraylist of all materials objects.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
    public static List<NewMaterial> getAllMaterials() throws CustomException {
        PreparedStatement ps = null;
        List<NewMaterial> materials = new ArrayList<>();
        
        String SQL = "SELECT * FROM materials";
        
        try {
            Connection con = Connector.connection();
            
            ps = con.prepareStatement(SQL);
            final ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NewMaterial material = new NewMaterial() {{
                    setId(rs.getInt("id"));
                    setName(rs.getString("name"));
                    setTypeId( rs.getInt( "type_id" ));
                    setPrice(rs.getInt("price"));
                    setSize( rs.getInt( "size" ));
                    setDescription(rs.getString("description"));
                    setAmount(rs.getInt("amount"));
                }};    
                
                materials.add(material);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Log.severe(ex);
            throw new CustomException( "Kunne ikke hente infomation" );
        } finally {
            closeStatement(ps);
        }
        return materials;
    }

    /**
     * Get Material by id.
     * This method calls the database with a prepared statement to
     * request an element from the materials table by it's 'plank_id'.
     * 
     * @param id The id attached to the material. Should not be out of index bounds.
     * @return A material object with requested id.
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or the closeStatement() method can't close the connection.
     */
    public static NewMaterial getMaterial(int id) throws CustomException {
        PreparedStatement ps = null;
        
        String SQL = "SELECT * FROM materials WHERE id = ?";
        
        try {
            Connection con = Connector.connection();
            ps = con.prepareStatement(SQL);
            ps.setInt(1, id);
            final ResultSet rs = ps.executeQuery();

            if (rs.first()) {                          
                return new NewMaterial() {{
                    setId(rs.getInt("id"));
                    setName(rs.getString("name"));
                    setTypeId( rs.getInt( "type_id" ));
                    setPrice(rs.getInt("price"));
                    setSize( rs.getInt( "size" ));
                    setDescription(rs.getString("description"));
                    setAmount(rs.getInt("amount"));
                }};
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Log.severe(ex);
            throw new CustomException( "Kunne ikke hente infomation" );
        } finally {
            closeStatement(ps);
        }
        throw new CustomException( "Kunne ikke hente information" );
    }
    
    
    /**
     * Update Material by id, size, price and desciption.
     * This method calls the database with a prepared statement to
     * request an update on a specific material. By using multiple parameters,
     * this method can update multiple attributes for the giving id.
     * 
     * @param id The id of the material. Should not be out of index bounds.
     * @param name
     * @param typeId
     * @param size The modified material size. Should not be negative.
     * @param price The modified material price. Should not be negative.
     * @param desc The modified material description. Should not be null.
     * @param amount
     * @throws CustomException if SQl syntax contains errors, can't connect to database, 
     * the connection class isn't found or if the closeStatement() method can't close the connection.
     */
    public static void updateMaterial(int id, String name, int typeId, int price, int size, String desc, int amount) throws CustomException {
        if ( size < 0 || price < 0 || amount < 1 ) throw new CustomException( "Kunne ikke hente information" );
        PreparedStatement ps = null;
        
        String SQL  = "UPDATE materials SET "
                    + "name = ?, type_id = ?, price = ?, size = ?, description = ?, amount= ? "
                    + "WHERE id = ?";
        
        try {
            Connection con = Connector.connection();
            ps = con.prepareStatement(SQL);

            ps.setString(1, name);
            ps.setInt(2, typeId);
            ps.setInt(3, price);
            ps.setInt(4, size);
            ps.setString(5, desc);
            ps.setInt(6, amount);
            ps.setInt(7, id);
 
            int rs = ps.executeUpdate();
            if ( rs == 0 ) throw new CustomException( "Kunne ikke hente information" );
        } catch (SQLException | ClassNotFoundException | NullPointerException ex) {
            Log.severe(ex);
            throw new CustomException( "Kunne ikke hente information" );
        } finally {
            closeStatement(ps);
        }
    }
    
    public static HashMap<String, ArrayList<NewMaterial>> getMaterialsMap() throws CustomException{
        
        HashMap<String, ArrayList<NewMaterial>> map = new HashMap<>();
        PreparedStatement ps = null;
        
        String SQL  = "SELECT materials.*, type.name AS type_name FROM materials "
                    + "JOIN types ON materials.type_id = types.id ";
        
        try {
            Connection con = Connector.connection();
            ps = con.prepareStatement( SQL );
            final ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                NewMaterial nm = new NewMaterial() {{
                    setId(rs.getInt("id"));
                    setName(rs.getString("name"));
                    setTypeId( rs.getInt( "type_id" ));
                    setPrice(rs.getInt("price"));
                    setSize( rs.getInt( "size" ));
                    setDescription(rs.getString("description"));
                    setAmount(rs.getInt("amount"));
                }};
                
                String tn = rs.getString("type_name");
                
                if(map.get(tn) == null){
                    map.put(tn, new ArrayList<NewMaterial>());
                }
                map.get( tn ).add(nm);
            }
            
        } catch ( ClassNotFoundException | SQLException e) {
            Log.severe(e);
            throw new CustomException("Der gik noget galt, prøv igen senere.");
        } catch (CustomException e){
            throw e;
        } finally {
            closeStatement( ps );
        }

        return map;
    }
    
    public List<NewMaterial> getMaterials(String typeName) throws CustomException{
        List<NewMaterial> list = new ArrayList<>();
        PreparedStatement ps = null;
        
        String SQL  = "SELECT * FROM materials "
                    + "JOIN ON materials.type_id = types.id "
                    + "WHERE types.name = ?";

        try {
            Connection con = Connector.connection();
            ps = con.prepareStatement( SQL );
            ps.setString(1, typeName);
            final ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                NewMaterial nm = new NewMaterial() {{
                    setId(rs.getInt("id"));
                    setName(rs.getString("name"));
                    setTypeId( rs.getInt( "type_id" ));
                    setPrice(rs.getInt("price"));
                    setSize( rs.getInt( "size" ));
                    setDescription(rs.getString("description"));
                    setAmount(rs.getInt("amount"));
                }};

                list.add(nm);
            } 
        } catch ( ClassNotFoundException | SQLException e) {
            Log.severe(e);
            throw new CustomException("Der gik noget galt, prøv igen senere.");
        } catch (CustomException e){
            throw e; 
        } finally {
            closeStatement(ps);
        }
        return list;
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
