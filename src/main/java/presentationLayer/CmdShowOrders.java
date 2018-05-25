package presentationLayer;

import java.util.List;
import logicLayer.CustomException;
import logicLayer.LogicFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicLayer.Order;

/**
 *
 * @author super
 */
public class CmdShowOrders extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            List<Order> orders = LogicFacade.getOrders(request.getParameter("email"));

            if (orders.isEmpty())
            {
                throw new CustomException("Denne email har ingen ordre.");
            }

            request.setAttribute("desiredOrdersFromEmail", orders);

        } catch (CustomException e)
        {
            request.setAttribute("feedback", e.getMessage());
            return "index";
        }

        return "specificUserOrders";
    }
}
