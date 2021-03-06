package presentationLayer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicLayer.CustomException;
import logicLayer.LogicFacade;

/**
 * This command gets the different roof types and returns the user to makeCarport.jsp
 * @author super
 */
public class CmdPageBuildCarport extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {
        
        try
        {
            request.setAttribute("roofs", LogicFacade.getAllRoofs());
        } catch (CustomException ex)
        {
            request.setAttribute("feedback", ex.getMessage());
        }
        return "makeCarport";
    }

}
