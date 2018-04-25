
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/jspf/header.jspf"%>

<div class="row">
    <div class="col s12">
        <a href="?command=PageBuildCarportCommand" class="waves-light"><img alt="" style="width:100%;" src="https://www.johannesfog.dk/globalassets/banner/services/carport_quickbyg.jpg"class="responsive-img z-depth-1"></a>
    </div>
</div>

<div class="divider"></div>


<div class="row"> 
    <form class="col s12" method="get" accept-charset="ISO-8859-1">

            <div class="input-field">
                <input id="email" type="email" name="email" required placeholder="Skriv din email for at se dine ordre">
                <label for="email"></label>
            </div>
        <button class="btn waves-effect waves-light" type="submit" name="command" value="ShowOrdersCommand">Find ordre<i class="material-icons right">send</i></button>

    </form>

</div>



</div>

<%@include file="/WEB-INF/jspf/footer.jspf"%>
