

package presentationLayer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicLayer.NoAccessException;

public class CmdLogout extends Command{


    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws NoAccessException
    {
        request.getSession().invalidate();
        return "index";
    }
}
