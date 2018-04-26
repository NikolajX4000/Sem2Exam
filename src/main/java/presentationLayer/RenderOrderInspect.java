package presentationLayer;

import functionLayer.Order;

public class RenderOrderInspect {

    private static Order o;

    private RenderOrderInspect() {
    }

    private static String tab1() {
        String s = "<li>";

        s += "<div class=\"collapsible-header\">";
        s += "<i class=\"material-icons\">zoom_out_map</i>Detaljer";
        s += "<span class=\"new badge " + o.getStatusColor() + "\" data-badge-caption=\"" + o.getStatus() + "\"></span>";
        s += "</div>";

        s += "<div class=\"collapsible-body\"><p>ID: "+o.getId()+"</p></div>";

        return s += "</li>";
    }

    private static String tab2() {
        String s = "<li>";

        s += "<div class=\"collapsible-header\">";
        s += "<i class=\"material-icons\">photo</i>Tegninger";
        s += "</div>";

        s += "<div class=\"collapsible-body\"><p>Lorem ipsum dolor sit amet.</p></div>";

        return s += "</li>";
    }

    private static String tab3() {
        String s = "<li>";

        s += "<div class=\"collapsible-header\">";
        s += "<i class=\"material-icons\">format_list_bulleted</i>Stykliste";
        s += "</div>";

        s += "<div class=\"collapsible-body\"><p>Lorem ipsum dolor sit amet.</p></div>";

        return s += "</li>";
    }

    private static String tab4() {
        String s = "<li>";

        s += "<div class=\"collapsible-header\">";
        s += "<i class=\"material-icons\">event_note</i>Bemærkning";
        s += "</div>";

        String txt = (o.getNote().equals(""))? "Der er ikke nogen bemærkning til denne ordre." : o.getNote();
        s += "<div class=\"collapsible-body\"><p>"+ txt +"</p></div>";

        return s += "</li>";
    }
    
    
    private static String isChecked(String status){
        return status.equals(o.getStatus())? "checked" : "";
    }
    
    private static String updateStatus() {
        String s = "<div class=\"row\"><div class=\"col s12\">";
        
        s+= "<p>Opdater ordestatus:</p>";
        
        s+="<form action=\"?\"method=\"post\" accept-charset=\"ISO-8859-1\">";
        
        s+="<input type=\"hidden\" name=\"target\" value=\""+o.getId()+"\">";
        
        s+="<p>";
            s+="<input "+isChecked("Modtaget")+" name=\"newStatus\" type=\"radio\" id=\""+o.getId()+"label1\" value=\"Modtaget\"/>";
            s+="<label for=\""+o.getId()+"label1\">Modtaget</label>";
        s+="</p>";
        
        s+="<p>";
            s+="<input "+isChecked("Sendt")+" name=\"newStatus\" type=\"radio\" id=\""+o.getId()+"label2\" value=\"Sendt\"/>";
            s+="<label for=\""+o.getId()+"label2\">Sendt</label>";
        s+="</p>";
        
        s+="<p>";
            s+="<input "+isChecked("Behandles")+" name=\"newStatus\" type=\"radio\" id=\""+o.getId()+"label3\" value=\"Behandles\"/>";
            s+="<label for=\""+o.getId()+"label3\">Behandles</label>";
        s+="</p>";
        
        s+="<p>";
            s+="<input "+isChecked("Annulleret")+" name=\"newStatus\" type=\"radio\" id=\""+o.getId()+"label4\" value=\"Annulleret\"/>";
            s+="<label for=\""+o.getId()+"label4\">Annulleret</label>";
        s+="</p>";
        
        
        s+="<button class=\"btn waves-effect waves-light blue btn-large\" type=\"submit\" name=\"command\" value=\"UpdateOrderCommand\">Opdater";
            s+="<i class=\"material-icons right\">send</i>";
        s+="</button>";
        
        s+="</form>";
        
        
        
        return s += "</div></div>";
    }
    
    public static String print(Order o) {

        RenderOrderInspect.o = o;

        String s = "";

        s += "<div id=\"modal" + o.getStringId() + "\" class=\"modal\">";
        s += "<div class=\"modal-content\">";
        
        s += "<ul class=\"collapsible\" data-collapsible=\"accordion\">";
        s += tab1();
        s += tab2();
        s += tab3();
        s += tab4();
        s += "</ul>";
        
        s += updateStatus();
        
        s += "</div>";
        s += "</div>";

        return s;

        //return "RenderOrder{" + "s=" + s + '}';
    }
}
