<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Пассажир добавлен</title>
    <link href="<c:url value='/main.css'/>" rel="stylesheet" type="text/css">
</head>
<body>
<h1>Билет выдан:</h1>
<table>
    <tbody>
    <c:forEach var="x" items="${passengers}">
    <tr>
        <td>${x.id}</td>
        <td>${x.name}</td>
        <td>${x.ticket}</td>
    </tr>
    </c:forEach>
    <tbody>
</table>
<div id="footer">
    <input type="button" onclick="history.back();" value="Назад"/>
    <p></p>
    <form action="list.do"><input type="submit" value="На главную"/></form>
</div>
</body>
</html>
