package functionLayer;

import storageLayer.StorageFacade;
import java.util.List;

/**
 *
 * @author super
 */
public class LogicFacade {

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
    
    public static Material updateMaterial(int id, int size, int price, String description) throws CustomException {
        return StorageFacade.updateMaterial(id, size, price, description);
    }

    public static List<Roof> getAllRoofs() throws CustomException {
        return StorageFacade.getAllRoofs();
    }
}
