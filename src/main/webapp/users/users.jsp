<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 13.05.2019
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show All Users</title>
</head>
<body>

<table border="1">
    <thead>
    <tr>
        <th>Name</th>
        <th>Password</th>
        <th>Email</th>
        <th>Role</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
        <td><c:out value="${user.name}"/> </td>
        <td><c:out value="${user.passwodr}"/> </td>
        <td><c:out value="${user.email}"/> </td>
        <td><c:out value="${user.role}"/> </td>

        <td><a href="/admin/users/update?name=<c:out value="${user.name}"/>">Update</a></td>
        <td><a href="/admin/users/delete?name=<c:out value="${user.name}"/>">Delete</a></td>
        </tr>
    </c:forEach>>
    </tbody>
</table>
</body>
</html>
