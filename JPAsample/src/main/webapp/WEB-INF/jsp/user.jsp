<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Здравствуй</title>
    <link href="<c:url value='/main.css'/>" rel="stylesheet" type="text/css">
</head>
<body background="/src/images/poezd.jpg">
<h1>Привет ${user}:</h1>
<ul>
    <li>
        <form action="loginpassenger.do"><input type="submit" value="Показать все маршруты"/></form>
    </li>
</ul>

<h1>Маршруты:</h1>
<table>
    <tbody>
    <c:forEach var="x" items="${routes}">
    <tr>
        <td>${x.id}</td>
        <td>${x.dispatchcity}</td>
        <td>${x.destinationcity}</td>
    </tr>
    </c:forEach>
    <tbody>
</table>

<h1 align="left">Купить билет</h1>
<form action="buyaticket.do">
    <h1 align="left">Выбрать маршрут</h1>
    <p><select name="select_route" size = 5 required>
        <c:forEach var="y" items="${routes}">
            <option value="${y.id}">${y.id} ${y.thisRoute}</option>
        </c:forEach>
    </select></p>
    <input type="submit" value="Купить билет"/>
</form>

<form action="showmytickets.do"><input type="submit" value="Показать мои билеты"/></form>

<style type="text/css">
    #footer {
        position: fixed; /* Фиксированное положение */
        left: 0; bottom: 0; /* Левый нижний угол */
        padding: 10px; /* Поля вокруг текста */
        background: #39b54a; /* Цвет фона */
        color: #fff; /* Цвет текста */
        width: 100%; /* Ширина слоя */
    }
</style>
<div id="footer">
    <input type="button" onclick="history.back();" value="Назад"/>
    <p></p>
    <form action="login.do"><input type="submit" value="На главную"/></form>
</div>
</body>
</html>