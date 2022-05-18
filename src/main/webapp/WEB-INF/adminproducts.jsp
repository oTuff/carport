<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Admin Produktliste

    </jsp:attribute>

    <jsp:attribute name="footer">
        Admin Produktliste

    </jsp:attribute>

    <jsp:body>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>ID</th>
                <th>Produkt Navn</th>
                <th>Produkt Pris</th>
                <th>Enhed</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${requestScope.productlist}">
                <tr>
                    <td>${product.productId}</td>
                    <td>${product.name}</td>
                    <td>${product.price} kr</td>
                    <td>${product.unitId}</td>
                    <td>
                    <form action="servletproductedit" method="post">
                        <button type="submit" name="edit" value="${product.productId}">Rediger</button>
                    </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:body>
</t:pagetemplate>