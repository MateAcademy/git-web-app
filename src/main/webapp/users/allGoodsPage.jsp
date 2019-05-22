<%--
  Created by IntelliJ IDEA.
  model.User: Sergey
  Date: 28.04.2019
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head charset=\"utf-8\">
    <title>Приветствие юзера!</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body style="background-image:url(../fon.jpg);  color:#fff; font-weight: normal ">

<h3>
    Приветствую тебя: <c:out value="${name}"/> после успешного входа в CRM систему :) <br>
    session account: <c:out value="${sessionUser}"/>, session will last 2 minutes<br>
</h3>

<div class="w3-container w3-left-align">
    <table border="2" width="70%">
        <tr>
            <th>ID</th>
            <th>Название товара</th>
            <th>Описание</th>
            <th>Цена</th>
            <th>Купить!</th>
        </tr>
        <с:forEach items="${goods}" var="good">
            <tr>
            <td><c:out value="${good.getId()}"/></td>
            <td><c:out value="${good.getName()}"/></td>
            <td><c:out value="${good.getDescription()}"/></td>
            <td><c:out value="${good.getPrice()}"/></td>
            <td><a href='buy?id=${good.getId()}'>Купить!</a></td>
            </tr>
        </с:forEach>
    </table>
</div>

<br>
<button class="w3-btn w3-hover-blue w3-round-large" onclick="location.href='..'">Back to main</button>
</body>
</html>
