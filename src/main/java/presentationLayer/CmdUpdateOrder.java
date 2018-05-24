package presentationLayer;

import logicLayer.CustomException;
import logicLayer.LogicFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicLayer.NoAccessException;

/**
 *
 * @author super
 */
public class CmdUpdateOrder extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws NoAccessException
    {
        
        if(request.getSession().getAttribute("user") == null){
            throw new NoAccessException();
        }

        //params: "newStatus" -> String med nye status // "target" -> id på den ordre der skal skiftes
        try
        {
            //update status
            LogicFacade.updateOrder(Integer.parseInt(request.getParameter("target")), request.getParameter("newStatus"));

            //update pris
            if (request.getParameter("newPrice") != null)
            {
                LogicFacade.updatePrice(Integer.parseInt(request.getParameter("target")), Integer.parseInt(request.getParameter("newPrice")));
            }

            request.setAttribute("feedback", "Ordre opdateret!");

        } catch (NumberFormatException e)
        {

            request.setAttribute("feedback", "Der gik noget galt.");

        } catch (CustomException ex)
        {

            request.setAttribute("feedback", ex.getMessage());
        }

        return "allOrders";
    }
}
