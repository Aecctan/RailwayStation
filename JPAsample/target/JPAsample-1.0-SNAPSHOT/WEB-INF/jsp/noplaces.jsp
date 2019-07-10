<%--
  Created by IntelliJ IDEA.
  User: Timur
  Date: 23.06.2019
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>СВОБОДНЫХ МЕСТ НЕТ!!!</title>
</head>
<body>
<h1>СВОБОДНЫХ МЕСТ НЕТ!!!</h1>

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
