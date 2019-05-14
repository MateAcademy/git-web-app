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
    <meta charset="utf-8">
    <title>List of users</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style type="text/css">
        table {
            table-layout: fixed; /* Фиксированная ширина ячеек */
        }
        TD.dino { overflow: hidden;}
    </style>

</head>
<body style="background-image:url(../fon.jpg); color:#ffffff">
<div class="w3-container w3-left-align">
    <h2><c:out value="${message}"/></h2>
    <h1>List of users</h1>
</div>
    <table  width="80%" cellspacing="0" cellpadding="4" border="2" >
        <col width="10%">
        <col width="10%">
        <col width="60%">
        <col width="10%">
        <col width="10%">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Password</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr >
                <td class="dino" ><c:out value="${user.id}"/></td>
                <td class="dino" ><c:out value="${user.name}"/></td>
                <td class="dino" ><c:out value="${user.password}"/></td>
                <td class="dino" ><a href='/edit?name=${user.name}'>edit</a></td>
                <td class="dino" ><a href='/delete?name=${user.name}&password=${user.password}'>delete</a></td>
            </tr>
        </c:forEach>
    </table>
<br/>
<button class="w3-btn w3-hover-blue w3-round-large" onclick="location.href='/onAdminPage'">Back to edit or delete page </button>
</body>
</html>