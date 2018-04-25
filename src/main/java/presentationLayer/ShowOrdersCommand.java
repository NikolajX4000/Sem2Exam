package presentationLayer;

import functionLayer.CustomException;
import functionLayer.LogicFacade;
import functionLayer.Order;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author super
 */
public class ShowOrdersCommand extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CustomException {
        String email = request.getParameter("email");
        List<Order> orders = LogicFacade.getOrders(email);
        HttpSession s = request.getSession();
        s.setAttribute("orders", orders);
        return "vieworders";
    }
}
