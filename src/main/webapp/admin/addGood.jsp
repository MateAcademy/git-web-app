<%@ page import="java.util.*" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add good</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<style>
    .b1 {
        background: navy; /* Синий цвет фона */
        color: white; /* Белые буквы */
        font-size: 9pt; /* Размер шрифта в пунктах */
    }
</style>
<body style="background-image:url(../fon.jpg)">


<form action="../addGoodAdminServlet" method="post">
     <h3> На этой страничке можешь добавить товар в бд:</h3>

    <p> Имя товара: <input type="text" name="name" value=" как называется товар"></p>
    <p> Описание товара: <input type="text" name="description" value=" напишите что это за товар"></p>
    <p> Цена товара: <input type="text" name="cost" value=" введите здесь цену товара"></p>
    <input type="submit" value="Добавить" class="b1">
</form>


<br>
<button class="w3-btn w3-hover-blue w3-round-large" onclick="location.href='../goods'">Вернуться обратно на страничку! </button>

</body>
</html>
