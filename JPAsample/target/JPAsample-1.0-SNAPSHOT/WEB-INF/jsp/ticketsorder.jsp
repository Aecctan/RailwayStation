<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Заказ билетов</title>
    <link href="<c:url value='/second.css'/>" rel="stylesheet" type="text/css">
</head>
<body>
<h1 align="center">Заказ билетов</h1>

<form action="addticket.do">

    <p align="left">Выбрать пассажира</p>
    <p><select name="select_passenger" size = 5 required>
        <c:forEach var="x" items="${passengers}">
            <option value="${x.id}">${x.id} ${x.name}</option>
        </c:forEach>
    </select></p>

    <p align="left">Выбрать маршрут</p>
    <p><select name="select_route" size = 5 required>
        <c:forEach var="y" items="${routes}">
            <option value="${y.id}">${y.id} ${y.dispatchcity} ${y.destinationcity} ${y.trainStr}</option>
        </c:forEach>
    </select></p>
    <p><input type="submit" value="Отправить"> </p></form>

<h1 align="center">Выдать билет новому клиенту</h1>
<form action="addpassenger.do">
    <p align="left">Выбрать маршрут</p>
    <p><select name="select_route_new_passenger" size = 5 required>
        <c:forEach var="y" items="${routes}">
            <option value="${y.id}">${y.id} ${y.dispatchcity} ${y.destinationcity} ${y.train}</option>
        </c:forEach>
    </select></p>
    <input type="submit" value="Выдать билет новому клиенту"/> <input name="name" required/>
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
    <form action="list.do"><input type="submit" value="На главную"/></form>
</div>
<div class="status">${status}</div>
</body>
</html>
