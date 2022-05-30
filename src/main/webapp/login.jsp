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
                <h2>Log ind</h2>
                <form action="servletlogin" method="post">
                    <label for="username">Email: </label>
                    <input type="text" autofocus class="form-control" required placeholder="Eksempel@gmail.com" autocomplete="off" id="username" name="username"/>
                    <label for="password">Kode: </label>
                    <input type="password" class="form-control" required placeholder="Kode" id="password" name="password"/>
                    <input type="submit" class="btn btn-primary btn-block mb-4" value="Log ind"/>
                    <br>
                    <a href="createuser.jsp" class="opretBruger" style="color: whitesmoke; font-weight: bold">Opret bruger</a>
                    <br>
                    <a href="forgotpassword.jsp" class="glemtkodeord" style="color: whitesmoke; font-weight: bold" >glemt kodeord?</a>
                </form>
            </div>
        </div>

    </jsp:body>
</t:pagetemplate>