<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>

    <jsp:body>
        <div class="thirdPage mx-auto" style="width:80%;">
            <div class="w3-border mx-auto" style="width:25%;">
                <div class="w3-blue text-center" style="height:24px;width:100%;">3/3</div>
            </div>
            <br>
            <div class="mx-auto text-center">
                <h1>Tak fordi du valgte os!
                    <br>
                    Vi vil besvare din forespørgsel hurtigst muligt.
                    <br>
                    <br>
                </h1>
                <p>Du kan finde og betale under <a href="${pageContext.request.contextPath}/servletmyorders">ordre</a> når din forespørgsel er blevet accepteret af vores professionelle håndværkere.</p>

                <a href="${pageContext.request.contextPath}/servletmyorders">
                    <button class="btn btn-primary" >Mine ordrer</button>
                </a>
            </div>
        </div>

    </jsp:body>

</t:pagetemplate>