<%-- 
    Document   : materialpage
    Created on : 16-05-2018, 09:07:08
    Author     : Hupra Laptop
--%>

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


    String s = "";
    
for(Material m : allMaterial){
    s += m.getName() + " ";
}



%>



<div class="row">
    
    <%=s%>
    
</div>
    
    
<%@include file="/WEB-INF/jspf/footer.jspf"%>