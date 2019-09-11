<%@ page import="by.tms.lesson1.entities.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator with history and authentication</title>
</head>
<body>
<%
    User currentUser = (User) request.getSession().getAttribute("user");
    if (currentUser != null ) {
        System.out.println("<H1>Hello, " + currentUser.getName() + "!</H1>");
    } else {
        System.out.println("<H1>Please, enter your name, age and password in the request http://yourserver:port/index/reg?name=[yourName]&age=[yourAge]&password=[yourPassword]</H1>");
    }
%>

<H1>Hello, ${sessionScope.user}!</H1>
</body>
</html>
