package functionLayer;

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
}
