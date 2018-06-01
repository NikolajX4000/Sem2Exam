package presentationLayer;

import logicLayer.CustomException;
import logicLayer.LogicFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicLayer.NoAccessException;

/**
 * This command updates a given material and returns the user to the editMaterials.jsp page.
 * @author Hupra Laptop
 */
public class CmdUpdateMaterial extends Command
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
            int size = Integer.parseInt(request.getParameter("size"));
            int price = Integer.parseInt(request.getParameter("price"));
            String description = request.getParameter("description");

            if (description == null)
            {
                LogicFacade.updateMaterial(id, size, price);
                request.setAttribute("feedback", "Opdateret!");
            } else
            {
                LogicFacade.updateMaterial(id, size, price, description);
                request.setAttribute("feedback", description + " nu opdateret!");
            }

        } catch (NumberFormatException ex)
        {

            request.setAttribute("feedback", "Der gik noget galt.");

        } catch (CustomException ex)
        {

            request.setAttribute("feedback", ex.getMessage());
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
