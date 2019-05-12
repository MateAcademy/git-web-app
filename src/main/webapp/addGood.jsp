<%@ page import="java.util.*" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add good</title>
</head>
<body style="background-image:url(fon.jpg)">


<form action="addGood" method="post">
    <h1> На этой страничке можешь добавить товар в бд:</h1>
    <h3>
    <p>Имя товара: <input type="text" name="name" value=" как называется товар"></p>
    <p>    Описание товара: <input type="text" name="description" value=" напишите что это за товар"></p>
    <p>   Цена товара: <input type="text" name="cost" value=" введите здесь цену товара"></p>
    <input type="submit" value="Добавить" class="b1"></h3>
</form>

</body>
</html>
