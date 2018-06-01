package logicLayer;

import java.util.ArrayList;
import java.util.HashMap;
import storageLayer.StorageFacade;
import java.util.List;

/**
 *
 * @author super
 */
public class LogicFacade {

    /**
     * @see storageLayer.StorageFacade#updatePrice(int, int) 
     * @param id
     * @param price
     * @throws CustomException
     */
    public static void updatePrice(int id, int price) throws CustomException {
        StorageFacade.updatePrice(id, price);
    }

    /**
     *
     * @see storageLayer.StorageFacade#getOrders(java.lang.String) 
     * @param email
     * @return
     * @throws CustomException
     */
    public static List<Order> getOrders(String email) throws CustomException {
        return StorageFacade.getOrders(email);
    }

    /**
     * @see storageLayer.StorageFacade#getOrder(int) 
     * @param id
     * @return
     * @throws CustomException
     */
    public static Order getOrder(int id) throws CustomException {
        return StorageFacade.getOrder(id);
    }

    /**
     * @see storageLayer.StorageFacade#addOrder(logicLayer.Order) 
     * @param o
     * @return
     * @throws CustomException
     */
    public static Order addOrder(Order o) throws CustomException {
        return StorageFacade.addOrder(o);
    }

    /**
     * @see storageLayer.StorageFacade#getAllOrders() 
     * @return @throws CustomException
     */
    public static List<Order> getAllOrders() throws CustomException {
        return StorageFacade.getAllOrders();
    }

    /**
     * @see storageLayer.StorageFacade#updateStatus(int, java.lang.String) 
     * @param id
     * @param status
     * @throws CustomException
     */
    public static void updateStatus(int id, String status) throws CustomException {
        StorageFacade.updateStatus(id, status);
    }

    /**
     * @see storageLayer.StorageFacade#getAllMaterials() 
     * @return List of all material
     * @throws CustomException
     */
    public static List<Material> getAllMaterials() throws CustomException {
        return StorageFacade.getAllMaterials();
    }

    /**
     * Take the returned List from getAllMaterials() and make a HashMap out of them
     * @return
     * @throws CustomException
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
     * @see storageLayer.StorageFacade#updateMaterial(int, int, int, java.lang.String) 
     * @param id
     * @param size
     * @param price
     * @param description
     * @throws CustomException
     */
    public static void updateMaterial(int id, int size, int price, String description) throws CustomException {
        StorageFacade.updateMaterial(id, size, price, description);
    }

    /**
     * @see storageLayer.StorageFacade#updateMaterial(int, int, int) 
     * @param id
     * @param size
     * @param price
     * @throws CustomException
     */
    public static void updateMaterial(int id, int size, int price) throws CustomException {
        StorageFacade.updateMaterial(id, size, price);
    }

    /**
     * @see storageLayer.StorageFacade#updateTool(int, int, int) 
     * @param id
     * @param unitSize
     * @param price
     * @throws CustomException
     */
    public static void updateTool(int id, int unitSize, int price) throws CustomException {
        StorageFacade.updateTool(id, unitSize, price);
    }

    /**
     * @see storageLayer.StorageFacade#updateRoof(int, java.lang.String, java.lang.String) 
     * @param id
     * @param name
     * @param oldname
     * @throws CustomException
     */
    public static void updateRoof(int id, String name, String oldname) throws CustomException {
        StorageFacade.updateRoof(id, name, oldname);
    }

    /**
     * @see storageLayer.StorageFacade#getAllRoofs() 
     * @return
     * @throws CustomException
     */
    public static List<Roof> getAllRoofs() throws CustomException {
        return StorageFacade.getAllRoofs();
    }

    /**
     * @see storageLayer.StorageFacade#getAllTools() 
     * @return
     * @throws CustomException
     */
    public static List<Material> getAllTools() throws CustomException {
        return StorageFacade.getAllTools();
    }

    /**
     * @see storageLayer.StorageFacade#login(java.lang.String, java.lang.String) 
     * @param name
     * @param password
     * @return
     * @throws CustomException
     */
    public static Employee login(String name, String password) throws CustomException {
        return StorageFacade.login(name, password);
    }
}
