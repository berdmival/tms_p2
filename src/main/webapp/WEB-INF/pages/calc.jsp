<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator with history and authentication</title>
</head>
<body>
<%
    out.println("<br>History :<br><br>");
    for (String historyItem : (List<String>)session.getAttribute("history")) {
        out.println(historyItem + "<br>");
    }
    out.println("<hr size=\"2\" color=\"black\"><br><br>");
%>
<form action="${pageContext.request.contextPath}/index/calc" method="post">
    <input required placeholder="num1" type="number" name="num1">
    <select required name="action">
        <option value="sum">+</option>
        <option value="mult">*</option>
        <option value="diff">-</option>
        <option value="div">/</option>
    </select>
    <input required placeholder="num2" type="number" name="num2"><br>
    <button type="submit">Calculate</button>
</form>
<button onclick="document.location='/index'">Home</button>
<button onclick="document.location='/index/logout'">Logout</button>
</body>
</html>
