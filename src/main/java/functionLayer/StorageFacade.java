package functionLayer;

import java.util.ArrayList;
import storageLayer.OrderMapper;

/**
 *
 * @author super
 */
public class StorageFacade {
    
    /**
     * 
     * @see storageLayer.OrderMapper#addOrder( Order )
     * @param order
     * @return 
     * @throws CustomException 
     */
    public static Order addOrder( Order order ) throws CustomException {
        return OrderMapper.addOrder( order );
    }
    
    /**
     * 
     * @see storageLayer.OrderMapper#getOrder( int )
     * @param id
     * @return
     * @throws CustomException 
     */
    public static Order getOrder( int id ) throws CustomException {
        return OrderMapper.getOrder( id );
    }
    
    /**
     * 
     * @see storageLayer.OrderMapper#getOrders( String )
     * @param email
     * @return
     * @throws CustomException 
     */
    public static ArrayList<Order> getOrders( String email ) throws CustomException {
        return OrderMapper.getOrders( email );
    }
    
    /**
     * 
     * @see storageLayer.OrderMapper#getAllOrders()
     * @return
     * @throws CustomException 
     */
    public static ArrayList<Order> getAllOrders() throws CustomException {
        return OrderMapper.getAllOrders();
    }
    
    /**
     * 
     * @see storageLayer.OrderMapper#updateOrder( Order )
     * @param order
     * @return
     * @throws CustomException 
     */
    public static Order updateOrder( Order order ) throws CustomException {
        return OrderMapper.updateOrder( order );
    }
    
    /**
     * 
     * @see storageLayer.OrderMapper#updateStatus( Order )
     * @param order
     * @return
     * @throws CustomException 
     */
    public static Order updateStatus( Order order ) throws CustomException {
        return OrderMapper.updateStatus( order );
    }
}
