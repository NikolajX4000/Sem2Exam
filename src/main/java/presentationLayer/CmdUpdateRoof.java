package presentationLayer;

import functionLayer.CustomException;
import functionLayer.LogicFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmdUpdateRoof extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String oldname = request.getParameter("oldname");

            LogicFacade.updateRoof(id, name, oldname);

            request.setAttribute("feedback", name + " nu opdateret!");

        } catch (NumberFormatException e)
        {

            request.setAttribute("feedback", "Der gik noget galt.");

        } catch (CustomException e)
        {

            request.setAttribute("feedback", e);
        }

        return "materialpage";
    }

}
