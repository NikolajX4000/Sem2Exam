package presentationLayer;

import logicLayer.CustomException;
import logicLayer.LogicFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicLayer.NoAccessException;

/**
 * This command updates a given tool and returns the user to the editMaterials.jsp page.
 * @author Hupra Laptop
 */
public class CmdUpdateTool extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws NoAccessException
    {
        
        if(request.getSession().getAttribute("user") == null){
            throw new NoAccessException();
        }
        
        try
        {
            int id = Integer.parseInt(request.getParameter("id"));
            int unitSize = Integer.parseInt(request.getParameter("unitSize"));
            int price = Integer.parseInt(request.getParameter("price"));
            String name = request.getParameter("name");

            LogicFacade.updateTool(id, unitSize, price);

            request.setAttribute("feedback", name + " nu opdateret!");

        } catch (NumberFormatException e)
        {

            request.setAttribute("feedback", "Der gik noget galt.");

        } catch (CustomException ex)
        {

            request.setAttribute("feedback", ex.getMessage());
        }

        
        
        //content to page
        try
        {
            request.setAttribute("tools", LogicFacade.getAllTool());
            request.setAttribute("mats", LogicFacade.getAllMaterialsAsMap());
            request.setAttribute("roofs", LogicFacade.getAllRoofs());
            
        } catch (CustomException ex)
        {
            request.setAttribute("feedback", ex.getMessage());
        }
        return "editMaterials";
    }
}
