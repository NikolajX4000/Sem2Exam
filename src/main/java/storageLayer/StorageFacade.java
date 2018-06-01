package storageLayer;

import logicLayer.CustomException;
import logicLayer.Employee;
import logicLayer.Material;
import logicLayer.Order;
import logicLayer.Roof;
import java.util.List;

/**
 * A facade between logicLayer and storageLayer.
 *
 * @author super
 */
public class StorageFacade {

    /**
     * Update price by id.
     *
     * @see storageLayer.OrderMapper#updatePrice(int, int)
     *
     * @param id The id of the order. Should not be out of index bounds.
     * @param price The modified price. Should not be negative.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static void updatePrice(int id, int price) throws CustomException {
        OrderMapper.updatePrice(id, price);
    }

    /**
     * Add order.
     *
     * @see storageLayer.OrderMapper#addOrder(logicLayer.Order)
     *
     * @param order The new order object. Should not be null.
     * @return The new order object with the generated id key and status.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static Order addOrder(Order order) throws CustomException {
        return OrderMapper.addOrder(order);
    }

    /**
     * Get order by id.
     *
     * @see storageLayer.OrderMapper#getOrder(int)
     *
     * @param id The id of the order, should not be out of index bounds.
     * @return An order object with requested id.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static Order getOrder(int id) throws CustomException {
        return OrderMapper.getOrder(id);
    }

    /**
     * Get orders by email.
     *
     * @see storageLayer.OrderMapper#getOrders(java.lang.String)
     * @param email The email attached to the orders. Should not be null.
     *
     * @return A List of order objects with requested email.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static List<Order> getOrders(String email) throws CustomException {
        return OrderMapper.getOrders(email);
    }

    /**
     * Get all orders.
     *
     * @see storageLayer.OrderMapper#getAllOrders()
     *
     * @return A List of all order objects.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static List<Order> getAllOrders() throws CustomException {
        return OrderMapper.getAllOrders();
    }

    /**
     * Update order by order.
     *
     * @see storageLayer.OrderMapper#updateOrder(logicLayer.Order)
     *
     * @param order The modified order object. Should not be null.
     * @return An updated order object.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static Order updateOrder(Order order) throws CustomException {
        return OrderMapper.updateOrder(order);
    }

    /**
     * Update status by order.
     *
     * @see storageLayer.OrderMapper#updateStatus(logicLayer.Order)
     *
     * @param order The modified order object. Should not be null.
     * @return An updated order object.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static Order updateStatus(Order order) throws CustomException {
        return OrderMapper.updateStatus(order);
    }

    /**
     * Update status by id.
     *
     * @see storageLayer.OrderMapper#updateStatus(int, java.lang.String)
     *
     * @param id The id of the order. Should not be out of index bounds.
     * @param status The modified status. Should not be null.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static void updateStatus(int id, String status) throws CustomException {
        OrderMapper.updateStatus(id, status);
    }

    /**
     * Get all materials.
     *
     * @see storageLayer.MaterialMapper#getAllMaterials()
     *
     * @return A List of all materials objects.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static List<Material> getAllMaterials() throws CustomException {
        return MaterialMapper.getAllMaterials();
    }

    /**
     * Get materials by name.
     *
     * @see storageLayer.MaterialMapper#getMaterials(java.lang.String)
     *
     * @param material The name attached to the materials. Should not be null.
     * @return An arraylist of material objects with requested name.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static List<Material> getMaterials(String material) throws CustomException {
        return MaterialMapper.getMaterials(material);
    }

    /**
     * Update material with material object.
     *
     * @see storageLayer.MaterialMapper#updateMaterial(logicLayer.Material)
     *
     * @param material The modified material object. Should not be null.
     * @return An updated material object.
     * @throws CustomException 'Kunne ikke hente infomation'.
     */
    public static Material updateMaterial(Material material) throws CustomException {
        return MaterialMapper.updateMaterial(material);
    }

    /**
     * Update material by id with describtion.
     *
     * @see storageLayer.MaterialMapper#updateMaterial(int, int, int,
     * java.lang.String)
     *
     * @param id The id of the material. Should not be out of index bounds.
     * @param size The modified material size. Should not be negative.
     * @param price The modified material price. Should not be negative.
     * @param description The modified material description. Should not be null.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or if the closeStatement()
     * method can't close the connection.
     */
    public static void updateMaterial(int id, int size, int price, String description) throws CustomException {
        MaterialMapper.updateMaterial(id, size, price, description);
    }

    /**
     * Update material by id.
     *
     * @see storageLayer.MaterialMapper#updateMaterial(int, int, int)
     *
     * @param id The id of the material. Should not be out of index bounds.
     * @param size The modified material size. Should not be negative.
     * @param price The modified material price. Should not be negative.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or if the closeStatement()
     * method can't close the connection.
     */
    public static void updateMaterial(int id, int size, int price) throws CustomException {
        MaterialMapper.updateMaterial(id, size, price);
    }

    /**
     * Get all tools.
     *
     * @see storageLayer.ToolMapper#getAllTool()
     *
     * @return An arraylist of all tools objects.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static List<Material> getAllTools() throws CustomException {
        return ToolMapper.getAllTool();

    }

    /**
     * Get tool by id.
     *
     * @see storageLayer.ToolMapper#getTool(int)
     *
     * @param tool_id The id attached to the tool. Should not be out of index
     * bounds.
     * @return A Material object with requested id.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static Material getTool(int tool_id) throws CustomException {
        return ToolMapper.getTool(tool_id);
    }

    /**
     * Update tool with material object.
     *
     * @see storageLayer.ToolMapper#updateTool(logicLayer.Material)
     *
     * @param material The modified material object. Should not be null.
     * @return An updated material object.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static Material updateTool(Material material) throws CustomException {
        return ToolMapper.updateTool(material);
    }

    /**
     * Update tool by id.
     *
     * @see storageLayer.ToolMapper#updateTool(int, int, int)
     *
     * @param id The id attached to the tool. Should not be out of index bounds.
     * @param unitSize The modified unitsize. Should not be negative.
     * @param price The modified price. Should not be negative.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static void updateTool(int id, int unitSize, int price) throws CustomException {
        ToolMapper.updateTool(id, unitSize, price);
    }

    /**
     * Update roof by id.
     *
     * @see storageLayer.RoofMapper#updateRoof(int, java.lang.String,
     * java.lang.String)
     *
     * @param id The id og the roof. Should not be null.
     * @param name The modified name. Should not be null.
     * @param oldname The current name. Should not be null.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static void updateRoof(int id, String name, String oldname) throws CustomException {
        RoofMapper.updateRoof(id, name, oldname);
    }

    /**
     * Get all roofs.
     *
     * @see storageLayer.RoofMapper#getAllRoofs()
     *
     * @return An arraylist of all roofs objects.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static List<Roof> getAllRoofs() throws CustomException {
        return RoofMapper.getAllRoofs();
    }

    /**
     * Get roof by id.
     *
     * @see storageLayer.RoofMapper#getRoofById(int)
     *
     * @param id The id attached to the roof. Should not be out of index bounds.
     * @return A roof object with requested id.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static Roof getRoofById(int id) throws CustomException {
        return RoofMapper.getRoofById(id);
    }

    /**
     * Login as employee.
     *
     * @see storageLayer.EmployeeMapper#login(java.lang.String,
     * java.lang.String)
     *
     * @param name The username. Should not be null.
     * @param password The password. Should not be null.
     * @return A employee object with the name and password.
     * @throws CustomException if SQl syntax contains errors, can't connect to
     * database, the connection class isn't found or the closeStatement() method
     * can't close the connection.
     */
    public static Employee login(String name, String password) throws CustomException {
        return EmployeeMapper.login(name, password);
    }
}
