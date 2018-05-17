/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationLayer;

import functionLayer.CustomException;
import functionLayer.LogicFacade;
import functionLayer.Order;
import functionLayer.Roof;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author super
 */
public class CmdCreateOrder extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws CustomException
    {
        
        Order o = new Order();
        try
        {
            int width = Integer.parseInt(request.getParameter("width"));
            int length = Integer.parseInt(request.getParameter("length"));
            int shedwidth = Integer.parseInt(request.getParameter("shedwidth"));
            int shedlength = Integer.parseInt(request.getParameter("shedlength"));
            
            boolean hasShed = (request.getParameter("hasShed") != null);
            
            if(width-30<shedwidth){
                throw new CustomException("Caporten skal være bredere end skuret");
            }
            if(length-30<shedlength){
                throw new CustomException("Caporten skal være længere end skuret");
            }
        

            o.setWidth(width);
            o.setLength(length);

            o.setRoof(Integer.parseInt(request.getParameter("roof")));

            if(hasShed){
               o.setShedLength(shedlength);
               o.setShedWidth(shedwidth);
            }

            if(request.getParameter("hasAngle") != null){
                o.setAngle(Integer.parseInt(request.getParameter("angle")));
            }

            //information
            o.setName(request.getParameter("name"));
            o.setAddress(request.getParameter("address"));
            o.setZipCode(Integer.parseInt(request.getParameter("zipcode")));
            o.setCity(request.getParameter("city"));
            o.setPhone(request.getParameter("phone"));
            o.setEmail(request.getParameter("email"));
            o.setNote(request.getParameter("note"));
            
            LogicFacade.addOrder(o);
            
            //request.setAttribute("test", o.toString());
            
            request.setAttribute("desiredOrdersFromEmail", LogicFacade.getOrders(o.getEmail()));
            
            return "vieworders";
            
            
        } catch (NumberFormatException e)
        {
            request.setAttribute("feedback", "<p>Der gik noget galt prøv igen senere!</p>");
            request.setAttribute("test", e.getMessage());
            return "order"; 
        } catch (CustomException e){
            request.setAttribute("feedback", e.getMessage());
        }
        
        return "order";

    }

}
