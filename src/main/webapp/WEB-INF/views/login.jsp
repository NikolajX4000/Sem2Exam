
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/jspf/header.jspf"%>

<form class="row" method="get" accept-charset="ISO-8859-1">

        <div class="input-field col m6 s12">
            <input id="user" type="text" class="validate" name="user" minlength="3" required>
            <label for="user">Bruger</label>
        </div>
    
        <div class="input-field col m6 s12">
            <input id="pw" type="password" class="validate" name="pw" required>
            <label for="pw">Password</label>
        </div>
    
        <div class="input-field col s12">
            <button class="btn waves-effect waves-light blue" type="submit" name="command" value="CmdLogin">Login<i class="material-icons right">send</i></button>
        </div>
    
        <div class="input-field col s12"><p>${feedback}</p></div>
    
</form>

<%@include file="/WEB-INF/jspf/footer.jspf"%>
