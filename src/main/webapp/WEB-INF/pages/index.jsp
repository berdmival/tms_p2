<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WEB-INF/pages/head.jsp"%>
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
    <a class="btn" href="${pageContext.request.contextPath}/index/reg">Sign up</a>
</c:if>
<c:if test="${sessionScope.user == null}">
    <a class="btn" href="${pageContext.request.contextPath}/index/auth">Sign in</a>
</c:if>
<c:if test="${sessionScope.user != null}">
    <a class="btn" href="${pageContext.request.contextPath}/index/logout">Logout</a>
</c:if>
<c:if test="${sessionScope.user != null}">
    <a class="btn" href="${pageContext.request.contextPath}/index/calc">Calculator</a>
</c:if>
</body>
</html>
