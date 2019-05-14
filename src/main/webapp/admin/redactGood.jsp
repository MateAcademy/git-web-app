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
<h3> На этой страничке можешь редактировать данные о товаре:</h3>
<table border="2" width="70%">
    <tr>
        <th>ID</th>
        <th>Название товара</th>
        <th>Описание</th>
        <th>Цена</th>

    </tr>
        <tr>
            <td><c:out value="${id}"/></td>
            <td><c:out value="${name}"/></td>
            <td><c:out value="${description}"/></td>
            <td><c:out value="${price}"/></td>
        </tr>
</table>

<form action="updateTableGoods" method="post">
    <h3> Новая информация:</h3>
    <h4>
        <p> Имя товара: <input type="text" name="name" value=" как называется товар"></p>
        <p> Описание товара: <input type="text" name="description" value=" напишите что это за товар"></p>
        <p> Цена товара: <input type="text" name="cost" value=" введите здесь цену товара"></p>
        <th><input type="text" name="id" value= "${id}" hidden="false" /></th>
        <input type="submit" value="Обновить" class="b1"></h4>
</form>
</body>
</html>
