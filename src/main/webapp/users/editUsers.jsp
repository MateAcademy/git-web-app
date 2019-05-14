<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 13.05.2019
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update user </title>
</head>
<body>

<form action="/admin/users/update" method="post">
    Nickname: <input type="text" name="name"
                     value="<c:out value="${user.name}" />" /> <br />
    Password: <input type="text" name="password"/>" /> <br />
    Email: <input type="text" name="email"
                     value="<c:out value="${user.email}" />" /> <br />
    Role: <input type="text" name="role"
                     value="<c:out value="${user.role}" />" /> <br />
    <input type="submit" value="Submit">
</form>
</body>
</html>
