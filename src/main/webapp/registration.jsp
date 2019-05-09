<%@ page import="java.util.*" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head charset="utf-8">
    <title>Web application Мэйт Академии</title>
</head>
<style>
    .b1 {
        background: navy; /* Синий цвет фона */
        color: white; /* Белые буквы */
        font-size: 9pt; /* Размер шрифта в пунктах */
    }
</style>

<body style="background-image:url(girl.jpg) ">

<form action="registration" method="post">
    <h3> Пройдите регистрацию:</h3>
    <p>Имя: <input type="text" name="name" value=" Введите свое имя ">
        Пароль: <input type="text" name="password" value=" Введите пароль "></p>
    <input type="checkbox" name="agree" value="yes"> Согласен с политикой обработки данных</input>
    <br><br><input type="submit" value="Зарегистрироваться" class="b1">
</form>

</body>
</html>
