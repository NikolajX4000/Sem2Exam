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
public class CmdShowOrders extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CustomException {
        
        String email = request.getParameter("email");
        
        if(email != null){
            request.setAttribute("desiredOrdersFromEmail", LogicFacade.getOrders(email));
        }
        
        return "vieworders";
    }
}
