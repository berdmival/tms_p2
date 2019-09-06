package by.tms.lesson1.servlets;

import by.tms.lesson1.users.User;

import javax.servlet.ServletContext;
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

@WebServlet(urlPatterns = "/index/reg")
public class RegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(HTML_HEADER);

        HttpSession currentSession = req.getSession();
        ServletContext servletContext = getServletContext();
        if (currentSession.isNew() || currentSession.getAttribute("user") == null) {
            String currentUserName = req.getParameter("name");
            String currentUserAge = req.getParameter("age");
            String currentUserPassword = req.getParameter("password");
            if ((currentUserName != null) & (currentUserAge != null) & (currentUserPassword != null)) {
                User currentUser = new User(currentUserName, Integer.parseInt(currentUserAge), currentUserPassword);

                if (servletContext.getAttribute("users") == null) {
                    servletContext.setAttribute("users", new ArrayList<>());
                }

                ArrayList<User> users = (ArrayList<User>) servletContext.getAttribute("users");
                users.add(currentUser);

                resp.getWriter().println("<H1>Welcome, " + currentUser.getName() + "</H1>");
            } else {
                resp.getWriter().println(REGISTER_REQUEST);
            }
        } else {
            resp.getWriter().println("<H1>Hello, " + ((User) (currentSession.getAttribute("user"))).getName() + "</H1>");
        }

        resp.getWriter().println(HTML_FOOTER);
    }
}
