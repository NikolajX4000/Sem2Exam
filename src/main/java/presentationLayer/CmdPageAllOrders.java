package presentationLayer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicLayer.CustomException;
import logicLayer.LogicFacade;
import logicLayer.NoAccessException;

/**
 * This command gets all orders from the database and returns the user to allorders.jsp
 * @author super
 */
public class CmdPageAllOrders extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws NoAccessException
    {
        
        if(request.getSession().getAttribute("user") == null){
            throw new NoAccessException();
        }
        
        //content
        try
        {
            request.setAttribute("orders", LogicFacade.getAllOrders());
            
        } catch (CustomException ex)
        {
            request.setAttribute("feedback", ex.getMessage());
        }
        return "allOrders";
    }

}
