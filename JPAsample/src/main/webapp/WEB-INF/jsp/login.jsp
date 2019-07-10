<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Войти в базу данных</title>
    <link href="<c:url value='/login.css'/>" rel="stylesheet" type="text/css">
</head>
<body>
<form action="loginto.do">
    <input name="user" type="text" placeholder="имя"/>
    <input name="password" type="password" placeholder="пароль" name="p"/>
    <input type="submit" value="Вход"/>
</form>
<form action="showtimetable.do">
    <input type="submit" value="Посмотреть расписание|Зарегистрироваться"/>
</form>
</body>
</html>
