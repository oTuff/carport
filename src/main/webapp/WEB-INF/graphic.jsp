<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<t:pagetemplate>

    <jsp:body>
        <div class="secondPage mx-auto" style="width:80%;">
            <div class="w3-border mx-auto" style="width:50%;">
                <div class="w3-blue text-center" style="height:24px;width:67%;">2/3</div>
            </div>
            <br>
            <div class="mx-auto text-center">

                <h1><fmt:formatNumber type = "number" maxFractionDigits="3" value="${requestScope.order.orderPrice}" /> kr</h1>

                <br>
                <p><strong>Skitse</strong></p>
                ${requestScope.svgdrawing}

                <br>
                <form action="${pageContext.request.contextPath}/servletrequestsent" method="get">
                    <a href="">
                        <button class="btn btn-primary mb-2">Send forespørgsel</button>
                    </a>
                </form>

                <a href="${pageContext.request.contextPath}/index.jsp">
                    <button class="btn btn-primary">Tilbage</button>
                </a>
            </div>

        </div>

    </jsp:body>

</t:pagetemplate>