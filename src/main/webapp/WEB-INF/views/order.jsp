
<%@page import="functionLayer.Roof"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/jspf/header.jspf"%>
<%
    List<Roof> roofs = (List<Roof>) request.getAttribute("roofs");
    StringBuilder flatRoofs = new StringBuilder();
    StringBuilder raisedRoofs = new StringBuilder();
    for (Roof r : roofs) {
        switch (r.getTYPE()) {
            case 0:
                flatRoofs.append("<option value=\"" + r.getID() + "\">" + r.getNAME() + "</option>");
                break;
            case 1:
                raisedRoofs.append("<option value=\"" + r.getID() + "\">" + r.getNAME() + "</option>");
                break;
        }
    }
    request.setAttribute("flatRoofs", flatRoofs.toString());
    request.setAttribute("raisedRoofs", raisedRoofs.toString());
%>
<div class="row">

    <form class="col s12 m6" method="post" action="?" id="test1" accept-charset="ISO-8859-1">

        <p>Carport Information</p>
        ${feedback}
        <br>
        <div class="row">
            <div class="input-field col s6">
                <select name="width" required class="validate">
                    <option value="" disabled selected>Vælg brede</option>
                    <option value="240">240 cm</option>
                    <option value="270">270 cm</option>
                    <option value="300">300 cm</option>
                    <option value="330">330 cm</option>
                    <option value="360">360 cm</option>
                    <option value="390">390 cm</option>
                    <option value="420">420 cm</option>
                    <option value="450">450 cm</option>
                    <option value="480">480 cm</option>
                    <option value="510">510 cm</option>
                    <option value="540">540 cm</option>
                    <option value="570">570 cm</option>
                    <option value="600">600 cm</option>
                    <option value="630">630 cm</option>
                    <option value="660">660 cm</option>
                    <option value="690">690 cm</option>
                    <option value="720">720 cm</option>
                    <option value="750">750 cm</option>
                </select>
                <label>Carport bredde</label>
            </div>

            <div class="input-field col s6">
                <select name="length" required class="validate">
                    <option value="" disabled selected>Vælg længde</option>
                    <option value="240">240 cm</option>
                    <option value="270">270 cm</option>
                    <option value="300">300 cm</option>
                    <option value="330">330 cm</option>
                    <option value="360">360 cm</option>
                    <option value="390">390 cm</option>
                    <option value="420">420 cm</option>
                    <option value="450">450 cm</option>
                    <option value="480">480 cm</option>
                    <option value="510">510 cm</option>
                    <option value="540">540 cm</option>
                    <option value="570">570 cm</option>
                    <option value="600">600 cm</option>
                    <option value="630">630 cm</option>
                    <option value="660">660 cm</option>
                    <option value="690">690 cm</option>
                    <option value="720">720 cm</option>
                    <option value="750">750 cm</option>
                    <option value="780">780 cm</option>
                </select>
                <label>Carport længde</label>
            </div>



            <div class="switch col s6" style="margin: 0 auto 30px;">
                <p>Ønsker du redskabsrum?</p>
                <label>
                    Nej
                    <input type="checkbox" class="shedbox" name="hasShed" value="true">
                    <span class="lever"></span>
                    Ja
                </label>
                <br>
            </div>

            <div class="switch col s6" style="margin: 0 auto 30px;">
                <p>Tag med rejsning?</p>
                <label>
                    Nej
                    <input type="checkbox" class="anglebox" name="hasAngle" value="true">
                    <span class="lever"></span>
                    Ja
                </label>
                <br>
            </div>

            <div class="shedinfo">
                <div class="input-field col s6">
                    <select name="shedwidth" required class="validate">
                        <option value="210">210 cm</option>
                        <option value="240">240 cm</option>
                        <option value="270">270 cm</option>
                        <option value="300">300 cm</option>
                        <option value="330">330 cm</option>
                        <option value="360">360 cm</option>
                        <option value="390">390 cm</option>
                        <option value="420">420 cm</option>
                        <option value="450">450 cm</option>
                        <option value="480">480 cm</option>
                        <option value="510">510 cm</option>
                        <option value="540">540 cm</option>
                        <option value="570">570 cm</option>
                        <option value="600">600 cm</option>
                        <option value="630">630 cm</option>
                        <option value="660">660 cm</option>
                        <option value="690">690 cm</option>
                        <option value="720">720 cm</option>
                    </select>
                    <label>Redskabsrum bredde</label>
                </div>

                <div class="input-field col s6">
                    <select name="shedlength" required class="validate">
                        <option value="150">150 cm</option>
                        <option value="180">180 cm</option>
                        <option value="210">210 cm</option>
                        <option value="240">240 cm</option>
                        <option value="270">270 cm</option>
                        <option value="300">300 cm</option>
                        <option value="330">330 cm</option>
                        <option value="360">360 cm</option>
                        <option value="390">390 cm</option>
                        <option value="420">420 cm</option>
                        <option value="450">450 cm</option>
                        <option value="480">480 cm</option>
                        <option value="510">510 cm</option>
                        <option value="540">540 cm</option>
                        <option value="570">570 cm</option>
                        <option value="600">600 cm</option>
                        <option value="630">630 cm</option>
                        <option value="660">660 cm</option>
                        <option value="690">690 cm</option>
                    </select>
                    <label>Redskabsrum længde</label>
                </div>
            </div>





            <div class="input-field col s12 flat">
                <select name="roof" id="flatroof" required class="validate">
                    <option value="" disabled selected>Vælg tagtype/farve</option>
                    ${flatRoofs}
                </select>
                <label>Tag</label>
            </div>

            <div class="input-field col s12 angeled">
                <select name="roof" id="heighroof" required class="validate">
                    <option value="" disabled selected>Vælg tagtype/farve</option>
                    ${raisedRoofs}
                </select>
                <label>Tag</label>
            </div>

            <div class="input-field col s12 angleinfo">
                <select name="angle" required class="validate">
                    <option value="15">15 grader</option>
                    <option value="20">20 grader</option>
                    <option value="25" selected>25 grader</option>
                    <option value="30">30 grader</option>
                    <option value="35">35 grader</option>
                    <option value="40">40 grader</option>
                    <option value="45">45 grader</option>
                </select>
                <label>Taghældning</label>
            </div>



        </div>
        <p>Person Information</p>
        <div class="row">

            <div class="input-field col s12">
                <input id="name" type="text" class="validate" name="name" required maxlength="100" pattern="[a-zA-ZøæåØÆÅ]([- ]?[a-zA-ZøæåØÆÅ]){1,99}">
                <label for="name" data-error="Name must be less than 100 characters and not include any special characters">Navn</label>
            </div>
            <div class="input-field col s12">
                <input id="address" type="text" class="validate" name="address" required maxlength="100" pattern="[\a-zA-ZøæåØÆÅ]([\wøæåØÆÅ.,][- ][\wøæåØÆÅ.,]|[\wøæåØÆÅ.,]){1,100}">
                <label for="address" data-error="Adressen skal være mindre end 100 karaktere og ikke indeholde nogle specialtegn">Adresse</label>
            </div>
            <div class="input-field col s3">
                <input id="zipcode" type="text" class="validate" name="zipcode" required maxlength="4" pattern="\d{4}">
                <label for="zipcode" data-error="Skriv et gyldigt postnummer">Postnummer</label>
            </div>
            <div class="input-field col s9">
                <input id="city" type="text" class="validate" name="city" required maxlength="100" pattern="[a-zA-ZøæåØÆÅ]+[a-zA-ZøæåØÆÅ -.]?[a-zA-ZøæåØÆÅ]+">
                <label for="city" data-error="Skriv en rigtig by">By</label>
            </div>
            <div class="input-field col s12">
                <input id="phone" type="text" class="validate" name="phone" required maxlength="20" pattern="[+]?([\d][ -]?){4,19}">
                <label for="phone" data-error="Skriv et rigtigt telefonnummer">Telefon</label>
            </div>
            <div class="input-field col s12">
                <input id="email" type="email" class="validate" name="email" maxlength="100">
                <label for="email" data-error="Skriv en rigtig e-mail">E-mail adresse</label>
            </div>
            <div class="input-field col s12">
                <textarea id="note" class="materialize-textarea" name="note"  data-length="500" maxlength="500"></textarea>
                <label for="note">Evt. bemærkninger</label>
            </div>
        </div>
        <button class="btn waves-effect waves-light blue" type="submit" name="command" value="CmdCreateOrder">Send Forespørgsel
            <i class="material-icons right">send</i>
        </button>
    </form>

</div>

<%@include file="/WEB-INF/jspf/footer.jspf"%>
