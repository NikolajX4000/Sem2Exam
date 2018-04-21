
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/jspf/header.jspf"%> 
<%@include file="/WEB-INF/jspf/debug.jspf"%> 

<div class="row">

    <form class="col s12 m6">
        <p>Login Below</p>
        <div class="row">
            <div class="input-field col s9">
                <input id="name" type="text" class="validate" name="lname" required>
                <label for="name">Name</label>
            </div>
        </div>
        <button class="btn waves-effect waves-light" type="submit" name="action">Submit
            <i class="material-icons right">send</i>
        </button>
    </form>
    <form class="col s12 m6">
        <p>Register Below</p>
        <div class="row">
            <div class="input-field col s9">
                <input required id="name" type="text" class="validate" name="rname" data-length="24" pattern="[\w]{1,24}">
                <label for="name" data-error="Name must be less than 24characters and not include any spaces">Name</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s9">
                <input required id="email" type="email" class="validate" name="rmail" data-length="50">
                <label for="email" data-error="Please enter a valid email">Email</label>
            </div>
        </div>
        <button class="btn waves-effect waves-light" type="submit" name="action">Submit
            <i class="material-icons right">send</i>
        </button>
    </form>
</div>

<%@include file="/WEB-INF/jspf/footer.jspf"%>
