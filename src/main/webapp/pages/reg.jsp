<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator with history and authentication</title>
    <link rel="stylesheet" href="<c:url value='${applicationScope.css_path}' />">
</head>
<body>
<h1>Register your account, please, or click "Sign in" to log in</h1>
<h2><c:out value="${requestScope.message}" /></h2>
<form action="${pageContext.request.contextPath}/index/reg" method="post">
    <input required autofocus placeholder="Your name" name="name"><br>
    <input required placeholder="Your age" type="number" name="age"><br>
    <input required placeholder="Your password" type="password" name="password"><br>
    <button type="submit">Sign up</button>
</form>
<button onclick="document.location='/index/auth'">Sign in</button>
</body>
</html>
