<%--
  Created by IntelliJ IDEA.
  model.User: Sergey
  Date: 28.04.2019
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head charset=\"utf-8\">
    <title>Web application Мэйт Академии</title>
</head>
<body style="background-image:url(girl.jpg); color:#ff6c36">

    You already have account, just sign in <br>
    Твое имя пользователя уже есть в базе: <c:out value="${name}"/><br>
    И твой пароль тоже уже зарегистрирован : <c:out value="${login}"/><br>

</body>
</html>
