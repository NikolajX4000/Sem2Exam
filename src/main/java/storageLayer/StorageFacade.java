package storageLayer;

import functionLayer.CustomException;
import functionLayer.Employee;
import functionLayer.Material;
import functionLayer.Order;
import functionLayer.Roof;
import java.util.List;

/**
 *
 * @author super
 */
public class StorageFacade {

    /**
     *
     * @param id
     * @param price
     * @throws CustomException
     */
    public static void updatePrice(int id, int price) throws CustomException {
        OrderMapper.updatePrice(id, price);
    }

    /**
     *
     * @see storageLayer.OrderMapper#addOrder( Order )
     * @param order
     * @return
     * @throws CustomException
     */
    public static Order addOrder(Order order) throws CustomException {
        return OrderMapper.addOrder(order);
    }

    /**
     *
     * @see storageLayer.OrderMapper#getOrder( int )
     * @param id
     * @return
     * @throws CustomException
     */
    public static Order getOrder(int id) throws CustomException {
        return OrderMapper.getOrder(id);
    }

    /**
     *
     * @see storageLayer.OrderMapper#getOrders( String )
     * @param email
     * @return
     * @throws CustomException
     */
    public static List<Order> getOrders(String email) throws CustomException {
        return OrderMapper.getOrders(email);
    }

    /**
     *
     * @see storageLayer.OrderMapper#getAllOrders()
     * @return
     * @throws CustomException
     */
    public static List<Order> getAllOrders() throws CustomException {
        return OrderMapper.getAllOrders();
    }

    /**
     *
     * @see storageLayer.OrderMapper#updateOrder( Order )
     * @param order
     * @return
     * @throws CustomException
     */
    public static Order updateOrder(Order order) throws CustomException {
        return OrderMapper.updateOrder(order);
    }

    /**
     *
     * @see storageLayer.OrderMapper#updateStatus( Order )
     * @param order
     * @return
     * @throws CustomException
     */
    public static Order updateStatus(Order order) throws CustomException {
        return OrderMapper.updateStatus(order);
    }

    /**
     * @see storageLayer.OrderMapper#updateStatus( int id, String status )
     * @param id
     * @param status
     * @throws CustomException
     */
    public static void updateStatus(int id, String status) throws CustomException {
        OrderMapper.updateStatus(id, status);
    }

    /**
     * @return @throws functionLayer.CustomException
     */
    public static List<Material> getAllMaterials() throws CustomException {
        return MaterialMapper.getAllMaterials();
    }

    /**
     * @param material
     * @return
     * @throws functionLayer.CustomException
     */
    public static List<Material> getMaterials(String material) throws CustomException {
        return MaterialMapper.getMaterials(material);
    }

    /**
     * @param material
     * @return
     * @throws functionLayer.CustomException
     */
    public static Material updateMaterial(Material material) throws CustomException {
        return MaterialMapper.updateMaterial(material);
    }

    public static void updateMaterial(int id, int size, int price, String description) throws CustomException {
        MaterialMapper.updateMaterial(id, size, price, description);
    }
    
    public static void updateMaterial(int id, int size, int price) throws CustomException {
        MaterialMapper.updateMaterial(id, size, price);
    }

    /**
     * @return @throws CustomException
     */
    public static List<Material> getAllTool() throws CustomException {
        return ToolMapper.getAllTool();

    }

    /**
     * @param tool_id
     * @return
     * @throws CustomException
     */
    public static Material getTool(int tool_id) throws CustomException {
        return ToolMapper.getTool(tool_id);
    }

    /**
     * @param material
     * @return
     * @throws CustomException
     */
    public static Material updateTool(Material material) throws CustomException {
        return ToolMapper.updateTool(material);
    }
    public static void updateTool(int id, int unitSize, int price) throws CustomException {
        ToolMapper.updateTool(id, unitSize, price);
    }
    
    public static void updateRoof(int id, String name) throws CustomException {
        RoofMapper.updateRoof(id, name);
    }

    public static List<Roof> getAllRoofs() throws CustomException {
        return RoofMapper.getAllRoofs();
    }

    public static Roof getRoofById(int id) throws CustomException {
        return RoofMapper.getRoofById(id);
    }

    public static Employee login(String name, String password) throws CustomException {
        return EmployeeMapper.login(name, password);
    }
}
