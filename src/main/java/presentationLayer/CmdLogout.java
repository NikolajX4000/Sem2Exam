

package presentationLayer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicLayer.NoAccessException;

/**
 * This command logs the employee out of the website
 * @author Hupra Laptop
 */
public class CmdLogout extends Command{


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws NoAccessException
    {
        request.getSession().invalidate();
        return "index";
    }
}
