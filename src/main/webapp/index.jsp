<%@ page import="java.util.*" %>
<%@ page isELIgnored="false" %>
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
<body style="background-image:url(girl.jpg)">
<center>
    Форма входа:
<br>
<c:if test="${registered == true}">
    <p>Вы успешно зарегестрированы!</p>
</c:if>

<c:out value="${error}"/>

<form action="login" method="post">
    <h3> Здесь Вы можете войти на сайт (для зарегистрированных пользователей):</h3>
    <p>Имя: <input type="text" name="name" value=" Введите свое имя">
        Пароль: <input type="text" name="password" value=" Введите здесь свой пароль"></p>
    <input type="submit" value="Войти" class="b1">
</form>

<br>
Еще не зарегестрированы?
<br>
<a href="registration.jsp">Регистрация</a>

</center>
</body>
</html>