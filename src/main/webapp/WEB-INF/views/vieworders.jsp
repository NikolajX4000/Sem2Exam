<%-- 
    Document   : vieworders
    Created on : 25-04-2018, 11:03:14
    Author     : super
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="functionLayer.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/jspf/header.jspf"%> 
<%@include file="/WEB-INF/jspf/debug.jspf"%> 

<%
    HttpSession s = request.getSession();
    List<Order> orders = (ArrayList<Order>) s.getAttribute("orders");
%>

<%@include file="/WEB-INF/jspf/footer.jspf"%>