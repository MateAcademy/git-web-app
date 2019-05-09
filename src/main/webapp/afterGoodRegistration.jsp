<%--
  Created by IntelliJ IDEA.
  model.User: Sergey
  Date: 28.04.2019
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head charset=\"utf-8\">
    <title>Web application Мэйт Академии</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body style="background-image:url(girl.jpg);  color:#ff6c36; font-weight: normal ">

<c:if test="${isRegistrated == true}">
    <p>Вы успешно зарегестрированы!</p>
</c:if>

<c:out value="${error}"/>

<h3>Hello <c:out value="${name}"/> after registration <br>
    Ваш пароль: <c:out value="${login}"/> after registration :) <br>
    Согласен ли ты с политикой обработки данных: <c:out value="${agree}"/> <br>
    Мой первый servlet, метод: " <c:out value="${method}"/> "<br>
    session account: <c:out value="${sessionUser}"/> <br>
    servletContext usera: <c:out value="${servletContext}"/> <br>
</h3>
<br>

<button class="w3-btn w3-hover-blue w3-round-large" onclick="location.href='/'">Back to main</button>
</body>
</body>
</html>
