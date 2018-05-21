
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf"%>
<!DOCTYPE html>




<div class="row">

    <div class="col s6">
        <form action="?" method="post">

            <input type="hidden" name="TESTslider">
            <p class="range-field">
                <label>Width <span id="width_span">510</span></label>
                <input type="range" value="510" min="240" max="780" step="30" id="width" class="range" name="width"/>
            </p>
            <p class="range-field">
                <label>Length <span id="length_span">510</span></label>
                <input type="range" value="510" min="240" max="780" step="30" id="length" class="range" name="length"/>
            </p>

            <p class="range-field">
                <label>Shed Width <span id="shed_width_span">210</span></label>
                <input type="range" value="210" min="210" max="480" step="30" id="shed_width" class="range" name="shed_width"/><!-- max-30 -->
            </p>
            <p class="range-field">
                <label>Shed Length <span id="shed_length_span">150</span></label>
                <input type="range" value="150" min="150" max="450" step="30" id="shed_length" class="range" name="shed_length"/><!-- max-60 -->
            </p>

            <button type="submit">Update</button>
        </form>

    </div>



    <div class="col s6" id="contentbox">Tegninger her</div>



</div>



<script>


    function updateDrawing(){
        $.ajax({
            type: 'GET',
            url: '${requestScope['javax.servlet.forward.request_uri']}/ServletGetDrawings',
            data: {
                width: $("#width").val(),
                length: $("#length").val(),
                shed_width: $("#shed_width").val(),
                shed_length: $("#shed_length").val()
            },
            success: function (data) {
                //alert(data);
                $("#contentbox").html(data);
            }
        });
    }
    
    $(document).ready(updateDrawing());

    $(".range").change(function () {

        //changes span value to reflect what the bar is at
        var spanToChange = "#" + $(this).attr("id") + "_span";
        $(spanToChange).html($(this).val());

        updateDrawing();

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




</script>



<%@include file="/WEB-INF/jspf/footer.jspf"%>