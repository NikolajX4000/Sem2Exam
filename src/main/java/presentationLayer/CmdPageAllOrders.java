package presentationLayer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author super
 */
public class CmdPageAllOrders extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {
        return "employeepage";
    }

}
