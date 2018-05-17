<%-- 
    Document   : materialpage
    Created on : 16-05-2018, 09:07:08
    Author     : Hupra Laptop
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="functionLayer.Material"%>
<%@page import="presentationLayer.RenderOrder"%>
<%@page import="functionLayer.LogicFacade"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="functionLayer.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@include file="/WEB-INF/jspf/header.jspf"%>

<%

    List<Material> allMaterial = LogicFacade.getAllMaterials();

    HashMap<String, ArrayList<Material>> mats = new HashMap();
    
    for(Material m : allMaterial){
        
        // check if first of that type annd add to map if true
        if(mats.get(m.getName()) == null)
            mats.put(m.getName(), new ArrayList());

        //add material to map
        mats.get(m.getName()).add(m);
    }
    

    //generate dropdown links and popups
    StringBuilder dropDownLinks = new StringBuilder();
    StringBuilder popUps = new StringBuilder();
    
    for (Map.Entry<String, ArrayList<Material>> item : mats.entrySet()) {
        
        dropDownLinks.append("<li>");
        
            dropDownLinks.append("<div class='collapsible-header'>").append(item.getKey()).append("</div>");
            
            dropDownLinks.append("<div class='collapsible-body'><table class='highlight'><thead><tr><th>Beskrivelse</th><th>Størrelse</th><th>Pris</th><th>Knap</th></tr></thead><tbody>");
            
                for (Material m : item.getValue()){
                    //rows for table
                    dropDownLinks.append("<tr>");
                    dropDownLinks.append("<td>").append(m.getDescription()).append("</td>");
                    dropDownLinks.append("<td>").append(m.getSize()).append("</td>");
                    dropDownLinks.append("<td>").append(m.getPrice()).append("</td>");
                    dropDownLinks.append("<td>").append("<a href=\"#modal").append(m.getId()).append("\" class=\"btn-floating btn-small waves-effect waves-light blue tooltipped modal-trigger\" data-position=\"right\" data-delay=\"20\" data-tooltip=\"Rediger\"><i class=\"material-icons\">edit</i></a>").append("</td>");
                    dropDownLinks.append("</tr>");
                    
                    //modals
                    popUps.append("<div id=\"modal").append(m.getId()).append("\" class=\"modal\"><div class=\"modal-content\">");
                    
                        popUps.append("<p>").append(m.getName()).append(" #").append(m.getId()).append("</p>");
                        popUps.append("<form class=\"row\" method=\"post\" action=\"?\" id=\"test1\" accept-charset=\"ISO-8859-1\">");
                        
                            popUps.append("<input type=\"hidden\" name=\"material\" value=\"").append(m).append("\">");
                        
                            popUps.append("<div class=\"input-field col s12  m4\">");
                                popUps.append("<input id='description' type='text' class='validate' name='description' required maxlength='200' value='").append(m.getDescription()).append("'>");
                                popUps.append("<label for='description' data-error='Skriv en beskrivelse'>Beskrivelse</label>");
                            popUps.append("</div>");
                            
                            popUps.append("<div class=\"input-field col s12  m4\">");
                                popUps.append("<input id='size' type='number' class='validate' name='size' required maxlength='40' pattern='' value='").append(m.getSize()).append("'>");
                                popUps.append("<label for='size' data-error='Skriv en størrelse'>Størrelse</label>");
                            popUps.append("</div>");
                            
                            popUps.append("<div class=\"input-field col s12 m4\">");
                                popUps.append("<input id='price' type='number' class='validate' name='price' required maxlength='40' pattern='' value='").append(m.getPrice()).append("'>");
                                popUps.append("<label for='price' data-error='Skriv en pris'>Pris</label>");
                            popUps.append("</div>");
                            
                            popUps.append("<div class=\"input-field col s12\"><button class='btn waves-effect waves-light blue ' type='submit' name='command' value='CmdPageMaterial'>");
                                popUps.append("Gem <i class='material-icons right'>send</i>");
                            popUps.append("</button></div>");
                                
                        popUps.append("</form>");
                        

                    popUps.append("</div><div class=\"modal-footer\">");
                        
                        popUps.append("<a href=\"#!\" class=\"modal-action modal-close waves-effect waves-green btn-flat\">Close</a>");
                    
                    popUps.append("</div></div>");
                    
                }
                
            dropDownLinks.append("</tbody></table></div>");

        dropDownLinks.append("</li>");
        
    }
    
    request.setAttribute("dropDownLinks", dropDownLinks.toString());
    request.setAttribute("popUps", popUps.toString());
    //request.setAttribute("myMap", mats);
    pageContext.setAttribute("myMap", mats);
    

%>
<div class="row">
    
    <div class="col s12">
    
        <ul class="collapsible" data-collapsible="accordion">
            ${dropDownLinks}
        </ul>
        
    </div>
        
    ${popUps}
    
</div>
        
    
<%@include file="/WEB-INF/jspf/footer.jspf"%>  