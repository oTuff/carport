<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>

    <jsp:body>
        <div class="secondPage mx-auto" style="width:80%;">
            <div class="w3-border mx-auto" style="width:50%;">
                <div class="w3-blue text-center" style="height:24px;width:67%;">2/3</div>
            </div>
            <br>
            <div class="mx-auto text-center">
                <h1>Vejledende pris: 16.525kr</h1>
                <br>
                <p><strong>Skitse</strong></p>
                <img src="https://i.imgur.com/Jh23mpv.png" alt="Vejledende skitse" style="max-width:400px;">

                <br>
                <form action="${pageContext.request.contextPath}/ServletRequestSent" method="get">
                    <a href="">
                        <button class="btn btn-primary mb-2">Send forespørgsel</button>
                    </a>
                </form>

                <a href="">
                    <button class="btn btn-primary">Tilbage</button>
                </a>
            </div>

        </div>

    </jsp:body>

</t:pagetemplate>