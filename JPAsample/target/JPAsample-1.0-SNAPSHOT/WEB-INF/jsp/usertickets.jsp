<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Мои билеты</title>
    <link href="<c:url value='/main.css'/>" rel="stylesheet" type="text/css">
</head>
<body background="/src/images/poezd.jpg">
<h1>Привет ${user}:</h1>

<h1>Мои билеты:</h1>
<form action="requesttoreturnticket.do">
    <p align="left">Выбрать город отправки</p>
    <p><select name="select_ticket_to_return_id" size = 5 required>
        <c:forEach var="x" items="${usertickets}">
            <option value="${x.id}">${x.id} ${x.ownerStr} ${x.routeStr}</option>
        </c:forEach>
    </select></p>
    <p><input type="submit" value="Вернуть билет"> </p>
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