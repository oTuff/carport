<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<div class="receiptbg">
    <t:pagetemplate>
    <jsp:attribute name="header">
    </jsp:attribute>

        <jsp:attribute name="footer">
    </jsp:attribute>

        <jsp:body>
            <h1>Kvittering</h1>
            <h4>Tak for dit køb!</h4>

            <div class="receiptdesign">
                <form method="post">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Email</th>
                            <th>Total Bredde</th>
                            <th>Total Længde</th>
                            <th>Vejledende Pris</th>
                            <th>Skur</th>
                            <th>Leveringsadresse</th>
                            <th>Tidspunkt</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="order" items="${requestScope.orderId}">
                            <tr>
                                <td>${order.partslistOrderId}</td>
                                <td>${order.email}</td>
                                <td>${order.width}</td>
                                <td>${order.length}</td>
                                <td>${order.orderPrice}</td>
                                <td>${order.shedId}</td>
                                <td>
                                        <%--HUSK///<td>${sessionScope.user.address}</td>--%>
                                </td>
                                <td>
                                    <jsp:useBean id="date" class="java.util.Date"/>
                                    <fmt:formatDate value="${date}" pattern="yyyy-MM-dd HH:mm"/>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>
                                <button name="proceedbt" id="proceedbt" value="proceedbt"
                                        formaction="index.jsp">
                                    VEND TILBAGE TIL FORSIDEN
                                </button>
                            </td>
                        <tr>
                            <td>
                                <img src="images/transicons.png"
                                     style="margin-right: auto; min-height: 30px; max-width: 130px">
                            </td>
                        </tr>
                        </tr>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <div>stykliste</div>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>produkt</th>
                    <th>længde</th>
                    <th>antal</th>
                    <th>enhed</th>
                    <th>beskrivelse</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="line" items="${requestScope.partsList}">
                    <tr>
                        <td>${line.product.name}</td>
                        <td>${line.length}</td>
                        <td>${line.quantity}</td>
                        <td>${line.product.unit}</td>
                        <td>${line.description}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </jsp:body>
    </t:pagetemplate>
</div>