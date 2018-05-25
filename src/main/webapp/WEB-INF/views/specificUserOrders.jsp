
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/header.jspf"%>

<div class="row">

    <c:forEach items="${desiredOrdersFromEmail}" var="o">
        <div class="col l6 m12">
            <div class="card">


                <!-- top card information -->
                <div class="card-content">

                    <span class="${o.statusColor} new badge right" data-badge-caption="${o.status}"></span>
                    <span class="card-title">Carport #${o.id}</span>

                    <blockquote style="border-color: #2196f3">
                        <div class="right">
                            <p class="right">Bestilt: ${o.placed}</p>
                        </div>
                        <div>
                            <p>Navn: ${o.name}</p>
                            <p>Addresse: ${o.address}, ${o.zipCode} ${o.city}</p><br>
                        </div>

                        <c:if test="${(o.status ne 'Behandles') and (o.status ne 'Annulleret')}">
                            <div>
                                <p>Pris: ${o.price}</p><br>
                            </div>  
                        </c:if>

                        <div>
                            <p >Telefon: ${o.phone}</p>
                            <p >E-mail: ${o.email}</p>
                        </div>

                    </blockquote>
                </div>
                <!-- //////////////////// -->

                <!-- card tabs -->
                <div class="card-tabs">
                    <ul class="tabs">
                        <li class="tab">
                            <a href="#${o.stringId}a" class="tooltipped" data-position="top" data-delay="50" data-tooltip="Detaljer">
                                <i class="material-icons">zoom_out_map</i>
                            </a>
                        </li>
                        <li class="tab">
                            <a href="#${o.stringId}b" class="tooltipped" data-position="top" data-delay="50" data-tooltip="Tegninger">
                                <i class="material-icons">photo</i>
                            </a>
                        </li>
                        <li class="tab">
                            <!-- når der klikkes på denne knap/tab loaded styklisten med ajax -->
                            <a href="#${o.stringId}c" id="partlistbtn${o.id}" class="tooltipped partlistloadbtn" data-position="top" data-delay="50" data-tooltip="Stykliste">
                                <i class="material-icons">format_list_bulleted</i>
                            </a>
                        </li>
                        <li class="tab">
                            <a href="#${o.stringId}d" class="tooltipped" data-position="top" data-delay="50" data-tooltip="Bemærkning">
                                <i class="material-icons">event_note</i>
                            </a>
                        </li>
                    </ul>
                </div>
                <!-- ///////// -->

                <div class="card-content grey lighten-4">

                    <!-- details -->
                    <div id="${o.stringId}a">
                        <div class="row black-text">

                            <div class="input-field col s6">
                                <input disabled class="black-text" type="text" value="${o.width} cm">
                                <label>Carport Bredde</label>
                            </div>
                            <div class="input-field col s6">
                                <input disabled class="black-text" type="text" value="${o.length} cm">
                                <label>Carport Længde</label>
                            </div>

                            <c:if test="{o.hasShed}">
                                <div class="input-field col s6">
                                    <input disabled class="black-text" type="text" value="${o.shedWidth} cm">
                                    <label>Skur Bredde</label>
                                </div>
                                <div class="input-field col s6">
                                    <input disabled class="black-text" type="text" value="${o.shedLength} cm">
                                    <label>Skur Længde</label>
                                </div>
                            </c:if>

                            <div class="input-field col s6">
                                <input disabled class="black-text" type="text" value="${o.roof.NAME}">
                                <label>Tag</label>
                            </div>

                            <c:if test="${!o.flat}">
                                <div class="input-field col s6">
                                    <input disabled class="black-text" type="text" value="${o.angle} grader">
                                    <label>Taghældning</label>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <!-- /////// -->


                    <!-- drawings -->
                    <div id="${o.stringId}b">
                        <span class="card-title">Tegninger</span>
                        <div class="row">
                            <div class="col m6 s12">
                                <div class="materialboxed z-depth-1">
                                    ${o.drawingTop}
                                </div>
                            </div>

                            <div class="col m6 s12">
                                <div class="materialboxed z-depth-1">
                                    ${o.drawingSide}
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /////// -->

                    <!-- partlist -->
                    <div id="${o.stringId}c">
                        <span class="card-title">Stykliste</span>
                        <!-- denne div med id "partlistcontentID" vil indeholde styklisten loaded via ajax -->
                        <div id="partlistcontent${o.id}">
                            <!-- loader -->
                            <div class="preloader-wrapper big active">
                                <div class="spinner-layer spinner-blue-only">

                                    <div class="circle-clipper left">
                                        <div class="circle"></div>
                                    </div>
                                    <div class="gap-patch">
                                        <div class="circle"></div>
                                    </div>
                                    <div class="circle-clipper right">
                                        <div class="circle"></div>
                                    </div>

                                </div>
                            </div>
                            <!-- ////// -->
                        </div>
                    </div>
                    <!-- //////// -->


                    <!-- note -->
                    <div id="${o.stringId}d">

                        <div id="${o.stringId}d">
                            <span class="card-title">Bemærkning</span>
                            <p>${o.note eq '' ? 'Der er ikke nogen bemærkninger til denne ordre.' : o.note}</p>
                        </div>

                    </div>
                    <!-- //// -->

                </div>
            </div>
        </div>
    </c:forEach>
</div>


<%@include file="/WEB-INF/jspf/footer.jspf"%>