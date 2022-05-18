<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>


<t:pagetemplate>

    <jsp:attribute name="header">
        FOG Carport
    </jsp:attribute>


    <jsp:body>
        <form action="${pageContext.request.contextPath}/ServletGraphic" method="post">
            <div class="w3-border mx-auto" style="width:50%;">
                <div class="w3-blue text-center" style="height:24px;width:33%;">1/3</div>
            </div>
            <label for="carportWidth">Carport bredde:</label>
            <select class="form-control" id="carportWidth" required name="carportWidth" title="carportWidth">
                <option selected="selected" value="">Vælg bredde</option>
                <option value="240">240 cm</option>
                <option value="270">270 cm</option>
                <option value="300">300 cm</option>
                <option value="330">330 cm</option>
                <option value="360">360 cm</option>
                <option value="390">390 cm</option>
                <option value="420">420 cm</option>
                <option value="450">450 cm</option>
                <option value="480">480 cm</option>
                <option value="510">510 cm</option>
                <option value="540">540 cm</option>
                <option value="570">570 cm</option>
                <option value="600">600 cm</option>
            </select>

            <label for="carportLength">Carport længde:</label>
            <select class="form-control" id="carportLength" required name="carportLength" title="Carport længde">
                <option selected="selected" value="">Vælg længde</option>
                <option value="240">240 cm</option>
                <option value="270">270 cm</option>
                <option value="300">300 cm</option>
                <option value="330">330 cm</option>
                <option value="360">360 cm</option>
                <option value="390">390 cm</option>
                <option value="420">420 cm</option>
                <option value="450">450 cm</option>
                <option value="480">480 cm</option>
                <option value="510">510 cm</option>
                <option value="540">540 cm</option>
                <option value="570">570 cm</option>
                <option value="600">600 cm</option>
                <option value="630">630 cm</option>
                <option value="660">660 cm</option>
                <option value="690">690 cm</option>
                <option value="720">720 cm</option>
                <option value="750">750 cm</option>
                <option value="780">780 cm</option>
            </select>

            <input type="checkbox" id="skurCheckbox" onclick="hideSkurForm()">
            <label for="skurCheckbox">Vil du have et skur?</label>
            <br>

            <div id="skur" style="display: none;">
                <label for="shedWidth">Skur bredde</label>
                <select class="form-control" id="shedWidth" name="shedWidth" title="Skur bredde">
                    <option selected="selected" value="Vælg bredde">Vælg bredde</option>
                        <option value="210">210 cm</option>
                        <option value="240">240 cm</option>
                        <option value="270">270 cm</option>
                        <option value="300">300 cm</option>
                        <option value="330">330 cm</option>
                        <option value="360">360 cm</option>
                        <option value="390">390 cm</option>
                        <option value="420">420 cm</option>
                        <option value="450">450 cm</option>
                        <option value="480">480 cm</option>
                        <option value="510">510 cm</option>
                        <option value="540">540 cm</option>
                        <option value="570">570 cm</option>
                        <option value="600">600 cm</option>
                        <option value="630">630 cm</option>
                        <option value="660">660 cm</option>
                        <option value="690">690 cm</option>
                        <option value="720">720 cm</option>
                    </select>

                <label for="shedLength">Skur længde</label>
                <select class="form-control" id="shedLength" name="shedLength" title="Skur længde">
                    <option selected="selected" value="Vælg længde">Vælg længde</option>
                        <option value="150">150 cm</option>
                        <option value="180">180 cm</option>
                        <option value="210">210 cm</option>
                        <option value="240">240 cm</option>
                        <option value="270">270 cm</option>
                        <option value="300">300 cm</option>
                        <option value="330">330 cm</option>
                        <option value="360">360 cm</option>
                        <option value="390">390 cm</option>
                        <option value="420">420 cm</option>
                        <option value="450">450 cm</option>
                        <option value="480">480 cm</option>
                        <option value="510">510 cm</option>
                        <option value="540">540 cm</option>
                        <option value="570">570 cm</option>
                        <option value="600">600 cm</option>
                        <option value="630">630 cm</option>
                        <option value="660">660 cm</option>
                        <option value="690">690 cm</option>
                    </select>
                </select>
            </div>

            <br>

            <label for="address">Leveringsadresse</label>
            <input type="text" class="form-control"  disabled value="${sessionScope.user.address}" placeholder="Adresse" id="address" name="address" required>
            <input type="checkbox" onclick="letUserChangeAddress()" id="addressCheckbox">
            <label for="addressCheckbox">Levering til anden adresse</label>
            <br>
            <br>

            <c:if test="${sessionScope.user != null}">
                <button type="submit" class="btn btn-primary" disabled="true" value="Næste" style="width:25%; font-weight: bold;">
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
                    addressBar.placeholder = "Leveringsadresse";
                } else {
                    addressBar.disabled = true;
                }
            }
        </script>
    </jsp:body>

</t:pagetemplate>