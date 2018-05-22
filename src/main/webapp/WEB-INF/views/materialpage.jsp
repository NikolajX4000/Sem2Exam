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


<div class="row">
    <div class="col s12">
      <ul class="tabs">
        <li class="tab col"><a href="#materials">Materials</a></li>
        <li class="tab col"><a href="#tools">Tools</a></li>
        <li class="tab col"><a href="#roofs">Roofs</a></li>
      </ul>
    </div>
    
    <%@include file="/WEB-INF/jspf/materialsTEST.jspf"%>  
    <%@include file="/WEB-INF/jspf/toolsTEST.jspf"%> 
    <%@include file="/WEB-INF/jspf/roofsTEST.jspf"%> 
    
    
</div>
        
    
<%@include file="/WEB-INF/jspf/footer.jspf"%>  