<%--
  Created by IntelliJ IDEA.
  model.User: Sergey
  Date: 28.04.2019
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head charset=\"utf-8\">
    <title>Web application Мэйт Академии</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body style="background-image:url(girl.jpg);  color:#ff6c36; font-weight: normal ">

<h3>
    Приветствую тебя: <c:out value="${name}"/> после успешного входа в CRM систему :) <br>
    session account: <c:out value="${sessionUser}"/>, session will last 2 minutes<br>
</h3>
<br>
<button class="w3-btn w3-hover-blue w3-round-large" onclick="location.href='/'">Back to main</button>
</body>
</html>
