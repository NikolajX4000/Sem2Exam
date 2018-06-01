package logicLayer;

import java.util.ArrayList;
import java.util.HashMap;
import storageLayer.StorageFacade;
import java.util.List;

/**
 * A facade between the presentaitionLayer and logicLayer
 *
 * @author super
 */
public class LogicFacade {

    /**
     * @see storageLayer.StorageFacade#updatePrice(int, int)
     * @param id The id of the order. Should not be out of index bounds.
     * @param price The modified price. Should not be negative.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static void updatePrice(int id, int price) throws CustomException {
        StorageFacade.updatePrice(id, price);
    }

    /**
     *
     * @see storageLayer.StorageFacade#getOrders(java.lang.String)
     * @param email The email attached to the orders. Should not be null.
     * @return A List of order objects with requested email.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static List<Order> getOrders(String email) throws CustomException {
        return StorageFacade.getOrders(email);
    }

    /**
     * @see storageLayer.StorageFacade#getOrder(int)
     * @param id The id of the order, should not be out of index bounds.
     * @return An order object with requested id.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static Order getOrder(int id) throws CustomException {
        return StorageFacade.getOrder(id);
    }

    /**
     * @see storageLayer.StorageFacade#addOrder(logicLayer.Order)
     * @param o The new order object. Should not be null.
     * @return The new order object with the generated id key and status.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static Order addOrder(Order o) throws CustomException {
        return StorageFacade.addOrder(o);
    }

    /**
     * @see storageLayer.StorageFacade#getAllOrders()
     * @return A List of all order objects.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static List<Order> getAllOrders() throws CustomException {
        return StorageFacade.getAllOrders();
    }

    /**
     * @see storageLayer.StorageFacade#updateStatus(int, java.lang.String)
     * @param id The id of the order. Should not be out of index bounds.
     * @param status The modified status. Should not be null.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static void updateStatus(int id, String status) throws CustomException {
        StorageFacade.updateStatus(id, status);
    }

    /**
     * @see storageLayer.StorageFacade#getAllMaterials()
     * @return A List of all materials objects.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static List<Material> getAllMaterials() throws CustomException {
        return StorageFacade.getAllMaterials();
    }

    /**
     * Take the returned List from getAllMaterials() and make a HashMap out of
     * them
     *
     * @return A HashMap of all the materials in the database
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static HashMap<String, ArrayList<Material>> getAllMaterialsAsMap() throws CustomException {

        HashMap<String, ArrayList<Material>> mats = new HashMap();

        for (Material m : getAllMaterials()) {

            // check if first of that type annd add to map if true
            if (mats.get(m.getName()) == null) {
                mats.put(m.getName(), new ArrayList());
            }

            //add material to map
            mats.get(m.getName()).add(m);
        }
        return mats;
    }

    /**
     * @see storageLayer.StorageFacade#updateMaterial(int, int, int,
     * java.lang.String)
     * @param id The id of the material. Should not be out of index bounds.
     * @param size The modified material size. Should not be negative.
     * @param price The modified material price. Should not be negative.
     * @param description The modified material description. Should not be null.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or if the closeStatement()
     * method can't close the connection.
     */
    public static void updateMaterial(int id, int size, int price, String description) throws CustomException {
        StorageFacade.updateMaterial(id, size, price, description);
    }

    /**
     * @see storageLayer.StorageFacade#updateMaterial(int, int, int)
     * @param id The id of the material. Should not be out of index bounds.
     * @param size The modified material size. Should not be negative.
     * @param price The modified material price. Should not be negative.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or if the closeStatement()
     * method can't close the connection.
     */
    public static void updateMaterial(int id, int size, int price) throws CustomException {
        StorageFacade.updateMaterial(id, size, price);
    }

    /**
     * @see storageLayer.StorageFacade#updateTool(int, int, int)
     * @param id The id attached to the tool. Should not be out of index bounds.
     * @param unitSize The modified unitsize. Should not be negative.
     * @param price The modified price. Should not be negative.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static void updateTool(int id, int unitSize, int price) throws CustomException {
        StorageFacade.updateTool(id, unitSize, price);
    }

    /**
     * @see storageLayer.StorageFacade#updateRoof(int, java.lang.String,
     * java.lang.String)
     * @param id The id og the roof. Should not be null.
     * @param name The modified name. Should not be null.
     * @param oldname The current name. Should not be null.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static void updateRoof(int id, String name, String oldname) throws CustomException {
        StorageFacade.updateRoof(id, name, oldname);
    }

    /**
     * @see storageLayer.StorageFacade#getAllRoofs()
     * @return An arraylist of all roofs objects.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static List<Roof> getAllRoofs() throws CustomException {
        return StorageFacade.getAllRoofs();
    }

    /**
     * @see storageLayer.StorageFacade#getAllTools()
     * @return An arraylist of all tools objects.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static List<Material> getAllTools() throws CustomException {
        return StorageFacade.getAllTools();
    }

    /**
     * @see storageLayer.StorageFacade#login(java.lang.String, java.lang.String)
     * @param name The username. Should not be null.
     * @param password The password. Should not be null.
     * @return A employee object with the name and password.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static Employee login(String name, String password) throws CustomException {
        return StorageFacade.login(name, password);
    }
}
