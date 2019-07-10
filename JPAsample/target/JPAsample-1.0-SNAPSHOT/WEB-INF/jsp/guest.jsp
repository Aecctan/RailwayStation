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
        <form action="showroutes.do"><input type="submit" value="Показать все маршруты"/></form>
    </li>
</ul>

<h1>Маршруты:</h1>
<table>
    <tbody>
    <c:forEach var="x" items="${routes}">
    <tr>
        <td>${x.id}</td>
        <td>${x.dispatch_city}</td>
        <td>${x.destination_city}</td>
    </tr>
    </c:forEach>
    <tbody>
</table>
<form action="registration.do">
    <input type="submit" value="Зарегистрироваться"/>
</form>

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
