
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/jspf/header.jspf"%> 
<%@include file="/WEB-INF/jspf/debug.jspf"%> 

<div class="row">
    
    <form class="col s12 m6" method="post" id="test1" accept-charset="ISO-8859-1">
        <p>Carport Information</p><br> 
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
                    <option value="780">750 cm</option>
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
                    <option value="2">Plasttrapezplader</option>
                </select>
                <label>Tag</label>
            </div>
            
            <div class="input-field col s12 angeled">
                <select name="roof" id="heighroof" required class="validate">
                    <option value="" disabled selected>Vælg tagtype/farve</option>
                    <option value="1">Betontagsten - Rød</option>
                    <option value="1">Betontagsten - Teglrød</option>
                    <option value="1">Betontagsten - Brun</option>
                    <option value="1">Betontagsten - Sort</option>
                    <option value="1">Eternittag B6 - Grå</option>
                    <option value="1">Eternittag B6 - Sort</option>
                    <option value="1">Eternittag B6 - Mokka (brun)</option>
                    <option value="1">Eternittag B6 - Rødbrun</option>
                    <option value="1">Eternittag B6 - Teglrød</option>
                    <option value="1">Eternittag B7 - Grå</option>
                    <option value="1">Eternittag B7 - Sort</option>
                    <option value="1">Eternittag B7 - Mokka (brun)</option>
                    <option value="1">Eternittag B7 - Rødbrun</option>
                    <option value="1">Eternittag B7 - Teglrød</option>
                    <option value="1">Eternittag B7 - Rødflammet</option>
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
                <label for="address" data-error="Adressen skal være mindre end 100 karaktere og ikke indeholde indeholde nogle specialtegn">Adresse</label>
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
        <button class="btn waves-effect waves-light blue" type="submit" name="command" value="CreateOrderCommand">Send Forespørgsel
            <i class="material-icons right">send</i>
        </button>
    </form>
    
</div>
    
    
    
    
    


<script>
    
$("select[required]").css({display: "block", height: 0, padding: 0, width: 0, position: 'absolute'});
    
$(document).ready(function() {
    $('select').material_select();
});

$(function () {
  $('.shedbox').change(function () {                
     $('.shedinfo').toggle(this.checked);
  }).change(); //ensure visible state matches initially
});

$(function () {
  $('.anglebox').change(function () {                
     $('.angleinfo').toggle(this.checked);
     
     $('.angeled').toggle(this.checked);
     $('.flat').toggle(!this.checked);
     
    if (this.checked) {
        $("#flatroof").attr("disabled", true);
        
        
        // toggle required to right select
        $("#heighroof").attr("required", true);
        $("#flatroof").removeAttr("required");
        
    } else {
        $("#flatroof").removeAttr("disabled");
        
        
        // toggle required to right select
        $("#flatroof").attr("required", true);
        $("#heighroof").removeAttr("required");
    }
     
  }).change(); //ensure visible state matches initially
});


</script>
      
<%@include file="/WEB-INF/jspf/footer.jspf"%>
