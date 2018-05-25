<%-- 
    Document   : employeepage
    Created on : 25-04-2018, 14:40:04
    Author     : super
--%>

<%@page import="presentationLayer.RenderOrderInspect"%>
<%@page import="presentationLayer.RenderOrder"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.List"%>
<%@page import="logicLayer.Order"%>
<%@page import="logicLayer.LogicFacade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/jspf/header.jspf"%>


<%
    
    List<Order> os;
    try {
        os = LogicFacade.getAllOrders();
    } catch (Exception e) {
        os = null;
    }
    //Order test = null;
    String tableRows = "";
    String inspectors = "";
    
    if(os != null && os.size()>0){
        for (int i = 0; i < os.size(); i++)
        {
            String s = "<tr>";

            s += "<td>" + os.get(i).getId() + "</td>";
            s += "<td>" + os.get(i).getName() + "</td>";
            s += "<td>" + os.get(i).getCity() + "</td>";
            s += "<td>" + os.get(i).getPlaced() + "</td>";
            s += "<td><span class=\"dot " + os.get(i).getStatusColor() + "\"></span>" + os.get(i).getStatus() + "</td>";
            s += "<td>" + os.get(i).getPrice() + "</td>";

            s += "<td class=\"center\"><a href=\"#modal" + os.get(i).getStringId() + "\" class=\"btn-floating btn-small waves-effect waves-light blue tooltipped modal-trigger\" data-position=\"right\" data-delay=\"20\" data-tooltip=\"Inspicer ordre\"><i class=\"material-icons\">navigate_next</i></a></td>";

            s += "</tr>";

            tableRows += s;
            inspectors += RenderOrderInspect.print(os.get(i));

            //test = os.get(i);
        }
    }else{
        tableRows = "<td colspan='5'>Der er ingen ordre på nuværende tidspunkt.</td>";
    }
    

    //request.setAttribute("test123", RenderOrder.print(test));
    
    request.setAttribute("inspectors", inspectors);
    request.setAttribute("tr", tableRows);

%>

<div class="row">
    
    <table>
        <thead>
            <tr>
                <th>#</th>
                <th>Køber</th>
                <th>Område</th>
                <th>Dato</th>
                <th>Status</th>
                <th>Pris</th>
                <th class="center">Inspicer</th>
            </tr>
        </thead>

        <tbody>
            ${tr}
        </tbody>
    </table>

</div>
        
        ${inspectors}

<%@include file="/WEB-INF/jspf/footer.jspf"%>