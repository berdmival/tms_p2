<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator with history and authentication</title>
</head>
<body>
<h1>${requestScope.message}</h1>
<form action="${pageContext.request.contextPath}/index/reg" method="post">
    <input required placeholder="Your name" name="name"><br>
    <input required placeholder="Your age" type="number" name="age"><br>
    <input required placeholder="Your password" type="password" name="password"><br>
    <button type="submit">Sign up</button>
</form>
<button onclick="document.location='/index/auth'">Sign in</button>
</body>
</html>
