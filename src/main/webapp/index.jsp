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
            <select class="form-control" id="carportWidth" name="carportWidth" title="carportWidth">
                <option selected="selected" value="">Vælg bredde</option>
                <option value="240 cm">240 cm</option>
                <option value="270 cm">270 cm</option>
                <option value="300 cm">300 cm</option>
                <option value="330 cm">330 cm</option>
                <option value="360 cm">360 cm</option>
                <option value="390 cm">390 cm</option>
                <option value="420 cm">420 cm</option>
                <option value="450 cm">450 cm</option>
                <option value="480 cm">480 cm</option>
                <option value="510 cm">510 cm</option>
                <option value="540 cm">540 cm</option>
                <option value="570 cm">570 cm</option>
                <option value="600 cm">600 cm</option>
            </select>

            <label for="carportLength">Carport længde:</label>
            <select class="form-control" id="carportLength" name="carportLength" title="Carport længde"><option selected="selected" value="">Vælg længde</option>
                <option value="240 cm">240 cm</option>
                <option value="270 cm">270 cm</option>
                <option value="300 cm">300 cm</option>
                <option value="330 cm">330 cm</option>
                <option value="360 cm">360 cm</option>
                <option value="390 cm">390 cm</option>
                <option value="420 cm">420 cm</option>
                <option value="450 cm">450 cm</option>
                <option value="480 cm">480 cm</option>
                <option value="510 cm">510 cm</option>
                <option value="540 cm">540 cm</option>
                <option value="570 cm">570 cm</option>
                <option value="600 cm">600 cm</option>
                <option value="630 cm">630 cm</option>
                <option value="660 cm">660 cm</option>
                <option value="690 cm">690 cm</option>
                <option value="720 cm">720 cm</option>
                <option value="750 cm">750 cm</option>
                <option value="780 cm">780 cm</option>
            </select>
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