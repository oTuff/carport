<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Opret Burger
    </jsp:attribute>

    <jsp:attribute name="footer">
            Opret Bruger
    </jsp:attribute>

    <jsp:body>

        <c:if test="${sessionScope.user != null}">
            <p>Du er allerede logget ind</p>
        </c:if>

        <c:if test="${sessionScope.user == null}">

                <h1>Opret bruger</h1>
                <br>
                <form action="${pageContext.request.contextPath}/servletcreateuser" method="post">
                    <input type="email" autofocus class="form-control" autocomplete="off" placeholder="Email" name="email">
                    <input type="text" class="form-control" placeholder="Fulde navn" name="fullname">
                    <input type="password" class="form-control" placeholder="Kode" name="password">
                    <input type="password" class="form-control" placeholder="Gentag kode" name="password">
                    <input type="text" class="form-control" placeholder="Adresse" name="address">
                    <input type="text" class="form-control" placeholder="Postnummer" name="zipnr">
                    <input type="submit" class="btn btn-primary" value="Opret">
                </form>
        </c:if>

    </jsp:body>

</t:pagetemplate>
