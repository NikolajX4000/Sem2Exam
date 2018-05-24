package presentationLayer;

import functionLayer.CustomException;
import functionLayer.LogicFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author super
 */
public class CmdShowOrders extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {

        String email = request.getParameter("email");

        if (email != null)
        {
            try
            {
                request.setAttribute("desiredOrdersFromEmail", LogicFacade.getOrders(email));
            } catch (CustomException e)
            {
                request.setAttribute("feedback", e.getMessage());
            }
        }

        return "viewordersTEST";
    }
}
