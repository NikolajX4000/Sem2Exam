/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationLayer;

import functionLayer.CustomException;
import functionLayer.LogicFacade;
import functionLayer.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateOrderCommand extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CustomException
    {
        Order o = new Order();
        try
        {
            
        

            o.setWidth(Integer.parseInt(request.getParameter("width")));
            o.setLength(Integer.parseInt(request.getParameter("length")));

            o.setRoof(Integer.parseInt(request.getParameter("roof")));

            if(request.getParameter("hasShed") != null){
               o.setShedLength(Integer.parseInt(request.getParameter("shedlength")));
               o.setShedWidth(Integer.parseInt(request.getParameter("shedwidth")));
            }

            if(request.getParameter("hasAngle") != null){
                o.setAngle(Integer.parseInt(request.getParameter("angle")));
            }

            //information
            o.setName((String)request.getParameter("name"));
            o.setAddress((String)request.getParameter("address"));
            o.setZipCode(Integer.parseInt(request.getParameter("zipcode")));
            o.setCity((String)request.getParameter("city"));
            o.setPhone((String)request.getParameter("phone"));
            o.setEmail((String)request.getParameter("email"));
            o.setNote((String)request.getParameter("note"));
            
            LogicFacade.addOrder(o);
            
            //request.setAttribute("test", o.toString());
            
            request.setAttribute("desiredOrdersFromEmail", o.getEmail());
            
            return "order";
            
            
        } catch (Exception e)
        {
            request.setAttribute("feedback", "<p class=\"red-text\">Der gik noget galt prøv igen senere!</p>");
            //request.setAttribute("test", e.getMessage());
            return "order"; 
        }
        

    }

}
