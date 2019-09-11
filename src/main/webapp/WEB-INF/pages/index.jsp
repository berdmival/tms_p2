<%@ page import="by.tms.lesson1.entities.User" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Calculator with history and authentication</title>
</head>
<body>
<%
    User currentUser = (User) session.getAttribute("user");
    if (currentUser != null) {
        out.println("<H1>Hello, " + currentUser.getName() + "!</H1>");
        for (Map.Entry<String, HttpSession> sessionItem : ((Map<String, HttpSession>) application.getAttribute("sessions")).entrySet()) {
            out.println("Session with id " + sessionItem.getKey() + ": " + ((HttpSession) sessionItem.getValue()).getAttribute("user") + "<br>");
            out.println("<br>History :<br><br>");
            for (String historyItem : (List<String>) ((HttpSession) sessionItem.getValue()).getAttribute("history")) {
                out.println(historyItem + "<br>");
            }
            out.println("<hr size=\"2\" color=\"black\">");
        }
    } else {
        out.println("<H1>Please, authorise first</H1>");
    }
%>

<button onclick="document.location='/index/reg'">Sign up</button>
<button onclick="document.location='/index/auth'">Sign in</button>
<button onclick="document.location='/index/logout'">Logout</button>
<button onclick="document.location='/index/calc'">Calculator</button>
</body>
</html>
