<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Mine ordre

    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>Order ID</th>
                <th>Email</th>
                <th>Total Bredde</th>
                <th>Total Længde</th>
                <th>Vejledende Pris</th>
                <th>Skur</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${requestScope.myorderlist}">
                <tr>
                    <td>${order.partslistOrderId}</td>
                    <td>${order.email}</td>
                    <td>${order.width}</td>
                    <td>${order.length}</td>
                    <td>${order.orderPrice}</td>
                    <td>${order.shedId}</td>
                    <td>
                        <c:if test="${sessionScope.user == null }">
                        <button name="afventer" id="afventer" disabled="true" value="${requestScope.myorderlist.indexOf(order)}"
                                formaction="">AFVENTER
                        </button>
                        </c:if>
                        <c:if test="${sessionScope.user != null }">
                            <button name="betal" id="betal" disabled="false" value="${requestScope.myorderlist.indexOf(order)}"
                                    formaction="">BETAL
                            </button>
                        </c:if>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:body>
</t:pagetemplate>