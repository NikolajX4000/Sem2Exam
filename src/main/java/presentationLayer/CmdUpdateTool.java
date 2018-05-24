/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;

import functionLayer.CustomException;
import functionLayer.LogicFacade;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmdUpdateTool extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CustomException
    {
        try {
            
            int id = Integer.parseInt(request.getParameter("id"));
            int unitSize = Integer.parseInt(request.getParameter("unitSize"));
            int price = Integer.parseInt(request.getParameter("price"));
            String name = request.getParameter("name");

            LogicFacade.updateTool(id, unitSize, price);

            request.setAttribute("feedback", name + " nu opdateret!");

        } catch (Exception e)
        {

            request.setAttribute("feedback", "Der gik noget galt.");
            request.setAttribute("test", e);

        }

        return "materialpage";
    }
}

