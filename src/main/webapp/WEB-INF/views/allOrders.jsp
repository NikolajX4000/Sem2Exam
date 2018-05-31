<%-- 
    Document   : employeepage
    Created on : 25-04-2018, 14:40:04
    Author     : super
--%>

<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf"%>

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
            <c:choose>
                <c:when test="${not empty orders}">
                    <c:forEach items="${orders}" var="o">
                        <tr>
                            <td>${o.id}</td>
                            <td>${o.name}</td>
                            <td>${o.city}</td>
                            <td>${o.placed}</td>
                            <td><span class="dot ${o.statusColor}"></span>${o.status}</td>
                            <td>${o.price}</td>
                            <td class="center">
                                <a href="#modal${o.stringId}" class="btn-floating btn-small waves-effect waves-light blue tooltipped modal-trigger" data-position="right" data-delay="20" data-tooltip="Inspicer ordre">
                                    <i class="material-icons">navigate_next</i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                <td colspan="7">Der er ingen ordre på nuværende tidspunkt.</td>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>

</div>


<!-- popups -->
<c:forEach items="${orders}" var="o">

    <div id="modal${o.stringId}" class="modal">
        <div class="modal-content">
            <ul class="collapsible" data-collapsible="accordion">

                <!-- Detaljer -->
                <li>
                    <div class="collapsible-header">
                        <i class="material-icons">zoom_out_map</i>Detaljer
                        <span class="new badge ${o.statusColor}" data-badge-caption="${o.status}"></span>
                    </div>

                    <div class="collapsible-body">

                        <div class="row black-text">

                            <div class="input-field col s6">
                                <input disabled class="black-text" type="text" value="${o.id}">
                                <label>Ordre Nummer</label>
                            </div>
                            <div class="input-field col s6">
                                <input disabled class="black-text" type="text" value="${o.price}">
                                <label>Pris</label>
                            </div>
                            <div class="input-field col s6">
                                <input disabled class="black-text" type="text" value="${o.name}">
                                <label>Navn</label>
                            </div>
                            <div class="input-field col s6">
                                <input disabled class="black-text" type="text" value="${o.phone}">
                                <label>Telefon</label>
                            </div>
                            <div class="input-field col s6">
                                <input disabled class="black-text" type="text" value="${o.email}">
                                <label>Email</label>
                            </div>
                            <div class="input-field col s6">
                                <input disabled class="black-text" type="text" value="${o.address}">
                                <label>Adresse</label>
                            </div>
                            <div class="input-field col s6">
                                <input disabled class="black-text" type="text" value="${o.zipCode}">
                                <label>Postnummer</label>
                            </div>
                            <div class="input-field col s6">
                                <input disabled class="black-text" type="text" value="${o.city}">
                                <label>By</label>
                            </div>
                            <div class="input-field col s6">
                                <input disabled class="black-text" type="text" value="${o.width} cm">
                                <label>Carport Bredde</label>
                            </div>
                            <div class="input-field col s6">
                                <input disabled class="black-text" type="text" value="${o.length} cm">
                                <label>Carport Længde</label>
                            </div>
                            <c:if test="${o.shedWidth ne 0 || o.shedLength ne 0}">
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
                </li>
                <!-- //////// -->

                <!-- tegninger -->
                <li>
                    <div class="collapsible-header">
                        <i class="material-icons">photo</i>Tegninger
                    </div>

                    <div class="collapsible-body">
                        <div class="row">
                            <div class="col m6 s12">
                                <div class='materialboxed z-depth-1'>${o.drawingTop}</div>
                            </div>
                            <div class="col m6 s12">
                                <div class='materialboxed z-depth-1'>${o.drawingSide}</div>
                            </div>
                        </div>
                    </div>
                </li>
                <!-- ///////// -->

                <!-- stykliste -->
                <li>
                    <!-- når der klikkes på denne tab loader man styklisten -> .partlistloadbtn -->
                    <div class="collapsible-header partlistloadbtn" id="partlistbtn${o.id}">
                        <i class="material-icons">format_list_bulleted</i>Stykliste
                    </div>

                    <div class="collapsible-body">
                        <!-- preloader til inden stykliste er laodet ind -->
                        <div id="partlistcontent${o.id}">
                            <div class="preloader-wrapper big active">
                                <div class="spinner-layer spinner-blue-only">
                                    <div class="circle-clipper left">
                                        <div class="circle"></div>
                                    </div><div class="gap-patch">
                                        <div class="circle"></div>
                                    </div><div class="circle-clipper right">
                                        <div class="circle"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                <!-- ///////// -->

                <!-- note-->
                <li>
                    <div class="collapsible-header">
                        <i class="material-icons">event_note</i>Bemærkning
                    </div>

                    <div class="collapsible-body">
                        <p>${empty o.note ? 'Der er ikke nogen bemærkning til denne ordre.' : o.note}</p>
                    </div>
                </li>
                <!-- ////-->
            </ul>

            <form action="?"method="post" accept-charset="ISO-8859-1">

                <div class="row">

                    <!-- opdater status -->
                    <div class="col m6 s12">

                        <p>Opdater ordestatus:</p>
                        <input type="hidden" name="target" value="${o.id}">

                        <p>
                            <input <c:if test="${o.status eq 'Modtaget'}">checked</c:if> name="newStatus" type="radio" id="label_a_${o.stringId}" value="Modtaget"/>
                            <label for="label_a_${o.stringId}">Modtaget</label>
                        </p>
                        <p>
                            <input <c:if test="${o.status eq 'Sendt'}">checked</c:if> name="newStatus" type="radio" id="label_b_${o.stringId}" value="Sendt"/>
                            <label for="label_b_${o.stringId}">Sendt</label>
                        </p>
                        <c:if test="${o.status eq 'Behandles'}">
                            <p>
                                <input checked name="newStatus" type="radio" id="label_c_${o.stringId}" value="Behandles"/>
                                <label for="label_c_${o.stringId}">Behandles</label>
                            </p>
                        </c:if>
                        <p>
                            <input <c:if test="${o.status eq 'Annulleret'}">checked</c:if> name="newStatus" type="radio" id="label_d_${o.stringId}" value="Annulleret"/>
                            <label for="label_d_${o.stringId}">Annulleret</label>
                        </p>

                        <button class="btn waves-effect waves-light blue btn-large" type="submit" name="command" value="CmdUpdateOrder">Opdater
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                    <!-- ////////////// -->

                    <!-- opdater pris -->
                    <div class="col m6 s12">
                        <c:if test="${o.status eq 'Behandles'}">
                            <p>Opdater pris:</p>
                            <div class="row">
                                <br>
                                <div class="input-field col s12">
                                    <input disabled class="black-text" type="text" value="${o.materialPrice}">
                                    <label>Vejledende pris:</label>
                                </div>
                                <br>
                                <div class="input-field col s12">
                                    <input id="newPrice" type="number" class="validate" name="newPrice" required min="1" max="999999" value="${o.priceInt}">
                                    <label for="newPrice">Personlig pris:</label>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <!-- //////////// -->
                </div>
            </form>
        </div>
    </div>
</c:forEach>

<%@include file="/WEB-INF/jspf/footer.jspf"%>