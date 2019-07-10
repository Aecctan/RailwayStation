<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Содержимое базы ${user}</title>
    <link href="<c:url value='/main.css'/>" rel="stylesheet" type="text/css">
</head>
<body background="/src/images/poezd.jpg">
<%--<script>--%>
<%--    if (${admin})--%>
<%--        window.location.replace("login.do");--%>
<%--</script>--%>
<h1>Привет ${user}:</h1>
<ul>
    <li>
        <form action="list.do"><input type="submit" value="Посмотреть информацию"/></form></li>
    <li>
        <form action="orderaticket.do"><input type="submit" value="Заказать билет"/></form>
    </li>
    <li>
        <form action="deletePassenger.do"><input type="submit" value="Удалить пассажира по ID: "/> <input name="id"/></form>
    </li>
    <li>
        <form action="deleteRoute.do"><input type="submit" value="Удалить маршрут по ID: "/> <input name="id"/></form>
    </li>
    <li>
        <form action="returnTicket.do"><input type="submit" value="Возврат билета"/> <input name="id"/></form>
    </li>
    <li>
        <form action="addacity.do"><input type="submit" value="Добавить город"/> <input name="city"/></form>
    </li>
    <li>
        <form action="addaroute.do"><input type="submit" value="Добавить маршрут"/></form>
    </li>
    <li>
        <form action="addatrain.do"><input type="submit" value="Добавить поезд"/></form>
    </li>
    <li>
        <form action="acceptrequeststoreturntickets.do"><input type="submit" value="Вернуть билеты по запросам"/></form>
    </li>

</ul>

<h1>Пассажиры:</h1>
<table>
    <tbody>
    <c:forEach var="x" items="${passengers}">
    <tr>
        <td>${x.id}</td>
        <td>${x.name}</td>
        <td>${x.ticketIds}</td>
        <td>${x.password}</td>
    </tr>
    </c:forEach>
    <tbody>
</table>

<h1>Города:</h1>
<table>
    <tbody>
    <c:forEach var="x" items="${cities}">
    <tr>
        <td>${x.id}</td>
        <td>${x.city}</td>
    </tr>
    </c:forEach>
    <tbody>
</table>

<h1>Поезда:</h1>
<table>
    <tbody>
    <c:forEach var="x" items="${trains}">
    <tr>
        <td>${x.id}</td>
        <td>${x.model}</td>
        <td>${x.places}</td>
    </tr>
    </c:forEach>
    <tbody>
</table>

<h1>Маршруты:</h1>
<table>
    <tbody>
    <c:forEach var="x" items="${routes}">
    <tr>
        <td>${x.id}</td>
        <td>${x.dispatchcity}</td>
        <td>${x.destinationcity}</td>
        <td>${x.trainStr}</td>
    </tr>
    </c:forEach>
    <tbody>
</table>

<div class="status">${status}</div>

<style type="text/css">
    #footer {
        position: fixed; /* Фиксированное положение */
        left: 0; bottom: 0; /* Левый нижний угол */
        /*padding: 10px; !* Поля вокруг текста *!*/
        /*background: #39b54a; !* Цвет фона *!*/
        color: #fff; /* Цвет текста */
        /*width: 100%; !* Ширина слоя *!*/
    }
</style>
<div id="footer">
    <input type="button" onclick="history.back();" value="Назад"/>
    <p></p>
    <form action="login.do"><input type="submit" value="На главную"/></form>
</div>

</body>
</html>
