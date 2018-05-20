
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%//@include file="/WEB-INF/jspf/header.jspf"%>
<!DOCTYPE html>







<button id="partlistbtn1" class="partlistloadbtn">KNAP1</button>
<button id="partlistbtn2" class="partlistloadbtn">KNAP2</button>
<button id="partlistbtn3" class="partlistloadbtn">KNAP3</button>
<button id="partlistbtn4" class="partlistloadbtn">KNAP4</button>
<button id="partlistbtn5" class="partlistloadbtn">KNAP5</button>




<div>----------------</div>
<div id="partlistcontent1">
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
<div>----------------</div>
<div id="partlistcontent2">
    <div id="content1">
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
<div>----------------</div>
<div id="partlistcontent3">
    <div id="content1">
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
<div>----------------</div>
<div id="partlistcontent4">
    <div id="content1">
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
<div>----------------</div>
<div id="partlistcontent5">
    <div id="content1">
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
<div>----------------</div>



<!-- jQuery + UI -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<script>

    $('.partlistloadbtn').click(function (e) {

        var id = $(this).attr('id').substring(11);

        $.ajax({
            type: 'GET',
            url: '${requestScope['javax.servlet.forward.request_uri']}/ServletGetPartlist',
            data: {id: id},
            success: function (data) {
                //alert(data);
                $("#partlistcontent" + id).html(data);
            }
        });

        e.preventDefault();
    });

</script>



<%//@include file="/WEB-INF/jspf/footer.jspf"%>