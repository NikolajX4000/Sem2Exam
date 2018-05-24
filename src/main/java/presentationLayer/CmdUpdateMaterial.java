/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationLayer;

import functionLayer.CustomException;
import functionLayer.LogicFacade;
import functionLayer.Material;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmdUpdateMaterial extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CustomException
    {
        
        try {
            
            int id = Integer.parseInt(request.getParameter("id"));
            int size = Integer.parseInt(request.getParameter("size"));
            int price = Integer.parseInt(request.getParameter("price"));
            String description = request.getParameter("description");
            
            if(description == null){
                LogicFacade.updateMaterial(id, size, price);
                request.setAttribute("feedback", "Opdateret!");
            }else{
                LogicFacade.updateMaterial(id, size, price, description);
                request.setAttribute("feedback", description + " nu opdateret!");
            }
            
            
            
        } catch (Exception e) {
            
            request.setAttribute("feedback", "Der gik noget galt.");
            request.setAttribute("test", e);
            
        }

        return "materialpage";
    }

}
