<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 04.05.2019
  Time: 0:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit of users:</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body style="background-image:url(fon.jpg); color:#ffffff">
<div class="w3-container w3-left-align">
    <h2><c:out value="${message}"/></h2>
    <h1>Edit all user data:</h1>
</div>
<div class="w3-container w3-left-align">
    <table border='2' width='80%'>
        <tr>
            <th>Name</th>
            <th>Password</th>
            <th>Edit</th>

        </tr>
        <tr><form action="ShowUsersAfterEdit">
            <th><input type="text" name="login" value= "${name}" disabled  /></th>
            <th><input type="text" name="login" value= "${name}" hidden="false" /></th>
            <th><input type="text" name="password" value=" Введите новый пароль "></th>
            <th> <input type="submit" value="Edit" ></th>
        </form>
        </tr>
    </table>
</div><br />

<button class="w3-btn w3-hover-blue w3-round-large" onclick="location.href='/'">Back to main</button>
</body>
</html>