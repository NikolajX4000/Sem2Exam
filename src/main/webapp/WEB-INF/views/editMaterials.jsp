
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf"%>

<div class="row">
    <div class="col s12">
        <ul class="tabs">
            <li class="tab col"><a href="#materials">Materials</a></li>
            <li class="tab col"><a href="#tools">Tools</a></li>
            <li class="tab col"><a href="#roofs">Roofs</a></li>
        </ul>
    </div>

    <%@include file="/WEB-INF/jspf/materialsEdit.jspf"%>  
    <%@include file="/WEB-INF/jspf/toolsEdit.jspf"%> 
    <%@include file="/WEB-INF/jspf/roofsEdit.jspf"%> 

</div>


<%@include file="/WEB-INF/jspf/footer.jspf"%>  