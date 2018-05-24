package presentationLayer;

import logicLayer.CustomException;
import logicLayer.LogicFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmdUpdateTool extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {
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

        } catch (CustomException e)
        {

            request.setAttribute("feedback", e);
        }

        return "editMaterials";
    }
}
