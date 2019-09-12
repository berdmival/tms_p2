<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator with history and authentication</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}${applicationScope.css_path}">
</head>
<body>
<h1>Enter numbers and select action. Then click "Calculate".</h1>

<c:if test="${sessionScope.history.size() > 0}">
    <h2>History:</h2>
    <c:forEach var="historyItem" items="${sessionScope.history}">
        <p>${historyItem}</p>
    </c:forEach>
</c:if>

<h2>${requestScope.message}</h2>
<form action="${pageContext.request.contextPath}/index/calc" method="post">
    <input required placeholder="num1" type="number" name="num1">
    <select required name="action">
        <option value="sum">+</option>
        <option value="mult">*</option>
        <option value="diff">-</option>
        <option value="div">/</option>
    </select>
    <input required placeholder="num2" type="number" name="num2">
    <button type="submit">Calculate</button>
</form>
<button onclick="document.location='/index'">Home</button>
<button onclick="document.location='/index/logout'">Logout</button>
</body>
</html>
