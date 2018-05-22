package presentationLayer;

import functionLayer.CustomException;
import functionLayer.LogicFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author super
 */
public class CmdUpdateOrder extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CustomException {
        
        
        //params: "newStatus" -> String med nye status // "target" -> id på den ordre der skal skiftes
        
        try {
            //update status
            LogicFacade.updateOrder(Integer.parseInt(request.getParameter("target")), request.getParameter("newStatus"));
            
            //update pris
            if(request.getParameter("newPrice") != null){
                LogicFacade.updatePrice(Integer.parseInt(request.getParameter("target")), Integer.parseInt(request.getParameter("newPrice")));
            }
            
            
            
            request.setAttribute("feedback", "Ordre opdateret!");
        } catch (Exception e) {
            request.setAttribute("test", e);
            //request.setAttribute("feedback", "Der gik noget galt.");
        }
        
        
        return "employeepage";
    }
}
