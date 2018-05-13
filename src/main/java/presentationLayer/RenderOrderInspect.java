package presentationLayer;

import functionLayer.Order;

/**
 *
 * @author super
 */
public class RenderOrderInspect {

    private static Order o;

    private RenderOrderInspect() {
    }

    private static String tabStart(String icon, String text){
        
        StringBuilder s = new StringBuilder();
        
        s.append("<li>");

        s.append("<div class=\"collapsible-header\">");
        s.append("<i class=\"material-icons\">").append(icon).append("</i>").append(text);
        
        if(text.equals("Detaljer")){
            s.append("<span class=\"new badge ").append(o.getStatusColor()).append("\" data-badge-caption=\"").append(o.getStatus()).append("\"></span>");
        }
        
        s.append("</div>");

        s.append("<div class=\"collapsible-body\">");
        
        return s.toString();
    }
    
    private static String tabEnd(){
        return "</div></li>";
    }
    
    private static String shortDetail(Object value, String label){
        StringBuilder s = new StringBuilder();
        
        s.append("<div class=\"input-field col s6\">");
            s.append("<input disabled class=\"black-text\" type=\"text\" value=\"").append(value).append("\">");
            s.append("<label>").append(label).append("</label>");
        s.append("</div>");
        
        return s.toString();
    }
    
    private static String tabDetails() {
        StringBuilder s = new StringBuilder();
        s.append(tabStart("zoom_out_map", "Detaljer"));

        s.append("<div class=\"row black-text\">");
        
        
            s.append(shortDetail(o.getId(), "Ordre Nummer"));
            s.append(shortDetail(o.getPrice(), "Pris"));
            s.append(shortDetail(o.getName(), "Navn"));
            s.append(shortDetail(o.getPhone(), "Telefon"));
            s.append(shortDetail(o.getEmail(), "Email"));
            s.append(shortDetail(o.getAddress(), "Adresse"));
            s.append(shortDetail(o.getZipCode(), "Postnummer"));
            s.append(shortDetail(o.getCity(), "By"));
            s.append(shortDetail(o.getWidth() + " cm", "Carport Bredde"));
            s.append(shortDetail(o.getLength() + " cm", "Carport Længde"));
            
            if(o.hasShed()){
                
                s.append(shortDetail(o.getShedWidth() + " cm", "Redskabsrum Bredde"));
                s.append(shortDetail(o.getShedLength() + " cm", "Redskabsrum Længde"));
            }
            
            s.append(shortDetail(o.getRoof() + " Flot R\u00f8dt Tag", "Tag"));
            
            if(!o.isFlat()){
                s.append(shortDetail(o.getAngle() + " grader", "Taghældning"));
            }
            
        s.append("</div>");
        
        s.append(tabEnd());
        return s.toString();
    }

    private static String tabDrawing() {
        StringBuilder s = new StringBuilder();
        
        s.append(tabStart("photo", "Tegninger"));
        s.append("<div class='row'>");
            
        if(o.isFlat()){
            s.append("<div class=\"col m6\"><div class='materialboxed z-depth-1'>").append(DrawCarport.flatTop(o.getLength(), o.getWidth(), o.getShedLength(), o.getShedWidth(), o.hasShed())).append("</div></div>");
            s.append("<div class=\"col m6\"><div class='materialboxed z-depth-1'>").append(DrawCarport.flatSide(o.getLength(), o.getWidth(), o.getShedLength(), o.hasShed())).append("</div></div>");
        }else{
            s.append("<div class=\"col m6\"><div class='materialboxed z-depth-1'>").append(DrawCarport.angledTop(o.getLength(), o.getWidth(), o.getShedLength(), o.getShedWidth(), o.hasShed())).append("</div></div>");
            s.append("<div class=\"col m6\"><div class='materialboxed z-depth-1'>").append(DrawCarport.angledSide(o.getLength(), o.getWidth(), o.getShedLength(), o.getAngle(), o.hasShed())).append("</div></div>");
        }
        
        s.append("</div>");
        s.append(tabEnd());
        
        return s.toString();
    }

    private static String tabPartlist() {
        
        StringBuilder s = new StringBuilder();
        s.append(tabStart("format_list_bulleted", "Stykliste"));
        
        
        s.append("<p>Lorem ipsum dolor sit amet.</p>");
        
        
        s.append(tabEnd());
        
        return s.toString();
    }

    private static String tabNote() {
        StringBuilder s = new StringBuilder();
        s.append(tabStart("event_note", "Bemærkning"));

        
        String txt = (o.getNote().equals(""))? "Der er ikke nogen bemærkning til denne ordre." : o.getNote();
        s.append("<p>").append(txt).append("</p>");
        

        s.append(tabEnd());
        return s.toString();
    }
    
    
    private static String isChecked(String status){
        return status.equals(o.getStatus())? "checked" : "";
    }
    
    private static String updateStatus() {
        StringBuilder s = new StringBuilder();
        
        s.append("<div class=\"row\"><div class=\"col s12\">");
        
        s.append("<p>Opdater ordestatus:</p>");
        
        s.append("<form action=\"?\"method=\"post\" accept-charset=\"ISO-8859-1\">");
        
        s.append("<input type=\"hidden\" name=\"target\" value=\"").append(o.getId()).append("\">");
        
        s.append("<p>");
            s.append("<input ").append(isChecked("Modtaget")).append(" name=\"newStatus\" type=\"radio\" id=\"").append(o.getId()).append("label1\" value=\"Modtaget\"/>");
            s.append("<label for=\"").append(o.getId()).append("label1\">Modtaget</label>");
        s.append("</p>");
        
        s.append("<p>");
            s.append("<input ").append(isChecked("Sendt")).append(" name=\"newStatus\" type=\"radio\" id=\"").append(o.getId()).append("label2\" value=\"Sendt\"/>");
            s.append("<label for=\"").append(o.getId()).append("label2\">Sendt</label>");
        s.append("</p>");
        
        
        if(o.getStatus().equals("Behandles")){
            s.append("<p>");
                s.append("<input ").append(isChecked("Behandles")).append(" name=\"newStatus\" type=\"radio\" id=\"").append(o.getId()).append("label3\" value=\"Behandles\"/>");
                s.append("<label for=\"").append(o.getId()).append("label3\">Behandles</label>");
            s.append("</p>");
        }
        
        s.append("<p>");
            s.append("<input ").append(isChecked("Annulleret")).append(" name=\"newStatus\" type=\"radio\" id=\"").append(o.getId()).append("label4\" value=\"Annulleret\"/>");
            s.append("<label for=\"").append(o.getId()).append("label4\">Annulleret</label>");
        s.append("</p>");
        
        
        s.append("<button class=\"btn waves-effect waves-light blue btn-large\" type=\"submit\" name=\"command\" value=\"UpdateOrderCommand\">Opdater");
            s.append("<i class=\"material-icons right\">send</i>");
        s.append("</button>");
        
        s.append("</form>");
        
        s.append("</div></div>");
        
        return s.toString();
    }
    
    /**
     *
     * @param o
     * @return
     */
    public static String print(Order o) {

        RenderOrderInspect.o = o;

        StringBuilder s = new StringBuilder();

        s.append("<div id=\"modal").append(o.getStringId()).append("\" class=\"modal\">");
        s.append("<div class=\"modal-content\">");
        
        s.append("<ul class=\"collapsible\" data-collapsible=\"accordion\">");
        s.append(tabDetails());
        s.append(tabDrawing());
        s.append(tabPartlist());
        s.append(tabNote());
        s.append("</ul>");
        
        s.append(updateStatus());
        
        s.append("</div>");
        s.append("</div>");

        return s.toString();

        //return "RenderOrder{" + "s=" + s + '}';
    }
}
