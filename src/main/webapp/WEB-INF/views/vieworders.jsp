<%-- 
    Document   : vieworders
    Created on : 25-04-2018, 11:03:14
    Author     : super
--%>

<%@page import="presentationLayer.RenderOrder"%>
<%@page import="functionLayer.LogicFacade"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="functionLayer.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/jspf/header.jspf"%>


<%
    HttpSession s = request.getSession();
    List<Order> orders = (List<Order>)request.getAttribute("desiredOrdersFromEmail");
    
    String orderString = "";
    for (int i = 0; i < orders.size(); i++) {
        
        orderString += RenderOrder.print(orders.get(i));
        if(i%2==1){
            orderString +="</div><div class=\"row\">";
        }
    }
    
    if(orderString.equals("")){
        orderString = "<p>Du har ikke nogle ordre</p>";
    }
    
    request.setAttribute("orders", orderString);
%>

<div class="row">
    
    ${orders}
    
</div>



<script type="text/javascript">
    
    $(document).ready(function () {
        $('ul.tabs').tabs();
    });
    
</script>





<%@include file="/WEB-INF/jspf/footer.jspf"%>