<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 05.05.2019
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin</title>
</head>
<body style="background-image:url(../girl.jpg)">
<style>
    .b1 {
        background: navy; /* Синий цвет фона */
        color: white; /* Белые буквы */
        font-size: 9pt; /* Размер шрифта в пунктах */
    }
</style>
Приветствую тебя администратор:  <c:out value="${name}"/>!

<br>
<form action="editDeleteUsersServlet" method="post">
    <h3> Здесь Вы можете редактировать или удалять пользователей из БД:</h3>
    <input type="submit" value="Edit / Delete" class="b1">
</form>

<br>
<form action="goods" method="post">
    <h3> Здесь Вы можете редактировать или удалять твары из БД:</h3>
    <input type="submit" value="Goods Edit / Goods Delete" class="b1">
</form>

</body>
</html>
