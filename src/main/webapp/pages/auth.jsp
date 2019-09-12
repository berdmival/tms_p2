<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator with history and authentication</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}${applicationScope.css_path}">
</head>
<body>
<h1>Log in to your account, please, or click "Sign up" to register</h1>
<h2>${requestScope.message}</h2>
<form action="${pageContext.request.contextPath}/index/auth" method="post">
    <input required autofocus placeholder="Your name" name="name"><br>
    <input required placeholder="Your password" type="password" name="password"><br>
    <button type="submit">Sign in</button>
</form>
<button onclick="document.location='/index/reg'">Sign up</button>
</body>
</html>
