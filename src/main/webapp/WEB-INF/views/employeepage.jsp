<%-- 
    Document   : employeepage
    Created on : 25-04-2018, 14:40:04
    Author     : super
--%>

<%@page import="presentationLayer.RenderOrder"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.List"%>
<%@page import="functionLayer.Order"%>
<%@page import="functionLayer.LogicFacade"%>
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
    Order test = null;
    String tableRows = "";
    Random rand = new Random();

    for (int i = 0; i < os.size(); i++) {
        String s = "<tr>";

        s += "<td>" + os.get(i).getId() + "</td>";
        s += "<td>" + os.get(i).getName() + "</td>";
        s += "<td>" + os.get(i).getCity() + "</td>";
        s += "<td>" + "26 April, 2018" + "</td>";
        s += "<td><span class=\"dot " + os.get(i).getStatusColor() + "\"></span>" + os.get(i).getStatus() + "</td>";
        s += "<td>20." + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + " kr.</td>";

        s += "<td class=\"center\"><a class=\"btn-floating btn-small waves-effect waves-light blue tooltipped\" data-position=\"right\" data-delay=\"20\" data-tooltip=\"Gå til ordre\"><i class=\"material-icons\">navigate_next</i></a></td>";

        s += "</tr>";

        tableRows += s;
        
        test = os.get(i);
    }
    
    
    request.setAttribute("test123", RenderOrder.print(test));
    
    request.setAttribute("tr", tableRows);

%>

<style>
    .dot{
        position: relative;
        margin-right: 20px;
    }
    .dot:after{
        content: '\2022';
        position: absolute;
        font-size: 50px;
        left: 0;
        top:-28px;
    }
    .dot.green:after{
        color: #4caf50;
    }
    .dot.red:after{
        color: #f44336;
    }
    .dot.yellow:after{
        color: #ffeb3b;
    }
    .dot.blue:after{
        color: #2196f3;
    }
    .dot.orange:after{
        color: #ff9800;
    }
</style>


<!-- Modal Trigger -->
<a class="waves-effect waves-light btn modal-trigger" href="#modal1">Modal</a>

<!-- Modal Structure -->
<div id="modal1" class="modal">
    <div class="modal-content">
        <!--<table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>Køber</th>
                    <th>Område</th>
                    <th>Dato</th>
                    <th>Status</th>
                    <th>Pris</th>
                </tr>
            </thead>

            <tbody>
                <tr>
                    <td>1</td>
                    <td>Daniel Lindholm</td>
                    <td>Solrød Strand</td>
                    <td>4 juni, 2018</td>
                    <td><span class="dot green"></span>Modtaget</td>
                    <td>20.000 Kr.</td>
                </tr>
            </tbody>
        </table>
        <br><div class="divider"></div><br>-->
        
        
        
        ${test123}
        
        
        <div class="row">
            <p>Opdater ordestatus:</p>
            <a class="waves-effect waves-light btn green">Modtaget</a>
            <a class="waves-effect waves-light btn blue">Sendt</a>
            <a class="waves-effect waves-light btn orange">Behandles</a>
            <a class="waves-effect waves-light btn red">Annulleret</a>
        </div>
    </div>
    <div class="modal-footer">
        <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Luk</a>
    </div>
</div>

<script>
    $(document).ready(function () {
        // the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
        $('.modal').modal();
    });
</script>

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

<%@include file="/WEB-INF/jspf/footer.jspf"%>