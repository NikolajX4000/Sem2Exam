<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<table>
    <thead>
        <tr>
            <th>Antal</th>
            <th>Størrelse</th>
            <th>Navn</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${items}" var="i">
            <tr>
                <td>${i.amount}</td>
                <td>${i.size}</td>
                <td>${i.material.name}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>