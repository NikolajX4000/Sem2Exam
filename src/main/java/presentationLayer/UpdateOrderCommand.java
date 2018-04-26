package presentationLayer;

import functionLayer.CustomException;
import functionLayer.LogicFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author super
 */
public class UpdateOrderCommand extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CustomException {
        
        
        //params: "newStatus" -> String med nye status // "target" -> id på den ordre der skal skiftes
        
        try {
            LogicFacade.updateOrder(Integer.parseInt(request.getParameter("target")), request.getParameter("newStatus"));
        } catch (Exception e) {
        }
        
        
        return "employeepage";
    }
}
