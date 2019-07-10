<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Добавление маршрута</title>
    <link href="<c:url value='/second.css'/>" rel="stylesheet" type="text/css">
</head>
<body>
<h1 align="center">Добавление маршрута</h1>

<form action="routeadd.do">
    <p align="left">Выбрать город отправки</p>
    <p><select name="select_dispatch_city" size = 5 required>
        <c:forEach var="x" items="${cities}">
            <option value="${x.id}">${x.id} ${x.city}</option>
        </c:forEach>
    </select></p>

    <p align="left">Выбрать город назначения</p>
    <p><select name="select_destination_city" size = 5 required>
        <c:forEach var="y" items="${cities}">
            <option value="${y.id}">${y.id} ${y.city}</option>
        </c:forEach>
    </select></p>

    <p align="left">Выбрать тип поезда</p>
    <p><select name="select_train_type" size = 5 required>
        <c:forEach var="x" items="${trains}">
            <option value="${x.id}">${x.id} ${x.model} ${x.places}</option>
        </c:forEach>
    </select></p>
    <p><input type="submit" value="Отправить"> </p></form>

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
    <form action="list.do"><input type="submit" value="На главную"/></form>
</div>
<div class="status">${status}</div>
</body>
</html>