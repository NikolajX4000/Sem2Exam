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
     *
     * @param id
     * @param price
     * @throws CustomException
     */
    public static void updatePrice(int id, int price) throws CustomException {
        StorageFacade.updatePrice(id, price);
    }

    /**
     *
     * @see storageLayer.OrderMapper#getOrders( String )
     * @param email
     * @return
     * @throws CustomException
     */
    public static List<Order> getOrders(String email) throws CustomException {
        return StorageFacade.getOrders(email);
    }

    public static Order getOrder(int id) throws CustomException {
        return StorageFacade.getOrder(id);
    }

    /**
     *
     * @param o
     * @return
     * @throws CustomException
     */
    public static Order addOrder(Order o) throws CustomException {
        return StorageFacade.addOrder(o);
    }

    /**
     *
     * @return @throws CustomException
     */
    public static List<Order> getAllOrders() throws CustomException {
        return StorageFacade.getAllOrders();
    }

    /**
     *
     * @param id
     * @param status
     * @throws CustomException
     */
    public static void updateOrder(int id, String status) throws CustomException {
        StorageFacade.updateStatus(id, status);
    }

    /**
     *
     * @return List of all material
     * @throws CustomException
     */
    public static List<Material> getAllMaterials() throws CustomException {
        return StorageFacade.getAllMaterials();
    }

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

    public static void updateMaterial(int id, int size, int price, String description) throws CustomException {
        StorageFacade.updateMaterial(id, size, price, description);
    }

    public static void updateMaterial(int id, int size, int price) throws CustomException {
        StorageFacade.updateMaterial(id, size, price);
    }

    public static void updateTool(int id, int unitSize, int price) throws CustomException {
        StorageFacade.updateTool(id, unitSize, price);
    }

    public static void updateRoof(int id, String name, String oldname) throws CustomException {
        StorageFacade.updateRoof(id, name, oldname);
    }

    public static List<Roof> getAllRoofs() throws CustomException {
        return StorageFacade.getAllRoofs();
    }

    public static List<Material> getAllTool() throws CustomException {
        return StorageFacade.getAllTools();
    }

    public static Employee login(String name, String password) throws CustomException {
        return StorageFacade.login(name, password);
    }
}
