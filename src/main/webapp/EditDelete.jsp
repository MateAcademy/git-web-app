<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 03.05.2019
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List of users</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body style="background-image:url(fon.jpg); color:#ffffff">
<div class="w3-container w3-left-align">
    <h2><c:out value="${message}"/></h2>
    <h1>List of users</h1>
</div>
<div class="w3-container w3-left-align">
    <table border='2' width='80%'>
        <tr>
            <th>Name</th>
            <th>Password</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><a href='/edit?name=${user.name}'>edit all</a></td>
                <td><a href='/delete?name=${user.name}&password=${user.password}'>delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<br/>

<button class="w3-btn w3-hover-blue w3-round-large" onclick="location.href='/'">Back to main</button>
</body>
</html>