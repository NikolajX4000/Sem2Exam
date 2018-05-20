
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>







<button id="order1" class="min_knap">KNAP1</button>
<button id="order2" class="min_knap">KNAP2</button>
<button id="order3" class="min_knap">KNAP3</button>
<button id="order4" class="min_knap">KNAP4</button>
<button id="order6" class="min_knap">KNAP5</button>




<div>----------------</div>
<div id="content"></div>
<div>----------------</div>



<!-- jQuery + UI -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<script>
    
$('.min_knap').click(function (e) {
    $.ajax({
        type: 'GET',
        url: '${requestScope['javax.servlet.forward.request_uri']}/ServletGetPartlist',
        data: {id: $(this).attr('id').substring(5)},
        success: function(data){
            //alert(data);
            $("#content").html(data);
        }
    });

    e.preventDefault();
});

</script>