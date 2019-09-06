package by.tms.lesson1.servlets;

import by.tms.lesson1.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
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
        if (currentSession.getAttribute("user") == null) {
            String currentUserName = req.getParameter("name");
            String currentUserPassword = req.getParameter("password");

            if ((currentUserName != null) & (currentUserPassword != null)) {
                User currentUser = new User();
                currentUser.setName(currentUserName);
                currentUser.setPassword(currentUserPassword);
                ArrayList<User> users = (ArrayList<User>) getServletContext().getAttribute("users");
                boolean authorized = false;
                for (User user : users) {
                    if (user.equals(currentUser)) {
                        authorized = true;
                        currentSession.setAttribute("user", user);
                        currentSession.setAttribute("history", new LinkedList<String>());
                        addCurrentSessionToMapOfSessions(currentSession);
                        resp.getWriter().println("<H1>Welcome, " + ((User) currentSession.getAttribute("user")).getName() + "</H1>");
                    }
                }
                if (!authorized) {
                    resp.getWriter().println(AUTH_FAIL);
                }
            } else {
                resp.getWriter().println(REGISTER_REQUEST);
            }
        } else {
            resp.getWriter().println("<H1>Hello, " + ((User) currentSession.getAttribute("user")).getName() + "</H1>");
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
