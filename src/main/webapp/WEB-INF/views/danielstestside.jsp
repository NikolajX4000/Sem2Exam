
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>







<button id="min_knap">KNAP</button>





<div>Text123</div>
<div id="content"></div>
<div>Text123</div>





<script>
$('#min_knap').click(function (e) {
    $.ajax({
        type: 'GET',
        url: 'ServletGetPartlist',
        data: $('#house-form').serialize(),
        success: [function (data) {
                renderWall(document.getElementById('front-canvas'), data['front'], data['width'], data['height']);
                renderWall(document.getElementById('back-canvas'), data['back'], data['width'], data['height']);
                renderWall(document.getElementById('left-canvas'), data['left'], data['depth'], data['height']);
                renderWall(document.getElementById('right-canvas'), data['right'], data['depth'], data['height']);
                populateTable($("#bricks-table"), data);
                $("#preview-row").show(500);
            }]
    });
    e.preventDefault();
});
</script>