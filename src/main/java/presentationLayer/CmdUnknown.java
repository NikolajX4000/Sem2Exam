package presentationLayer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This command returns the user to the index.jsp page
 * @author super
 */
public class CmdUnknown extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {

        return "index";
    }

}
