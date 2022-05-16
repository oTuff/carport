<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Brugeroversigt

    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>Email</th>
                <th>Fulde navn</th>
                <th>Kodeord</th>
                <th>Saldo</th>
                <th>Leaveringsadresse</th>
                <th>Postnummer</th>
                <th>Rolle</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${requestScope.userList}">
                <tr>
                    <td>${user.email}</td>
                    <td>${user.fullName}</td>
                    <td>${user.password}</td>
                    <td>${user.balance}</td>
                    <td>${user.address}</td>
                    <td>${user.zipNr}</td>
                    <td>${user.role}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:body>
</t:pagetemplate>
