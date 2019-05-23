<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head charset=\"utf-8\">
    <title>allGoodsPageForAdmin</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body style="background-image:url(../fon.jpg);  color:#f8f9fa; font-weight: normal ">
<h3>Приветствую тебя администратор, здесь ты можешь редактировать, добавлять и удалять товары в базе данных:</h3>


<div class="w3-container w3-left-align">
    <table border="2" width="90%">
        <tr>
            <th>ID</th>
            <th>Название товара</th>
            <th>Описание</th>
            <th>Цена</th>
            <th>Купить!</th>
            <th>Редактировать товар!</th>
            <th>Удалить товар из БД!</th>
        </tr>
        <с:forEach items="${goods}" var="good">
            <tr>
                <td><c:out value="${good.getId()}"/></td>
                <td><c:out value="${good.getName()}"/></td>
                <td><c:out value="${good.getDescription()}"/></td>
                <td><c:out value="${good.getPrice()}"/></td>
                <td><a href='/buy?id=${good.getId()}'>Купить!</a></td>
                <td><a href='/admin/redactGood?id=${good.getId()}'>Edit!</a></td>
                <td><a href='/admin/deleteGoods?id=${good.getId()}'>remove!</a></td>
            </tr>
        </с:forEach>
    </table>
</div>

<br>
<button class="w3-btn w3-hover-blue w3-round-large" onclick="location.href='/admin/addGood.jsp'">Админ может добавить товар!</button>

<br>
<button class="w3-btn w3-hover-blue w3-round-large" onclick="location.href='/admin'">Back to edit or delete page</button>
</body>
</html>
