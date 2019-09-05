package by.tms.lesson1.servlets;

import by.tms.lesson1.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static by.tms.lesson1.html_fragmets.Templates.*;

@WebServlet(urlPatterns = "/index/auth")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(HTML_HEADER);

        HttpSession currentSession = req.getSession();
        if (currentSession.isNew() || currentSession.getAttribute("user") == null) {
            String currentUserName = req.getParameter("name");
            String currentUserAge = req.getParameter("age");
            if ((currentUserName != null) & (currentUserAge != null)) {
                User currentUser = new User(currentUserName, Integer.parseInt(currentUserAge));
                currentSession.setAttribute("user", currentUser);
                currentSession.setAttribute("history", new LinkedList<String>());
                addCurrentSessionToMapOfSessions(currentSession);
                resp.getWriter().println("<H1>Hello, " + currentUser.getName() + "</H1>");
            } else {
                resp.getWriter().println(REGISTER_REQUEST);
            }
        } else {
            resp.getWriter().println("<H1>Hello, " + ((User)(currentSession.getAttribute("user"))).getName() + "</H1>");
        }

        resp.getWriter().println(HTML_FOOTER);
    }

    private void addCurrentSessionToMapOfSessions(HttpSession currentSession) {
        Map<String, HttpSession> listOfSessions = (Map<String, HttpSession>) getServletContext().getAttribute("sessions");
        if (listOfSessions != null) {
            listOfSessions.put(currentSession.getId(), currentSession);
        } else {
            listOfSessions = new HashMap<>();
            listOfSessions.put(currentSession.getId(), currentSession);
            getServletContext().setAttribute("sessions", listOfSessions);
        }
    }
}
