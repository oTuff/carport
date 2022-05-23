<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="da">
<head>
    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <jsp:invoke fragment="header"/>
    </title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">
                <img src="${pageContext.request.contextPath}/images/Screenshot 2022-05-12 at 11.56.11.png" width="1300"
                     height="125"/>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end">
                <div class="navbar-nav">

                </div>
            </div>
        </div>
    </nav>
</header>

<header>
    <nav class="navbar navbar-expand-md navbar-light bg-light" rel="stylesheet">
        <div class="container">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse justify-content-start" id="navbarNavAltMarkup">
                <%--LEFT NAV SIDE--%>
                <div class="navbar-nav mr-auto">
                    <c:if test="${sessionScope.user.role == 'user' }">
                        <a class="nav-link" href="${pageContext.request.contextPath}/servletmyorders">| MINE ORDRE |</a>
                    </c:if>
                    <c:if test="${sessionScope.user.role == 'admin' }">
                        <a class="nav-link" href="${pageContext.request.contextPath}/servletadminpanel">| ADMINPANEL</a>
                    </c:if>
                    <c:if test="${sessionScope.user.role == 'admin' }">
                        <a class="nav-link" href="${pageContext.request.contextPath}/servletadminproducts">|
                            PRODUKTLISTE |</a>
                    </c:if>
                    <c:if test="${sessionScope.user.role == 'admin' }">
                        <a class="nav-link" href="${pageContext.request.contextPath}/servletuseroverview">BRUGEROVERSIGT
                            |</a>
                    </c:if>
                </div>
            </div>

            <%--RIGHT NAV SIDE--%>
            <div class="navbar-nav ml-auto">
                <c:if test="${sessionScope.user != null }">
                    <a class="nav-item nav-link">${sessionScope.user.email}</a>
                </c:if>
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/">| HJEM |</a>
                <c:if test="${sessionScope.user == null }">
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/login.jsp">LOG IND |</a>
                </c:if>
                <c:if test="${sessionScope.user != null }">
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/servletlogout">LOG UD
                        |</a>
                </c:if>
            </div>
        </div>
    </nav>
</header>


<div id="body" class="container mt-4" style="min-height: 400px;">
    <jsp:doBody/>
</div>


<!-- Footer -->
<div class="panel-footer">
    <div class="container mt-3">
        <hr/>
        <div class="row mt-4">
            <div class="col">
                <p><strong>Kontakt</strong></p>
                <p>Johannes Fog A/S
                    <br>
                    Firskovvej 20
                    <br>
                    2800 Lyngby
                    <br>
                    45 87 10 01
                    <br>
                    info@johannesfog.dk</p>
            </div>
            <div class="col">
                <jsp:invoke fragment="footer"/>
                <br/>
                <p>&copy; 2022 Cphbusiness</p>
            </div>
            <div class="col">
                Datamatikeruddannelsen<br/>
                2. semester forår 2022
            </div>
        </div>
    </div>
</div>
</header>
<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>