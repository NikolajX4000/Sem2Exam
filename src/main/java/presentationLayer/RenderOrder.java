/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationLayer;

import functionLayer.Order;

public class RenderOrder {
    
    Order o;

    public RenderOrder(Order o)
    {
        this.o = o;
    }

    
    private String cartTop(){
        String s = "";
        
        s+="<div class=\"card-content\">";
        
        
        
        
        s+="</div>";
        
        return s;
    }
    
    
    
    
    
    
    @Override
    public String toString()
    {
        String s = "";
        
        s+= "<div class=\"col l6 m12\">";
        s+= "<div class=\"card\">";
        
        
        
        
        
        s+= "</div>";
        s+= "</div>";
        
        
        
        return "RenderOrder{" + "s=" + s + '}';
    }
    
    
    
    

}
