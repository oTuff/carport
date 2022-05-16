<%--
  Created by IntelliJ IDEA.
  User: gustavbogh
  Date: 12/05/2022
  Time: 13.04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <div id="admin">
                        <c:if test="${sessionScope.user.role == 'admin' }">
                            <a  class="nav-link" href="${pageContext.request.contextPath}/servletuseroverview">BRUGEROVERSIGT</a>
                        </c:if>
                    </div>
                    <div id="overview">
                        <c:if test="${sessionScope.user.role == 'admin' }">
                            <a id="overview" class="nav-link" href="${pageContext.request.contextPath}/servletadminpanel">| ADMIN |</a>
                        </c:if>
                    </div>
                    <c:if test="${sessionScope.user != null }">
                        <a class="nav-item nav-link">${sessionScope.user.email}</a>
                    </c:if>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/">HJEM</a>
                    <c:if test="${sessionScope.user == null }">
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/login.jsp">LOG IND</a>
                    </c:if>
                    <c:if test="${sessionScope.user != null }">
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/servletlogout">LOG UD</a>
                    </c:if>
                </div>
            </div>
        </div>
    </nav>
</header>

</body>
</html>
