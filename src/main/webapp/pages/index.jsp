<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator with history and authentication</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}${applicationScope.css_path}">
</head>
<body>
<c:if test="${sessionScope.user != null}">
    <H1>Hello, ${sessionScope.user.getName()}!</H1>
    <c:forEach items="${applicationScope.sessions}" var="sessionItem">
        <h2>Session with id ${sessionItem.getId()}:</h2>
        ${sessionItem.getAttribute("user")}<br>
        <h2>History:</h2>
        <c:forEach items="${sessionItem.getAttribute(\"history\")}" var="historyItem">
            <p>${historyItem}</p>
        </c:forEach>
        <hr size="2" color="black">
    </c:forEach>
</c:if>
<c:if test="${sessionScope.user == null}">
    <H1>Please, authorise first</H1>
</c:if>

<c:if test="${sessionScope.user == null}">
    <button onclick="document.location='/index/reg'">Sign up</button>
</c:if>
<c:if test="${sessionScope.user == null}">
    <button onclick="document.location='/index/auth'">Sign in</button>
</c:if>
<c:if test="${sessionScope.user != null}">
    <button onclick="document.location='/index/logout'">Logout</button>
</c:if>
<c:if test="${sessionScope.user != null}">
    <button onclick="document.location='/index/calc'">Calculator</button>
</c:if>
</body>
</html>
