/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logicLayer.CustomException;
import logicLayer.LogicFacade;

public class CmdPageLogin extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {
        
        //content
        try
        {
            request.setAttribute("orders", LogicFacade.getAllOrders());
            
        } catch (CustomException ex)
        {
            request.setAttribute("feedback", ex.getMessage());
        }
        return "login";
    }

}
