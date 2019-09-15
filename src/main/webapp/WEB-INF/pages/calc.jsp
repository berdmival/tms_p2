<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WEB-INF/pages/head.jsp"%>
<body>
<h1>Enter numbers and select action. Then click "Calculate".</h1>

<c:if test="${sessionScope.history.size() > 0}">
    <h2>History:</h2>
    <c:forEach var="historyItem" items="${sessionScope.history}">
        <p>${historyItem}</p>
    </c:forEach>
    <hr size="2" color="black">
</c:if>
<c:if test="${sessionScope.history.size() == 0}">
    <h2>History is empty</h2>
</c:if>

<h2>Current result: </h2>
<p><c:out value="${requestScope.message}"/></p>

<form action="${pageContext.request.contextPath}/index/calc" method="post">
    <input required autofocus placeholder="num1" type="number" name="num1" step="any">
    <select required name="action">
        <option value="sum">+</option>
        <option value="mult">*</option>
        <option value="diff">-</option>
        <option value="div">/</option>
    </select>
    <input required placeholder="num2" type="number" name="num2" step="any">
    <button type="submit">Calculate</button>
</form>
<a class="btn" href="${pageContext.request.contextPath}/index">Home</a>
<a class="btn" href="${pageContext.request.contextPath}/index/logout">Logout</a>
</body>
</html>
