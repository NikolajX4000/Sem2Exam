/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationLayer;

import functionLayer.Order;

/**
 *
 * @author super
 */
public class RenderOrder {
    
    private static Order o;

    private RenderOrder() {
    }
    
    private static String cardTop(){
        
        StringBuilder s = new StringBuilder();
        
        s.append("<div class=\"card-content\">");
        
            s.append("<span class=\"new badge ").append(o.getStatusColor()).append(" right\" data-badge-caption=\"").append(o.getStatus()).append("\"></span>");
            s.append("<span class=\"card-title\">Carport #").append(o.getId()).append("</span>");

            s.append("<blockquote style=\"border-color: #2196f3;\">");
                
                s.append("<div class=\"right\">");
                
                    s.append("<p class=\"right\">Bestilt: ").append(o.getPlaced()).append("</p><br><br>");
                    
                    if(o.isFlat()){
                        s.append("<img class=\"right\" src=\"https://www.johannesfog.dk/globalassets/service/quickbyg/quickurejs.gif\">");
                    }else{
                        s.append("<img class=\"right\" src=\"https://www.johannesfog.dk/globalassets/service/quickbyg/quickmrejs.gif\">");
                    }
                    
                s.append("</div>");
                
                s.append("<p>Navn: ").append(o.getName()).append("</p>");
                s.append("<p>Addresse: ").append(o.getAddress()).append(", ").append(o.getZipCode()).append(" ").append(o.getCity()).append("</p><br>");
                
                s.append("<div>");
                    s.append("<p >Telefon: ").append(o.getPhone()).append("</p>");
                    s.append("<p >E-mail: ").append(o.getEmail()).append("</p>");
                s.append("</div>");

            s.append("</blockquote>");
        
        
        s.append("</div>");
        
        return s.toString();
    }
    
    private static String cardTabs(){
        StringBuilder s = new StringBuilder();
        
        s.append("<div class=\"card-tabs\"><ul class=\"tabs\">");
        
        s.append("<li class=\"tab\"><a href=\"#").append(o.getStringId()).append("a\" class=\"tooltipped\" data-position=\"top\" data-delay=\"50\" data-tooltip=\"Detaljer\"><i class=\"material-icons\">zoom_out_map</i></a></li>");
        s.append("<li class=\"tab\"><a href=\"#").append(o.getStringId()).append("b\" class=\"tooltipped\" data-position=\"top\" data-delay=\"50\" data-tooltip=\"Tegninger\"><i class=\"material-icons\">photo</i></a></li>");
        s.append("<li class=\"tab\"><a href=\"#").append(o.getStringId()).append("c\" class=\"tooltipped\" data-position=\"top\" data-delay=\"50\" data-tooltip=\"Stykliste\"><i class=\"material-icons\">format_list_bulleted</i></a></li>");
        s.append("<li class=\"tab\"><a href=\"#").append(o.getStringId()).append("d\" class=\"tooltipped\" data-position=\"top\" data-delay=\"50\" data-tooltip=\"Bem\u00e6rkning\"><i class=\"material-icons\">event_note</i></a></li>");
        
        s.append("</ul></div>");
        
        return s.toString();
    }
    
    
    private static String tabDetails(){
        StringBuilder s = new StringBuilder();
        
        s.append("<div id=\"").append(o.getStringId()).append("a\">");
        s.append("<div class=\"row black-text\">");
        
            s.append("<div class=\"input-field col s6\">");
                s.append("<input disabled class=\"black-text\" type=\"text\" value=\"").append(o.getWidth()).append(" cm\">");
                s.append("<label>Carport Bredde</label>");
            s.append("</div>");
            
            s.append("<div class=\"input-field col s6\">");
                s.append("<input disabled class=\"black-text\" type=\"text\" value=\"").append(o.getLength()).append(" cm\">");
                s.append("<label>Carport Længde</label>");
            s.append("</div>");
            
            
            
            if(o.hasShed()){
            s.append("<div class=\"input-field col s6\">");
                s.append("<input disabled class=\"black-text\" type=\"text\" value=\"").append(o.getShedWidth()).append(" cm\">");
                s.append("<label>Redskabsrum Bredde</label>");
            s.append("</div>");

            s.append("<div class=\"input-field col s6\">");
                s.append("<input disabled class=\"black-text\" type=\"text\" value=\"").append(o.getShedLength()).append(" cm\">");
                s.append("<label>Redskabsrum Længde</label>");
            s.append("</div>");
            }
            
            
            
            s.append("<div class=\"input-field col s6\">");
                s.append("<input disabled class=\"black-text\" type=\"text\" value=\"").append(o.getRoof()).append(" Flot R\u00f8dt Tag\">");
                s.append("<label>Tag</label>");
            s.append("</div>");
            
            if(!o.isFlat()){
            s.append("<div class=\"input-field col s6\">");
                s.append("<input disabled class=\"black-text\" type=\"text\" value=\"").append(o.getAngle()).append(" grader\">");
                s.append("<label>Taghældning</label>");
            s.append("</div>");
            }
            
        s.append("</div>");
        s.append("</div>");
        
        return s.toString();
    }
    
    private static String tabDrawing(){
        StringBuilder s = new StringBuilder();
        
        s.append("<div id=\"").append(o.getStringId()).append("b\">");
            s.append("<span class=\"card-title\">Tegninger</span>");
            s.append("<div class='row'>");
            if(o.isFlat()){
                s.append("<div class=\"col m6 s12\"><div class='materialboxed z-depth-1'>").append(DrawCarport.flatSide(o.getLength(), o.getWidth(), o.getShedLength(), o.hasShed())).append("</div></div>");
                s.append("<div class=\"col m6 s12\"><div class='materialboxed z-depth-1'>").append(DrawCarport.flatTop(o.getLength(), o.getWidth(), o.getShedLength(), o.getShedWidth(), o.hasShed())).append("</div></div>");
            }else{
                s.append("<div class=\"col m6 s12\"><div class='materialboxed z-depth-1'>").append(DrawCarport.angledSide(o.getLength(), o.getWidth(), o.getShedLength(), o.getAngle(), o.hasShed())).append("</div></div>");
                s.append("<div class=\"col m6 s12\"><div class='materialboxed z-depth-1'>").append(DrawCarport.angledTop(o.getLength(), o.getWidth(), o.getShedLength(), o.getShedWidth(), o.hasShed())).append("</div></div>");
            }
            s.append("</div>");
            
        s.append("</div>");
        
        return s.toString();
    }
    
    private static String tabPartlist(){
        
        StringBuilder s = new StringBuilder();
        
        s.append("<div id=\""+ o.getStringId() + "c" +"\">");
            s.append("<span class=\"card-title\">Stykliste</span>");
            s.append("<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>");
        s.append("</div>");
        
        return s.toString();
    }
    
    private static String tabNote(){
        StringBuilder s = new StringBuilder();
        
        String note = (o.getNote().equals(""))? "Der er ikke nogen bemærkninger til denne ordre." : o.getNote();
        
        s.append("<div id=\"").append(o.getStringId()).append("d\">");
            s.append("<span class=\"card-title\">Bemærkning</span>");
            s.append("<p>").append(note).append("</p>");
        s.append("</div>");
        
        return s.toString();
    }
    
    /**
     *
     * @param o
     * @return
     */
    public static String print(Order o)
    {
        
        RenderOrder.o = o;
        StringBuilder s = new StringBuilder();
        
        s.append( "<div class=\"col l6 m12\">");
        s.append( "<div class=\"card\">");
        
        s.append(cardTop());
        s.append(cardTabs());
        
        s.append("<div class=\"card-content grey lighten-4\">");
        
            s.append(tabDetails());
            s.append(tabDrawing());
            s.append(tabPartlist());
            s.append(tabNote());
        
        s.append("</div>");
        
        s.append( "</div>");
        s.append( "</div>");
        
        return s.toString();
        
    }

}
