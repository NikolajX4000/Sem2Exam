<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/jspf/header.jspf"%>

<div class="row">

    <form class="col s12 m6" method="post" action="?" accept-charset="ISO-8859-1">

        <p>Carport Information</p>
        <div class="row">

            <p class="range-field col l6 s12">
                <label>Carport længde: <span id="length_span">510</span></label>
                <input type="range" value="510" min="240" max="780" step="30" id="length" class="range" name="length"/>
            </p>

            <p class="range-field col l6 s12">
                <label>Carport bredde: <span id="width_span">510</span></label>
                <input type="range" value="510" min="240" max="780" step="30" id="width" class="range" name="width"/>
            </p>

            <p class="range-field col l6 s12 shedinfo">
                <label>Skur længde: <span id="shed_length_span">150</span></label>
                <input type="range" value="150" min="150" max="450" step="30" id="shed_length" class="range" name="shed_length"/><!-- max-60 -->
            </p>

            <p class="range-field col l6 s12 shedinfo">
                <label>Skur bredde: <span id="shed_width_span">210</span></label>
                <input type="range" value="210" min="210" max="480" step="30" id="shed_width" class="range" name="shed_width"/><!-- max-30 -->
            </p>


            <div class="input-field col s12 angleinfo">
                <select name="angle" id="angle" required class="validate range">
                    <option value="15" selected>15 grader</option>
                    <option value="20">20 grader</option>
                    <option value="25">25 grader</option>
                    <option value="30">30 grader</option>
                    <option value="35">35 grader</option>
                    <option value="40">40 grader</option>
                    <option value="45">45 grader</option>
                </select>
                <label>Taghældning</label>
            </div>


            <div class="switch col s6" style="margin: 0 auto 30px;">
                <p>Ønsker du redskabsrum?</p>
                <label>
                    Nej
                    <input type="checkbox" class="shedbox" id="shedbox" name="hasShed">
                    <span class="lever"></span>
                    Ja
                </label>
                <br>
            </div>

            <div class="switch col s6" style="margin: 0 auto 30px;">
                <p>Tag med rejsning?</p>
                <label>
                    Nej
                    <input type="checkbox" class="anglebox" id="anglebox" name="hasAngle">
                    <span class="lever"></span>
                    Ja
                </label>
                <br>
            </div>

            <div class="input-field col s12 flat">
                <select name="roof" id="flatroof" required class="validate">
                    <option value="" disabled selected>Vælg tagtype/farve</option>
                    <c:forEach items="${roofs}" var="r">
                        <c:if test="${r.TYPE eq 0}">
                            <option value="${r.ID}">${r.NAME}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <label>Tag</label>
            </div>

            <div class="input-field col s12 angeled">
                <select name="roof" id="heighroof" required class="validate">
                    <option value="" disabled selected>Vælg tagtype/farve</option>
                    <c:forEach items="${roofs}" var="r">
                        <c:if test="${r.TYPE eq 1}">
                            <option value="${r.ID}">${r.NAME}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <label>Tag</label>
            </div>

        </div>


        <p>Kontakt Information</p>
        <div class="row">

            <div class="input-field col s12">
                <input id="name" type="text" class="validate" name="name" required maxlength="100" pattern="[a-zA-ZøæåØÆÅ]([- ]?[a-zA-ZøæåØÆÅ]){1,99}" value="${param.name}">
                <label for="name" data-error="Name must be less than 100 characters and not include any special characters">Navn</label>
            </div>
            <div class="input-field col s12">
                <input id="address" type="text" class="validate" name="address" required maxlength="100" pattern="[\a-zA-ZøæåØÆÅ]([\wøæåØÆÅ.,][- ][\wøæåØÆÅ.,]|[\wøæåØÆÅ.,]){1,100}" value="${param.address}">
                <label for="address" data-error="Adressen skal være mindre end 100 karaktere og ikke indeholde nogle specialtegn">Adresse</label>
            </div>
            <div class="input-field col s3">
                <input id="zipcode" type="text" class="validate" name="zipcode" required maxlength="4" pattern="\d{4}" value="${param.zipcode}">
                <label for="zipcode" data-error="Skriv et gyldigt postnummer">Postnummer</label>
            </div>
            <div class="input-field col s9">
                <input id="city" type="text" class="validate" name="city" required maxlength="100" pattern="[a-zA-ZøæåØÆÅ]+[a-zA-ZøæåØÆÅ -.]?[a-zA-ZøæåØÆÅ]+" value="${param.city}">
                <label for="city" data-error="Skriv en rigtig by">By</label>
            </div>
            <div class="input-field col s12">
                <input id="phone" type="text" class="validate" name="phone" required maxlength="20" pattern="[+]?([\d][ -]?){4,19}" value="${param.phone}">
                <label for="phone" data-error="Skriv et rigtigt telefonnummer">Telefon</label>
            </div>
            <div class="input-field col s12">
                <input id="email" type="email" class="validate" name="email" maxlength="100" value="${param.email}">
                <label for="email" data-error="Skriv en rigtig e-mail">E-mail adresse</label>
            </div>
            <div class="input-field col s12">
                <textarea id="note" class="materialize-textarea" name="note"  data-length="500" maxlength="500" value="${param.note}"></textarea>
                <label for="note">Evt. bemærkninger</label>
            </div>
        </div>
        <button class="btn waves-effect waves-light blue" type="submit" name="command" value="CmdCreateOrder">Send Forespørgsel
            <i class="material-icons right">send</i>
        </button>
    </form>

    <div class="col s12 m6">
        <p class="center">Tegninger af carport</p>
        <div id="drawingbox"></div>
    </div>

</div>


<script>

    var hasAngle = false;
    var hasShed = false;

    function updateDrawing() {


        $.ajax({
            type: 'GET',
            url: '${requestScope['javax.servlet.forward.request_uri']}/ServletGetDrawings',
            data: {
                width: $("#width").val(),
                length: $("#length").val(),
                shed_width: $("#shed_width").val(),
                shed_length: $("#shed_length").val(),
                angle: $("#angle").val(),
                has_shed: hasShed,
                has_angle: hasAngle
            },
            success: function (data) {
                //alert(data);
                $("#drawingbox").html(data);
            }
        });
    }

    //$(document).ready(updateDrawing());

    $(".range").change(function () {

        //changes span value to reflect what the bar is at
        //var spanToChange = "#" + $(this).attr("id") + "_span";
        //$(spanToChange).html($(this).val());

        updateDrawing();

    });

    $(".range").on("change mousemove", function () {
        var spanToChange = "#" + $(this).attr("id") + "_span";
        $(spanToChange).html($(this).val());
        //updateDrawing();
    });



    $("#width").change(function () {
        var newMax = ($(this).val()) - 30;
        $("#shed_width").attr({"max": newMax});

        var spanToChange = "#shed_" + $(this).attr("id") + "_span";
        $(spanToChange).html($("#shed_width").val());
    });

    $("#length").change(function () {
        var newMax = ($(this).val()) - 60;
        $("#shed_length").attr({"max": newMax});

        var spanToChange = "#shed_" + $(this).attr("id") + "_span";
        $(spanToChange).html($("#shed_length").val());
    });




    //togle display shedwith on create
    $('.shedbox').change(function () {

        $('.shedinfo').toggle(this.checked);

        if (this.checked) {
            hasShed = true;
        } else {
            hasShed = false;
        }

        updateDrawing();

    }).change();

    //togle display anngle, and swap the pickable roofs
    $('.anglebox').change(function () {

        // toggle om vinler vises
        $('.angleinfo').toggle(this.checked);

        //toggler hvilket slags tag vises
        $('.angeled').toggle(this.checked);
        $('.flat').toggle(!this.checked);

        // IS RAISED
        if (this.checked) {
            // hvis tag er raised disable flatroof input
            $("#flatroof").attr("disabled", true);

            // toggle required to right select
            $("#heighroof").attr("required", true);
            $("#flatroof").removeAttr("required");

            hasAngle = true;

            // IS FLAT
        } else {
            // fjern disable fra flatroof, form tager altid første input af det navn når den sender.
            $("#flatroof").removeAttr("disabled");

            // toggle required to right select
            $("#flatroof").attr("required", true);
            $("#heighroof").removeAttr("required");

            hasAngle = false;
        }

        updateDrawing();
    }).change();

</script>

<%@include file="/WEB-INF/jspf/footer.jspf"%>
