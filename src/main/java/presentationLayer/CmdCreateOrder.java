/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationLayer;

import functionLayer.CustomException;
import functionLayer.DanielsPostHus;
import functionLayer.LogicFacade;
import functionLayer.Order;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author super
 */
public class CmdCreateOrder extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response)
    {
        
        
        try
        {
            int width = Integer.parseInt(request.getParameter("width"));
            int length = Integer.parseInt(request.getParameter("length"));
            int shedwidth = Integer.parseInt(request.getParameter("shed_width"));
            int shedlength = Integer.parseInt(request.getParameter("shed_length"));
            
            boolean hasShed = (request.getParameter("hasShed") != null);
            
            if(width-30<shedwidth){
                throw new CustomException("Caporten skal være bredere end skuret");
            }
            if(length-60<shedlength){
                throw new CustomException("Caporten skal være længere end skuret");
            }
        
            Order o = new Order();
            
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
            
            try {
                // send mail til fog omkring ny ordre
                DanielsPostHus.newOrder(o);
            } catch (MessagingException ex) {
                //Logger.getLogger(CmdCreateOrder.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.setAttribute("desiredOrdersFromEmail", LogicFacade.getOrders(o.getEmail()));
            
            return "specificUserOrders";
            
            
        } catch (NumberFormatException e)
        {
            request.setAttribute("feedback", "<p>Der gik noget galt prøv igen senere!</p>");
            
        } catch (CustomException e){
            request.setAttribute("feedback", e.getMessage());
        }
        
        return "makeCarport";

    }

}
