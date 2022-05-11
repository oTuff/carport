<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:body>
        <form action="${pageContext.request.contextPath}/ServletGraphic" method="post">
            <div class="w3-border mx-auto" style="width:50%;">
                <div class="w3-blue text-center" style="height:24px;width:33%;">1/3</div>
            </div>
            <label for="carportWidth">Carport bredde:</label>
            <select class="form-control" id="carportWidth" required name="carportWidth" title="carportWidth">
                <option selected="selected" value="">Vælg bredde</option>
                <option value="240 cm">240 cm</option>
                <option value="270 cm">270 cm</option>
                <option value="300 cm">300 cm</option>
                <option value="330 cm">330 cm</option>
                <option value="360 cm">360 cm</option>
                <option value="390 cm">390 cm</option>
                <option value="420 cm">420 cm</option>
                <option value="450 cm">450 cm</option>
                <option value="480 cm">480 cm</option>
                <option value="510 cm">510 cm</option>
                <option value="540 cm">540 cm</option>
                <option value="570 cm">570 cm</option>
                <option value="600 cm">600 cm</option>
            </select>

            <label for="carportLength">Carport længde:</label>
            <select class="form-control" id="carportLength" required name="carportLength" title="Carport længde">
                <option selected="selected" value="">Vælg længde</option>
                <option value="240 cm">240 cm</option>
                <option value="270 cm">270 cm</option>
                <option value="300 cm">300 cm</option>
                <option value="330 cm">330 cm</option>
                <option value="360 cm">360 cm</option>
                <option value="390 cm">390 cm</option>
                <option value="420 cm">420 cm</option>
                <option value="450 cm">450 cm</option>
                <option value="480 cm">480 cm</option>
                <option value="510 cm">510 cm</option>
                <option value="540 cm">540 cm</option>
                <option value="570 cm">570 cm</option>
                <option value="600 cm">600 cm</option>
                <option value="630 cm">630 cm</option>
                <option value="660 cm">660 cm</option>
                <option value="690 cm">690 cm</option>
                <option value="720 cm">720 cm</option>
                <option value="750 cm">750 cm</option>
                <option value="780 cm">780 cm</option>
            </select>

            <!--
            <label for="roof">Tag:</label>
            <select class="form-control" id="roof" name="roof" title="Tagtype/farve">
                <option selected="selected" value="">Vælg tagtype/farve</option>
                <option value="Betontagsten - Rød">Betontagsten - Rød</option>
                <option value="Betontagsten - Teglrød">Betontagsten - Teglrød</option>
                <option value="Betontagsten - Brun">Betontagsten - Brun</option>
                <option value="Betontagsten - Sort">Betontagsten - Sort</option>
                <option value="Eternittag B6 - Grå">Eternittag B6 - Grå</option>
                <option value="Eternittag B6 - Sort">Eternittag B6 - Sort</option>
                <option value="Eternittag B6 - Mokka (brun)">Eternittag B6 - Mokka (brun)</option>
                <option value="Eternittag B6 - Rødbrun">Eternittag B6 - Rødbrun</option>
                <option value="Eternittag B6 - Teglrød">Eternittag B6 - Teglrød</option>
                <option value="Eternittag B7 - Grå">Eternittag B7 - Grå</option>
                <option value="Eternittag B7 - Sort">Eternittag B7 - Sort</option>
                <option value="Eternittag B7 - Mokka (brun)">Eternittag B7 - Mokka (brun)</option>
                <option value="Eternittag B7 - Rødbrun">Eternittag B7 - Rødbrun</option>
                <option value="Eternittag B7 - Teglrød">Eternittag B7 - Teglrød</option>
                <option value="Eternittag B7 - Rødflammet">Eternittag B7 - Rødflammet</option>
            </select>

            <label for="roofPitch">Taghældning:</label>
            <select class="form-control" id="roofPitch" name="roofPitch" title="Taghældning">
                <option value="15 grader">15 grader</option>
                <option value="20 grader">20 grader</option>
                <option selected="selected" value="25 grader">25 grader</option>
                <option value="30 grader">30 grader</option>
                <option value="35 grader">35 grader</option>
                <option value="40 grader">40 grader</option>
                <option value="45 grader">45 grader</option>
            </select>
            
            <br>

        -->
            <input type="checkbox" id="skurCheckbox" onclick="hideSkurForm()">
            <label for="skurCheckbox">Vil du have et skur?</label>
            <br>

            <div id="skur" style="display: none;">
                <label for="shedWidth">Skur bredde</label>
                <select class="form-control" id="shedWidth" name="shedWidth" title="Skur bredde">
                    <option selected="selected" value="Vælg bredde">Vælg bredde</option>
                        <option value="210 cm">210 cm</option>
                        <option value="240 cm">240 cm</option>
                        <option value="270 cm">270 cm</option>
                        <option value="300 cm">300 cm</option>
                        <option value="330 cm">330 cm</option>
                        <option value="360 cm">360 cm</option>
                        <option value="390 cm">390 cm</option>
                        <option value="420 cm">420 cm</option>
                        <option value="450 cm">450 cm</option>
                        <option value="480 cm">480 cm</option>
                        <option value="510 cm">510 cm</option>
                        <option value="540 cm">540 cm</option>
                        <option value="570 cm">570 cm</option>
                        <option value="600 cm">600 cm</option>
                        <option value="630 cm">630 cm</option>
                        <option value="660 cm">660 cm</option>
                        <option value="690 cm">690 cm</option>
                        <option value="720 cm">720 cm</option>
                    </select>

                <label for="shedLength">Skur længde</label>
                <select class="form-control" id="shedLength" name="shedLength" title="Skur længde">
                    <option selected="selected" value="Vælg længde">Vælg længde</option>
                        <option value="150 cm">150 cm</option>
                        <option value="180 cm">180 cm</option>
                        <option value="210 cm">210 cm</option>
                        <option value="240 cm">240 cm</option>
                        <option value="270 cm">270 cm</option>
                        <option value="300 cm">300 cm</option>
                        <option value="330 cm">330 cm</option>
                        <option value="360 cm">360 cm</option>
                        <option value="390 cm">390 cm</option>
                        <option value="420 cm">420 cm</option>
                        <option value="450 cm">450 cm</option>
                        <option value="480 cm">480 cm</option>
                        <option value="510 cm">510 cm</option>
                        <option value="540 cm">540 cm</option>
                        <option value="570 cm">570 cm</option>
                        <option value="600 cm">600 cm</option>
                        <option value="630 cm">630 cm</option>
                        <option value="660 cm">660 cm</option>
                        <option value="690 cm">690 cm</option>
                    </select>
                </select>
            </div>

            <br>

            <label for="address">Leveringsadresse</label>
            <input type="text" class="form-control" disabled value="${sessionScope.user.address}" placeholder="Adresse" id="address" required>
            <input type="checkbox" onclick="letUserChangeAddress()" id="addressCheckbox">
            <label for="addressCheckbox">Levering til anden adresse</label>
            <br>
            <br>

            <c:if test="${sessionScope.user != null}">
                <input type="submit" class="btn btn-primary" value="Næste" style="width:25%; font-weight: bold;">
            </c:if>

            <c:if test="${sessionScope.user == null}">
                <input type="submit" class="btn btn-primary" value="Næste" style="width:25%; font-weight: bold;">
            </c:if>

        </form>

        <script>
            function hideSkurForm() {
                var checkBox = document.getElementById("skurCheckbox");
                var text = document.getElementById("skur");
                if (checkBox.checked == true){
                    text.style.display = "block";
                } else {
                    text.style.display = "none";
                }
            }

            function letUserChangeAddress(){
                var checkBox = document.getElementById("addressCheckbox");
                var addressBar = document.getElementById("address");
                if (checkBox.checked == true){
                    addressBar.disabled = false;
                    addressBar.value = "";
                    addressBar.placeholder = "Din adresse";
                } else {
                    addressBar.disabled = true;
                }
            }
        </script>
    </jsp:body>

</t:pagetemplate>