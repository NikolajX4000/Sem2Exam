package presentationLayer;

import logicLayer.CustomException;
import logicLayer.LogicFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmdUpdateMaterial extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {

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

        } catch (NumberFormatException e)
        {

            request.setAttribute("feedback", "Der gik noget galt.");

        } catch (CustomException e)
        {

            request.setAttribute("feedback", e);
        }

        return "editMaterials";
    }

}
