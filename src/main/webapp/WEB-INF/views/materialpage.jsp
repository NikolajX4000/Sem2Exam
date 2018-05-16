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
    

    //generate dropdown links
    
    StringBuilder dropDownLinks = new StringBuilder();
    
    for (Map.Entry<String, ArrayList<Material>> item : mats.entrySet()) {
        
        dropDownLinks.append("<li>");
        
            dropDownLinks.append("<div class='collapsible-header'>").append(item.getKey()).append("</div>");
            
            dropDownLinks.append("<div class='collapsible-body'><table class='highlight'><thead><tr><th>Beskrivelse</th><th>Størrelse</th><th>Pris</th><th>Knap</th></tr></thead><tbody>");
            
                for (Material m : item.getValue()){
                    dropDownLinks.append("<tr>");
                    
                    dropDownLinks.append("<td>").append(m.getDescription()).append("</td>");
                    dropDownLinks.append("<td>").append(m.getSize()).append("</td>");
                    dropDownLinks.append("<td>").append(m.getPrice()).append("</td>");
                    dropDownLinks.append("<td>").append("<button>knap</button>").append("</td>");
                    
                    dropDownLinks.append("</tr>");
                }
                
            dropDownLinks.append("</tbody></table></div>");

        dropDownLinks.append("</li>");
        
    }
    
    request.setAttribute("dropDownLinks", dropDownLinks.toString());

%>



<div class="row">
    
    <div class="col s12">
    
        <ul class="collapsible" data-collapsible="accordion">
            ${dropDownLinks}
        </ul>
        
    </div>
    
</div>
    
    
<%@include file="/WEB-INF/jspf/footer.jspf"%>  