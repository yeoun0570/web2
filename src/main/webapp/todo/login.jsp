<%--
  Created by IntelliJ IDEA.
  User: LG
  Date: 2024-09-10
  Time: 오전 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<c:if test="${param.result == 'error'}">
    <h1>로그인 에러 발생</h1>
</c:if>

<form action="/login" method="post">
    <input type="text" name="mid" placeholder="Login">
    <input type="password" name="mpw" placeholder="Password">
    <button type="submit">LOGIN</button>
</form>
</body>
</html>
