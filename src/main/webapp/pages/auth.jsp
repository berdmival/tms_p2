<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator with history and authentication</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/mainCSS.css">
</head>
<body>
<h1>Log in to your account, please, or click "Sign up" to register</h1>
<h1>${requestScope.message}</h1>
<form action="${pageContext.request.contextPath}/auth" method="post">
    <input required placeholder="Your name" name="name"><br>
    <input required placeholder="Your password" type="password" name="password"><br>
    <button type="submit">Sign in</button>
</form>
<button onclick="document.location='/reg'">Sign up</button>
</body>
</html>
