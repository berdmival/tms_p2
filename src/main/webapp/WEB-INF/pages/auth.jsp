<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator with history and authentication</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/index/auth" method="post">
    <input required placeholder="Your name" name="name"><br>
    <input required placeholder="Your password" type="password" name="password"><br>
    <button type="submit">Sign in</button>
</form>
<button onclick="document.location='/index/reg'">Sign up</button>
</body>
</html>
