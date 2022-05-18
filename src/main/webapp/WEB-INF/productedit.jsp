<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
              Rediger Produkt
    </jsp:attribute>

    <jsp:attribute name="footer">
            Rediger Produkt
    </jsp:attribute>

    <jsp:body>
        <form action="servletproductupdate" method="post">
            <label for="id">Produkt ID:</label>
            <input type="text" id="id" readonly name="id" value="${requestScope.product.productId}"/><br/>
            <label for="name">Produkt Navn:</label>
            <input type="text" id="name" name="name" value="${requestScope.product.name}"/><br/>
            <label for="price">Produkt Pris:</label>
            <input type="text" id="price" name="price" value="${requestScope.product.price}"/><br/>
            <label for="unitId">Enhed ID:</label>
            <input type="text" id="unitId" name="unitId" value="${requestScope.product.unitId}"/><br/>
            <input type="submit" value="OPDATER"/>
        </form>
    </jsp:body>
</t:pagetemplate>
