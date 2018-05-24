package presentationLayer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicLayer.CustomException;
import logicLayer.LogicFacade;

public class CmdPageMaterial extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {
        //content to page
        try
        {
            request.setAttribute("tools", LogicFacade.getAllTool());
            request.setAttribute("mats", LogicFacade.getAllMaterialsAsMap());
            request.setAttribute("roofs", LogicFacade.getAllRoofs());
            
        } catch (CustomException ex)
        {
            request.setAttribute("feedback", ex);
        }

        return "editMaterials";
    }

}
