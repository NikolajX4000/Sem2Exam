
package presentationLayer;

import functionLayer.CustomException;
import functionLayer.LogicFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmdUpdateRoof extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CustomException {
        try {
            
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");

            LogicFacade.updateRoof(id, name);

            request.setAttribute("feedback", name + " nu opdateret!");

        } catch (Exception e)
        {

            request.setAttribute("feedback", "Der gik noget galt.");
            request.setAttribute("test", e);

        }

        return "materialpage";
    }

}
