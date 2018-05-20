
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>







<button id="min_knap">KNAP</button>





<div>----------------</div>
<div id="content"></div>
<div>----------------</div>



<!-- jQuery + UI -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<script>
    
$('#min_knap').click(function (e) {
    $.ajax({
        type: 'GET',
        url: '${requestScope['javax.servlet.forward.request_uri']}/ServletGetPartlist',
        data: {id: 1},
        success: function(data){
            //alert(data);
            $("#content").html(data);
        }
    });

    e.preventDefault();
});

</script>