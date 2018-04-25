<%-- 
    Document   : vieworders
    Created on : 25-04-2018, 11:03:14
    Author     : super
--%>

<%@page import="functionLayer.LogicFacade"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="functionLayer.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/jspf/header.jspf"%> 
<%@include file="/WEB-INF/jspf/debug.jspf"%> 

<%
    HttpSession s = request.getSession();
    List<Order> orders = (List<Order>)request.getAttribute("desiredOrdersFromEmail");


%>


<style type="text/css">
    ul.tabs > li.tab > a, ul.tabs > li.tab > a.active{
        color: black;
    }
    ul.tabs > li.tab > a:hover{
        color: #2196f3;
    }
    .tabs .indicator {
        background-color:#2196f3;
    } /*Color of underline*/
</style>

<div class="row">
    <div class="col l6 m12">
        <div class="card">
            <div class="card-content">

                <!--<p class="right"><span class="dot green"></span>Sendt</p> -->

                <span class="new badge green right" data-badge-caption="Modtaget"></span>
                <span class="card-title">Carport</span>

                <blockquote style="border-color: #2196f3;">

                    <div class="right">
                        <p class="right">Bestilt: 4 juni, 2018</p><br><br>
                        <!-- <img class="right" src="https://www.johannesfog.dk/globalassets/service/quickbyg/quickurejs.gif"> -->
                        <img class="right" src="https://www.johannesfog.dk/globalassets/service/quickbyg/quickmrejs.gif">
                    </div>

                    <p>Navn: Daniel Lindholm</p>
                    <p>Addresse: Pedersvej 24, 2680 Solrød Strand</p>

                    <br>

                    <div>
                        <p >Telefon: 28737364</p>
                        <p >E-mail: Mail@danmark.com</p>
                    </div>

                </blockquote>
            </div>





            <div class="card-tabs">
                <ul class="tabs">
                    <li class="tab"><a href="#test3" class="tooltipped" data-position="top" data-delay="50" data-tooltip="Detaljer"><i class="material-icons">zoom_out_map</i></a></li>
                    <li class="tab"><a href="#test5" class="tooltipped" data-position="top" data-delay="50" data-tooltip="Tegninger"><i class="material-icons">photo</i></a></li>
                    <li class="tab"><a href="#test6" class="tooltipped" data-position="top" data-delay="50" data-tooltip="Stykliste"><i class="material-icons">format_list_bulleted</i></a></li>
                    <li class="tab"><a href="#test7" class="tooltipped" data-position="top" data-delay="50" data-tooltip="Bemærkning"><i class="material-icons">event_note</i></a></li>
                </ul>
            </div>
            <div class="card-content grey lighten-4">
                <div id="test3">

                    <div class="row black-text">

                        <div class="input-field col s6">
                            <input disabled class="black-text" type="text" value="230 cm">
                            <label>Carport Bredde</label>
                        </div>

                        <div class="input-field col s6">
                            <input disabled class="black-text" type="text" value="180 cm">
                            <label>Carport Længde</label>
                        </div>



                        <div class="input-field col s6">
                            <input disabled class="black-text" type="text" value="Betontagsten - Rød">
                            <label>Tag</label>
                        </div>



                        <div class="input-field col s6">
                            <input disabled class="black-text" type="text" value="25 grader">
                            <label>Taghældning</label>
                        </div>


                        <div class="input-field col s6">
                            <input disabled class="black-text" type="text" value="120 cm">
                            <label>Redskabsrum Bredde</label>
                        </div>

                        <div class="input-field col s6">
                            <input disabled class="black-text" type="text" value="90 cm">
                            <label>Redskabsrum Længde</label>
                        </div>

                    </div>



                </div>
                <div id="test5">
                    <span class="card-title">Tegninger</span>
                </div>
                <div id="test6">
                    <span class="card-title">Stykliste</span>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                </div>

                <div id="test7">
                    <span class="card-title">Bemærkning</span>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                </div>
            </div>
        </div>
    </div>


</div>



<script type="text/javascript">


    $(document).ready(function () {
        $('ul.tabs').tabs();
    });



</script>





<%@include file="/WEB-INF/jspf/footer.jspf"%>