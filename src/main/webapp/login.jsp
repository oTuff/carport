<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Login
    </jsp:attribute>

    <jsp:attribute name="footer">
            Login
    </jsp:attribute>

    <jsp:body>

        <div class="login">
            <div class="form mx-auto">
                <h1>Log ind</h1>
                <form action="servletlogin" method="post">
                    <label for="username">Email: </label>
                    <input type="text" autofocus class="form-control" required placeholder="Eksempel@gmail.com" autocomplete="off" id="username" name="username"/>
                    <label for="password">Kode: </label>
                    <input type="password" class="form-control" required placeholder="Kode" id="password" name="password"/>
                    <input type="submit" class="btn btn-primary btn-block mb-4" value="Log ind"/>
                    <a href="createuser.jsp" class="opretBruger">Opret bruger</a>
                    <a href="forgotpassword.jsp" class="glemtkodeord">glemt kodeord?</a>
                </form>
            </div>
        </div>



<%--        <h3>You can log in here</h3>--%>

<%--        <form action="servletlogin" method="post">--%>
<%--            <label for="username">Username: </label>--%>
<%--            <input type="text" id="username" name="username"/>--%>
<%--            <label for="password">Password: </label>--%>
<%--            <input type="password" id="password" name="password"/>--%>
<%--            <input type="submit"  value="Log ind"/>--%>
<%--            <a href="createuser.jsp" class="createUser">Opret bruger</a>--%>
<%--        </form>--%>

    </jsp:body>
</t:pagetemplate>