/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationLayer;

import functionLayer.CustomException;
import functionLayer.FlatCarPortList;
import functionLayer.Order;
import functionLayer.PartLine;
import functionLayer.TallCarPortList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author super
 */
public class RenderOrder {
    
    private static Order o;

    private RenderOrder() {
    }
    
    private static String shortDetail(Object value, String label){
        StringBuilder s = new StringBuilder();
        
        s.append("<div class=\"input-field col s6\">");
            s.append("<input disabled class=\"black-text\" type=\"text\" value=\"").append(value).append("\">");
            s.append("<label>").append(label).append("</label>");
        s.append("</div>");
        
        return s.toString();
    }
    
    private static String cardTop() throws CustomException{
        
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
                
                
                s.append("<div>");
                    s.append("<p>Navn: ").append(o.getName()).append("</p>");
                    s.append("<p>Addresse: ").append(o.getAddress()).append(", ").append(o.getZipCode()).append(" ").append(o.getCity()).append("</p><br>");
                s.append("</div>");
                
                /// kun vis pris hvis ordrer accepteret
                if(!o.getStatus().equals("Behandles") && !o.getStatus().equals("Annulleret")){
                    s.append("<div>");
                        s.append("<p>Pris: ").append(o.getPrice()).append("</p><br>");
                    s.append("</div>");
                }
                
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
    
    
    private static String tabDetails() throws CustomException{
        StringBuilder s = new StringBuilder();
        
        s.append("<div id=\"").append(o.getStringId()).append("a\">");
        s.append("<div class=\"row black-text\">");
        
            s.append(shortDetail(o.getWidth() + " cm", "Carport Bredde"));
            s.append(shortDetail(o.getLength() + " cm", "Carport Længde"));
            
            if(o.hasShed()){
                
                s.append(shortDetail(o.getShedWidth() + " cm", "Redskabsrum Bredde"));
                s.append(shortDetail(o.getShedLength() + " cm", "Redskabsrum Længde"));
            }
            
            s.append(shortDetail(o.getRoof().getNAME(), "Tag"));
            
            if(!o.isFlat()){
                s.append(shortDetail(o.getAngle() + " grader", "Taghældning"));
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
                //s.append("<div class=\"col m6 s12\"><div class='materialboxed z-depth-1'>").append(DrawCarport.flatSide(o.getLength(), o.getWidth(), o.getShedLength(), o.hasShed())).append("</div></div>");
                //s.append("<div class=\"col m6 s12\"><div class='materialboxed z-depth-1'>").append(DrawCarport.flatTop(o.getLength(), o.getWidth(), o.getShedLength(), o.getShedWidth(), o.hasShed())).append("</div></div>");
                s.append("<div class=\"col m6 s12\"><div class='materialboxed z-depth-1'>").append(new DrawCarportFlatTop(o)).append("</div></div>");
                s.append("<div class=\"col m6 s12\"><div class='materialboxed z-depth-1'>").append(new DrawCarportFlatSide(o)).append("</div></div>");
            }else{
                //s.append("<div class=\"col m6 s12\"><div class='materialboxed z-depth-1'>").append(DrawCarport.angledSide(o.getLength(), o.getWidth(), o.getShedLength(), o.getAngle(), o.hasShed())).append("</div></div>");
                //s.append("<div class=\"col m6 s12\"><div class='materialboxed z-depth-1'>").append(DrawCarport.angledTop(o.getLength(), o.getWidth(), o.getShedLength(), o.getShedWidth(), o.hasShed())).append("</div></div>");
                s.append("<div class=\"col m6 s12\"><div class='materialboxed z-depth-1'>").append(new DrawCarportAngleTop(o)).append("</div></div>");
                s.append("<div class=\"col m6 s12\"><div class='materialboxed z-depth-1'>").append(new DrawCarportAngleSide(o)).append("</div></div>");
            }
            s.append("</div>");
            
        s.append("</div>");
        
        return s.toString();
    }
    
    private static String tabPartlist(){
        
        StringBuilder s = new StringBuilder();
        
        s.append("<div id=\"").append(o.getStringId()).append("c\">");
            s.append("<span class=\"card-title\">Stykliste</span>");
           
                s.append("<table><tbody>");
                s.append("<tr><th>Antal</th><th>Størrelse</th><th>Navn</th></tr>");
                try {
                    
                //ArrayList
                List<PartLine> list = new ArrayList<>();
                if(o.isFlat()){
                    FlatCarPortList cpl = new FlatCarPortList(o);
                    list = cpl.getParts();
                }else
                {
                    TallCarPortList tcpl = new TallCarPortList(o);
                    list = tcpl.getParts();
                }
                    for(PartLine pl : list){
                        s.append("<tr>");
                        
                        s.append("<td>").append(pl.getAmount()).append("</td>");
                        s.append("<td>").append(pl.getSize()).append("</td>");
                        s.append("<td>").append(pl.getMaterial().getName()).append("</td>");
                        
                        s.append("</tr>");
                    }
                } catch (Exception e) {
                    s.append(e);
                }
                
                s.append("</tbody></table>");
                
            


            
            
            
            
            
            //s.append("<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>");
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
    public static String print(Order o) throws CustomException
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
