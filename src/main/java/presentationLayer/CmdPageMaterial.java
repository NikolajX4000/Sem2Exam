package presentationLayer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicLayer.CustomException;
import logicLayer.LogicFacade;
import logicLayer.NoAccessException;

/**
 * This command gets alle the different kinds of materials and returns the user to the editMaterials.jsp page
 * @author Hupra Laptop
 */
public class CmdPageMaterial extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws NoAccessException
    {
        
        if(request.getSession().getAttribute("user") == null){
            throw new NoAccessException();
        }
        
        //content to page
        try
        {
            request.setAttribute("tools", LogicFacade.getAllTools());
            request.setAttribute("mats", LogicFacade.getAllMaterialsAsMap());
            request.setAttribute("roofs", LogicFacade.getAllRoofs());
            
        } catch (CustomException ex)
        {
            request.setAttribute("feedback", ex.getMessage());
        }

        return "editMaterials";
    }

}
