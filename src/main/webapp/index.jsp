<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="footer">
        Welcome to the frontpage
    </jsp:attribute>

    <jsp:body>
        <form action="/">
            <label for="carportWidth">Carport bredde:</label>
            <input type="text" class="form-control" placeholder="Vælg bredde" id="carportWidth" placeholder="">
            <label for="carportLength">Carport længde:</label>
            <input type="text" class="form-control" placeholder="Vælg længde" id="carportLength" placeholder="">

            <br>


        <c:if test="${sessionScope.user != null}">
            <input type="submit" class="form-control" value="Godkend">
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <input type="submit" class="btn btn-primary" value="Log ind/opret bruger">
        </c:if>

        </form>

    </jsp:body>

</t:pagetemplate>